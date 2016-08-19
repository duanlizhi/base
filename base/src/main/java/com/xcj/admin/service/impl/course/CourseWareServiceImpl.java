package com.xcj.admin.service.impl.course;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareKp;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareQuestion;
import com.xcj.admin.entity.course.CourseWareScores;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.entity.course.WbtSbtKp;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.xcjenum.XMLTypes;
import com.xcj.api.sync.MQConstants;
import com.xcj.api.sync.RabbitMQProducerUtil;
import com.xcj.common.base.GenerateNumber;
import com.xcj.common.page.Page;
import com.xcj.common.page.PagerTag;
import com.xcj.common.util.xmlUtil;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;

/**
 * 
 * <b>function:</b> course_ware课件基本信息
 * 
 * @package com.xcj.admin.service.impl.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:19 CST 2015
 * @author name yy.niu
 */
@Service("courseWareServiceImpl")
public class CourseWareServiceImpl extends BaseServiceImpl implements CourseWareService {

	private static final Logger logger = LoggerFactory.getLogger(CourseWareServiceImpl.class);

	@Resource(name = "courseWareDaoImpl")
	private com.xcj.admin.dao.course.CourseWareDao courseWareDao;
	
	@Resource(name = "sbtTestScoreDaoImpl")
	private com.xcj.admin.dao.course.SbtTestScoreDao sbtTestScoreDao;

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name yy_niu
	 */
	public List<CourseWare> getAllList() throws Exception {
		return courseWareDao.getAllList();
	}

	/**
	 * <b>function:</b>根据courseNumber
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean queryStyResult(String courseNumber, String id)throws Exception {
		return courseWareDao.queryStyResult(courseNumber, id);
	}

	/**
	 * <b>function:</b>根据id查询CourseWare数据返回实体
	 * 
	 * @project xcjedu
	 * @package com.xcj.api.controller.course
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public CourseWare queryCourseNumbers(String courseNumber) throws Exception {
		return courseWareDao.queryCourseNumbers(courseNumber);
	}

	/**
	 * <b>function:</b>删除wbt课件、测评、拆装
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean deletewbtCourseWareAndTestAndDismantle(String ids)throws Exception {
		courseWareDao.deleteCourseById(DomainCourseWare.class, "courseWareId",ids);
		deleteWbtSbtKp(ids);
		courseWareDao.deleteCourseById(WbtScores.class, "courseWareId", ids);
		courseWareDao.deleteCourseById(CourseWareTool.class, "courseWareId",ids);
		courseWareDao.deleteCourseById(CourseWareParts.class, "courseWareId",ids);
		courseWareDao.deleteCourseById(CourseWareAction.class, "courseWareId",ids);
		courseWareDao.deleteEntityById(CourseWare.class, ids);
		courseWareDao.deleteCourseById(CourseWareKp.class, "courseWareId", ids);
		return true;
	}

	/**
	 * <b>function:</b>删除sbt课程
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean deleteSbtCourseWare(String id) throws Exception {
		deleteSbtFaults(id);
		deleteSbtcourseQuestion(id);
		courseWareDao.deleteCourseById(WbtScores.class, "courseWareId", id);
		courseWareDao.deleteCourseById(DomainCourseWare.class, "courseWareId",id);
		courseWareDao.deleteCourseById(SbtFault.class, "courseWareId", id);
		courseWareDao.deleteCourseById(CourseWareTool.class, "courseWareId", id);
		courseWareDao.deleteCourseById(CourseWareParts.class, "courseWareId",id);
		courseWareDao.deleteCourseById(CourseWareAction.class, "courseWareId",id);
		courseWareDao.deleteEntityById(CourseWare.class, id);
		courseWareDao.deleteCourseById(CourseWareKp.class, "courseWareId", id);
		return true;
	}

	public void deleteSbtScores(String id) throws Exception {
		List<SbtScores> list = courseWareDao.querySbtScores(id);
		if (list != null) {
			SbtScores sss = null;
			for (int i = 0; i < list.size(); i++) {
				sss = list.get(i);
				courseWareDao.deleteCourseById(WbtSbtKp.class, "sbtWbtId",sss.getId());
			}
		}
	}

	/**
	 * 删除 WbtSbtKp中数据
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteSbtFaults(String id) throws Exception {
		List<SbtFault> list = courseWareDao.querySbtFault(id);
		if (list != null) {
			SbtFault sft = null;
			for (int i = 0; i < list.size(); i++) {
				sft = list.get(i);
				courseWareDao.deleteCourseById(WbtSbtKp.class, "sbtWbtId",sft.getId());
				deleteSbtScores(sft.getId().toString());
			}
		}
	}

	/**
	 * <b>function:</b> 删除WbtSbtKp数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public void deleteWbtSbtKp(String id) throws Exception {
		List<WbtScores> list = courseWareDao.querWbtScores(id);
		if (list != null) {
			WbtScores wsKp = null;
			for (int i = 0; i < list.size(); i++) {
				wsKp = list.get(i);
				courseWareDao.deleteCourseById(WbtSbtKp.class, "sbtWbtId",wsKp.getId());
			}
		}
	}

	/**
	 * 
	 * 
	 * <b>function:</b>删除sbt数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean deleteSbtcourseQuestion(String id) throws Exception {
		List<SbtFault> sbtId = courseWareDao.querySbtFaultKey(id);
		if (sbtId != null) {
			for (int i = 0; i < sbtId.size(); i++) {
				SbtFault sbt = sbtId.get(i);
				courseWareDao.deleteCourseById(SbtQuestion.class, "sbtFaultId",sbt.getId());
				courseWareDao.deleteCourseById(SbtScores.class, "sbtFaultId",sbt.getId());
			}
		}
		return true;
	}

	/**
	 * 
	 * 
	 * <b>function:</b> 删除sbt故障数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean deleteSbtFault(String id) throws Exception {
		// SbtFault，SbtQuestion，SbtScores
		List<SbtFault> sbtId = courseWareDao.querySbtFaultKey(id);
		if (sbtId != null) {
			for (int i = 0; i < sbtId.size(); i++) {
				SbtFault sbt = sbtId.get(i);
				courseWareDao.deleteCourseById(SbtQuestion.class, "sbtFaultId",sbt.getId());
				courseWareDao.deleteCourseById(SbtScores.class, "sbtFaultId",sbt.getId());
			}
		} else {
			return false;
		}
		courseWareDao.deleteEntityById(SbtFault.class, id);
		return true;
	}

	/**
	 * <b>function:</b>根据courseNumber查询数据是否存在
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean queryCourseNumber(String courseNumber) throws Exception {
		return courseWareDao.queryCourseNumber(courseNumber);
	}

	/**
	 * <b>function:</b>根据faultNumber查询数据是否存在
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean queryFaultNumber(String faultNumber) throws Exception {
		return courseWareDao.queryFaultNumber(faultNumber);
	}

	/**
	 * <b>function:</b>添加xml数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @updateDate 2015-03-23 11:56:23
	 * @updateAuthor hehujun
	 * @author yy.niu
	 */
	public String saveData(String fileType, String fielUrl,
			String courseNumber, String faultNumber, String uploadPath,String realPath)throws Exception {
		boolean sbtFault = false;// sbtFault是否存在
		boolean emuFault = false;// emuFault是否存在
		boolean courseWare = false;// courseNumber是否存在
		String isSuccess = null;
		if (fileType.equals(XMLTypes.EMUFAULT.getType())) {
			// 如果是emu故障类型，emu故障编号为空 删除上传的问题 返回错误
			emuFault = queryFaultNumber(faultNumber);
			if (emuFault) {
				// 调用sbt故障方法 有些类型需要判断
				isSuccess = saveEmuFault(fielUrl, uploadPath);
				if (!isSuccess.equals("0")) {
					return isSuccess;
				}
			} else {
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		} else if (fileType.equals(XMLTypes.SBTFAULT.getType())) {
			// 如果是sbt故障类型，sbt故障编号为空 删除上传的问题 返回错误
			sbtFault = queryFaultNumber(faultNumber);
			if (sbtFault) {
				//TODO 调用sbt故障方法 有些类型需要判断
				isSuccess = saveSbtFault(fielUrl, uploadPath);
				if (!isSuccess.equals("0")) {
					return isSuccess;
				}
				isSuccess = saveSbtQuestionNew(fielUrl, uploadPath,realPath);
				if (!isSuccess.equals("0")) {
					return isSuccess;
				}
				
			} else {
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		} else {
			courseWare = queryCourseNumber(courseNumber);
			if (courseWare) {
				if (fileType.equals(XMLTypes.SBTCOURSEWARE.getType())) {
					// 调用sbt课件
					isSuccess = saveSbtCourseWare(fielUrl, uploadPath);
					if (!isSuccess.equals("0")) {
						return isSuccess;
					}
				} else if (fileType.equals(XMLTypes.WBTTESTS.getType())) {
					// 调用wbt测评
					isSuccess = saveWbtTests(fielUrl, uploadPath);
					if (!isSuccess.equals("0")) {
						return isSuccess;
					}
				} else if (fileType.equals(XMLTypes.WBTCOURSEWARE.getType())) {
					// 调用wbt课件
					isSuccess = saveWbtCourseWare(fielUrl, uploadPath);
					if (!isSuccess.equals("0")) {
						return isSuccess;
					}
				} else if (fileType.equals(XMLTypes.DMCCOURSEWARE.getType())) {
					// 调用拆装保养
					isSuccess = saveDisassemblingCourseDescription(fielUrl,
							uploadPath);
					if (!isSuccess.equals("0")) {
						return isSuccess;
					}
				} else if (fileType.equals(XMLTypes.EMUCOURSEWARE.getType())) {
					// 调用软模拟器
					isSuccess = saveEMUCourseWare(fielUrl, uploadPath);
					if (!isSuccess.equals("0")) {
						return isSuccess;
					}
				}
			} else {
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		}
		return "0";
	}

	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>解析工单——新</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、解析工单——新</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年1月26日 下午4:13:20</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public String saveSbtQuestionNew(String fielUrl, String uploadPath,String realPath)throws Exception {
			//TODO 更新sbtfault新
			SAXReader sax = new SAXReader();
			Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element faultNumber = (Element) root.element("faultNumber");
			Element psssScore = (Element) root.element("score");
			
			SbtTestScore sbtTestScore = new SbtTestScore();
			sbtTestScore.setModel(schema);
			sbtTestScore.setModelVersion(schemaversion);
			sbtTestScore.setName(name.getText());
			sbtTestScore.setCourseNumber(courseNumber.getText());
			sbtTestScore.setFaultNumber(faultNumber.getText());
			sbtTestScore.setIsEnable(1);
			sbtTestScore.setScore(Float.parseFloat(psssScore.getText()));
			sbtTestScore.setCreateDate(DateUtil.getCurrentTimeByDate());
			sbtTestScore.setModifyDate(DateUtil.getCurrentTimeByDate());
			JSONObject sbtTestScoreJson = new JSONObject();
			sbtTestScoreJson.put("schema", schema);
			sbtTestScoreJson.put("schemaversion", schemaversion);
			sbtTestScoreJson.put("name", name.getText());
			sbtTestScoreJson.put("courseNumber", courseNumber.getText());
			sbtTestScoreJson.put("type",((Element) root.element("type")).getText());
			sbtTestScoreJson.put("types",((Element) root.element("types")).getText());
			sbtTestScoreJson.put("sumscore",((Element) root.element("score")).getText());
			
			try {
					List<CourseWare> queryCourseWareId = courseWareDao.queryCourseWareId(courseNumber.getText());
					if (queryCourseWareId != null && queryCourseWareId.size() > 0) {
						sbtTestScore.setCourseWareId(queryCourseWareId.get(0).getId());
						List<SbtTestScore> sbtTestScoreList = sbtTestScoreDao.getListByCourseNumber(courseNumber.getText(),faultNumber.getText());
						for (SbtTestScore sbtTestScore2 : sbtTestScoreList) {
							sbtTestScoreDao.removeById(sbtTestScore2.getId());
						}
						
						//将score放入map中
						List<SbtScores> sbtScoresList = courseWareDao.getSbtScoreByFaultNumber(faultNumber.getText());
						Map<String,String> scoreMap = new HashMap<String,String>();
						for (SbtScores sbtScores : sbtScoresList) {
							scoreMap.put(sbtScores.getQid(), sbtScores.getRate());
						}
						
						Element questions = (Element) root.element("questions");
						JSONArray questionsJsonArray = new JSONArray();
						//将题放入到questionJsonArray中
						this.getCourseWareQuestion(questions, questionsJsonArray, scoreMap);
						
						// 分数<score>
						Element score = (Element) root.element("scores");
						JSONArray scoreJsonArray = new JSONArray();
						JSONArray exclusiveJsonArray = new JSONArray();
						for (Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();) {
							JSONObject scoreJsonObject = new JSONObject();
							Element scoreElement = (Element) iterInner.next();
							if("question".equals(scoreElement.attributeValue("type"))){
								scoreJsonObject.put("type",scoreElement.attributeValue("type"));
								scoreJsonObject.put("id", scoreElement.attributeValue("id"));
								scoreJsonObject.put("questionId", scoreElement.attributeValue("hid"));
								scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
								scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
								scoreJsonArray.add(scoreJsonObject);
							}
							if("handle".equals(scoreElement.attributeValue("type"))){
								scoreJsonObject.put("type",scoreElement.attributeValue("type"));
								scoreJsonObject.put("id", scoreElement.attributeValue("id"));
								scoreJsonObject.put("handleId", scoreElement.attributeValue("hid"));
								scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
								scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
								scoreJsonArray.add(scoreJsonObject);
							}
							if("exclusive".equals(scoreElement.getName())){
								JSONObject exclusiveJsonObject = new JSONObject();
								exclusiveJsonObject.put("id", scoreElement.attributeValue("id"));
								exclusiveJsonObject.put("priorityId", scoreElement.attributeValue("priorityId"));
								exclusiveJsonArray.add(exclusiveJsonObject);
							}
						}
						sbtTestScoreJson.put("exclusive", exclusiveJsonArray);
						sbtTestScoreJson.put("score", scoreJsonArray);
						sbtTestScoreJson.put("question", questionsJsonArray);
						
						//获取到课件主文件的xml
						CourseWare courseWare = courseWareDao.getByCourseNumber(courseNumber.getText());
						File jsonFile = new File(realPath+"/"+courseWare.getFileurl().substring(0,courseWare.getFileurl().indexOf(".xml"))+".txt");
					    String jsonString = xmlUtil.txtString(jsonFile);
						//跟故障xml的工单，分数一起放入工单表
					    JSONObject courseWareObject = JSONObject.parseObject(jsonString);
					    Object handle = JSONObject.parseObject(courseWareObject.get("handles").toString()).get("handle");
					    
					    JSONArray handleNew = new JSONArray();
					    JSONArray handleArr = JSONArray.parseArray(handle.toString());
					    for (Object object : handleArr) {
					    	JSONObject handleObj = new JSONObject();
					    	handleObj.put("id", JSONObject.parseObject(object.toString()).get("id"));
						    handleObj.put("condition", JSONObject.parseObject(object.toString()).get("condition"));
						    handleObj.put("rate",scoreMap.get(JSONObject.parseObject(object.toString()).get("id").toString())==null
						    						?"":scoreMap.get(JSONObject.parseObject(object.toString()).get("id").toString()));
						    handleNew.add(handleObj);
						}
					    sbtTestScoreJson.put("handle",handleNew);
					    sbtTestScoreJson.put("progress",JSONObject.parseObject(courseWareObject.get("progresses").toString()).get("progress"));
					    sbtTestScoreJson.put("value",JSONObject.parseObject(courseWareObject.get("values").toString()).get("value"));
					    
						sbtTestScore.setText(sbtTestScoreJson.toJSONString());
						// 保存入库
						courseWareDao.saveAndGetIdByInteger(sbtTestScore);
						/*
						boolean isSuccess = StringUtil.writeTxt(suffixs[0],data.toString());
						if (isSuccess) {
							sbtTestScoreList = sbtTestScoreDao.getListByCourseNumber(courseNumber.getText(), faultNumber.getText());
							for (SbtTestScore sbtTestScore2 : sbtTestScoreList) {
								sbtTestScoreDao.removeById(sbtTestScore2.getId());
							}
							return "2";// text写入失败
						}
						*/
					} else {
						courseWareDao.deleteSbtFault(faultNumber.getText());
						return "6";
					}
			
			} catch (Exception e) {
				courseWareDao.deleteSbtFault(faultNumber.getText());
				logger.error(CourseWareServiceImpl.class.getName()+ " saveSbtQuestion() exception: " + e.getMessage());
				e.printStackTrace();
				return "2";
			}
		return "0";
	}

	/**
	 * 
	 * <b>function:</b> 保存EMU课件
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-23 上午11:39:44
	 * @return String
	 * @author hehujun
	 */
	public String saveEMUCourseWare(String fielUrl, String uploadPath)
			throws Exception {
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			Document xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element description = (Element) root.element("description");
			Element difficulty = (Element) root.element("difficulty");
			Element duration = (Element) root.element("duration");
			Element client = (Element) root.element("client");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element passCondition = (Element) root.element("passCondition");
			Element completeCondition = (Element) root.element("completeCondition");
			
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			// isNull = StringUtil.isType(type);
			if (isNull) {
				return "1";// courseNumber或type不能为空
			}
			CourseWare courseWare = new CourseWare();
			courseWare.setModel(schema);
			courseWare.setModelVersion(schemaversion);
			courseWare.setName(StringUtil.isNullEmtp(name));
			courseWare.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			courseWare.setType("3");
			courseWare.setPurpose(StringUtil.isNullEmtp(purpose));
			courseWare.setRequires(StringUtil.isNullEmtp(require));
			courseWare.setKeyword(StringUtil.isNullEmtp(keyword));
			courseWare.setLanguage(StringUtil.isNullEmtp(language));
			courseWare.setVersion(StringUtil.isNullEmtp(version));
			courseWare.setDescription(StringUtil.isNullEmtp(description));
			courseWare.setClient(StringUtil.isNullEmtp(client));
			courseWare.setDifficulty(StringUtil.isNullzh(difficulty));
			courseWare.setDuration(StringUtil.isNullEmtp(duration));
			courseWare.setCategory(1);
			if(sumScore !=null && !"".equals(sumScore)){
				courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
			}
			courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
			courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
			courseWare.setEntry(StringUtil.isNullEmtp(entry));
			courseWare.setFileurl(StringUtil.interceptString(uploadPath));
			courseWare.setIsEnable(1);
			courseWare.setPassCondition(StringUtil.isNullEmtps(passCondition));
			courseWare.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			
			//data.put("courseWare", courseWare);
			
			courseWare = courseWareDao.saveCourseWare(courseWare);
			
			List<?> handles = root.selectNodes("//handles");
			JSONObject handlesObj = new JSONObject();
			for (int s = 0; s < handles.size(); s++) {
				Element handlesName = (Element) handles.get(s);
				JSONArray handleArr = new JSONArray();
				int rank = 0;
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					String actionId = handleName.attributeValue("id");
					String actionDiscription = handleName.attributeValue("discription");
					CourseWareAction courseWareAction = new CourseWareAction();
					courseWareAction.setActionId(actionId);
					courseWareAction.setDescription(actionDiscription);
					courseWareAction.setCode(GenerateNumber.getCourseWareCode());
					courseWareAction.setCreateDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setModifyDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setRank(rank++);
					courseWareAction.setType("3");
					courseWareAction.setCourseWareId(courseWare.getId());
					courseWareDao.save(courseWareAction);
					JSONArray conditionArr = new JSONArray();
					for (Iterator<?> conditionIterator = handleName.elementIterator(); conditionIterator.hasNext();) {
						Element conditions = (Element) conditionIterator.next();
						JSONObject conditionObj = new JSONObject();
						conditionObj.put("id",conditions.attributeValue("id"));
						conditionObj.put("linkVID",conditions.attributeValue("linkVID"));
						conditionObj.put("cdt",conditions.attributeValue("cdt"));
						conditionObj.put("cdtValue",conditions.attributeValue("cdtValue"));
						conditionObj.put("cdtID",conditions.attributeValue("cdtID"));
						conditionArr.add(conditionObj);
					}
					handleObj.put("condition",conditionArr);
					handleObj.put("id", actionId);
					handleObj.put("discription", actionDiscription);
					handleArr.add(handleObj);
				}
				handlesObj.put("handle", handleArr);
			}
			data.put("handles", handlesObj);
			
			List<?> values = root.selectNodes("//values");
			JSONObject valuesObj = new JSONObject();
			for (int s = 0; s < values.size(); s++) {
				Element handlesName = (Element) values.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("discription", handleName.attributeValue("discription"));
					handleObj.put("value", handleName.attributeValue("value"));
					handleArr.add(handleObj);
				}
				valuesObj.put("value", handleArr);
			}
			data.put("values", valuesObj);
			
			List<?> progresses = root.selectNodes("//progresses");
			JSONObject progressesObj = new JSONObject();
			for (int s = 0; s < progresses.size(); s++) {
				Element handlesName = (Element) progresses.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("handleId", handleName.attributeValue("hid"));
					handleObj.put("rate", handleName.attributeValue("rate"));
					handleArr.add(handleObj);
				}
				progressesObj.put("progress", handleArr);
			}
			data.put("progresses", progressesObj);
			
			// 分数<score>
			Element score = (Element) root.element("scores");
			JSONArray scoreJsonArray = new JSONArray();
			JSONArray exclusiveJsonArray = new JSONArray();
			Map<String,String> scoreMapTemp = new HashMap<String,String>();
			if(score != null){
				int rank=0;
				for (Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();) {
					JSONObject scoreJsonObject = new JSONObject();
					Element scoreElement = (Element) iterInner.next();
					if("question".equals(scoreElement.attributeValue("type"))){
						scoreJsonObject.put("type",scoreElement.attributeValue("type"));
						scoreJsonObject.put("id", scoreElement.attributeValue("id"));
						scoreJsonObject.put("questionId", scoreElement.attributeValue("hid"));
						scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
						scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
						//记录下知识点方便与题匹配
						scoreMapTemp.put(scoreElement.attributeValue("hid"), scoreElement.attributeValue("rate"));
						scoreJsonArray.add(scoreJsonObject);
						
						CourseWareScores courseWareScores = new CourseWareScores();
						courseWareScores.setRate(scoreElement.attributeValue("rate"));
						courseWareScores.setScoreTypeId(scoreElement.attributeValue("id"));
						courseWareScores.setType("question");
						courseWareScores.setCreateDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setModifyDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setRank(rank++);
						courseWareScores.setCourseWareId(courseWare.getId());;
						courseWareScores.setKp(scoreElement.attributeValue("kp"));
						courseWareScores.setQid(scoreElement.attributeValue("hid"));
						courseWareDao.save(courseWareScores);
					}
					if("handle".equals(scoreElement.attributeValue("type"))){
						scoreJsonObject.put("type",scoreElement.attributeValue("type"));
						scoreJsonObject.put("id", scoreElement.attributeValue("id"));
						scoreJsonObject.put("handleId", scoreElement.attributeValue("hid"));
						scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
						scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
						scoreJsonArray.add(scoreJsonObject);
						
						CourseWareScores courseWareScores = new CourseWareScores();
						courseWareScores.setRate(scoreElement.attributeValue("rate"));
						courseWareScores.setScoreTypeId(scoreElement.attributeValue("id"));
						courseWareScores.setType("handle");
						courseWareScores.setCreateDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setModifyDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setRank(rank++);
						courseWareScores.setCourseWareId(courseWare.getId());;
						courseWareScores.setKp(scoreElement.attributeValue("kp"));
						courseWareScores.setQid(scoreElement.attributeValue("hid"));
						courseWareDao.save(courseWareScores);
					}
					if("exclusive".equals(scoreElement.getName())){
						JSONObject exclusiveJsonObject = new JSONObject();
						exclusiveJsonObject.put("id", scoreElement.attributeValue("id"));
						exclusiveJsonObject.put("priorityId", scoreElement.attributeValue("priorityId"));
						exclusiveJsonArray.add(exclusiveJsonObject);
					}
				}
				data.put("exclusive", exclusiveJsonArray);
				data.put("score", scoreJsonArray);
			}
			
			//将score放入map中
			Map<String,String> scoreMap = new HashMap<String,String>();
			for (Object obj : scoreJsonArray) {
				if(((JSONObject)obj).get("questionId") != null){
					scoreMap.put(((JSONObject)obj).get("questionId").toString(), ((JSONObject)obj).get("rate").toString());
				}
				if(((JSONObject)obj).get("handleId") != null){
					scoreMap.put(((JSONObject)obj).get("handleId").toString(), ((JSONObject)obj).get("rate").toString());
				}
			}
			
			//入库coursequestion
			List<?> questions = root.selectNodes("//questions");
			for (int s = 0; s < questions.size(); s++) {
				Element questionsName = (Element) questions.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = questionsName.elementIterator(); iterInner.hasNext();) {
					Element questionName = (Element) iterInner.next();
					for (Iterator<?> questioniterInner = questionName.elementIterator(); questioniterInner.hasNext();) {
						Element question2Name = (Element) questioniterInner.next();
						String qid = question2Name.attributeValue("id");
						if(StringUtil.isNotEmpty(qid)){
							CourseWareQuestion courseWareQuestion = new CourseWareQuestion();
							courseWareQuestion.setQuestionId(qid);
							courseWareQuestion.setDescription(question2Name.attributeValue("description"));
							courseWareQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
							courseWareQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
							courseWareQuestion.setRank(rank++);
							courseWareQuestion.setCourseWareId(courseWare.getId());
							courseWareQuestion.setKp(scoreMapTemp.get(qid));
							courseWareQuestion.setType(question2Name.attributeValue("type")==null?0:Integer.parseInt(question2Name.attributeValue("type")));
							courseWareDao.save(courseWareQuestion);
						}
					}
				}
			}
			
			Element questions2 = (Element) root.element("questions");
			JSONArray questionsJsonArray = new JSONArray();
			//将题放入到questionJsonArray中
			if(questions != null){
				this.getCourseWareQuestion(questions2, questionsJsonArray, scoreMap);
			}
			data.put("question", questionsJsonArray);
			
			boolean isSuccess = StringUtil.writeTxt(txtUrl, data.toString());
			if (isSuccess) {
				// text写入失败调用删除方法
				this.deleteSbtCourseWare(courseWare.getId().toString());
				return "2";// text写入失败
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveSbtCourseWare() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * <b>function:</b>读取sbt课件xml添加sbt数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveSbtCourseWare(String fielUrl, String uploadPath)
			throws Exception {
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			Document xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element description = (Element) root.element("description");
			Element difficulty = (Element) root.element("difficulty");
			Element duration = (Element) root.element("duration");
			Element client = (Element) root.element("client");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element passCondition = (Element) root.element("passCondition");
			Element completeCondition = (Element) root.element("completeCondition");
			
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			// isNull = StringUtil.isType(type);
			if (isNull) {
				return "1";// courseNumber或type不能为空
			}
			CourseWare courseWare = new CourseWare();
			courseWare.setModel(schema);
			courseWare.setModelVersion(schemaversion);
			courseWare.setName(StringUtil.isNullEmtp(name));
			courseWare.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			courseWare.setType("1");
			courseWare.setPurpose(StringUtil.isNullEmtp(purpose));
			courseWare.setRequires(StringUtil.isNullEmtp(require));
			courseWare.setKeyword(StringUtil.isNullEmtp(keyword));
			courseWare.setLanguage(StringUtil.isNullEmtp(language));
			courseWare.setVersion(StringUtil.isNullEmtp(version));
			courseWare.setDescription(StringUtil.isNullEmtp(description));
			courseWare.setClient(StringUtil.isNullEmtp(client));
			courseWare.setDifficulty(StringUtil.isNullzh(difficulty));
			courseWare.setDuration(StringUtil.isNullEmtp(duration));
			courseWare.setCategory(1);
			if(sumScore !=null && !"".equals(sumScore)){
				courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
			}
			courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
			courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
			courseWare.setEntry(StringUtil.isNullEmtp(entry));
			courseWare.setFileurl(StringUtil.interceptString(uploadPath));
			courseWare.setIsEnable(1);
			courseWare.setPassCondition(StringUtil.isNullEmtps(passCondition));
			courseWare.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			
			//data.put("courseWare", courseWare);
			
			courseWare = courseWareDao.saveCourseWare(courseWare);
			
			List<?> handles = root.selectNodes("//handles");
			JSONObject handlesObj = new JSONObject();
			for (int s = 0; s < handles.size(); s++) {
				Element handlesName = (Element) handles.get(s);
				JSONArray handleArr = new JSONArray();
				int rank = 0;
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					String actionId = handleName.attributeValue("id");
					String actionDiscription = handleName.attributeValue("discription");
					CourseWareAction courseWareAction = new CourseWareAction();
					courseWareAction.setActionId(actionId);
					courseWareAction.setDescription(actionDiscription);
					courseWareAction.setCode(GenerateNumber.getCourseWareCode());
					courseWareAction.setCreateDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setModifyDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setRank(rank++);
					courseWareAction.setType("1");
					courseWareAction.setCourseWareId(courseWare.getId());
					courseWareDao.save(courseWareAction);
					JSONArray conditionArr = new JSONArray();
					for (Iterator<?> conditionIterator = handleName.elementIterator(); conditionIterator.hasNext();) {
						Element conditions = (Element) conditionIterator.next();
						JSONObject conditionObj = new JSONObject();
						conditionObj.put("id",conditions.attributeValue("id"));
						conditionObj.put("linkVID",conditions.attributeValue("linkVID"));
						conditionObj.put("cdt",conditions.attributeValue("cdt"));
						conditionObj.put("cdtValue",conditions.attributeValue("cdtValue"));
						conditionObj.put("cdtID",conditions.attributeValue("cdtID"));
						conditionArr.add(conditionObj);
					}
					handleObj.put("condition",conditionArr);
					handleObj.put("id", actionId);
					handleObj.put("discription", actionDiscription);
					handleArr.add(handleObj);
				}
				handlesObj.put("handle", handleArr);
			}
			data.put("handles", handlesObj);
			
			List<?> values = root.selectNodes("//values");
			JSONObject valuesObj = new JSONObject();
			for (int s = 0; s < values.size(); s++) {
				Element handlesName = (Element) values.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("discription", handleName.attributeValue("discription"));
					handleObj.put("value", handleName.attributeValue("value"));
					handleArr.add(handleObj);
				}
				valuesObj.put("value", handleArr);
			}
			data.put("values", valuesObj);
			
			List<?> progresses = root.selectNodes("//progresses");
			JSONObject progressesObj = new JSONObject();
			for (int s = 0; s < progresses.size(); s++) {
				Element handlesName = (Element) progresses.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("handleId", handleName.attributeValue("hid"));
					handleObj.put("rate", handleName.attributeValue("rate"));
					handleArr.add(handleObj);
				}
				progressesObj.put("progress", handleArr);
			}
			data.put("progresses", progressesObj);
			
			boolean isSuccess = StringUtil.writeTxt(txtUrl, data.toString());
			if (isSuccess) {
				// text写入失败调用删除方法
				this.deleteSbtCourseWare(courseWare.getId().toString());
				return "2";// text写入失败
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveSbtCourseWare() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * 
	 * <b>function:</b> 保存emu故障
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-23 上午11:55:08
	 * @return String
	 * @author hehujun
	 */
	// public String saveEmuFault(String fielUrl)throws Exception{
	// return this.saveSbtFault(fielUrl);
	// }

	/**
	 * <b>function:</b> 读取sbt故障添加sbt数据
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveSbtFault(String fielUrl, String uploadPath)
			throws Exception {
		try {
			//TODO 同步sbtFault
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element faultNumber = (Element) root.element("faultNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element description = (Element) root.element("description");
			Element duration = (Element) root.element("duration");
			Element difficulty = (Element) root.element("difficulty");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element client = (Element) root.element("client");
			Element completeCondition = (Element) root.element("completeCondition");
			Element passCondition = (Element) root.element("passCondition");

			// 查询知识点
			Element kp = (Element) root.element("kp");

			CourseWare courseWare = courseWareDao.queryCourseNumbers(StringUtil.isNullEmtp(courseNumber));
			if (courseWare == null) {
				return "5";// 请先上传sbt课件
			}
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			isNull = StringUtil.isbool(faultNumber);
			isNull = StringUtil.isbool(type);
			if (isNull) {
				return "1";
			}
			SbtFault sbtFault = new SbtFault();
			sbtFault.setModel(schema);
			sbtFault.setModelVersion(schemaversion);
			sbtFault.setName(StringUtil.isNullEmtp(name));
			sbtFault.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			sbtFault.setFaultNumber(StringUtil.isNullEmtp(faultNumber));
			sbtFault.setType("1");
			sbtFault.setPurpose(StringUtil.isNullEmtp(purpose));
			sbtFault.setRequires(StringUtil.isNullEmtp(require));
			sbtFault.setKeyword(StringUtil.isNullEmtp(keyword));
			sbtFault.setDescription(StringUtil.isNullEmtp(description));
			sbtFault.setDuration(StringUtil.isNullEmtp(duration));
			sbtFault.setDifficulty(StringUtil.isNullEmtps(difficulty));
			sbtFault.setSumScore(StringUtil.isNullEmtps(sumScore));
			sbtFault.setCreateDate(DateUtil.getCurrentTimeByDate());
			sbtFault.setModifyDate(DateUtil.getCurrentTimeByDate());

			sbtFault.setFileurl(uploadPath);
			sbtFault.setEntry(StringUtil.isNullEmtp(entry));
			sbtFault.setLanguage(StringUtil.isNullEmtp(language));
			sbtFault.setVersion(StringUtil.isNullEmtp(version));
			sbtFault.setClient(StringUtil.isNullEmtp(client));
			sbtFault.setIsEnable(1);

			sbtFault.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			sbtFault.setPassCondition(StringUtil.isNullEmtps(passCondition));
			
			sbtFault.setCourseWareId(courseWare.getId());
			data.put("sbtFault", sbtFault);
			courseWareDao.saveAndGetIdByInteger(sbtFault);
			
			// 添加知识点
			if(kp != null && !"".equals(kp)){
				boolean isKp = savesbtKp(kp, sbtFault.getId(), 4);
				if (isKp) {
					// 知识点添加出现问题是 删除数据
					this.deleteSbtFault(sbtFault.getId().toString());
					return "3";// 知识点没有关联
				}
			}

			Map<String,String> questionMap = new HashMap<String,String>();
			Map<String,String> questionFlagMap = new HashMap<String,String>();
			List<String> hidList = new ArrayList<String>();
			List<?> score = root.selectNodes("//scores");
			for (int s = 0; s < score.size(); s++) {
				Element scoresName = (Element) score.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = scoresName.elementIterator(); iterInner.hasNext();) {
					Element scoreName = (Element) iterInner.next();
					String id = scoreName.attributeValue("id");
					String rate = scoreName.attributeValue("rate");
					String scourType = scoreName.attributeValue("type");
					String scourKp = scoreName.attributeValue("kp");
					String hid = scoreName.attributeValue("hid");

					//方便下面解析question的时候把分数信息拿出来
					SbtScores sbtScores = new SbtScores();
					if("handle".equals(scourType) || "question".equals(scourType) ){
						sbtScores.setRate(rate);
						sbtScores.setScoreTypeId(id);
						sbtScores.setType(scourType);
						sbtScores.setCreateDate(DateUtil.getCurrentTimeByDate());
						sbtScores.setModifyDate(DateUtil.getCurrentTimeByDate());
						sbtScores.setRank(rank++);
						sbtScores.setSbtFaultId(sbtFault.getId());
						sbtScores.setKp(scourKp);
						sbtScores.setQid(hid);
						Integer sbtScoresId = courseWareDao.saveAndGetIdByInteger(sbtScores);
						sbtScores.setId(sbtScoresId);
						if("question".equals(scourType)){
							hidList.add(hid);
						}
						questionMap.put(hid, scourKp==null?"":scourKp);
						questionFlagMap.put(hid, "falg");
						// 添加知识点数据
						if(scourKp != null && !"".equals(scourKp)){
							boolean isKps = saveSbtKPs(scourKp, sbtScores.getId(), 2);
							if (isKps) {
								// 知识点添加出现问题是 删除数据
								this.deleteSbtFault(sbtFault.getId().toString());
								return "4";// 分数里的知识点没有关联
							}
						}
					}

				}
			}
			
			
			JSONArray questionArr = new JSONArray();
			List<?> questions = root.selectNodes("//questions");
			for (int s = 0; s < questions.size(); s++) {
				Element questionsName = (Element) questions.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = questionsName.elementIterator(); iterInner.hasNext();) {
					Element questionName = (Element) iterInner.next();
					for (Iterator<?> questioniterInner = questionName.elementIterator(); questioniterInner.hasNext();) {
						Element question2Name = (Element) questioniterInner.next();
						
						String qid = question2Name.attributeValue("id");
						if(questionFlagMap.get(qid) != null){
							SbtQuestion sbtQuestion = new SbtQuestion();
							sbtQuestion.setQuestionId(qid);
							sbtQuestion.setDescription(question2Name.attributeValue("description"));
							sbtQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
							sbtQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
							sbtQuestion.setRank(rank++);
							sbtQuestion.setSbtFaultId(sbtFault.getId());
							sbtQuestion.setKp(questionMap.get(qid));
							sbtQuestion.setType(question2Name.attributeValue("type")==null?0:Integer.parseInt(question2Name.attributeValue("type")));
							courseWareDao.save(sbtQuestion);
						}
					}
				}
			}
			data.put("questions", questionArr);
			//data.put("score", scoreArr);
			boolean isSuccess = StringUtil.writeTxt(txtUrl, data.toString());
			if (isSuccess) {
				// 知识点添加出现问题是 删除数据
				this.deleteSbtFault(sbtFault.getId().toString());
				return "2";
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveSbtFault() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * 
	 * <b>function:</b> 保存emu故障
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-23 上午11:55:08
	 * @return String
	 * @author hehujun
	 */
	public String saveEmuFault(String fielUrl, String uploadPath)
			throws Exception {
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element faultNumber = (Element) root.element("faultNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element description = (Element) root.element("description");
			Element duration = (Element) root.element("duration");
			Element difficulty = (Element) root.element("difficulty");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element client = (Element) root.element("client");
			Element passCondition = (Element) root.element("passCondition");
			Element completeCondition = (Element) root.element("completeCondition");
			
			// 查询知识点
			Element kp = (Element) root.element("kp");

			CourseWare courseWare = courseWareDao.queryCourseNumbers(StringUtil
					.isNullEmtp(courseNumber));
			if (courseWare == null) {
				return "5";// 请先上传sbt课件
			}
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			isNull = StringUtil.isbool(faultNumber);
			isNull = StringUtil.isbool(type);
			if (isNull) {
				return "1";
			}
			SbtFault sbtFault = new SbtFault();
			sbtFault.setModel(schema);
			sbtFault.setModelVersion(schemaversion);
			sbtFault.setName(StringUtil.isNullEmtp(name));
			sbtFault.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			sbtFault.setFaultNumber(StringUtil.isNullEmtp(faultNumber));
			sbtFault.setType("3");
			sbtFault.setPurpose(StringUtil.isNullEmtp(purpose));
			sbtFault.setRequires(StringUtil.isNullEmtp(require));
			sbtFault.setKeyword(StringUtil.isNullEmtp(keyword));
			sbtFault.setDescription(StringUtil.isNullEmtp(description));
			sbtFault.setDuration(StringUtil.isNullEmtp(duration));
			sbtFault.setDifficulty(StringUtil.isNullEmtps(difficulty));
			sbtFault.setSumScore(StringUtil.isNullEmtps(sumScore));
			sbtFault.setCreateDate(DateUtil.getCurrentTimeByDate());
			sbtFault.setModifyDate(DateUtil.getCurrentTimeByDate());

			sbtFault.setFileurl(uploadPath);
			sbtFault.setEntry(StringUtil.isNullEmtp(entry));
			sbtFault.setLanguage(StringUtil.isNullEmtp(language));
			sbtFault.setVersion(StringUtil.isNullEmtp(version));
			sbtFault.setClient(StringUtil.isNullEmtp(client));
			sbtFault.setIsEnable(1);

			sbtFault.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			sbtFault.setPassCondition(StringUtil.isNullEmtps(passCondition));
			
			sbtFault.setCourseWareId(courseWare.getId());
			data.put("sbtFault", sbtFault);
			courseWareDao.save(sbtFault);
			
			// 添加知识点
			if(kp != null && !"".equals(kp)){
				boolean isKp = savesbtKp(kp, sbtFault.getId(), 4);
				if (isKp) {
					// 知识点添加出现问题是 删除数据
					this.deleteSbtFault(sbtFault.getId().toString());
					return "3";// 知识点没有关联
				}
			}

			JSONArray questionArr = new JSONArray();
			List<?> questions = root.selectNodes("//questions");
			for (int s = 0; s < questions.size(); s++) {
				Element questionsName = (Element) questions.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = questionsName.elementIterator(); iterInner
						.hasNext();) {
					Element questionName = (Element) iterInner.next();
					JSONObject quertionData = new JSONObject();
					String qid = questionName.attributeValue("qid");
					String discription = questionName
							.attributeValue("discription");
					SbtQuestion sbtQuestion = new SbtQuestion();
					sbtQuestion.setQuestionId(qid);
					sbtQuestion.setDescription(discription);
					sbtQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
					sbtQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
					sbtQuestion.setRank(rank++);
					sbtQuestion.setSbtFaultId(sbtFault.getId());
					courseWareDao.save(sbtQuestion);
					quertionData.put("qid", qid);
					quertionData.put("discription", discription);
					questionArr.add(quertionData);
				}
			}
			data.put("questions", questionArr);

			JSONArray scoreArr = new JSONArray();
			List<?> score = root.selectNodes("//scores");
			for (int s = 0; s < score.size(); s++) {
				Element scoresName = (Element) score.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = scoresName.elementIterator(); iterInner
						.hasNext();) {
					Element scoreName = (Element) iterInner.next();
					JSONObject scoreData = new JSONObject();
					String id = scoreName.attributeValue("id");
					String rate = scoreName.attributeValue("rate");
					String scourType = scoreName.attributeValue("type");
					String scourKp = scoreName.attributeValue("kp");
					SbtScores sbtScores = new SbtScores();
					sbtScores.setRate(rate);
					sbtScores.setScoreTypeId(id);
					sbtScores.setType(scourType);
					sbtScores.setCreateDate(DateUtil.getCurrentTimeByDate());
					sbtScores.setModifyDate(DateUtil.getCurrentTimeByDate());
					sbtScores.setRank(rank++);
					sbtScores.setSbtFaultId(sbtFault.getId());
					scoreData.put("type", scourType);
					scoreData.put("id", id);
					scoreData.put("rate", rate);
					scoreData.put("scourKp", scourKp);
					scoreArr.add(scoreData);
					courseWareDao.save(sbtScores);
					// 添加知识点数据
					if(kp != null && !"".equals(kp)){
						boolean isKps = saveSbtKPs(scourKp, sbtScores.getId(), 2);
						if (isKps) {
							// 知识点添加出现问题是 删除数据
							this.deleteSbtFault(sbtFault.getId().toString());
							return "4";// 分数里的知识点没有关联
						}
					}
				}
			}
			data.put("score", scoreArr);
			obj.put("wbtTests", data);
			boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
			if (isSuccess) {
				// 知识点添加出现问题是 删除数据
				this.deleteSbtFault(sbtFault.getId().toString());
				return "2";
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveSbtFault() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * <b>function:</b> 查询知识点数据匹配上保存 wbt
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean saveWbtKp(Element kp, Integer courseId) throws Exception {
		String kps = StringUtil.isNullEmtp(kp);
		String[] kpName = kps.split(",");
		if (kpName.length > 0) {
			for (int k = 0; k < kpName.length; k++) {
				KnowledgePoint knowLedge = courseWareDao
						.queryKonwName(kpName[k]);
				if (knowLedge != null) {
					// 添加数据卡
					CourseWareKp courseKp = new CourseWareKp();
					courseKp.setKpId(knowLedge.getId());
					courseKp.setCourseWareId(courseId);
					courseWareDao.save(courseKp);

				} else {
					return true;
				}
			}
		} else {
			return true;
		}
		return false;

	}

	/**
	 * <b>function:</b> 查询知识点数据匹配上保存 wbt
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean saveWbtKPs(String kp, Integer courseId, Integer type)
			throws Exception {

		KnowledgePoint knowLedge = courseWareDao.queryKonwName(kp);
		if (knowLedge != null) {
			// 添加数据卡
			WbtSbtKp wbtSbtKp = new WbtSbtKp();
			wbtSbtKp.setKpId(knowLedge.getId());
			wbtSbtKp.setSbtWbtId(courseId);
			wbtSbtKp.setType(type);
			courseWareDao.save(wbtSbtKp);
		} else {
			return true;
		}
		return false;
	}

	/**
	 * <b>function:</b> 查询知识点数据匹配上保存 wbt
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean savesbtKp(Element kp, Integer courseId, Integer type)
			throws Exception {
		String kps = StringUtil.isNullEmtp(kp);
		String[] kpName = kps.split(",");
		if (kpName.length > 0) {
			for (int k = 0; k < kpName.length; k++) {
				KnowledgePoint knowLedge = courseWareDao
						.queryKonwName(kpName[k]);
				if (knowLedge != null) {
					// 添加数据卡
					WbtSbtKp wbtSbtKp = new WbtSbtKp();
					wbtSbtKp.setKpId(knowLedge.getId());
					wbtSbtKp.setSbtWbtId(courseId);
					wbtSbtKp.setType(type);
					courseWareDao.save(wbtSbtKp);

				} else {
					return true;
				}
			}
		} else {
			return true;
		}
		return false;

	}

	/**
	 * <b>function:</b> 查询知识点数据匹配上保存 sbt
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public boolean saveSbtKPs(String kp, Integer courseId, Integer type)
			throws Exception {

		KnowledgePoint knowLedge = courseWareDao.queryKonwName(kp);
		if (knowLedge != null) {
			// 添加数据卡
			WbtSbtKp wbtSbtKp = new WbtSbtKp();
			wbtSbtKp.setKpId(knowLedge.getId());
			wbtSbtKp.setSbtWbtId(courseId);
			wbtSbtKp.setType(type);
			courseWareDao.save(wbtSbtKp);
		} else {
			return true;
		}
		return false;
	}

	/**
	 * <b>function:</b>添加wbt测评xml
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveWbtTests(String fielUrl, String uploadPath)
			throws Exception {
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			Document xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element description = (Element) root.element("description");
			Element difficulty = (Element) root.element("difficulty");
			Element duration = (Element) root.element("duration");
			Element client = (Element) root.element("client");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element kp = (Element) root.element("kp");
			Element passCondition = (Element) root.element("passCondition");
			Element completeCondition = (Element) root.element("completeCondition");
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			// isNull = StringUtil.isType(type);
			if (isNull) {
				return "1";
			}
			CourseWare courseWare = new CourseWare();
			courseWare.setModel(schema);
			courseWare.setModelVersion(schemaversion);
			courseWare.setName(StringUtil.isNullEmtp(name));
			courseWare.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			courseWare.setType("2");
			courseWare.setPurpose(StringUtil.isNullEmtp(purpose));
			courseWare.setRequires(StringUtil.isNullEmtp(require));
			courseWare.setKeyword(StringUtil.isNullEmtp(keyword));
			courseWare.setLanguage(StringUtil.isNullEmtp(language));
			courseWare.setVersion(StringUtil.isNullEmtp(version));
			courseWare.setDescription(StringUtil.isNullEmtp(description));
			courseWare.setClient(StringUtil.isNullEmtp(client));
			courseWare.setDifficulty(StringUtil.isNullzh(difficulty));
			courseWare.setEntry(StringUtil.isNullEmtp(entry));
			courseWare.setFileurl(StringUtil.interceptString(uploadPath));
			courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
			courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
			courseWare.setDuration(StringUtil.isNullEmtp(duration));
			courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
			courseWare.setCategory(1);
			courseWare.setIsEnable(1);
			courseWare.setPassCondition(StringUtil.isNullEmtps(passCondition));
			courseWare.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			courseWareDao.save(courseWare);
			// 添加知识点
			boolean isKp = saveWbtKp(kp, courseWare.getId());
			if (isKp) {
				// 知识点 添加失败删除数据
				this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId()
						.toString());
				return "3";
			}

			data.put("courseWare", courseWare);

			JSONArray chaptArr = new JSONArray();
			List<?> listChapter = root.selectNodes("//chapter");
			for (int l = 0; l < listChapter.size(); l++) {
				JSONObject chapter = new JSONObject();
				Element chapterData = (Element) listChapter.get(l);
				chapter.put("id", chapterData.attributeValue("id"));
				chapter.put("name", chapterData.attributeValue("name"));
				chapter.put("url", chapterData.attributeValue("url"));
				chapter.put("type", chapterData.attributeValue("type"));
				chaptArr.add(chapter);
				JSONArray chapterArra = new JSONArray();
				for (Iterator<?> pageInner = chapterData.elementIterator(); pageInner
						.hasNext();) {
					Element page = (Element) pageInner.next();
					JSONObject pageData = new JSONObject();
					pageData.put("id", page.attributeValue("id"));
					pageData.put("url", page.attributeValue("url"));
					pageData.put("isCommit", page.attributeValue("isCommit"));
					pageData.put("canBook", page.attributeValue("canBook"));
					pageData.put("canTool", page.attributeValue("canTool"));
					pageData.put("canFailure",
							page.attributeValue("canFailure"));
					pageData.put("canHistory",
							page.attributeValue("canHistory"));
					chapterArra.add(pageData);
				}
				chapter.put("page", chapterArra);
			}
			data.put("chapter", chaptArr);
			JSONObject menuj = new JSONObject();
			JSONArray menujs = new JSONArray();
			List<?> menus = root.selectNodes("//menu");
			for (int m = 0; m < menus.size(); m++) {
				Element menu = (Element) menus.get(m);
				menuj.put("id", menu.attributeValue("id"));
				menuj.put("name", menu.attributeValue("name"));
				menuj.put("url", menu.attributeValue("url"));
				menuj.put("type", menu.attributeValue("type"));
				for (Iterator<?> iterInner = menu.elementIterator(); iterInner
						.hasNext();) {
					Element page = (Element) iterInner.next();
					JSONObject pagesj = new JSONObject();
					pagesj.put("id", page.attributeValue("id"));
					pagesj.put("url", page.attributeValue("url"));
					pagesj.put("style", page.attributeValue("style"));
					pagesj.put("name", page.attributeValue("name"));
					pagesj.put("isCommit", page.attributeValue("isCommit"));
					menujs.add(pagesj);
				}
				menuj.put("page", menujs);
			}
			data.put("menu", menuj);

			JSONArray scoreArr = new JSONArray();
			List<?> scores = root.selectNodes("//score");
			for (int s = 0; s < scores.size(); s++) {
				Element score = (Element) scores.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = score.elementIterator(); iterInner
						.hasNext();) {
					Element question = (Element) iterInner.next();
					JSONObject questionObj = new JSONObject();
					String id = question.attributeValue("id");
					String pageId = question.attributeValue("pageid");
					String questionType = question.attributeValue("type");
					String rate = question.attributeValue("rate");
					String questionKp = question.attributeValue("kp");
					WbtScores wbtScores = new WbtScores();
					wbtScores.setCourseWareId(courseWare.getId());
					wbtScores.setCreateDate(DateUtil.getCurrentTimeByDate());
					wbtScores.setModifyDate(DateUtil.getCurrentTimeByDate());
					wbtScores.setPageid(pageId);
					wbtScores.setQuestionId(id);
					wbtScores.setRank(rank++);
					wbtScores.setRate(rate);
					wbtScores.setType(questionType);
					courseWareDao.save(wbtScores);
					// 添加知识点数据
					boolean isKps = saveWbtKPs(questionKp, wbtScores.getId(), 3);
					if(kp != null && !"".equals(kp)){
						if (isKps) {
							// 知识点 添加失败删除数据
							this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
							return "4";
						}
					}
					questionObj.put("id", id);
					questionObj.put("pageid", pageId);
					questionObj.put("type", questionType);
					questionObj.put("rate", rate);
					questionObj.put("kp", questionKp);
					scoreArr.add(questionObj);
				}
			}
			data.put("score", scoreArr);
			obj.put("wbtTests", data);
			boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
			if (isSuccess) {
				// 知识点 添加失败删除数据
				this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId()
						.toString());
				return "2";
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveWbtTests() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * <b>function:</b>添加wbt课件
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveWbtCourseWare(String fielUrl, String uploadPath)
			throws Exception {
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			Document xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element type = (Element) root.element("type");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element description = (Element) root.element("description");
			Element difficulty = (Element) root.element("difficulty");
			Element duration = (Element) root.element("duration");
			Element client = (Element) root.element("client");
			Element entry = (Element) root.element("entry");
			Element sumScore = (Element) root.element("sumScore");
			Element passCondition = (Element) root.element("passCondition");
			Element completeCondition = (Element) root.element("completeCondition");
			
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			if (isNull) {
				return "1";// courseNumber或type不能为空
			}
			CourseWare courseWare = new CourseWare();
			courseWare.setModel(schema);
			courseWare.setModelVersion(schemaversion);
			courseWare.setName(StringUtil.isNullEmtp(name));
			courseWare.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			courseWare.setType("2");
			courseWare.setPurpose(StringUtil.isNullEmtp(purpose));
			courseWare.setRequires(StringUtil.isNullEmtp(require));
			courseWare.setKeyword(StringUtil.isNullEmtp(keyword));
			courseWare.setLanguage(StringUtil.isNullEmtp(language));
			courseWare.setVersion(StringUtil.isNullEmtp(version));
			courseWare.setDescription(StringUtil.isNullEmtp(description));
			courseWare.setClient(StringUtil.isNullEmtp(client));
			courseWare.setDifficulty(StringUtil.isNullzh(difficulty));
			courseWare.setDuration(StringUtil.isNullEmtp(duration));
			courseWare.setCategory(1);
			if(sumScore !=null && !"".equals(sumScore)){
				courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
			}
			courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
			courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
			courseWare.setEntry(StringUtil.isNullEmtp(entry));
			courseWare.setFileurl(StringUtil.interceptString(uploadPath));
			courseWare.setIsEnable(1);
			courseWare.setPassCondition(StringUtil.isNullEmtps(passCondition));
			courseWare.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			
			courseWare = courseWareDao.saveCourseWare(courseWare);
			
			List<?> handles = root.selectNodes("//handles");
			JSONObject handlesObj = new JSONObject();
			for (int s = 0; s < handles.size(); s++) {
				Element handlesName = (Element) handles.get(s);
				JSONArray handleArr = new JSONArray();
				int rank = 0;
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					String actionId = handleName.attributeValue("id");
					String actionDiscription = handleName.attributeValue("discription");
					CourseWareAction courseWareAction = new CourseWareAction();
					courseWareAction.setActionId(actionId);
					courseWareAction.setDescription(actionDiscription);
					courseWareAction.setCode(GenerateNumber.getCourseWareCode());
					courseWareAction.setCreateDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setModifyDate(DateUtil.getCurrentTimeByDate());
					courseWareAction.setRank(rank++);
					courseWareAction.setType("2");
					courseWareAction.setCourseWareId(courseWare.getId());
					courseWareDao.save(courseWareAction);
					JSONArray conditionArr = new JSONArray();
					for (Iterator<?> conditionIterator = handleName.elementIterator(); conditionIterator.hasNext();) {
						Element conditions = (Element) conditionIterator.next();
						JSONObject conditionObj = new JSONObject();
						conditionObj.put("id",conditions.attributeValue("id"));
						conditionObj.put("linkVID",conditions.attributeValue("linkVID"));
						conditionObj.put("cdt",conditions.attributeValue("cdt"));
						conditionObj.put("cdtValue",conditions.attributeValue("cdtValue"));
						conditionObj.put("cdtID",conditions.attributeValue("cdtID"));
						conditionArr.add(conditionObj);
					}
					handleObj.put("condition",conditionArr);
					handleObj.put("id", actionId);
					handleObj.put("discription", actionDiscription);
					handleArr.add(handleObj);
				}
				handlesObj.put("handle", handleArr);
			}
			data.put("handles", handlesObj);
			
			List<?> values = root.selectNodes("//values");
			JSONObject valuesObj = new JSONObject();
			for (int s = 0; s < values.size(); s++) {
				Element handlesName = (Element) values.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("discription", handleName.attributeValue("discription"));
					handleObj.put("value", handleName.attributeValue("value"));
					handleArr.add(handleObj);
				}
				valuesObj.put("value", handleArr);
			}
			data.put("values", valuesObj);
			
			List<?> progresses = root.selectNodes("//progresses");
			JSONObject progressesObj = new JSONObject();
			for (int s = 0; s < progresses.size(); s++) {
				Element handlesName = (Element) progresses.get(s);
				JSONArray handleArr = new JSONArray();
				for (Iterator<?> iterInner = handlesName.elementIterator(); iterInner.hasNext();) {
					Element handleName = (Element) iterInner.next();
					JSONObject handleObj = new JSONObject();
					handleObj.put("id", handleName.attributeValue("id"));
					handleObj.put("handleId", handleName.attributeValue("hid"));
					handleObj.put("rate", handleName.attributeValue("rate"));
					handleArr.add(handleObj);
				}
				progressesObj.put("progress", handleArr);
			}
			data.put("progresses", progressesObj);
			
			// 分数<score>
			Element score = (Element) root.element("scores");
			JSONArray scoreJsonArray = new JSONArray();
			JSONArray exclusiveJsonArray = new JSONArray();
			Map<String,String> scoreMapTemp = new HashMap<String,String>();
			if(score != null){
				int rank=0;
				for (Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();) {
					JSONObject scoreJsonObject = new JSONObject();
					Element scoreElement = (Element) iterInner.next();
					if("question".equals(scoreElement.attributeValue("type"))){
						scoreJsonObject.put("type",scoreElement.attributeValue("type"));
						scoreJsonObject.put("id", scoreElement.attributeValue("id"));
						scoreJsonObject.put("questionId", scoreElement.attributeValue("hid"));
						scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
						scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
						//记录下知识点方便与题匹配
						scoreMapTemp.put(scoreElement.attributeValue("hid"), scoreElement.attributeValue("rate"));
						scoreJsonArray.add(scoreJsonObject);
						
						CourseWareScores courseWareScores = new CourseWareScores();
						courseWareScores.setRate(scoreElement.attributeValue("rate"));
						courseWareScores.setScoreTypeId(scoreElement.attributeValue("id"));
						courseWareScores.setType("question");
						courseWareScores.setCreateDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setModifyDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setRank(rank++);
						courseWareScores.setCourseWareId(courseWare.getId());;
						courseWareScores.setKp(scoreElement.attributeValue("kp"));
						courseWareScores.setQid(scoreElement.attributeValue("hid"));
						courseWareDao.save(courseWareScores);
					}
					if("handle".equals(scoreElement.attributeValue("type"))){
						scoreJsonObject.put("type",scoreElement.attributeValue("type"));
						scoreJsonObject.put("id", scoreElement.attributeValue("id"));
						scoreJsonObject.put("handleId", scoreElement.attributeValue("hid"));
						scoreJsonObject.put("rate",scoreElement.attributeValue("rate"));
						scoreJsonObject.put("kp", scoreElement.attributeValue("kp"));
						scoreJsonArray.add(scoreJsonObject);
						
						CourseWareScores courseWareScores = new CourseWareScores();
						courseWareScores.setRate(scoreElement.attributeValue("rate"));
						courseWareScores.setScoreTypeId(scoreElement.attributeValue("id"));
						courseWareScores.setType("handle");
						courseWareScores.setCreateDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setModifyDate(DateUtil.getCurrentTimeByDate());
						courseWareScores.setRank(rank++);
						courseWareScores.setCourseWareId(courseWare.getId());;
						courseWareScores.setKp(scoreElement.attributeValue("kp"));
						courseWareScores.setQid(scoreElement.attributeValue("hid"));
						courseWareDao.save(courseWareScores);
					}
					if("exclusive".equals(scoreElement.getName())){
						JSONObject exclusiveJsonObject = new JSONObject();
						exclusiveJsonObject.put("id", scoreElement.attributeValue("id"));
						exclusiveJsonObject.put("priorityId", scoreElement.attributeValue("priorityId"));
						exclusiveJsonArray.add(exclusiveJsonObject);
					}
				}
				data.put("exclusive", exclusiveJsonArray);
				data.put("score", scoreJsonArray);
			}
			
			//将score放入map中
			Map<String,String> scoreMap = new HashMap<String,String>();
			for (Object obj : scoreJsonArray) {
				if(((JSONObject)obj).get("questionId") != null){
					scoreMap.put(((JSONObject)obj).get("questionId").toString(), ((JSONObject)obj).get("rate").toString());
				}
				if(((JSONObject)obj).get("handleId") != null){
					scoreMap.put(((JSONObject)obj).get("handleId").toString(), ((JSONObject)obj).get("rate").toString());
				}
			}
			
			//入库coursequestion
			List<?> questions = root.selectNodes("//questions");
			for (int s = 0; s < questions.size(); s++) {
				Element questionsName = (Element) questions.get(s);
				int rank = 0;
				for (Iterator<?> iterInner = questionsName.elementIterator(); iterInner.hasNext();) {
					Element questionName = (Element) iterInner.next();
					for (Iterator<?> questioniterInner = questionName.elementIterator(); questioniterInner.hasNext();) {
						Element question2Name = (Element) questioniterInner.next();
						String qid = question2Name.attributeValue("id");
						if(StringUtil.isNotEmpty(qid)){
							CourseWareQuestion courseWareQuestion = new CourseWareQuestion();
							courseWareQuestion.setQuestionId(qid);
							courseWareQuestion.setDescription(question2Name.attributeValue("description"));
							courseWareQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
							courseWareQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
							courseWareQuestion.setRank(rank++);
							courseWareQuestion.setCourseWareId(courseWare.getId());
							courseWareQuestion.setKp(scoreMapTemp.get(qid));
							courseWareQuestion.setType(question2Name.attributeValue("type")==null?0:Integer.parseInt(question2Name.attributeValue("type")));
							courseWareDao.save(courseWareQuestion);
						}
					}
				}
			}
			
			Element questions2 = (Element) root.element("questions");
			JSONArray questionsJsonArray = new JSONArray();
			//将题放入到questionJsonArray中
			if(questions != null){
				this.getCourseWareQuestion(questions2, questionsJsonArray, scoreMap);
			}
			data.put("question", questionsJsonArray);
			
			boolean isSuccess = StringUtil.writeTxt(txtUrl, data.toString());
			if (isSuccess) {
				// text写入失败调用删除方法
				this.deleteSbtCourseWare(courseWare.getId().toString());
				return "2";// text写入失败
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveSbtCourseWare() exception: " + e.getMessage());
		}
		return "0";
	}

	/**
	 * <b>function:</b> 读取拆装课程描述xml
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveDisassemblingCourseDescription(String fielUrl,
			String uploadPath) throws Exception {

		try {
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			SAXReader sax = new SAXReader();
			Document xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");
			Element pro = (Element) list.get(0);
			String schema = pro.elementText("schema");
			String schemaversion = pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();// 根节点
			Element name = (Element) root.element("name");
			Element courseNumber = (Element) root.element("courseNumber");
			Element type = (Element) root.element("type");
			Element kp = (Element) root.element("kp");
			Element purpose = (Element) root.element("purpose");
			Element require = (Element) root.element("require");
			Element keyword = (Element) root.element("keyword");
			Element language = (Element) root.element("language");
			Element version = (Element) root.element("version");
			Element description = (Element) root.element("description");
			Element passScore = (Element) root.element("pass_score");
			Element client = (Element) root.element("client");
			Element entry = (Element) root.element("entry");
			Element duration = (Element) root.element("duration");
			Element difficulty = (Element) root.element("difficulty");
			Element sumScore = (Element) root.element("sumScore");
			Element completeCondition = (Element) root.element("completeCondition");
			
			boolean isNull = false;
			isNull = StringUtil.isbool(type);
			isNull = StringUtil.isbool(courseNumber);
			// isNull = StringUtil.isType(type);
			if (isNull) {
				return "1";
			}
			CourseWare courseWare = new CourseWare();
			courseWare.setModel(schema);
			courseWare.setModelVersion(schemaversion);
			courseWare.setName(StringUtil.isNullEmtp(name));
			courseWare.setCourseNumber(StringUtil.isNullEmtp(courseNumber));
			courseWare.setType("4");
			courseWare.setPurpose(StringUtil.isNullEmtp(purpose));
			courseWare.setRequires(StringUtil.isNullEmtp(require));
			courseWare.setKeyword(StringUtil.isNullEmtp(keyword));
			courseWare.setLanguage(StringUtil.isNullEmtp(language));
			courseWare.setVersion(StringUtil.isNullEmtp(version));
			courseWare.setDescription(StringUtil.isNullEmtp(description));
			courseWare.setClient(StringUtil.isNullEmtp(client));
			courseWare.setEntry(StringUtil.isNullEmtp(entry));
			courseWare.setDifficulty(StringUtil.isNullEmtps(difficulty));
			courseWare.setDuration(StringUtil.isNullEmtp(duration));
			courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
			courseWare.setCategory(1);
			courseWare.setIsEnable(1);
			courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
			courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
			courseWare.setFileurl(StringUtil.interceptString(uploadPath));
			courseWare.setPassCondition(StringUtil.isNullEmtps(passScore));
			courseWare.setCompleteCondition(StringUtil.isNullEmtps(completeCondition));
			
			data.put("courseWare", courseWare);

			courseWareDao.save(courseWare);
			// 添加知识点
			boolean isKp = saveWbtKp(kp, courseWare.getId());
			if (isKp) {
				// 知识点 添加失败删除数据
				this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId()
						.toString());
				return "3";
			}

			JSONObject actionObj = new JSONObject();
			List<?> actions = root.selectNodes("//actions");
			for (int a = 0; a < actions.size(); a++) {
				Element action = (Element) actions.get(a);
				JSONArray actionsArrj = new JSONArray();
				int rank = 0;
				for (Iterator<?> iterAction = action.elementIterator(); iterAction
						.hasNext();) {
					Element actionData = (Element) iterAction.next();

					String actionId = actionData.attributeValue("id");
					String actionDiscription = actionData
							.attributeValue("discription");

					JSONObject actionsData = new JSONObject();
					actionsData.put("id", actionId);
					actionsData.put("discription", actionDiscription);
					actionsArrj.add(actionsData);
					CourseWareAction courseWareAction = new CourseWareAction();
					courseWareAction.setActionId(actionId);
					courseWareAction.setDescription(actionDiscription);
					courseWareAction.setCourseWareId(courseWare.getId());
					courseWareAction.setType("4");
					courseWareAction
							.setCode(GenerateNumber.getCourseWareCode());
					courseWareAction.setCreateDate(DateUtil
							.getCurrentTimeByDate());
					courseWareAction.setModifyDate(DateUtil
							.getCurrentTimeByDate());
					courseWareAction.setRank(rank++);
					courseWareDao.save(courseWareAction);
				}
				actionObj.put("action", actionsArrj);
			}
			data.put("actions", actionObj);
			JSONArray parArry = new JSONArray();
			JSONObject parsData = new JSONObject();
			List<?> parts = root.selectNodes("//parts");
			for (int p = 0; p < parts.size(); p++) {
				Element partsData = (Element) parts.get(p);
				for (Iterator<?> iterPart = partsData.elementIterator(); iterPart
						.hasNext();) {
					Element partData = (Element) iterPart.next();
					JSONObject parData = new JSONObject();
					String partId = partData.attributeValue("id");
					parData.put("id", partId);
					parArry.add(parData);
					JSONArray stateArry = new JSONArray();
					int rank = 0;
					for (Iterator<?> iterState = partData.elementIterator(); iterState
							.hasNext();) {
						Element state = (Element) iterState.next();
						JSONObject stateData = new JSONObject();
						String stateId = state.attributeValue("id");
						String stateDiscription = state
								.attributeValue("discription");
						stateData.put("id", stateId);
						stateData.put("discription", stateDiscription);
						CourseWareParts courseWareParts = new CourseWareParts();
						courseWareParts.setStateId(stateId);
						courseWareParts.setDescription(stateDiscription);
						courseWareParts.setRank(rank++);
						courseWareParts.setType("4");
						courseWareParts.setCreateDate(DateUtil
								.getCurrentTimeByDate());
						courseWareParts.setModifyDate(DateUtil
								.getCurrentTimeByDate());
						courseWareParts.setCourseWareId(courseWare.getId());
						courseWareParts.setCode(GenerateNumber
								.getCourseWareCode());
						courseWareParts.setPartId(partId);
						courseWareParts.setModifyDate(DateUtil
								.getCurrentTimeByDate());
						courseWareDao.save(courseWareParts);

						stateArry.add(stateData);
					}
					parData.put("state", stateArry);
				}
				parsData.put("part", parArry);
			}
			data.put("parts", parsData);

			JSONArray toolArry = new JSONArray();
			JSONObject toolsData = new JSONObject();
			List<?> tools = root.selectNodes("//tools");
			for (int t = 0; t < tools.size(); t++) {
				Element tool = (Element) tools.get(t);
				for (Iterator<?> iterTool = tool.elementIterator(); iterTool
						.hasNext();) {
					Element toolData = (Element) iterTool.next();
					JSONObject toolDatas = new JSONObject();
					String toolId = toolData.attributeValue("id");
					toolDatas.put("id", toolId);
					toolArry.add(toolDatas);
					JSONArray stateArry = new JSONArray();
					int rank = 0;
					for (Iterator<?> iterState = toolData.elementIterator(); iterState
							.hasNext();) {
						Element state = (Element) iterState.next();
						JSONObject stateData = new JSONObject();

						String stateId = state.attributeValue("id");
						String stateDiscription = state
								.attributeValue("discription");
						CourseWareTool courseWareTool = new CourseWareTool();
						courseWareTool.setCourseWareId(courseWare.getId());
						courseWareTool.setCreateDate(DateUtil
								.getCurrentTimeByDate());
						courseWareTool.setModifyDate(DateUtil
								.getCurrentTimeByDate());
						courseWareTool.setType("4");
						courseWareTool.setCode(GenerateNumber
								.getCourseWareCode());
						courseWareTool.setRank(rank++);
						courseWareTool.setStateId(stateId);
						courseWareTool.setToolId(toolId);
						courseWareTool.setDescription(stateDiscription);
						courseWareDao.save(courseWareTool);
						stateData.put("id", stateId);
						stateData.put("discription", stateDiscription);

						stateArry.add(stateData);
					}
					toolDatas.put("state", stateArry);
				}
				toolsData.put("tool", toolArry);
			}
			data.put("tools", toolsData);
			JSONObject querstionObj = new JSONObject();
			JSONArray questionArr = new JSONArray();
			List<?> questions = root.selectNodes("//questions");
			for (int q = 0; q < questions.size(); q++) {
				Element question = (Element) questions.get(q);
				for (Iterator<?> iterQuestion = question.elementIterator(); iterQuestion
						.hasNext();) {
					Element questionData = (Element) iterQuestion.next();
					JSONObject questionDatas = new JSONObject();
					String questionIds = questionData.attributeValue("id");
					String questionDiscription = questionData
							.attributeValue("discription");
					questionDatas.put("id", questionIds);
					questionDatas.put("discription", questionDiscription);
					JSONArray answArr = new JSONArray();
					for (Iterator<?> iterAnswer = questionData
							.elementIterator(); iterAnswer.hasNext();) {
						Element answer = (Element) iterAnswer.next();
						JSONObject answerData = new JSONObject();
						String answerId = answer.attributeValue("id");
						String answerDiscription = answer
								.attributeValue("discription");
						answerData.put("id", answerId);
						answerData.put("discription", answerDiscription);
						answArr.add(answerData);
					}
					questionDatas.put("answer", answArr);
					questionArr.add(questionDatas);
				}
				querstionObj.put("question", questionArr);
			}
			data.put("questions", querstionObj);

			JSONObject scoreObj = new JSONObject();

			List<?> scores = root.selectNodes("//scores");
			for (int s = 0; s < scores.size(); s++) {
				Element score = (Element) scores.get(s);
				JSONArray scoreArr = new JSONArray();
				int rank = 0;
				for (Iterator<?> iterInner = score.elementIterator(); iterInner
						.hasNext();) {
					Element courseData = (Element) iterInner.next();
					JSONObject wbtScoresObj = new JSONObject();
					String id = courseData.attributeValue("id");
					String scoreKp = courseData.attributeValue("kp");
					String questionType = courseData.attributeValue("type");
					String rate = courseData.attributeValue("rate");
					wbtScoresObj.put("id", id);
					wbtScoresObj.put("kp", scoreKp);
					wbtScoresObj.put("type", questionType);
					wbtScoresObj.put("rate", rate);
					scoreArr.add(wbtScoresObj);
					WbtScores wbtScores = new WbtScores();
					wbtScores.setCourseWareId(courseWare.getId());
					wbtScores.setCreateDate(DateUtil.getCurrentTimeByDate());
					wbtScores.setModifyDate(DateUtil.getCurrentTimeByDate());
					wbtScores.setQuestionId(id);
					wbtScores.setRate(rate);
					wbtScores.setRank(rank++);

					wbtScores.setType(questionType);
					courseWareDao.save(wbtScores);
					// 添加知识点数据
					boolean isKps = saveWbtKPs(scoreKp, wbtScores.getId(), 4);
					if(kp != null && !"".equals(kp)){
						if (isKps) {
							// 知识点 添加失败删除数据
							this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
							return "4";
						}
					}
				}
				scoreObj.put("score", scoreArr);
			}
			data.put("scores", scoreObj);
			obj.put("dismantle", data);
			// 如果写入失败 返回false
			boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
			if (isSuccess) {
				// 知识点 添加失败删除数据
				this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId()
						.toString());
				return "2";
			}
		} catch (DocumentException e) {
			logger.error(CourseWareServiceImpl.class.getName()
					+ " saveDisassemblingCourseDescription() exception: "
					+ e.getMessage());
		}
		return "0";
	}

	/**
	 * <b>function:</b>根据id查询CourseWare数据
	 * 
	 * @project xcjedu
	 * @package com.xcj.api.controller.course
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public List<CourseWare> queryCourseWareId(String courseNumber)
			throws Exception {
		return courseWareDao.queryCourseWareId(courseNumber);
	}

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
	 */
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> ps,
			CourseWare courseWare) throws Exception {
		Page<CourseWare> page = courseWareDao.getByCourseWarePage(ps,
				courseWare);

		return page;
	}

	/**
	 * <b>function:</b> course_ware课件基本信息---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		courseWareDao.removeById(id);
	}

	/**
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午11:06:49
	 * @return Page<CourseWare>
	 * @author dapeng_wu
	 */
	public Page<CourseWare> getByPage(Page<CourseWare> page, String search)
			throws Exception {
		Page<CourseWare> pageCourseWare = courseWareDao.getByPage(page,
				new CourseWare(), search);
		return pageCourseWare;
	}

	/**
	 * <b>function:</b> 课程库集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 上午10:31:41
	 * @return
	 * @author yy.niu
	 */
	public Page<CourseWare> getByPages(Page<CourseWare> page, String search)
			throws Exception {
		Page<CourseWare> pageCourseWare = courseWareDao.getByPages(page,
				new CourseWare(), search);
		return pageCourseWare;
	}

	/**
	 * 
	 * <b>function:</b> 根据ids找到集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 上午10:31:41
	 * @return
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByIds(String ids) throws Exception {
		return courseWareDao.getCourseWareByIds(ids);
	}

	/**
	 * <b>function:</b> 根据courseNumber获取课程信息 CourseWareService
	 * 
	 * @createDate 2015-2-9 下午05:01:35
	 * @author su_jian
	 * @email sujiansoft@163.com return type CourseWare
	 */
	public CourseWare getByCourseNumber(String courseNumber) throws Exception {
		return courseWareDao.getByCourseNumber(courseNumber);
	}

	/**
	 * 
	 * <b>function:</b> 获取当前类别下所有已选的课程
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-3 下午01:20:33
	 * @return List<CourseWare>
	 * @author hehujun
	 */
	public List<CourseWare> getAllSelectedCourses(Integer category,
			Integer domainId) throws Exception {
		return courseWareDao.getAllSelectedCourses(category, domainId);
	}

	/**
	 * 
	 * <b>function:</b> 当前类别下已选择课程(分页) <br>
	 * 比如：课程类别、题类别
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-2 下午06:24:29
	 * @return Page<CourseWare>
	 * @author hehujun
	 */
	public Page<CourseWare> getSelectedCoursesPage(Page<CourseWare> ps,
			Integer category, String search, Integer domainId) throws Exception {
		return courseWareDao.getSelectedCoursesPage(ps, category, search,
				domainId);
	}

	/**
	 * 
	 * <b>function:</b> 根据domainId 删除域与课程库关联表中的数据
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-4 上午10:10:06
	 * @return boolean
	 * @author hehujun
	 */
	public boolean deleteDomainCourseWare(Integer domainId) throws Exception {
		return courseWareDao.deleteDomainCourseWare(domainId);
	}

	/**
	 * 
	 * <b>function:</b> 批量插入domain_course_ware表
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-4 上午11:19:07
	 * @return boolean
	 * @author hehujun
	 */
	public boolean updateDomainCourseWare(Integer domainId, String courseWareIds)
			throws Exception {
		return courseWareDao.updateDomainCourseWare(domainId, courseWareIds);
	}

	/**
	 * 
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-4 下午06:04:55
	 * @return String
	 * @author hehujun
	 */
	public String pageAsString(Page<CourseWare> page, HttpServletRequest request)
			throws Exception {
		PagerTag tag = new PagerTag();
		return tag.doStartTagStringWithoutJs(page, request);
	}

	/**
	 * 
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-4 下午06:04:55
	 * @return String
	 * @author hehujun
	 */
	public String pageAsStringTwo(Page<CourseWare> page,
			HttpServletRequest request) throws Exception {
		PagerTag tag = new PagerTag();
		return tag.doStartTagTwoStringWithoutJS(page, request);
	}

	/**
	 * <b>function:</b>根据userName查询 courseWare数据
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public List<?> getCourseWareList(String userName) throws Exception {
		return courseWareDao.getCourseWareList(userName);
	}

	/**
	 * <b>function:</b> 根据域名称获取课件集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-6 下午05:29:34
	 * @return List<CourseWare>
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithDominUsername(
			String domainUsername, Integer category) throws Exception {
		return courseWareDao.getCourseWareListWithDominUsername(domainUsername,
				category);
	}

	/**
	 * 
	 * <b>function:</b> 根据域名称获取试卷paper集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-24 下午01:17:47
	 * @return List<Paper>
	 * @author dapeng_wu
	 */
	public List<Paper> getPaperListWithDominUsername(String domainUsername)
			throws DataAccessException {
		return courseWareDao.getPaperListWithDominUsername(domainUsername);
	}

	/**
	 * 
	 * <b>function:</b> 根据paper获取已经发布的题库集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.course
	 * @fileName com.xcj.***
	 * @createDate 2015-3-24 下午02:31:34
	 * @return
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithPaper(Paper paper)
			throws Exception {
		return courseWareDao.getCourseWareListWithPaper(paper);
	}

	/**
	 * 
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>获取故障集合</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、获取故障集合</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年12月21日 上午10:04:22</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>wu_dapeng</dd> </dl>
	 */
	public List<SbtFault> querySbtFault(String courseWareId) throws Exception {
		return courseWareDao.querySbtFaultKey(courseWareId);
	}

	/**
	 * 
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>同步sbtFault</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、同步sbtFault</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年12月23日 上午9:59:26</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>wu_dapeng</dd> </dl>
	 */
	public List<SbtFault> getSbtFaultByCourseWareIds(String cwids)
			throws DataAccessException {
		return courseWareDao.getSbtFaultByCourseWareIds(cwids);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>修改课件禁用</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、修改课件禁用</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月25日 下午4:43:57</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void updateCourseWareByIds(String ids)throws Exception{
		courseWareDao.updateCourseWareByIds(ids);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据ids获取courseware集合</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、根据ids获取courseware集合</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年3月31日 下午1:53:26</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<CourseWare> getCourseWareListByids(String ids) throws Exception{
		return courseWareDao.getCourseWareListByids(ids);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>解析题放入到questionJsonArray中</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、解析题放入到questionJsonArray中</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年5月4日 下午3:19:43</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void getCourseWareQuestion(Element questions,JSONArray questionsJsonArray,Map<String,String> scoreMap) throws Exception{
		for (Iterator<?> iterInner = questions.elementIterator(); iterInner.hasNext();) {
			Element questionsElement = (Element) iterInner.next();
			JSONObject questionsJsonObject = new JSONObject();
			questionsJsonObject.put("id",questionsElement.attributeValue("id"));
			questionsJsonObject.put("remark",questionsElement.attributeValue("remark"));
			questionsJsonObject.put("description",questionsElement.attributeValue("description"));
			JSONArray question1JsonArray = new JSONArray();
			
			for (Iterator<?> question1IterInner = questionsElement.elementIterator(); question1IterInner.hasNext();) {
				Element questions1Element = (Element) question1IterInner.next();
				JSONObject question1JsonObject = new JSONObject();
				question1JsonObject.put("id",questions1Element.attributeValue("id"));
				question1JsonObject.put("description",questions1Element.attributeValue("description"));
				question1JsonObject.put("type",questions1Element.attributeValue("type"));
				question1JsonObject.put("rate", scoreMap.get(questions1Element.attributeValue("id"))==null?"":scoreMap.get(questions1Element.attributeValue("id")));
				
				//1单选2多选3表格4单选+多选5多选+多选
				if ("1".equals(questions1Element.attributeValue("type"))|| "2".equals(questions1Element.attributeValue("type"))) {
					JSONArray optionJsonArray = new JSONArray();
					for (Iterator<?> optionIterInner = questions1Element.elementIterator(); optionIterInner.hasNext();) {
						Element optionElement = (Element) optionIterInner.next();
						JSONObject optionJsonObject = new JSONObject();
						optionJsonObject.put("id",optionElement.attributeValue("id"));
						optionJsonObject.put("isRight", optionElement.attributeValue("isRight"));
						optionJsonObject.put("description", optionElement.attributeValue("description"));
						optionJsonArray.add(optionJsonObject);
					}
					question1JsonObject.put("option", optionJsonArray);
				}
				
				if ("4".equals(questions1Element.attributeValue("type"))) {
					JSONArray optionJsonArray = new JSONArray();
					for (Iterator<?> optionIterInner = questions1Element.elementIterator(); optionIterInner.hasNext();) {
						Element optionElement = (Element) optionIterInner.next();
						JSONObject optionJsonObject = new JSONObject();
						optionJsonObject.put("id",optionElement.attributeValue("id"));
						optionJsonObject.put("isRight", optionElement.attributeValue("isRight"));
						optionJsonObject.put("description", optionElement.attributeValue("description"));
						//后面跟多选选项
							JSONArray optionSonJsonArray = new JSONArray();
							for (Iterator<?> optionSonIterInner = optionElement.elementIterator(); optionSonIterInner.hasNext();){
								Element optionSonElement = (Element) optionSonIterInner.next();
								JSONObject answerSonJsonObject = new JSONObject();
								answerSonJsonObject.put("id",optionSonElement.attributeValue("id"));
								answerSonJsonObject.put("isRight",optionSonElement.attributeValue("isRight"));
								answerSonJsonObject.put("description",optionSonElement.attributeValue("description"));
								optionSonJsonArray.add(answerSonJsonObject);
							}
							
						optionJsonObject.put("option",optionSonJsonArray);
						optionJsonArray.add(optionJsonObject);
					}
					question1JsonObject.put("option", optionJsonArray);
				}
				
				if ("3".equals(questions1Element.attributeValue("type"))) {
					JSONArray question2JsonArray = new JSONArray();
					for (Iterator<?> question2IterInner = questions1Element.elementIterator(); question2IterInner.hasNext();) {
						Element question2Element = (Element) question2IterInner.next();
						
						JSONArray jsonArrayOpiton = new JSONArray();
						JSONArray tdJsonArr = new JSONArray();
						JSONArray opitonJsonArr = new JSONArray();
						for (Iterator<?> questionContentIterInner = question2Element.elementIterator(); questionContentIterInner.hasNext();) {
							Element questionContent = (Element) questionContentIterInner.next();
							
							if("description".equals(questionContent.getName())){
								for (Iterator<?> discriptionIterInner = questionContent.elementIterator(); discriptionIterInner.hasNext();) {
									Element tdElement = (Element) discriptionIterInner.next();
									if("td".equals(tdElement.getName())){
										tdJsonArr.add(tdElement.getText());
									}
									if("option".equals(tdElement.getName())){
										opitonJsonArr.add(tdElement.getText());
									}
								}
							}
							if("option".equals(questionContent.getName())){
								JSONObject optionJsonObject = new JSONObject();
								optionJsonObject.put("id",questionContent.attributeValue("id"));
								optionJsonObject.put("isRight",questionContent.attributeValue("isRight"));
								optionJsonObject.put("description",questionContent.attributeValue("description"));
								jsonArrayOpiton.add(optionJsonObject);
							}
						}
						JSONObject question2JsonObject = new JSONObject();
						question2JsonObject.put("id",question2Element.attributeValue("id"));
						question2JsonObject.put("rate", scoreMap.get(question2Element.attributeValue("id"))==null?"":scoreMap.get(question2Element.attributeValue("id")));
						JSONObject tdObj = new JSONObject();
						tdObj.put("td", tdJsonArr);
						tdObj.put("option", opitonJsonArr);
						question2JsonObject.put("td", tdObj);
						question2JsonObject.put("option", jsonArrayOpiton);
						question2JsonArray.add(question2JsonObject);

					}
					question1JsonObject.put("question",question2JsonArray);
				}
				
				if ("5".equals(questions1Element.attributeValue("type"))) {
					JSONArray question2JsonArray = new JSONArray();
					for (Iterator<?> question2IterInner = questions1Element.elementIterator(); question2IterInner.hasNext();) {
						Element question2Element = (Element) question2IterInner.next();
						if("question".equals(question2Element.getName())){
							JSONObject questionObj = new JSONObject();
							questionObj.put("id", question2Element.attributeValue("id"));
							questionObj.put("rate", scoreMap.get(question2Element.attributeValue("id"))==null?"":scoreMap.get(question2Element.attributeValue("id")));
							
							JSONArray descriptionJsonArr = new JSONArray();
							JSONArray optionJsonArr = new JSONArray();
							JSONObject descObj = new JSONObject();
							for (Iterator<?> descriptionIterInner = question2Element.elementIterator(); descriptionIterInner.hasNext();) {
								Element question3Element = (Element) descriptionIterInner.next();
								
								if("description".equals(question3Element.getName())){
									JSONArray questionJsonArr = new JSONArray();
									for (Iterator<?> questionIterInner = question3Element.elementIterator(); questionIterInner.hasNext();) {
										Element questionElement = (Element) questionIterInner.next();
										JSONObject questionObj2 = new JSONObject();
										questionObj2.put("index", questionElement.getText());
										questionObj2.put("description", questionElement.attributeValue("description"));
										questionObj2.put("isSub", questionElement.attributeValue("isSub"));
										questionJsonArr.add(questionObj2);
									}
									descObj.put("description", questionJsonArr);
									descriptionJsonArr.add(descObj);
								}
								
								if("option".equals(question3Element.getName())){
									JSONArray questionJsonArr = new JSONArray();
									for (Iterator<?> questionIterInner = question3Element.elementIterator(); questionIterInner.hasNext();) {
										Element questionElement = (Element) questionIterInner.next();
										JSONObject questionObj2 = new JSONObject();
										questionObj2.put("id",  questionElement.attributeValue("id"));
										questionObj2.put("description", questionElement.attributeValue("description"));
										questionObj2.put("isRight", questionElement.attributeValue("isRight"));
										questionJsonArr.add(questionObj2);
									}
									
									JSONObject optionObj = new JSONObject();
									optionObj.put("id",  question3Element.attributeValue("id"));
									optionObj.put("description", question3Element.attributeValue("description"));
									optionObj.put("isRight", question3Element.attributeValue("isRight"));
									optionObj.put("option", questionJsonArr);
									optionJsonArr.add(optionObj);
									
								}

							}
							
							descObj.put("option", optionJsonArr);
							//descriptionJsonArr.add(descObj);
							
							questionObj.put("question", descObj);
							question2JsonArray.add(questionObj);
						}
					}
					question1JsonObject.put("question",question2JsonArray);
				}
				question1JsonArray.add(question1JsonObject);
			}
			questionsJsonObject.put("question", question1JsonArray);
			questionsJsonArray.add(questionsJsonObject);
		}
	}
	
	
	
	
	//rabbitmq同步课件
	@Async
	public void syncCourseWare(String coursewareArray) throws Exception{
		try {
			Thread.currentThread();
			Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			logger.error("同步课件出错："+e);
		}
		//获取结果
		 if(coursewareArray!=null){
			 //推送结果
			 //MQProducerUtil.SendCourseware(JSON.toJSONString(courseWare), 2, "demoCourseware", MQConstants.Topic_demo_course_ware,MQConstants.ProducerId_demo_course_ware);
			 RabbitMQProducerUtil.sendMsgToQuery(MQConstants.Topic_demo_course_ware, coursewareArray);
		 }
	}
	
	@Async
	public void syncSbtFault(String sbtFaultArray) throws Exception{
		try {
			Thread.currentThread();
			Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			logger.error("同步sbt故障出错："+e);
		}
		//获取结果
		 if(sbtFaultArray!=null){
			 //推送结果
			 //MQProducerUtil.SendCourseware(JSON.toJSONString(sbtFault), 2, "demoCourseware", MQConstants.Topic_demo_sbt_fault,MQConstants.ProducerId_demo_sbt_fault);
			 RabbitMQProducerUtil.sendMsgToQuery(MQConstants.Topic_demo_sbt_fault, sbtFaultArray);
		 }
	}
	
	@Async
	public void syncTestPosted(String testPostedArray) throws Exception{
		try {
			Thread.currentThread();
			Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			logger.error("同步测评出错："+e);
		}
		//获取结果
		if(testPostedArray!=null){
			//推送结果
			RabbitMQProducerUtil.sendMsgToQuery(MQConstants.Topic_demo_tests_posted, testPostedArray);
		}
	}
	
	@Async
	public void syncKnowledgePoint(String knowledgePointArray) throws Exception{
		try {
			Thread.currentThread();
			Thread.sleep(1*10*1000);//线程等待一分钟然后继续插入
		} catch (Exception e) {
			logger.error("同步知识："+e);
		}
		//获取结果
		if(knowledgePointArray!=null){
			//推送结果
			RabbitMQProducerUtil.sendMsgToQuery(MQConstants.Topic_demo_knowledge_point, knowledgePointArray);
		}
	}
	
}
