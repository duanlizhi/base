package com.xcj.admin.service.impl.questionLibaray;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.questionLibaray.QuestionLibarayDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareKp;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.WbtSbtKp;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.service.questionLibaray.QuestionLibarayService;
import com.xcj.admin.xcjenum.XMLTypes;
import com.xcj.common.base.GenerateNumber;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;

/**
 *
  * <b>function:</b>  questionLibaray
  * @package com.xcj.admin.service.impl.questionLibaray.
  * @fileName com.xcj.admin.*
  * @createDate Wed Feb 11 11:48:19 CST 2015
  * @author name yy.niu
 */
@Service("questionLibarayServiceImpl")
public class QuestionLibarayServiceImpl extends BaseServiceImpl implements QuestionLibarayService{
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionLibarayServiceImpl.class);

	@Resource(name ="questionLibarayDaoImpl")
	private QuestionLibarayDao questionLibarayDao;
	

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name yy_niu
	 */
	public List<CourseWare> getAllList() throws Exception {
		return questionLibarayDao.getAllList();
	}
	
	/**
     * <b>function:</b>根据courseNumber
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean queryStyResult(String courseNumber,String id)throws Exception{
		return questionLibarayDao.queryStyResult(courseNumber,id);
	}
	
	/**
	 * <b>function:</b>根据id查询CourseWare数据返回实体
	 * @project xcjedu
	 * @package com.xcj.api.controller.course
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public CourseWare queryCourseNumbers(String courseNumber)throws Exception{
		return questionLibarayDao.queryCourseNumbers(courseNumber);
	}
	
	
	/**
     * <b>function:</b>删除wbt课件、测评、拆装
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean deletewbtCourseWareAndTestAndDismantle (String ids)throws Exception{
		questionLibarayDao.deleteCourseById(DomainCourseWare.class,"courseWareId",ids); 
		deleteWbtSbtKp(ids);
		questionLibarayDao.deleteCourseById(WbtScores.class,"courseWareId",ids);
		questionLibarayDao.deleteCourseById(CourseWareTool.class,"courseWareId",ids);
		questionLibarayDao.deleteCourseById(CourseWareParts.class,"courseWareId",ids);
		questionLibarayDao.deleteCourseById(CourseWareAction.class,"courseWareId",ids);
		questionLibarayDao.deleteEntityById(CourseWare.class, ids);
		questionLibarayDao.deleteCourseById(CourseWareKp.class,"courseWareId",ids);
		return true;
	}
	
	/**
     * <b>function:</b>删除sbt课程
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean deleteSbtCourseWare(String id)throws Exception{
		deleteSbtFaults(id);
		deleteSbtcourseQuestion(id);
		questionLibarayDao.deleteCourseById(WbtScores.class,"courseWareId",id);
		questionLibarayDao.deleteCourseById(DomainCourseWare.class,"courseWareId",id); 
		questionLibarayDao.deleteCourseById(SbtFault.class,"courseWareId",id); 
		questionLibarayDao.deleteCourseById(CourseWareTool.class,"courseWareId",id);
		questionLibarayDao.deleteCourseById(CourseWareParts.class,"courseWareId",id);
		questionLibarayDao.deleteCourseById(CourseWareAction.class,"courseWareId",id);
		questionLibarayDao.deleteEntityById(CourseWare.class, id);
		questionLibarayDao.deleteCourseById(CourseWareKp.class,"courseWareId",id);
		return true;
	}
	
	public void deleteSbtScores(String id)throws Exception{
		List<SbtScores> list = questionLibarayDao.querySbtScores(id);
		if(list!=null){
			SbtScores sss = null;
			for(int i=0;i<list.size();i++){
				sss = list.get(i);
				questionLibarayDao.deleteCourseById(WbtSbtKp.class,"sbtWbtId", sss.getId());
			}
		}
	}
	
	/**
	 * 删除 WbtSbtKp中数据
	 * @param id
	 * @throws Exception
	 */
	public void deleteSbtFaults(String id)throws Exception{
		List<SbtFault> list = questionLibarayDao.querySbtFault(id);
		if(list!=null){
			SbtFault sft = null;
			for(int i=0;i<list.size();i++){
				sft = list.get(i);
				questionLibarayDao.deleteCourseById(WbtSbtKp.class,"sbtWbtId", sft.getId());
				deleteSbtScores(sft.getId().toString());
			}
		}
	}
	
	/**
     * <b>function:</b> 删除WbtSbtKp数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public void deleteWbtSbtKp(String id)throws Exception{
		List<WbtScores> list = questionLibarayDao.querWbtScores(id);
		if(list!=null){
			WbtScores wsKp = null;
			for(int i=0;i<list.size();i++){
				wsKp = list.get(i);
				questionLibarayDao.deleteCourseById(WbtSbtKp.class,"sbtWbtId", wsKp.getId());
			}
		}
	}
	
	
	/**
	 * 
		 * 
	     * <b>function:</b>删除sbt数据
	     * @project 
	     * @package 
	     * @fileName 
	     * @createDate 
	     * @author yy.niu
	 */
	public boolean deleteSbtcourseQuestion(String id)throws Exception{
		List<SbtFault> sbtId = questionLibarayDao.querySbtFaultKey(id);
		if(sbtId!=null){
			for(int i=0;i<sbtId.size();i++){
				SbtFault sbt = sbtId.get(i);
				questionLibarayDao.deleteCourseById(SbtQuestion.class,"sbtFaultId",sbt.getId());
				questionLibarayDao.deleteCourseById(SbtScores.class,"sbtFaultId",sbt.getId());
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * 
     * <b>function:</b> 删除sbt故障数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean deleteSbtFault(String id)throws Exception{
		//SbtFault，SbtQuestion，SbtScores
		List<SbtFault> sbtId = questionLibarayDao.querySbtFaultKey(id);
		if(sbtId!=null){
			for(int i=0;i<sbtId.size();i++){
				SbtFault sbt = sbtId.get(i);
				questionLibarayDao.deleteCourseById(SbtQuestion.class,"sbtFaultId",sbt.getId());
				questionLibarayDao.deleteCourseById(SbtScores.class,"sbtFaultId",sbt.getId());
			}
		}else{
			return false;
		}
		questionLibarayDao.deleteEntityById(SbtFault.class, id); 
		return true;
	}
	
	/**
     * <b>function:</b>根据courseNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean queryCourseNumber(String courseNumber) throws Exception{
		return questionLibarayDao.queryCourseNumber(courseNumber);
	}
	
	/**
     * <b>function:</b>根据faultNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean queryFaultNumber(String faultNumber)throws Exception{
		return questionLibarayDao.queryFaultNumber(faultNumber);
	}
	
	
	/**
     * <b>function:</b>添加xml数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @updateDate 2015-03-23 11:56:23
     * @updateAuthor hehujun
     * @author yy.niu
	 */
	public String saveData(String fileType,String fielUrl,String courseNumber,String faultNumber,String uploadPath)throws Exception{
		boolean sbtFault = false;//sbtFault是否存在
		boolean emuFault = false;//emuFault是否存在
		boolean courseWare = false;//courseNumber是否存在
		String isSuccess = null;
		if(fileType.equals(XMLTypes.EMUFAULT.getType())){
			//如果是emu故障类型，emu故障编号为空 删除上传的问题 返回错误
			emuFault = queryFaultNumber(faultNumber);
			if(emuFault){
				// 调用sbt故障方法 有些类型需要判断
 				isSuccess = saveEmuFault(fielUrl,uploadPath);
				if(!isSuccess.equals("0")){
					return isSuccess;
				}
			}else{
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		} else if (fileType.equals(XMLTypes.SBTFAULT.getType())){
			//如果是sbt故障类型，sbt故障编号为空 删除上传的问题 返回错误
			sbtFault = queryFaultNumber(faultNumber);
			if(sbtFault){
				// 调用sbt故障方法 有些类型需要判断
 				isSuccess = saveSbtFault(fielUrl,uploadPath);
				if(!isSuccess.equals("0")){
					return isSuccess;
				}
			}else{
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		}else{
			 courseWare = queryCourseNumber(courseNumber);
			 if(courseWare){
				 if(fileType.equals(XMLTypes.SBTCOURSEWARE.getType())){
					// 调用sbt课件
					isSuccess = saveSbtCourseWare(fielUrl,uploadPath);
				 	if(!isSuccess.equals("0")){
						return isSuccess;
					}
				}else if(fileType.equals(XMLTypes.WBTTESTS.getType())){
					//调用wbt测评
					isSuccess = saveWbtTests(fielUrl,uploadPath);
					if(!isSuccess.equals("0")){
						return isSuccess;
					}
				}else if(fileType.equals(XMLTypes.WBTCOURSEWARE.getType())){
					// 调用wbt课件
					isSuccess = saveWebtCourseWare(fielUrl,uploadPath);
					if(!isSuccess.equals("0")){
						return isSuccess;
					}
				}else if(fileType.equals(XMLTypes.DMCCOURSEWARE.getType())){
					// 调用拆装保养
					isSuccess = saveDisassemblingCourseDescription(fielUrl,uploadPath);
					if(!isSuccess.equals("0")){
						return isSuccess;
					}
				}else if(fileType.equals(XMLTypes.EMUCOURSEWARE.getType())){
					// 调用软模拟器
					isSuccess = saveEMUCourseWare(fielUrl,uploadPath);
					if(!isSuccess.equals("0")){
						return isSuccess;
					}
				}  
			}else{
				StringUtil.deleteFile(fielUrl);
				return "6";
			}
		}
		return "0";
	}
	
	/**
	 * 
	   * <b>function:</b> 保存EMU课件
	   * @project base
	   * @package com.xcj.admin.service.impl.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-23 上午11:39:44
	   * @return String
	   * @author hehujun
	 */
	public String saveEMUCourseWare(String fielUrl,String uploadPath)throws Exception{
		return this.saveSbtCourseWare(fielUrl,uploadPath);
	}
	
	/**
     * <b>function:</b>读取sbt课件xml添加sbt数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveSbtCourseWare(String fielUrl,String uploadPath)throws Exception{
		
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
	        Document xmlDoc = sax.read(new File(fielUrl)); 
	        String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion"); 
	        Element root = xmlDoc.getRootElement();//根节点  
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
	        //Element passScore = (Element) root.element("pass_score"); 
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        //isNull = StringUtil.isType(type);
	        if(isNull){
	        	return "1";//courseNumber或type不能为空
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
	        courseWare.setCategory(2);
	        //courseWare.setPassScore(StringUtil.isNullEmtps(passScore));
	        courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setEntry(StringUtil.isNullEmtp(entry));
	        courseWare.setFileurl(StringUtil.interceptString(uploadPath));
	        courseWare.setIsEnable(1);
	        
	        data.put("courseWare", courseWare);
	         
	        courseWare = questionLibarayDao.saveCourseWare(courseWare);
	        List<?> actions = root.selectNodes("//actions"); 
	        JSONObject actionsObj = new JSONObject();
	        for(int s = 0; s < actions.size();s++){  
	        	Element actionsName = (Element)actions.get(s);  
	        	JSONArray actionArr = new JSONArray();
	        	int rank = 0;
	            for(Iterator<?> iterInner = actionsName.elementIterator(); iterInner.hasNext();){
	            	Element actionName = (Element) iterInner.next();
	            	JSONObject actionObj = new JSONObject();
	            	String actionId =  actionName.attributeValue("id");
	            	String actionDiscription = actionName.attributeValue("discription");
	            	CourseWareAction courseWareAction = new CourseWareAction();
	            	courseWareAction.setActionId(actionId);
	            	courseWareAction.setDescription(actionDiscription);
	            	courseWareAction.setCode(GenerateNumber.getCourseWareCode());
	            	courseWareAction.setCreateDate(DateUtil.getCurrentTimeByDate());
	            	courseWareAction.setModifyDate(DateUtil.getCurrentTimeByDate());
	            	courseWareAction.setRank(rank++);
	            	courseWareAction.setType("1");
	            	courseWareAction.setCourseWareId(courseWare.getId());
	            	actionObj.put("id", actionId);
	            	actionObj.put("discription", actionDiscription);
	            	actionArr.add(actionObj);
	            	questionLibarayDao.save(courseWareAction);
	            }
	            actionsObj.put("action", actionArr);
	        }
	        data.put("actions", actionsObj);
	        
	        List<?> parts = root.selectNodes("//parts"); 
	        JSONObject partObject = new JSONObject();
	        for(int s = 0; s < parts.size();s++){  
	        	Element scoreName = (Element)parts.get(s);
	        	JSONArray parArr = new JSONArray();
	            for(Iterator<?> iterInner = scoreName.elementIterator(); iterInner.hasNext();){
	            	Element partsName = (Element) iterInner.next();
	            	JSONObject partsObj = new JSONObject();
	            	String parId =  partsName.attributeValue("id");
	            	partsObj.put("id", parId);
	            	
	            	JSONArray partArr = new JSONArray();
	            	 int rank = 0;
	            	 for(Iterator<?> iterPart = partsName.elementIterator(); iterPart.hasNext();){
	                 	Element partStateName = (Element) iterPart.next();
	                 	JSONObject part = new JSONObject();
	                 	String stateId =  partStateName.attributeValue("id");
	                 	String stateDiscription = partStateName.attributeValue("discription");
	                 	CourseWareParts courseWareParts = new CourseWareParts();
	                 	courseWareParts.setDescription(stateDiscription);
	                 	courseWareParts.setStateId(stateId);
	                 	part.put("id", stateId);
	                 	part.put("discription", stateDiscription);
	                 	courseWareParts.setCode(GenerateNumber.getCourseWareCode());
	                 	courseWareParts.setType("1");
	                 	courseWareParts.setCreateDate(DateUtil.getCurrentTimeByDate());
	                 	courseWareParts.setModifyDate(DateUtil.getCurrentTimeByDate());
	                 	courseWareParts.setRank(rank++);
	                 	courseWareParts.setCourseWareId(courseWare.getId());
	                 	courseWareParts.setPartId(parId);
	                 	questionLibarayDao.save(courseWareParts);
	                 	partArr.add(part);
	                 }
	            	 partsObj.put("state", partArr);
	            	 parArr.add(partsObj);
	            }
	            partObject.put("part", parArr);
	        }
	        data.put("parts", partObject);
	        
	        
	        List<?> tools = root.selectNodes("//tools");  
	        JSONObject toolsObj = new JSONObject();
	        for(int s = 0; s < tools.size();s++){  
	        	Element toolsName = (Element)tools.get(s);
	        	 JSONArray toolArr = new JSONArray();
	            for(Iterator<?> toolInner = toolsName.elementIterator(); toolInner.hasNext();){
	            	 Element toolName = (Element) toolInner.next();
	            	 JSONObject toolObj = new JSONObject();
	            	 String toolId =  toolName.attributeValue("id");
	            	 toolObj.put("id", toolId);
	            	 JSONArray stateArr = new JSONArray();
	            	 int rank =0;
	            	 for(Iterator<?> iterPart = toolName.elementIterator(); iterPart.hasNext();){
	                 	Element toolStateName = (Element) iterPart.next();
	                 	JSONObject stateObj = new JSONObject();
	                 	String stateId =  toolStateName.attributeValue("id");
	                 	String stateDiscription = toolStateName.attributeValue("discription");
	                 	stateObj.put("id", stateId);
	                 	stateObj.put("discription", stateDiscription);
	                 	CourseWareTool courseWareTool = new CourseWareTool();
	                 	courseWareTool.setDescription(stateDiscription);
	                 	courseWareTool.setCode(GenerateNumber.getCourseWareCode());
	                 	courseWareTool.setCreateDate(DateUtil.getCurrentTimeByDate());
	                 	courseWareTool.setModifyDate(DateUtil.getCurrentTimeByDate());
	                 	courseWareTool.setType("1");
	                 	courseWareTool.setCourseWareId(courseWare.getId());
	                 	courseWareTool.setRank(rank++);
	                 	courseWareTool.setToolId(toolId);
	                 	courseWareTool.setStateId(stateId);
	                 	questionLibarayDao.save(courseWareTool);
	                 	stateArr.add(stateObj);
	                 }
	            	 toolObj.put("state", stateArr);
	            	 toolArr.add(toolObj);
	            	}
	            	toolsObj.put("tool", toolArr);
	        	}
	        data.put("tools", toolsObj);
	        obj.put("sbtCourseWare", data);
	        boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
	        if(isSuccess){
	        	//text写入失败调用删除方法
	        	this.deleteSbtCourseWare(courseWare.getId().toString());
	        	return "2";//text写入失败
	        	
	        }
		} catch (DocumentException e) {
			logger.error(QuestionLibarayServiceImpl.class.getName()+" saveSbtCourseWare() exception: "+e.getMessage());
		} 
		return "0";
	}
	
	/**
	 * 
	   * <b>function:</b> 保存emu故障
	   * @project base
	   * @package com.xcj.admin.service.impl.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-23 上午11:55:08
	   * @return String
	   * @author hehujun
	 */
	public String saveEmuFault(String fielUrl,String uploadPath)throws Exception{
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();//根节点  
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
	        
	        //查询知识点
	        Element kp = (Element) root.element("kp");
	         
	        CourseWare courseWare = questionLibarayDao.queryCourseNumbers(StringUtil.isNullEmtp(courseNumber));
	        if(courseWare==null){
	        	return "5";//请先上传sbt课件
	        }
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        isNull = StringUtil.isbool(faultNumber);
	        isNull = StringUtil.isbool(type);
	        if(isNull){
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
	        
	        sbtFault.setCourseWareId(courseWare.getId());
	        data.put("sbtFault", sbtFault);
	        questionLibarayDao.save(sbtFault);
	        //添加知识点
	        boolean isKp = savesbtKp(kp,sbtFault.getId(),4);
	        if(isKp){
	        	//知识点添加出现问题是 删除数据
	        	this.deleteSbtFault(sbtFault.getId().toString());
	        	return "3";//知识点没有关联
	        }
	        
	        JSONArray questionArr = new JSONArray();
	        List<?> questions = root.selectNodes("//questions");  
	        for(int s = 0; s < questions.size();s++){  
	        	Element questionsName = (Element)questions.get(s); 
	        	int rank = 0;
	            for(Iterator<?> iterInner = questionsName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject quertionData = new JSONObject();
	            	String qid =  questionName.attributeValue("qid");
	            	String discription = questionName.attributeValue("discription");
	            	SbtQuestion sbtQuestion = new SbtQuestion();
	            	sbtQuestion.setQuestionId(qid);
	            	sbtQuestion.setDescription(discription);
	            	sbtQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
	            	sbtQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
	            	sbtQuestion.setRank(rank++);
	            	sbtQuestion.setSbtFaultId(sbtFault.getId());
	            	questionLibarayDao.save(sbtQuestion);
	            	quertionData.put("qid", qid);
	            	quertionData.put("discription", discription);
	            	questionArr.add(quertionData);
	            }
	        }
	        data.put("questions", questionArr);
	        
	        JSONArray scoreArr = new JSONArray();
	        List<?> score = root.selectNodes("//scores");  
	        for(int s = 0; s < score.size();s++){  
	        	Element scoresName = (Element)score.get(s);  
	        	int rank = 0;
	            for(Iterator<?> iterInner = scoresName.elementIterator(); iterInner.hasNext();){
	            	Element scoreName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String id =  scoreName.attributeValue("id");
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
	            	questionLibarayDao.save(sbtScores);
	            	//添加知识点数据
	            	boolean isKps = saveSbtKPs(scourKp,sbtScores.getId(),2);
	            	if(isKps){
	            		//知识点添加出现问题是 删除数据
	    	        	this.deleteSbtFault(sbtFault.getId().toString());
	            		return "4";//分数里的知识点没有关联
	            	}
	            }
	        }
	        data.put("score", scoreArr);
	        obj.put("wbtTests", data);
	        boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
	        if(isSuccess){
	        	//知识点添加出现问题是 删除数据
	        	this.deleteSbtFault(sbtFault.getId().toString());
	        	return "2";
	        }
		} catch (DocumentException e) {
			logger.error(QuestionLibarayServiceImpl.class.getName()+" saveSbtFault() exception: "+e.getMessage());
		}  
		return "0";
	}
	
	/**
     * <b>function:</b> 读取sbt故障添加sbt数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveSbtFault(String fielUrl,String uploadPath)throws Exception{
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			Document xmlDoc;
			xmlDoc = sax.read(new File(fielUrl));
			String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion");
			Element root = xmlDoc.getRootElement();//根节点  
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
	        
	        //查询知识点
	        Element kp = (Element) root.element("kp");
	         
	        CourseWare courseWare = questionLibarayDao.queryCourseNumbers(StringUtil.isNullEmtp(courseNumber));
	        if(courseWare==null){
	        	return "5";//请先上传sbt课件
	        }
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        isNull = StringUtil.isbool(faultNumber);
	        isNull = StringUtil.isbool(type);
	        if(isNull){
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
	        
	        sbtFault.setCourseWareId(courseWare.getId());
	        data.put("sbtFault", sbtFault);
	        questionLibarayDao.save(sbtFault);
	        //添加知识点
	        boolean isKp = savesbtKp(kp,sbtFault.getId(),4);
	        if(isKp){
	        	//知识点添加出现问题是 删除数据
	        	this.deleteSbtFault(sbtFault.getId().toString());
	        	return "3";//知识点没有关联
	        }
	        
	        JSONArray questionArr = new JSONArray();
	        List<?> questions = root.selectNodes("//questions");  
	        for(int s = 0; s < questions.size();s++){  
	        	Element questionsName = (Element)questions.get(s); 
	        	int rank = 0;
	            for(Iterator<?> iterInner = questionsName.elementIterator(); iterInner.hasNext();){
	            	Element questionName = (Element) iterInner.next();
	            	JSONObject quertionData = new JSONObject();
	            	String qid =  questionName.attributeValue("qid");
	            	String discription = questionName.attributeValue("discription");
	            	SbtQuestion sbtQuestion = new SbtQuestion();
	            	sbtQuestion.setQuestionId(qid);
	            	sbtQuestion.setDescription(discription);
	            	sbtQuestion.setModifyDate(DateUtil.getCurrentTimeByDate());
	            	sbtQuestion.setCreateDate(DateUtil.getCurrentTimeByDate());
	            	sbtQuestion.setRank(rank++);
	            	sbtQuestion.setSbtFaultId(sbtFault.getId());
	            	questionLibarayDao.save(sbtQuestion);
	            	quertionData.put("qid", qid);
	            	quertionData.put("discription", discription);
	            	questionArr.add(quertionData);
	            }
	        }
	        data.put("questions", questionArr);
	        
	        JSONArray scoreArr = new JSONArray();
	        List<?> score = root.selectNodes("//scores");  
	        for(int s = 0; s < score.size();s++){  
	        	Element scoresName = (Element)score.get(s);  
	        	int rank = 0;
	            for(Iterator<?> iterInner = scoresName.elementIterator(); iterInner.hasNext();){
	            	Element scoreName = (Element) iterInner.next();
	            	JSONObject scoreData = new JSONObject();
	            	String id =  scoreName.attributeValue("id");
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
	            	questionLibarayDao.save(sbtScores);
	            	//添加知识点数据
	            	boolean isKps = saveSbtKPs(scourKp,sbtScores.getId(),2);
	            	if(isKps){
	            		//知识点添加出现问题是 删除数据
	    	        	this.deleteSbtFault(sbtFault.getId().toString());
	            		return "4";//分数里的知识点没有关联
	            	}
	            }
	        }
	        data.put("score", scoreArr);
	        obj.put("wbtTests", data);
	        boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
	        if(isSuccess){
	        	//知识点添加出现问题是 删除数据
	        	this.deleteSbtFault(sbtFault.getId().toString());
	        	return "2";
	        }
		} catch (DocumentException e) {
			logger.error(QuestionLibarayServiceImpl.class.getName()+" saveSbtFault() exception: "+e.getMessage());
		}  
		return "0";
	}
	
	/**
     * <b>function:</b> 查询知识点数据匹配上保存 wbt
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean saveWbtKp(Element kp,Integer courseId)throws Exception{
		String kps = StringUtil.isNullEmtp(kp);
        String[] kpName = kps.split(",");
        if(kpName.length>0){
        	 for(int k=0;k<kpName.length;k++){
             	KnowledgePoint knowLedge = questionLibarayDao.queryKonwName(kpName[k]);
             	if(knowLedge!=null){
     	        	//添加数据卡
     	        	CourseWareKp courseKp = new CourseWareKp();
     	        	courseKp.setKpId(knowLedge.getId());
     	        	courseKp.setCourseWareId(courseId);
     	        	questionLibarayDao.save(courseKp);
     	        	
     	        }else{
     	        	return true;
     	        }
             }
        }else{
        	return true;
        }
        return false;
	      
	}
	

	/**
     * <b>function:</b> 查询知识点数据匹配上保存 wbt
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean saveWbtKPs(String kp,Integer courseId,Integer type)throws Exception{
		 
    	KnowledgePoint knowLedge = questionLibarayDao.queryKonwName(kp);
    	if(knowLedge!=null){
        	//添加数据卡
    		WbtSbtKp wbtSbtKp = new WbtSbtKp();
        	wbtSbtKp.setKpId(knowLedge.getId());
        	wbtSbtKp.setSbtWbtId(courseId);
        	wbtSbtKp.setType(type);
        	questionLibarayDao.save(wbtSbtKp);
	    }else{
	    	return true;
	    }
    	return false;
	}
	
	/**
     * <b>function:</b> 查询知识点数据匹配上保存 wbt
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean savesbtKp(Element kp,Integer courseId,Integer type)throws Exception{
		String kps = StringUtil.isNullEmtp(kp);
        String[] kpName = kps.split(",");
        if(kpName.length>0){
        	 for(int k=0;k<kpName.length;k++){
             	KnowledgePoint knowLedge = questionLibarayDao.queryKonwName(kpName[k]);
             	if(knowLedge!=null){
     	        	//添加数据卡
             		WbtSbtKp wbtSbtKp = new WbtSbtKp();
                	wbtSbtKp.setKpId(knowLedge.getId());
                	wbtSbtKp.setSbtWbtId(courseId);
                	wbtSbtKp.setType(type);
                	questionLibarayDao.save(wbtSbtKp);
     	        	
     	        }else{
     	        	return true;
     	        }
             }
        }else{
        	return true;
        }
        return false;
	      
	}
	
	/**
     * <b>function:</b> 查询知识点数据匹配上保存 sbt
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean saveSbtKPs(String kp,Integer courseId,Integer type)throws Exception{
		 
    	KnowledgePoint knowLedge = questionLibarayDao.queryKonwName(kp);
    	if(knowLedge!=null){
        	//添加数据卡
        	WbtSbtKp wbtSbtKp = new WbtSbtKp();
        	wbtSbtKp.setKpId(knowLedge.getId());
        	wbtSbtKp.setSbtWbtId(courseId);
        	wbtSbtKp.setType(type);
        	questionLibarayDao.save(wbtSbtKp);
	    }else{
	    	return true;
	    }
    	return false;
	}
	
	/**
     * <b>function:</b>添加wbt测评xml
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveWbtTests(String fielUrl,String uploadPath)throws Exception{
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
	        Document xmlDoc = sax.read(new File(fielUrl));
	        String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion");
	        Element root = xmlDoc.getRootElement();//根节点  
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
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        //isNull = StringUtil.isType(type);
	        if(isNull){
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
	        courseWare.setCategory(2);
	        courseWare.setIsEnable(1);
	        questionLibarayDao.save(courseWare);
	        //添加知识点
	        boolean isKp = saveWbtKp(kp,courseWare.getId());
	        if(isKp){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "3";
	        }
	        
	        data.put("courseWare", courseWare);
	        
	        JSONArray chaptArr = new JSONArray();
	        List<?> listChapter = root.selectNodes("//chapter");  
	        for(int l = 0; l < listChapter.size(); l++){  
	        	JSONObject chapter = new JSONObject();
	        	Element chapterData = (Element)listChapter.get(l);
	        	chapter.put("id", chapterData.attributeValue("id")); 
	        	chapter.put("name", chapterData.attributeValue("name")); 
	        	chapter.put("url", chapterData.attributeValue("url")); 
	        	chapter.put("type", chapterData.attributeValue("type"));
	        	chaptArr.add(chapter);
	        	JSONArray chapterArra = new JSONArray();
	            for(Iterator<?> pageInner = chapterData.elementIterator(); pageInner.hasNext();){
	            	Element page = (Element) pageInner.next();
	            	JSONObject pageData = new JSONObject();
	            	pageData.put("id", page.attributeValue("id")); 
	            	pageData.put("url", page.attributeValue("url")); 
	            	pageData.put("isCommit", page.attributeValue("isCommit")); 
	            	pageData.put("canBook", page.attributeValue("canBook")); 
	            	pageData.put("canTool", page.attributeValue("canTool")); 
	            	pageData.put("canFailure", page.attributeValue("canFailure")); 
	            	pageData.put("canHistory", page.attributeValue("canHistory")); 
	            	chapterArra.add(pageData);
	            }
	            chapter.put("page", chapterArra);
	        }  
	        data.put("chapter", chaptArr);
	        JSONObject menuj = new JSONObject();
	        JSONArray menujs = new JSONArray();
	        List<?> menus = root.selectNodes("//menu");  
	        for(int m = 0; m < menus.size(); m++){  
	        	Element menu = (Element)menus.get(m);  
	        	menuj.put("id", menu.attributeValue("id")); 
	        	menuj.put("name", menu.attributeValue("name")); 
	        	menuj.put("url", menu.attributeValue("url")); 
	        	menuj.put("type", menu.attributeValue("type")); 
	            for(Iterator<?> iterInner = menu.elementIterator(); iterInner.hasNext();){
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
	        for(int s = 0; s < scores.size(); s++){  
	        	Element score = (Element)scores.get(s); 
	        	int rank = 0;
	            for(Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();){
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
	            	 questionLibarayDao.save(wbtScores);
	            	 //添加知识点数据
	            	 boolean isKps = saveWbtKPs(questionKp,wbtScores.getId(),3);
	            	 if(isKps){
	            		//知识点 添加失败删除数据
	     	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	            		return "4";
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
	        if(isSuccess){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "2";
	        }
	    } catch (DocumentException e) {  
	    	logger.error(QuestionLibarayServiceImpl.class.getName()+" saveWbtTests() exception: "+e.getMessage());
	    }  
		return "0";
	}
	
	/**
     * <b>function:</b>添加wbt课件
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveWebtCourseWare(String fielUrl,String uploadPath)throws Exception{
		
		try {
			SAXReader sax = new SAXReader();
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
	        Document xmlDoc = sax.read(new File(fielUrl)); 
	        String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion");
	        Element root = xmlDoc.getRootElement();//根节点  
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
	        Element sumScore = (Element) root.element("sumScore"); 
	        Element client = (Element) root.element("client"); 
	        Element entry = (Element) root.element("entry");  
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        //isNull = StringUtil.isType(type);
	        if(isNull){
	        	return "1";
	        }
	         
	        Element kp = (Element) root.element("kp"); 
	        CourseWare courseWare = new CourseWare();
	        courseWare.setModel(schema);
	        courseWare.setModelVersion(schemaversion);
	        String nameText = StringUtil.isNullEmtp(name);
	        String courseNumberText = StringUtil.isNullEmtp(courseNumber);
	        String purposeText = StringUtil.isNullEmtp(purpose);
	        String requireText = StringUtil.isNullEmtp(require);
	        String keywordText = StringUtil.isNullEmtp(keyword);
	        String languageText = StringUtil.isNullEmtp(language);
	        String versionText = StringUtil.isNullEmtp(version);
	        String descriptionText = StringUtil.isNullEmtp(description);
	        String clientText = StringUtil.isNullEmtp(client);
	        String entryText = StringUtil.isNullEmtp(entry);
	        String durationText = StringUtil.isNullEmtp(duration);
	        courseWare.setName(nameText);
	        courseWare.setCourseNumber(courseNumberText);
	        courseWare.setType("2");
	        courseWare.setPurpose(purposeText);
	        courseWare.setRequires(requireText);
	        courseWare.setKeyword(keywordText);
	        courseWare.setLanguage(languageText);
	        courseWare.setVersion(versionText);
	        courseWare.setDescription(descriptionText);
	        courseWare.setClient(clientText);
	        courseWare.setEntry(entryText);
	        courseWare.setDuration(durationText);
	        courseWare.setIsEnable(1);
	        courseWare.setCategory(2);
	        courseWare.setSumScore(StringUtil.isNullEmtps(sumScore));
	        courseWare.setDifficulty(StringUtil.isNullzh(difficulty));
	        courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setFileurl(StringUtil.interceptString(uploadPath));
	        data.put("courseWare", courseWare);
	        questionLibarayDao.save(courseWare);
	      //添加知识点
	        boolean isKp = saveWbtKp(kp,courseWare.getId());
	        if(isKp){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "3";
	        }
	       
	        JSONArray chaptArr = new JSONArray();
	        List<?> listChapter = root.selectNodes("//chapter");  
	        for(int l = 0; l < listChapter.size(); l++){  
	        	JSONObject chapter = new JSONObject();
	        	Element chapterData = (Element)listChapter.get(l);
	        	chapter.put("id", chapterData.attributeValue("id")); 
	        	chapter.put("name", chapterData.attributeValue("name")); 
	        	chapter.put("url", chapterData.attributeValue("url")); 
	        	chapter.put("type", chapterData.attributeValue("type"));
	        	chaptArr.add(chapter);
	        	JSONArray chapterArra = new JSONArray();
	            for(Iterator<?> pageInner = chapterData.elementIterator(); pageInner.hasNext();){
	            	Element page = (Element) pageInner.next();
	            	JSONObject pageData = new JSONObject();
	            	pageData.put("id", page.attributeValue("id")); 
	            	pageData.put("url", page.attributeValue("url")); 
	            	pageData.put("isCommit", page.attributeValue("isCommit")); 
	            	pageData.put("canBook", page.attributeValue("canBook")); 
	            	pageData.put("canTool", page.attributeValue("canTool")); 
	            	pageData.put("canFailure", page.attributeValue("canFailure")); 
	            	pageData.put("canHistory", page.attributeValue("canHistory")); 
	            	chapterArra.add(pageData);
	            }
	            chapter.put("page", chapterArra);
	        }  
	        data.put("chapter", chaptArr);
	        JSONObject menuj = new JSONObject();
	        JSONArray menujs = new JSONArray();
	        List<?> menus = root.selectNodes("//menu");  
	        for(int m = 0; m < menus.size(); m++){  
	        	Element menu = (Element)menus.get(m);  
	        	menuj.put("id", menu.attributeValue("id")); 
	        	menuj.put("name", menu.attributeValue("name")); 
	        	menuj.put("url", menu.attributeValue("url")); 
	        	menuj.put("type", menu.attributeValue("type")); 
	            for(Iterator<?> iterInner = menu.elementIterator(); iterInner.hasNext();){
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
	        for(int s = 0; s < scores.size(); s++){  
	        	Element score = (Element)scores.get(s); 
	        	int rank = 0;
	            for(Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();){
	            	Element question = (Element) iterInner.next();
	            	JSONObject questionObj = new JSONObject();
	            	 String id = question.attributeValue("id");
	            	 String pageId = question.attributeValue("pageid");
	            	 String questionType = question.attributeValue("type");
	            	 String rate = question.attributeValue("rate");
	            	 WbtScores wbtScores = new WbtScores();
	            	 wbtScores.setCourseWareId(courseWare.getId());
	            	 wbtScores.setCreateDate(DateUtil.getCurrentTimeByDate());
	            	 wbtScores.setModifyDate(DateUtil.getCurrentTimeByDate());
	            	 wbtScores.setPageid(pageId);
	            	 wbtScores.setQuestionId(id);
	            	 wbtScores.setRank(rank++);
	            	 wbtScores.setRate(rate);
	            	 wbtScores.setType(questionType);
	            	 questionLibarayDao.save(wbtScores);
	            	 questionObj.put("id", id);
	            	 questionObj.put("pageid", pageId);
	            	 questionObj.put("type", questionType);
	            	 questionObj.put("rate", rate);
	            	 scoreArr.add(questionObj);
	            }
	        }
	        data.put("score", scoreArr);
	        obj.put("wbtCourseWare", data);
	        boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
	        if(isSuccess){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "2";
	        }
		} catch (DocumentException e) {  
			logger.error(QuestionLibarayServiceImpl.class.getName()+" saveWebtCourseWare() exception: "+e.getMessage());
	    } 
		return "0";
	}
	
	/**
     * <b>function:</b> 读取拆装课程描述xml
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveDisassemblingCourseDescription(String fielUrl,String uploadPath)throws Exception{
		
		try {
			JSONObject data = new JSONObject();
			JSONObject obj = new JSONObject();
			SAXReader sax = new SAXReader();  
	        Document xmlDoc = sax.read(new File(fielUrl));
	        String[] suffixs = fielUrl.split(".xml");
			String txtUrl = suffixs[0];
			List<?> list = xmlDoc.selectNodes("//metadata");  
	        Element pro = (Element)list.get(0);  
	        String schema = pro.elementText("schema");
	        String schemaversion= pro.elementText("schemaversion");
	        Element root = xmlDoc.getRootElement();//根节点  
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
	        Element sumScore = (Element) root.element("sumScore"); 
	        Element client = (Element) root.element("client"); 
	        Element entry = (Element) root.element("entry");  
	        Element duration = (Element) root.element("duration");
	        Element difficulty = (Element) root.element("difficulty");
	        boolean isNull = false;
	        isNull = StringUtil.isbool(type);
	        isNull = StringUtil.isbool(courseNumber);
	        //isNull = StringUtil.isType(type);
	        if(isNull){
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
	        courseWare.setCategory(2);
	        courseWare.setIsEnable(1);
	        courseWare.setCreateDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setModifyDate(DateUtil.getCurrentTimeByDate());
	        courseWare.setFileurl(StringUtil.interceptString(uploadPath));
	        data.put("courseWare", courseWare);
	       
	        questionLibarayDao.save(courseWare);
	        //添加知识点
	        boolean isKp = saveWbtKp(kp,courseWare.getId());
	        if(isKp){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "3";
	        }
	        
	        JSONObject actionObj = new JSONObject();
	        List<?> actions = root.selectNodes("//actions");
	        for(int a=0;a<actions.size();a++){
	        	Element action = (Element)actions.get(a);
	        	JSONArray actionsArrj = new JSONArray();
	        	int rank = 0;
	        	for(Iterator<?> iterAction = action.elementIterator(); iterAction.hasNext();){
	        		Element actionData = (Element) iterAction.next();
	        		
	        		
	        		String actionId = actionData.attributeValue("id");
	        		String actionDiscription = actionData.attributeValue("discription");
	        		
	        		JSONObject actionsData = new JSONObject();
	        		actionsData.put("id", actionId);
	        		actionsData.put("discription", actionDiscription);
	        		actionsArrj.add(actionsData);
	        		CourseWareAction courseWareAction = new CourseWareAction();
	        		courseWareAction.setActionId(actionId);
	        		courseWareAction.setDescription(actionDiscription);
	        		courseWareAction.setCourseWareId(courseWare.getId());
	        		courseWareAction.setType("4");
	        		courseWareAction.setCode(GenerateNumber.getCourseWareCode());
	        		courseWareAction.setCreateDate(DateUtil.getCurrentTimeByDate());
	        		courseWareAction.setModifyDate(DateUtil.getCurrentTimeByDate());
	        		courseWareAction.setRank(rank++);
	        		questionLibarayDao.save(courseWareAction);
	        	}
	        	 actionObj.put("action", actionsArrj);
	        }
	        data.put("actions", actionObj);
	        JSONArray parArry = new JSONArray();
	        JSONObject parsData = new  JSONObject();
	        List<?> parts = root.selectNodes("//parts");
	        for(int p=0;p<parts.size();p++){
	        	Element partsData = (Element)parts.get(p);
	        	for(Iterator<?> iterPart = partsData.elementIterator(); iterPart.hasNext();){
	        		Element partData = (Element) iterPart.next();
	        		JSONObject parData = new JSONObject();
	        		String partId = partData.attributeValue("id");
	        		parData.put("id", partId);
	        		parArry.add(parData);
	        		JSONArray stateArry = new JSONArray();
	        		int rank = 0;
	        		for(Iterator<?> iterState = partData.elementIterator(); iterState.hasNext();){
	        			Element state = (Element) iterState.next();
	        			JSONObject stateData = new JSONObject();
	        			String stateId = state.attributeValue("id");
	        			String stateDiscription = state.attributeValue("discription");
	        			stateData.put("id", stateId);
	        			stateData.put("discription", stateDiscription);
	        			CourseWareParts courseWareParts = new CourseWareParts();
	        			courseWareParts.setStateId(stateId);
	        			courseWareParts.setDescription(stateDiscription);
	        			courseWareParts.setRank(rank++);
	        			courseWareParts.setType("4");
	        			courseWareParts.setCreateDate(DateUtil.getCurrentTimeByDate());
	        			courseWareParts.setModifyDate(DateUtil.getCurrentTimeByDate());
	        			courseWareParts.setCourseWareId(courseWare.getId());
	        			courseWareParts.setCode(GenerateNumber.getCourseWareCode());
	        			courseWareParts.setPartId(partId);
	        			courseWareParts.setModifyDate(DateUtil.getCurrentTimeByDate());
	        			questionLibarayDao.save(courseWareParts); 
	        			
	        			stateArry.add(stateData);
	        		}
	        		parData.put("state", stateArry);
	        	}
	        	parsData.put("part", parArry);
	        }
	        data.put("parts", parsData);
	       
	         
	        JSONArray toolArry = new JSONArray();
	        JSONObject toolsData = new  JSONObject();
	        List<?> tools = root.selectNodes("//tools");
	        for(int t=0;t<tools.size();t++){
	        	Element tool = (Element)tools.get(t);
	        	for(Iterator<?> iterTool = tool.elementIterator(); iterTool.hasNext();){
	        		Element toolData = (Element) iterTool.next();
	        		JSONObject toolDatas = new JSONObject();
	        		String toolId = toolData.attributeValue("id");
	        		toolDatas.put("id", toolId);
	        		toolArry.add(toolDatas);
	        		JSONArray stateArry = new JSONArray();
	        		int rank = 0;
	        		for(Iterator<?> iterState = toolData.elementIterator(); iterState.hasNext();){
	        			Element state = (Element) iterState.next();
	        			JSONObject stateData = new JSONObject();
	        			
	        			String stateId = state.attributeValue("id");
	        			String stateDiscription = state.attributeValue("discription");
	        			CourseWareTool courseWareTool = new CourseWareTool();
	        			courseWareTool.setCourseWareId(courseWare.getId());
	        			courseWareTool.setCreateDate(DateUtil.getCurrentTimeByDate());
	        			courseWareTool.setModifyDate(DateUtil.getCurrentTimeByDate());
	        			courseWareTool.setType("4");
	        			courseWareTool.setCode(GenerateNumber.getCourseWareCode());
	        			courseWareTool.setRank(rank++);
	        			courseWareTool.setStateId(stateId);
	        			courseWareTool.setToolId(toolId);
	        			courseWareTool.setDescription(stateDiscription);
	        			questionLibarayDao.save(courseWareTool);
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
	        for(int q=0;q<questions.size();q++){
	        	Element question = (Element)questions.get(q);
	        	for(Iterator<?> iterQuestion = question.elementIterator(); iterQuestion.hasNext();){
	        		Element questionData = (Element) iterQuestion.next();
	        		JSONObject questionDatas = new JSONObject();
	        		String questionIds = questionData.attributeValue("id");
	        		String questionDiscription = questionData.attributeValue("discription");
	        		questionDatas.put("id", questionIds);
	        		questionDatas.put("discription", questionDiscription);
	        		JSONArray answArr = new JSONArray();
	        		for(Iterator<?> iterAnswer  = questionData.elementIterator(); iterAnswer.hasNext();){
	        			Element answer = (Element) iterAnswer.next();
	        			JSONObject answerData = new JSONObject();
	        			String answerId = answer.attributeValue("id");
	        			String answerDiscription = answer.attributeValue("discription");
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
	        for(int s = 0; s < scores.size(); s++){  
	        	Element score = (Element)scores.get(s); 
	        	JSONArray scoreArr = new JSONArray();
	        	int rank = 0;
	            for(Iterator<?> iterInner = score.elementIterator(); iterInner.hasNext();){
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
	            	 questionLibarayDao.save(wbtScores);
	            	//添加知识点数据
	            	 boolean isKps = saveWbtKPs(scoreKp,wbtScores.getId(),4);
	            	 if(isKps){
	            		//知识点 添加失败删除数据
	     	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	            		return "4";
	            	 }
	            }
	            scoreObj.put("score", scoreArr);
	        }
	        data.put("scores", scoreObj);
	        obj.put("dismantle", data);
	        //如果写入失败 返回false
	        boolean isSuccess = StringUtil.writeTxt(txtUrl, obj.toString());
	        if(isSuccess){
	        	//知识点 添加失败删除数据
	        	this.deletewbtCourseWareAndTestAndDismantle(courseWare.getId().toString());
	        	return "2";
	        }
		} catch (DocumentException e) {  
			logger.error(QuestionLibarayServiceImpl.class.getName()+" saveDisassemblingCourseDescription() exception: "+e.getMessage());
	    } 
		return "0";
	}
	
	/**
	 * <b>function:</b>根据id查询CourseWare数据
	 * @project xcjedu
	 * @package com.xcj.api.controller.course  
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public List<CourseWare> queryCourseWareId(String courseNumber)throws Exception{
		return questionLibarayDao.queryCourseWareId(courseNumber);
	}

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
	 */
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> ps,
			CourseWare courseWare) throws Exception {
		return questionLibarayDao.getByCourseWarePage(ps, courseWare);
	}
	
	/**
	 * <b>function:</b> course_ware课件基本信息---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		questionLibarayDao.removeById(id);
	}
	
	/**
	 * <b>function:</b> 
	 * @project base
	 * @package com.xcj.admin.service.impl.course  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-7 上午11:06:49
	 * @return Page<CourseWare>
	 * @author dapeng_wu
	 */
	public Page<CourseWare> getByPage(Page<CourseWare> page, String search) throws Exception{
		Page<CourseWare> pageCourseWare = questionLibarayDao.getByPage(page, new CourseWare(),search);
		return pageCourseWare;
	}
	
	/**
	 * <b>function:</b> 课程库集合
	 * @project base
	 * @package com.xcj.admin.service.impl.course  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-8 上午10:31:41
	 * @return 
	 * @author yy.niu
	 */
	public Page<CourseWare> getByPages(Page<CourseWare> page, String search) throws Exception{
		Page<CourseWare> pageCourseWare = questionLibarayDao.getByPages(page, new CourseWare(),search);
		return pageCourseWare;
	}
	
	/**
	 * 
	 * <b>function:</b> 根据ids找到集合
	 * @project base
	 * @package com.xcj.admin.service.impl.course  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-2-8 上午10:31:41
	 * @return 
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByIds(String ids) throws Exception{
		return questionLibarayDao.getCourseWareByIds(ids);
	}

	/** 
	 * <b>function:</b> 根据courseNumber获取课程信息
	 * CourseWareService
	 * @createDate  2015-2-9 下午05:01:35
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type CourseWare 
	 */ 
	public CourseWare getByCourseNumber(String courseNumber)  throws Exception{
		return questionLibarayDao.getByCourseNumber(courseNumber);
	}
}
