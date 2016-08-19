package com.xcj.api.oper.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.study.StudyLog;
import com.xcj.admin.entity.vo.StudyHistory;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.course.SbtTestScoreService;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.admin.service.study.StudyService;
import com.xcj.admin.service.study.TotalStudyInfoService;
import com.xcj.admin.xcjenum.CourseType;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.xmlUtil;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.StringUtil;

/**
 * 
  * 学习相关：初始化学习-->开始学习-->提交学习-->退出学习
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.api.oper.controller  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 上午09:18:12 
  * @author hehujun 
  * @email hehujun@126.com
 */
@Controller("studyApiController")
@RequestMapping("/api/oper/study/")
public class StudyApiController {
	
	private static Log logger = LogFactory.getLog(StudyApiController.class);
	
	@Resource(name="studyServiceImpl")
	private StudyService studyService;
	
	@Resource(name="domainServiceImpl")
	private DomainService domainService;
	
	@Resource(name="totalStudyInfoServiceImpl")
	private TotalStudyInfoService totalStudyInfoService;
	
	@Resource(name="courseWareServiceImpl")
	private CourseWareService courseWareService;
	
	@Resource(name="sbtTestScoreServiceImpl")
	private SbtTestScoreService sbtTestScoreService;

	/**
	 * 
	   * 初始化学习
	   * <b>function:</b> initStudy()
	   * @project base
	   * @package com.xcj.admin.controller.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-2 下午03:04:08
	   * @return ModelAndView
	   * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/initStudy", produces = "application/json;charset=UTF-8")
	public JSONObject initStudy(String objData) {
		
		logger.info("study init begin");
		
		JSONObject data = JSONObject.parseObject(objData);
		String courseNumber = data.getString("courseNumber");
		String domainUsername = data.getString("domainUsername");
		String token = data.getString("token");
		String userEmail = data.getString("userEmail");
		//TODO 处理数据的方法
		//TODO 修改fault_number 和study_flag 等标识 这两个参数可以为空
		String faultNumber = data.getString("faultNumber");
		String studyFlag = data.getString("studyFlag");

		if (ApiUtil.isParamNull(courseNumber, domainUsername, token, userEmail)) {
			logger.info("study init param null");
			return ApiUtil.returnParamNull();
		}
		
		/*
		  1	判断token是否满足条件
			1.1 	不满足返回
			1.2		满足
				1.2.1	根据域名称获取内容权限
					1.2.1.1		不满足条件，返回
					1.2.1.2		满足生成sessionId
		*/
		
		data.clear();
		
		try {
			if (!domainService.judgeTokenIsTime(token, domainUsername, userEmail)) {
				logger.info("study init token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			
			List<CourseWare> courseWares = studyService.getCourseWares(domainUsername);
			boolean flag = true;
			for (CourseWare courseWare:courseWares) {
				if (courseNumber.equals(courseWare.getCourseNumber())) {
					flag = false;
					break;
				}
			}
		
			if (flag) {
				logger.info("study init courseNumber not exists");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_COURSE_NUMBER_NOT_EXISTS);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_COURSE_NUMBER_NOT_EXISTS);
				return data;
			}
			
			// sessionId生成之后需要往study_log插入数据
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			String sessionId = studyService.generateSessionId(userEmail);
			Map<String, String> sessionmap = new HashMap<String, String>();
			sessionmap.put("sessionId", sessionId);
			data.put(Constants.XCJ_RETURN_API_DATA, sessionmap);
			HashMap<String, String> map =new HashMap<String, String>();
			//map.put(userEmail,sessionId);
			map.put(Constants.XCJ_MEMCACHED_CONSTANTS_STUDY_SESSIONID+"_"+userEmail,sessionId);
			MemcachedUtil.putMemcacheIntanceByTime(map, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			
			StudyLog studyLog = new StudyLog();
			studyLog.setCourseNumber(courseNumber);
			studyLog.setDomainUsername(domainUsername);
			studyLog.setSessionId(sessionId);
			studyLog.setUserEmail(userEmail);
			//nullToEmpty  TODO 修改故障编号以及学习标识
			studyLog.setFaultNumber(StringUtil.nullToEmpty(faultNumber));
			studyLog.setStudyFlag(StringUtil.nullToEmpty(studyFlag));
			
			if(null==studyService.getStudyTotalInfos(userEmail, courseNumber, faultNumber, studyFlag)){
				if (!studyService.save(studyLog, "init")) {
					logger.error("study init studyService save error");
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
					return data;
				}
			}
			
		} catch (IOException e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study init MemcachedClient:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		} catch (Exception e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study init studyService:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		} 
		logger.info("study init end");
		return data;
	}
	
	
	/**
	 * 
	   * 开始学习
	   * <b>function:</b>  startStudy()
	   * @project base
	   * @package com.xcj.admin.controller.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-2 下午03:12:16
	   * @return ModelAndView
	   * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/startStudy", produces = "application/json;charset=UTF-8")
	public JSONObject startStudy(HttpServletRequest request,String adapter) {
		logger.info("study start begin");
		JSONObject data = JSONObject.parseObject(adapter);
		if (ApiUtil.isParamNull(adapter,data)) {
			logger.info("study start param null");
			return ApiUtil.returnParamNull();
		}
		String courseNumber = data.getString("courseNumber");
		String domainUsername = data.getString("domainAccount");
		String sessionId = data.getString("sessionId");
		String token = data.getString("token");
		String userEmail = data.getString("userEmail");
		String faultNumber = StringUtil.nullToEmpty(data.getString("faultNumber"));
		try {
			//根据sessionId获取studyFlag
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		
			if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail, domainUsername)) {
				logger.info("study start param null");
				return ApiUtil.returnParamNull();
			}
			data.clear();
			String studyFlag =(String)mc1.get(token);
			//TODO 同步-----同步学习结果
			totalStudyInfoService.syncTotalStudyResult(userEmail,domainUsername,courseNumber,faultNumber,studyFlag);

			if (!domainService.judgeTokenIsTime(token, domainUsername, userEmail)) {
				logger.info("study start token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!studyService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("study start sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}
			
			StudyHistory studyHistory = studyService.getStudyTotalInfos(userEmail, courseNumber,faultNumber,studyFlag);
			
			if(studyHistory == null){
				//sessionId生成之后需要往study_log插入数据
				//data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
				Map<String, String> sessionmap = new HashMap<String, String>();
				sessionmap.put("sessionId", sessionId);
				//data.put(Constants.XCJ_RETURN_API_DATA, sessionmap);
				HashMap<String, String> map =new HashMap<String, String>();
				//map.put(userEmail,sessionId);
				map.put(Constants.XCJ_MEMCACHED_CONSTANTS_STUDY_SESSIONID+"_"+userEmail,sessionId);
				MemcachedUtil.putMemcacheIntanceByTime(map, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				
				StudyLog studyLog = new StudyLog();
				studyLog.setCourseNumber(courseNumber);
				studyLog.setDomainUsername(domainUsername);
				studyLog.setSessionId(sessionId);
				studyLog.setUserEmail(userEmail);
				//TODO 处理数据的方法
				studyLog.setFaultNumber(faultNumber);
				studyLog.setStudyFlag(studyFlag);

				if(null==studyService.getStudyTotalInfos(userEmail, courseNumber,faultNumber,studyFlag)){
					if (!studyService.save(studyLog, "init")) {
						logger.error("study init studyService save error");
						data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
						data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
						return data;
					}
				}
				studyHistory = studyService.getStudyTotalInfos(userEmail, courseNumber,faultNumber,studyFlag);
			}
			studyHistory.setSessionId(sessionId);
			//类型有可能会调整
			if (CourseType.SBT.getType().equalsIgnoreCase(studyHistory.getType()) && StringUtil.isEmptyYl(faultNumber)) {
				studyHistory.setUrl("/api/gopage/gosbt");
			} else if (CourseType.WBT.getType().equalsIgnoreCase(studyHistory.getType())) {
				studyHistory.setUrl("/api/gopage/gowbt");
			} else if (CourseType.EMU.getType().equalsIgnoreCase(studyHistory.getType())) {
				studyHistory.setUrl("/api/gopage/goemu");
			} else if (CourseType.DMC.getType().equalsIgnoreCase(studyHistory.getType())) {
				studyHistory.setUrl("/api/gopage/godmc");
			}else{
				studyHistory.setUrl("/api/gopage/gosbtnew");
			}
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("domainAccount",domainUsername);
			jsonObj.put("userEmail",userEmail);  
			jsonObj.put("sessionId",sessionId);        
			jsonObj.put("token",token);             
			jsonObj.put("courseNumber",courseNumber);     
			jsonObj.put("faultNumber",faultNumber);       
			jsonObj.put("type",studyHistory.getType());       
			jsonObj.put("seconds",studyHistory.getSeconds());           
			jsonObj.put("progress",studyHistory.getTopProgress());          
			jsonObj.put("score",studyHistory.getTopScore());          
			jsonObj.put("isComplete",studyHistory.getIsComplete());        
			jsonObj.put("serverTime",(new Date()).getTime());                
			jsonObj.put("isPass",studyHistory.getIsPass());            
			jsonObj.put("characterA",studyHistory.getCharacterA()==null?"":studyHistory.getCharacterA());        
			jsonObj.put("characterC",studyHistory.getCharacterC()==null?"":studyHistory.getCharacterC());        
			jsonObj.put("completeTime",studyHistory.getCompleteTime()==null?"":studyHistory.getCompleteTime());      
			jsonObj.put("passTime",studyHistory.getPassTime()==null?"":studyHistory.getPassTime());
			//如果是1代表sbt从故障中找，其他从主课件中找
			CourseWare courseWare = courseWareService.getByCourseNumber(courseNumber);
			
			if("1".equals(studyHistory.getType())){
				List<SbtFault> sbtFaultList = courseWareService.querySbtFault(courseWare.getId().toString());
				if(sbtFaultList != null){
						for (SbtFault sbtFault : sbtFaultList) {
							if(faultNumber.equals(sbtFault.getFaultNumber())){
								jsonObj.put("totalScore",sbtFault.getSumScore());
								jsonObj.put("courseName",sbtFault.getName());        
								jsonObj.put("passCondition",sbtFault.getPassCondition());    
								jsonObj.put("completeCondition",sbtFault.getCompleteCondition()); 
								List<SbtTestScore> sbtTestScoreList = sbtTestScoreService.getListByCourseNumber(courseNumber,faultNumber);
								//拼装characterB
								if(sbtTestScoreList != null && sbtTestScoreList.size()>0){
									String text = sbtTestScoreList.get(0).getText();
									JSONObject textObject = JSONObject.parseObject(text);
									JSONObject charB = new JSONObject();
									charB.put("handle", textObject.get("handle"));
									charB.put("progress", textObject.get("progress"));
									charB.put("value", textObject.get("value"));
									charB.put("exclusive", textObject.get("exclusive"));
									charB.put("score", textObject.get("score"));
									charB.put("question", textObject.get("question"));
									jsonObj.put("characterB",charB); 
								}else{
									jsonObj.put("characterB",""); 
								}
							}
						}
				}
			}else{
				jsonObj.put("totalScore",courseWare.getSumScore());
				jsonObj.put("courseName",courseWare.getName());        
				jsonObj.put("passCondition",courseWare.getPassCondition());    
				jsonObj.put("completeCondition",courseWare.getCompleteCondition()); 
				
				String realPath = request.getSession().getServletContext().getRealPath("");
				File jsonFile = new File(realPath+"/"+courseWare.getFileurl().substring(0,courseWare.getFileurl().indexOf(".xml"))+".txt");
			    String jsonString = xmlUtil.txtString(jsonFile);
				//跟故障xml的工单，分数一起放入工单表
			    JSONObject courseWareObject = JSONObject.parseObject(jsonString);
			    Object handle = JSONObject.parseObject(courseWareObject.get("handles").toString()).get("handle");
			    Object progress = JSONObject.parseObject(courseWareObject.get("progresses").toString()).get("progress");
			    Object value = JSONObject.parseObject(courseWareObject.get("values").toString()).get("value");
			    Object score = courseWareObject.get("score");
			    Object question = courseWareObject.get("question");
			    Object exclusive = courseWareObject.get("exclusive");
				JSONObject charB = new JSONObject();
				charB.put("handle", handle!=null?handle:"");
				charB.put("progress", progress!=null?progress:"");
				charB.put("value", value!=null?value:"");
				charB.put("score", score!=null?score:"");
				charB.put("question", question!=null?question:"");
				charB.put("exclusive", exclusive!=null?exclusive:"");
				jsonObj.put("characterB",charB.toJSONString());
			}
			
			data.put("adapter", jsonObj);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			
		} catch (IOException e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study start MemcachedClient:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study start studyService:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
			return data;
		}
		logger.info("study start end");
		
		return data;
	}
	
	/**
	 * 
	   * 提交学习数据
	   * <b>function:</b> commitStudy() 
	   * @project base
	   * @package com.xcj.admin.controller.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-2 下午03:13:35
	   * @return ModelAndView
	   * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/commitStudy", produces = "application/json;charset=UTF-8")
	public JSONObject commitStudy(String adapter) {
		
		logger.info("study commit begin");
		JSONObject data = JSONObject.parseObject(adapter);
		if (ApiUtil.isParamNull(adapter,data)) {
			logger.info("study start param null");
			return ApiUtil.returnParamNull();
		}
		String domainUsername = data.getString("domainAccount");
		String userEmail = data.getString("userEmail");
		String sessionId = data.getString("sessionId");
		String token = data.getString("token");
		String courseNumber = data.getString("courseNumber");
		String faultNumber = data.getString("faultNumber");
		String actionTime = data.getString("seconds");
		String progress = data.getString("progress");
		String score = data.getString("score");
		String isComplete = data.getString("isComplete");
		String isPass = data.getString("isPass");
		String characterA = data.getString("characterA");
		String characterC = data.getString("characterC");
		String receive = "提交";

		try {
			//根据sessionId获取studyFlag
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail, domainUsername, progress, score, actionTime)) {
				return ApiUtil.returnParamNull();
			}
			data.clear();
			String studyFlag = mc1.get(token).toString();
			//TODO 同步-----同步学习结果
			 totalStudyInfoService.syncTotalStudyResult(userEmail,domainUsername,courseNumber,faultNumber,studyFlag);

			if (!domainService.judgeTokenIsTime(token, domainUsername, userEmail)) {
				logger.info("study commit token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!studyService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("study commit sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}
			
			StudyLog studyLog = new StudyLog();
			studyLog.setCharacterA(characterA);
			studyLog.setCharacterC(characterC);
			studyLog.setCourseNumber(courseNumber);
			studyLog.setDomainUsername(domainUsername);
			studyLog.setProgress(Float.parseFloat(progress));
			studyLog.setReceive(receive);
			studyLog.setScore(Float.parseFloat(score));
			studyLog.setSessionId(sessionId);
			studyLog.setUserEmail(userEmail);
			studyLog.setActionTime(Integer.parseInt(actionTime));
			studyLog.setFaultNumber(StringUtil.nullToEmpty(faultNumber));
			studyLog.setStudyFlag(StringUtil.nullToEmpty(studyFlag));
			
			//是否完成
			if (!StringUtil.isEmptyYl(isComplete) && "1".equals(isComplete)) {
				studyLog.setIsComplete(Integer.parseInt(isComplete));
			} else {
				studyLog.setIsComplete(0);
			}
			
			//是否通过
			if (!StringUtil.isEmptyYl(isPass) && "1".equals(isPass)) {
				studyLog.setIsPass(Integer.parseInt(isPass));
			} else {
				studyLog.setIsPass(0);
			}
			
			try {
				if (!studyService.save(studyLog,"commit")) {
					logger.error("study commit studyService save error");
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
					return data;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//			Runnable runnable = new Runnable() {
//	            public void run() {  
					try {
						StudyResult	studyResult = studyService.getStudyResultBeforeSaveActionOrAnswer(courseNumber, domainUsername, userEmail, sessionId);
		    			if (!StringUtil.isEmptyYl(characterA)) {
		    				JSONObject charaA = JSONObject.parseObject(characterA);
		    				JSONArray progressArr = JSONArray.parseArray(charaA.get("progress").toString());
		    				JSONArray scoreArr = JSONArray.parseArray(charaA.get("score").toString());
		    				JSONArray handlesArr = JSONArray.parseArray(charaA.get("handle").toString());
		    				if(progressArr==null || scoreArr==null || handlesArr==null){
								data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
								data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
								return data;
		    				}
		    				
		    				if(!studyService.saveStudyActionAndQuestion(characterA, studyResult)){
		    					logger.info("study commit studyService.saveStudyActionAndQuestion param null");
								data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
								data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
								return data;
		    				}
		    			}  
					} catch (Exception e) {
						logger.error("提交学习数据动作、问题出错"+e.getMessage());
						data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
						data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
						return data;
					}
//	            }  
//	        };  
//	        new Thread(runnable).start();
			data.put("serverTime", (new Date()).getTime());
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study commit studyService:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
			return data;
		}
		
//		1 保存提交的数据
//		2 向数据库中更新学习数据，记录通信log
		logger.info("study commit end");
		
		return data;
	}
	
	/**
	 * 
	   * 退出学习
	   * <b>function:</b>  exitStudy()
	   * @project base
	   * @package com.xcj.admin.controller.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-2 下午03:14:56
	   * @return ModelAndView
	   * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/exitStudy", produces = "application/json;charset=UTF-8")
	public JSONObject exitStudy(String adapter) {
		logger.info("study exit begin");
		
		JSONObject data = JSONObject.parseObject(adapter);
		if (ApiUtil.isParamNull(adapter,data)) {
			logger.info("study start param null");
			return ApiUtil.returnParamNull();
		}
		String domainUsername = data.getString("domainAccount");
		String userEmail = data.getString("userEmail");
		String sessionId = data.getString("sessionId");
		String token = data.getString("token");
		String courseNumber = data.getString("courseNumber");
		String faultNumber = data.getString("faultNumber");
		String actionTime = data.getString("seconds");
		String progress = data.getString("progress");
		String score = data.getString("score");
		String isComplete = data.getString("isComplete");
		String isPass = data.getString("isPass");
		String characterA = data.getString("characterA");
		String characterC = data.getString("characterC");
		String receive = "退出";
		try {
			//根据sessionId获取studyFlag
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			
			if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail, domainUsername, progress, score,actionTime)) {
				return ApiUtil.returnParamNull();
			}
			
			data.clear();
			String studyFlag = mc1.get(token).toString();
			//TODO 同步-----同步学习结果
			 totalStudyInfoService.syncTotalStudyResult(userEmail,domainUsername,courseNumber,faultNumber,studyFlag);

			if (!domainService.judgeTokenIsTime(token, domainUsername, userEmail)) {
				logger.info("study exit token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!studyService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("study exit sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}
			//删除缓存退出学习
			mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_STUDY_SESSIONID+"_"+userEmail);
			
			StudyLog studyLog = new StudyLog();
			studyLog.setCharacterA(characterA);
			studyLog.setCharacterC(characterC);
			studyLog.setCourseNumber(courseNumber);
			studyLog.setDomainUsername(domainUsername);
			studyLog.setProgress(Float.parseFloat(progress));
			studyLog.setReceive(receive);
			studyLog.setScore(Float.parseFloat(score));
			studyLog.setSessionId(sessionId);
			studyLog.setUserEmail(userEmail);
			studyLog.setActionTime(Integer.parseInt(actionTime));
			studyLog.setFaultNumber(StringUtil.nullToEmpty(faultNumber));
			studyLog.setStudyFlag(StringUtil.nullToEmpty(studyFlag));
			
			if (!StringUtil.isEmptyYl(isComplete) && "1".equals(isComplete)) {
				studyLog.setIsComplete(Integer.parseInt(isComplete));
			} else {
				studyLog.setIsComplete(0);
			}
			if (!StringUtil.isEmptyYl(isPass) && "1".equals(isPass)) {
				studyLog.setIsPass(Integer.parseInt(isPass));
			} else {
				studyLog.setIsPass(0);
			}
			if (!studyService.save(studyLog,"commit")) {
				logger.error("study exit studyService save error");
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
				return data;
			}
			
//			Runnable runnable = new Runnable() {
//	            public void run() {  
					try {
						StudyResult studyResult = studyService.getStudyResultBeforeSaveActionOrAnswer(courseNumber, domainUsername, userEmail, sessionId);
						
						JSONObject charaA = JSONObject.parseObject(characterA);
	    				JSONArray progressArr = JSONArray.parseArray(charaA.get("progress").toString());
	    				JSONArray scoreArr = JSONArray.parseArray(charaA.get("score").toString());
	    				JSONArray handlesArr = JSONArray.parseArray(charaA.get("handle").toString());
	    				if(progressArr==null || scoreArr==null || handlesArr==null){
							data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
							data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
							return data;
	    				}
	    				
	    				if(!studyService.saveStudyActionAndQuestion(characterA, studyResult)){
	    					logger.info("study commit studyService.saveStudyActionAndQuestion param null");
							data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
							data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
							return data;
	    				}
					} catch (Exception e) {
						logger.error("学习退出数据动作、问题出错"+e.getMessage());
						data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_characterA);
						data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG_characterA);
						return data;
					}
//	            }  
//	        };  
//	        new Thread(runnable).start();
			data.put("serverTime", (new Date()).getTime());
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			
		} catch (IOException e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study exit MemcachedClient:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
			return data;
		} catch (Exception e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"study exit studyService:"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
			return data;
		}
		
//		1 向数据库中更新学习数据
		logger.info("study exit end");
		return data;
	}
	
	public static void main(String[] args) {
		HashMap<String,Object> map =new HashMap<String, Object>();
		map.put("xx", "xxxxxxxxxxxxxxxxxxx");
		System.out.println(map.get("sd"));
	}
	
}
