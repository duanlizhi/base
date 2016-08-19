package com.xcj.admin.service.impl.study;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.course.CourseWareDao;
import com.xcj.admin.dao.course.SbtTestScoreDao;
import com.xcj.admin.dao.study.StudyDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.study.StudyAction;
import com.xcj.admin.entity.study.StudyLog;
import com.xcj.admin.entity.vo.StudyHistory;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.study.StudyService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.md5.MD5Uitl;

/**
 * 
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.admin.service.impl.study  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 下午01:18:00 
  * @author hehujun 
  * @email hehujun@126.com
 */
@Service("studyServiceImpl")
public class StudyServiceImpl extends BaseServiceImpl implements StudyService {
	
	@Resource(name="studyDaoImpl")
	private StudyDao studyDao;
	@Resource(name="courseWareDaoImpl")
	private CourseWareDao courseWareDao;
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	@Resource(name = "sbtTestScoreDaoImpl")
	private SbtTestScoreDao sbtTestScoreDao;

	/**
	 * 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:04:30
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	public List<CourseWare> getCourseWares(String domineName) throws Exception {
		return studyDao.getCourseWares(domineName, null);
	}

	/**
	 * 
	   * 获取内容权限 根据域名称 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:04:30
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	public List<CourseWare> getCourseWares(String domineName, String type)
			throws Exception {
		if(StringUtil.isEmptyYl(type)) {
			return getCourseWares(domineName);
		}
		return studyDao.getCourseWares(domineName, type);
	}

	/**
	 * 
	   * 根据用户id 课程号获取 用户学习的信息 
	   * <b>function:</b> getStudyTotalInfos()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:03:59
	   * @return List<TotalStudyInfo>
	   * @author hehujun
	 */
	public StudyHistory getStudyTotalInfos(String userEmail, String courseNum,String faultNumber,String studyFlag)
			throws Exception {
		return studyDao.getStudyTotalInfos(userEmail, courseNum,faultNumber,studyFlag);
	}

	/**
	 * 
	   * 更新 学习数据 记录学习日志
	   * <b>function:</b> addStudyLog()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-4 上午10:31:45
	   * @return boolean
	   * @author hehujun
	 */
	public boolean addStudyLog(StudyLog studyLog) throws Exception {
		return studyDao.save(studyLog);
	}

	/**
	 * 
	   * 根据条件查询StudyScore,并且保存
	   * <b>function:</b> getAndSaveStudyScores()
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 上午09:41:17
	   * @return List<StudyScore>
	   * @author hehujun
	 */
	public List<StudyResult> getAndSaveStudyScores(List<StudyResult> results)
			throws Exception {
		return studyDao.getAndSaveStudyScores(results);
	}
	
	/**
	 * 
	   * <b>function:</b> saveStudyResults
	   * @project base
	   * @package com.xcj.admin.service.impl.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 下午03:00:22
	   * @return void
	   * @author hehujun
	 */
	public void saveStudyResults(List<StudyResult> results) {
		for(StudyResult result:results) {
			studyDao.save(result);
		}
	}
	
	/**
	 * 
	   * <b>function:</b> saveStudyLogs
	   * @project base
	   * @package com.xcj.admin.service.impl.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 下午03:01:00
	   * @return void
	   * @author hehujun
	 */
	public void saveStudyLogs(List<StudyLog> logs) {
		for(StudyLog log:logs) {
			studyDao.save(log);
		}
	}

	/**
	 * 
	   * 获取sessionId
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 上午09:47:05
	   * @return String
	   * @author hehujun
	 */
	public String generateSessionId(String useremail) throws Exception{
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.getCurrentTime());
		builder.append(MD5Uitl.MD5Encode(useremail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString())).toUpperCase();
	}

	/**
	 * 
	   * 判断sessionId是否有效
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 上午10:12:56
	   * @return boolean
	   * @author hehujun
	 */
	public boolean judgeSesssionIdValidate(String sessionId, String useremail)
			throws Exception {
		try {
			if(sessionId.length()!=32){
				return false;
			}
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			return  (sessionId.equals((String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_STUDY_SESSIONID+"_"+useremail)));
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * 
	   * 获取会话时间
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 下午03:28:21
	   * @return int
	   * @author hehujun
	 */
	public int getSessionTime(String useremail) throws Exception {
		MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		String cachetime = (String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"time_"+useremail);
		return (int)(System.currentTimeMillis() - Long.valueOf(cachetime)) / 1000;
	}

	/**
	 * 
	   * 保存studylog
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 下午03:41:10
	   * @return boolean
	   * @author hehujun
	 */
	public boolean save(StudyLog studyLog, String method) throws Exception {
		if ("init".equals(method)) {
			studyLog.setActionTime(0);
			studyLog.setCreateDate(new Date());
			studyLog.setIsComplete(0);
			studyLog.setIsPass(0);
			studyLog.setModifyDate(new Date());
			studyLog.setProgress(0f);
			studyLog.setReceive("");
			studyLog.setSend("");
			studyLog.setScore(0f);
			studyLog.setStartTime(new Date());
		} else if ("commit".equals(method)) {
			// TODO 暂时由课件计算
//			studyLog.setActionTime(getSessionTime(studyLog.getUserEmail()));
			
			studyLog.setCreateDate(new Date());
			studyLog.setModifyDate(new Date());
			studyLog.setSend("");
			studyLog.setStartTime(new Date());
			if (1 == (studyLog.getIsComplete()==null?0:studyLog.getIsComplete().intValue())) {
				studyLog.setCompleteTime(new Date());
			}
			if (1 == (studyLog.getIsPass()==null?0:studyLog.getIsComplete().intValue())) {
				studyLog.setPassTime(new Date());
			}
		}
		return save(studyLog);
	}

	/**
	 * 
	   * <b>function:</b> 获取学习结果id 在保存学习动作或者学习答案之前
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 上午11:14:56
	   * @return StudyResult
	   * @author hehujun
	 */
	public StudyResult getStudyResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail, String sessionId) throws Exception {
		return studyDao.getStudyResultBeforeSaveActionOrAnswer(courseNumer, domainUserName, userEmail, sessionId);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据动作id 获取动作
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午01:34:09
	   * @return CourseWareAction
	   * @author hehujun
	 */
	public CourseWareAction getCourseWareActionByActionId(String actionId) throws Exception {
		return studyDao.getCourseWareActionByActionId(actionId);
	}
	
	/**
	 * 
	 * <b>function:</b>  根据部件id 获取部件
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public CourseWareParts getCourseWarePartsByActionId(String partId) throws Exception {
		return studyDao.getCourseWarePartsByActionId(partId);
	}
	
	/**
	 * 
	 * <b>function:</b>  根据工具id 获取工具
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public CourseWareTool getCourseWareToolByActionId(String partId) throws Exception {
		return studyDao.getCourseWareToolByActionId(partId);
	}
	
	/**
	 * 
	 * <b>function:</b>  获取sbt题
	 * @project base
	 * @package com.xcj.admin.dao.study  
	 * @fileName com.xcj.*** 
	 * @createDate 2015-3-6 下午01:34:09
	 * @return CourseWareAction
	 * @author hehujun
	 */
	public SbtQuestion getSbtQuestionByQuestionId(String questionId) throws Exception {
		return studyDao.getSbtQuestionByQuestionId(questionId);
	}
	
	/**
	 * 
	   * <b>function:</b> 保存学习动作和学习question 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午03:31:29
	   * @return boolean
	   * @author hehujun
	 */
	public boolean saveStudyActionAndQuestion(String characterA, StudyResult studyResult) throws Exception {
		boolean flag = false;

		JSONObject charaA = JSONObject.parseObject(characterA);
		//动作
		JSONArray handles = JSONArray.parseArray(charaA.get("handle").toString());
		if (handles != null) {
			for (int i=0;i<handles.size();i++){
				StudyAction studyAction = new StudyAction();
				studyAction.setCreateDate(new Date());
				studyAction.setRank(i+1);
				String handledId = JSONObject.parseObject(handles.get(i).toString()).getString("id");
				studyAction.setActionId(handledId);
				studyAction.setModifyDate(new Date());
				studyAction.setScore(JSONObject.parseObject(handles.get(i).toString()).getString("score"));
				studyAction.setStudyResultId(studyResult.getId());
				// 动作
				CourseWareAction courseWareAction = this.getCourseWareActionByActionId(handledId);
				if(courseWareAction != null){
					studyAction.setDescription(courseWareAction.getDescription());
				}
				super.save(studyAction);
			}
			flag = true;
		}

		/**
		JSONArray score = JSONArray.parseArray(charaA.get("score").toString());
		if (score != null) {
			// 保存学习答案
			for (int i=0;i<score.size();i++){
				JSONObject tmp = JSONObject.parseObject(String.valueOf(score.get(i)));
				StudyQuestion studyQuestion = new StudyQuestion();
				studyQuestion.setCreateDate(new Date());
				studyQuestion.setModifyDate(new Date());
				studyQuestion.setRank(i+1);
				studyQuestion.setQuestionId(tmp.getString("id"));
				studyQuestion.setStudyResultId(studyResult.getId());
				SbtQuestion sbtQuestion = this.getSbtQuestionByQuestionId(tmp.getString("id"));
				if (null != sbtQuestion && null!=sbtQuestion.getDescription()) {
					studyQuestion.setDescription(sbtQuestion.getDescription());
				}
				studyQuestion.setAnswer(tmp.getString("answer"));
				this.save(studyQuestion);
			}
			flag = true;
		}
		**/
		
		return flag;
	}
	
	/**
	 * 
	   * <b>function:</b> 通过课件编号获取json文件的内容
	   * @project base
	   * @package com.xcj.admin.service.impl.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午04:26:55
	   * @return String
	   * @author dapeng_wu
	 */
	public String getCourseWareJsonNew(HttpServletRequest request,String courseNumber,String faultNumber)throws Exception{
		JSONObject jsonObject = new JSONObject();
		StringBuffer jsonSb = new StringBuffer("");
		CourseWare courseWare = courseWareService.queryCourseNumbers(courseNumber);
		if(courseWare == null){
			return "";
		}
		String FileName=request.getSession().getServletContext().getRealPath("/")+courseWare.getFileurl().substring(0,courseWare.getFileurl().length()-4)+".txt";
		File myFile=new File(FileName);
		if(!myFile.exists()){ 
			return jsonSb.toString();
		}
    	InputStreamReader isr=new InputStreamReader(new FileInputStream(myFile),"UTF-8");
        BufferedReader in = new BufferedReader(isr);
        String str;
        
        while ((str = in.readLine()) != null){
        	jsonSb.append(str);
        }
        in.close();
        
        //如果是sbt或emu就会有故障的xml;
        List<SbtFault> sbtFaultList = courseWareDao.querySbtFault(courseWare.getId().toString());
        if(sbtFaultList == null || sbtFaultList.size() == 0){
        	jsonObject.put(courseWare.getCourseNumber(), jsonSb.toString());
        	return jsonObject.toJSONString();
        }else{
        	jsonObject.put(courseWare.getCourseNumber(), jsonSb.toString());
        	for (SbtFault sbtFault : sbtFaultList) {
        		if(faultNumber.equals(sbtFault.getFaultNumber().toString())){
	        		jsonSb = new StringBuffer("");
		    		FileName=request.getSession().getServletContext().getRealPath("/")+sbtFault.getFileurl().substring(0,sbtFault.getFileurl().length()-4)+".txt";
		    		myFile=new File(FileName);
		    		if(!myFile.exists()){ 
		    			return jsonObject.toJSONString();
		    		}
		        	isr=new InputStreamReader(new FileInputStream(myFile),"UTF-8");
		            in = new BufferedReader(isr);
		            while ((str = in.readLine()) != null){
		            	jsonSb.append(str);
		            }
		            in.close();
		            jsonObject.put(sbtFault.getFaultNumber(), jsonSb.toString());
        		}
			}
        }
        
        List<SbtTestScore> sbtTestScoreList = sbtTestScoreDao.getListByCourseNumber(courseNumber,faultNumber);
        if(sbtTestScoreList != null && sbtTestScoreList.size() >0){
    		JSONObject jsonObj = JSONObject.parseObject(sbtTestScoreList.get(0).getText());
    		JSONArray jsonArray = (JSONArray)JSONObject.parseObject(jsonObj.get("sbtTestScore").toString()).get("score");
    		for (int i = 0; i < jsonArray.size(); i++) {
    			((JSONObject)jsonArray.get(i)).put("answer","");
    		}
    		JSONObject jsonObjNewSbtTestScore = JSONObject.parseObject(jsonObj.get("sbtTestScore").toString());
    		jsonObjNewSbtTestScore.put("score", jsonArray);
    		jsonObj.put("sbtTestScore", jsonObjNewSbtTestScore);
        	jsonObject.put("testScore", jsonObj.toJSONString());
        }
        return jsonObject.toJSONString();
	}
	
	public static void main(String[] args) {
		JSONObject jsonObj = JSONObject.parseObject("{\"sbtTestScore\":{\"courseNumber\":\"SBT_FCO_ES_IG_001\",\"name\":\"诊断碳罐电磁阀故障\",\"question\":[{\"description\":\"\",\"qid\":\"q01\",\"question\":[{\"answer\":[],\"qid\":\"q01_01\",\"rank\":\"1\",\"title\":\"发动机故障灯亮\",\"type\":\"1\"}],\"rank\":\"1\",\"title\":\"故障描述\"},{\"description\":\"\",\"qid\":\"q02\",\"question\":[{\"answer\":[{\"aid\":\"q02_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q02_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q02_01\",\"rank\":\"1\",\"title\":\"冷却液液位\",\"type\":\"1\"},{\"answer\":[{\"aid\":\"q02_02_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q02_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q02_02\",\"rank\":\"2\",\"title\":\"发动机润滑油液位\",\"type\":\"1\"}],\"rank\":\"2\",\"title\":\"启动前检查\"},{\"description\":\"\",\"qid\":\"q03\",\"question\":[{\"answer\":[{\"aid\":\"q03_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q03_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q03_01\",\"rank\":\"1\",\"title\":\"发动机故障灯MIL\",\"type\":\"1\"},{\"answer\":[{\"aid\":\"q03_02_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q03_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q03_02\",\"rank\":\"2\",\"title\":\"发动机启动及运转状况\",\"type\":\"1\"},{\"answer\":[{\"aid\":\"q03_02_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q03_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q03_03\",\"rank\":\"3\",\"title\":\"其他\",\"type\":\"1\"}],\"rank\":\"3\",\"title\":\"故障现象确认\"},{\"description\":\"\",\"qid\":\"q04\",\"question\":[{\"answer\":[{\"aid\":\"q04_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"无DTC\"},{\"aid\":\"q04_01_a2\",\"isSpace\":\"1\",\"rank\":\"2\",\"text\":\"有DTC\"}],\"qid\":\"q04_01\",\"rank\":\"1\",\"title\":\"\",\"type\":\"1\"}],\"rank\":\"4\",\"title\":\"故障代码检查\"},{\"description\":\"ssss\",\"qid\":\"q05\",\"question\":[{\"qid\":\"q05_01\",\"question\":[{\"qid\":\"q05_01_01\",\"rank\":\"1\",\"title\":\"基本数据\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"项目\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"数值\",\"type\":\"title\"},{\"rank\":\"3\",\"text\":\"单位\",\"type\":\"title\"},{\"rank\":\"4\",\"text\":\"判断\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"发动机转速\",\"type\":\"title\"},{\"rank\":\"2\",\"type\":\"space\"},{\"rank\":\"3\",\"text\":\"2323\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q05_01_01_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q05_01_01_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q05_01_01_01\",\"rank\":\"4\",\"type\":\"option1\"}]}]},{\"qid\":\"q05_01_02\",\"rank\":\"1\",\"title\":\"定格数据中除基本数据外的反应故障码特征的相关参数\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"项目\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"数值\",\"type\":\"title\"},{\"rank\":\"3\",\"text\":\"单位\",\"type\":\"title\"},{\"rank\":\"4\",\"text\":\"判断\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"发动机转速\",\"type\":\"title\"},{\"qid\":\"\",\"rank\":\"2\",\"type\":\"space\"},{\"rank\":\"3\",\"text\":\"2323\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q05_01_02_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q05_01_02_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q05_01_02_01\",\"rank\":\"4\",\"type\":\"option1\"}]}]}],\"rank\":\"1\",\"title\":\"定格数据记录\",\"type\":\"3\"},{\"qid\":\"q05_02\",\"question\":[{\"qid\":\"q05_02_01\",\"rank\":\"1\",\"title\":\"\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"项目\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"数值\",\"type\":\"title\"},{\"rank\":\"3\",\"text\":\"单位\",\"type\":\"title\"},{\"rank\":\"4\",\"text\":\"判断\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"发动机转速\",\"type\":\"title\"},{\"qid\":\"\",\"rank\":\"2\",\"type\":\"space\"},{\"rank\":\"3\",\"text\":\"2323\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q05_02_01_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q05_02_01_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q05_02_01_01\",\"rank\":\"4\",\"type\":\"option1\"}]}]}],\"rank\":\"2\",\"title\":\"与故障码特征相关的动态数据记录\",\"type\":\"3\"},{\"answer\":[],\"qid\":\"q05_03\",\"rank\":\"1\",\"title\":\"清楚故障码\",\"type\":\"1\"},{\"answer\":[{\"aid\":\"q05_04_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"无DTC\"},{\"aid\":\"q05_04_a2\",\"isSpace\":\"1\",\"rank\":\"2\",\"text\":\"有DTC\"}],\"qid\":\"q05_04\",\"rank\":\"1\",\"title\":\"确认故障码是否在次出现并填写结果\",\"type\":\"1\"}],\"rank\":\"5\",\"title\":\"正确读取数据流和清除故障码\"},{\"description\":\"ssss\",\"qid\":\"q06\",\"question\":[{\"qid\":\"q06_01\",\"question\":[{\"qid\":\"q06_01_01\",\"rank\":\"1\",\"title\":\"\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"部件\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"判断\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q06_01_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"可能\"},{\"aid\":\"q06_01_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不可能\"}],\"qid\":\"q06_01_01\",\"rank\":\"2\",\"type\":\"option1\"}]}]}],\"rank\":\"1\",\"title\":\"根据上述检查进行判断并填写可能故障范围\",\"type\":\"3\"}],\"rank\":\"6\",\"title\":\"确认故障范围\"},{\"description\":\"\",\"qid\":\"q07\",\"question\":[{\"answer\":[{\"aid\":\"q07_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q07_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q07_01\",\"rank\":\"1\",\"title\":\"线路/连接器外观及连接情况\",\"type\":\"1\"},{\"answer\":[{\"aid\":\"q07_02_a1\",\"isSpace\":\"1\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q07_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q07_02\",\"rank\":\"2\",\"title\":\"零件安装\",\"type\":\"1\"}],\"rank\":\"7\",\"title\":\"基本检查\"},{\"description\":\"ssss\",\"qid\":\"q08\",\"question\":[{\"qid\":\"q08_01\",\"question\":[{\"qid\":\"q08_01_01\",\"rank\":\"1\",\"title\":\"\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"部件\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"检查或检测后的判断后果\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q08_01_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q08_01_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q08_01_01\",\"rank\":\"1\",\"type\":\"option1\"}]}]}],\"rank\":\"1\",\"title\":\"对被怀疑的部件进行测试\",\"type\":\"3\"}],\"rank\":\"8\",\"title\":\"部件测试\"},{\"description\":\"ssss\",\"qid\":\"q09\",\"question\":[{\"qid\":\"q09_01\",\"question\":[{\"qid\":\"q09_01_01\",\"rank\":\"1\",\"title\":\"\",\"tr\":[{\"title\":[{\"rank\":\"1\",\"text\":\"部件\",\"type\":\"title\"},{\"rank\":\"2\",\"text\":\"检查或检测后的判断后果\",\"type\":\"title\"}]},{\"title\":[{\"rank\":\"1\",\"text\":\"\",\"type\":\"title\"},{\"answer\":[{\"aid\":\"q09_01_01_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"正常\"},{\"aid\":\"q09_01_01_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"不正常\"}],\"qid\":\"q09_01_01\",\"rank\":\"1\",\"type\":\"option1\"}]}]}],\"rank\":\"1\",\"title\":\"对被怀疑的部件进行测试\",\"type\":\"3\"}],\"rank\":\"9\",\"title\":\"电路测试\"},{\"description\":\"ssss\",\"qid\":\"q10\",\"question\":[{\"qid\":\"q10_01\",\"question\":[{\"qid\":\"q10_01\",\"rank\":\"1\",\"title\":\"\",\"tr\":[{\"title\":[{\"answer\":[{\"aid\":\"q10_01_01_a1\",\"rank\":\"1\",\"text\":\"元件损坏\"}],\"qid\":\"q10_01_01\",\"rank\":\"1\",\"type\":\"option1\"},{\"qid\":\"q10_01_02\",\"rank\":\"2\",\"title\":\"元件名称:\",\"type\":\"space\"}]},{\"title\":[{\"answer\":[{\"aid\":\"q10_02_01_a1\",\"rank\":\"1\",\"text\":\"线路故障\"}],\"qid\":\"q10_02_01\",\"rank\":\"1\",\"type\":\"option1\"},{\"qid\":\"q10_02_02\",\"rank\":\"2\",\"title\":\"线路区间:\",\"type\":\"space\"}]},{\"title\":[{\"answer\":[{\"aid\":\"q10_03_01_a1\",\"rank\":\"1\",\"text\":\"其他\"}],\"qid\":\"q10_03_01\",\"rank\":\"1\",\"type\":\"option1\"},{\"qid\":\"q10_03_02\",\"rank\":\"2\",\"title\":\"\",\"type\":\"space\"}]}]}],\"rank\":\"1\",\"title\":\"根据检查结果，确认故障内容并注明\",\"type\":\"3\"},{\"answer\":[{\"aid\":\"q10_02_a1\",\"isSpace\":\"0\",\"rank\":\"1\",\"text\":\"更换\"},{\"aid\":\"q10_02_a2\",\"isSpace\":\"0\",\"rank\":\"2\",\"text\":\"维修\"}],\"qid\":\"q10_02\",\"rank\":\"1\",\"title\":\"故障排除方式\",\"type\":\"1\"}],\"rank\":\"10\",\"title\":\"故障部位确认和排查\"}],\"schema\":\"XCJ\",\"schemaversion\":\"1.0\",\"score\":[{\"answer\":\"q02_01_a1\",\"id\":\"q02_01\",\"kp\":\"14\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"q07_02_a1#正确答案\",\"id\":\"q07_02\",\"kp\":\"15\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"答案答案\",\"id\":\"q10_03_02\",\"kp\":\"15\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_03\",\"kp\":\"15\",\"rate\":\"8\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_04\",\"kp\":\"17\",\"rate\":\"10\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_05\",\"kp\":\"16\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_06\",\"kp\":\"17\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_07\",\"kp\":\"17\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_08\",\"kp\":\"17\",\"rate\":\"26\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_09\",\"kp\":\"18\",\"rate\":\"20\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_10\",\"kp\":\"18\",\"rate\":\"15\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_11\",\"kp\":\"19\",\"rate\":\"3\",\"type\":\"question\"},{\"answer\":\"\",\"id\":\"q01_12\",\"kp\":\"20\",\"rate\":\"3\",\"type\":\"question\"}],\"type\":\"sbt\",\"types\":\"sbtQuestion\"}}");
		JSONArray jsonArray = (JSONArray)JSONObject.parseObject(jsonObj.get("sbtTestScore").toString()).get("score");
		for (int i = 0; i < jsonArray.size(); i++) {
			((JSONObject)jsonArray.get(i)).put("answer","");
		}
		JSONObject jsonObjNewSbtTestScore = JSONObject.parseObject(jsonObj.get("sbtTestScore").toString());
		jsonObjNewSbtTestScore.put("score", jsonArray);
		jsonObj.put("sbtTestScore", jsonObjNewSbtTestScore);
	}
	
	
	/**
	 * 
	   * <b>function:</b> 通过课件编号获取json文件的内容
	   * @project base
	   * @package com.xcj.admin.service.impl.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午04:26:55
	   * @return String
	   * @author dapeng_wu
	 */
	public String getCourseWareJson(HttpServletRequest request,String courseNumber)throws Exception{
		JSONObject jsonObject = new JSONObject();
		StringBuffer jsonSb = new StringBuffer("");
		CourseWare courseWare = courseWareService.queryCourseNumbers(courseNumber);
		if(courseWare == null){
			return "";
		}
		String FileName=request.getSession().getServletContext().getRealPath("/")+courseWare.getFileurl().substring(0,courseWare.getFileurl().length()-4)+".txt";
		File myFile=new File(FileName);
		if(!myFile.exists()){ 
			return jsonSb.toString();
		}
    	InputStreamReader isr=new InputStreamReader(new FileInputStream(myFile),"UTF-8");
        BufferedReader in = new BufferedReader(isr);
        String str;
        
        while ((str = in.readLine()) != null){
        	jsonSb.append(str);
        }
        in.close();
        
        //如果是sbt或emu就会有故障的xml;
        List<SbtFault> sbtFaultList = courseWareDao.querySbtFault(courseWare.getId().toString());
        if(sbtFaultList == null || sbtFaultList.size() == 0){
        	jsonObject.put(courseWare.getCourseNumber(), jsonSb.toString());
        	return jsonObject.toJSONString();
        }else{
        	jsonObject.put(courseWare.getCourseNumber(), jsonSb.toString());
        	for (SbtFault sbtFault : sbtFaultList) {
        		jsonSb = new StringBuffer("");
	    		FileName=request.getSession().getServletContext().getRealPath("/")+sbtFault.getFileurl().substring(0,sbtFault.getFileurl().length()-4)+".txt";
	    		myFile=new File(FileName);
	    		if(!myFile.exists()){ 
	    			return jsonObject.toJSONString();
	    		}
	        	isr=new InputStreamReader(new FileInputStream(myFile),"UTF-8");
	            in = new BufferedReader(isr);
	            while ((str = in.readLine()) != null){
	            	jsonSb.append(str);
	            }
	            in.close();
	            jsonObject.put(sbtFault.getFaultNumber(), jsonSb.toString());
			}
        }
        return jsonObject.toJSONString();
	}
}
