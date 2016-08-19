package com.xcj.api.oper.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.admin.service.test.TestReportService;
import com.xcj.admin.service.test.TestResultService;
import com.xcj.admin.service.tests.TestsService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.StringUtil;

/**
 * 测试相关：初始化测试-->开始测试-->提交测试-->退出测试 <b>function:</b>
 * 
 * @project base
 * @package com.xcj.api.oper.controller
 * @fileName com.xcj.*
 * @createDate 2015-3-7 上午10:52:04
 * @author dapeng_wu
 */
@Controller("testsApiController")
@RequestMapping("/api/oper/tests/")
public class TestsApiController {
	private static Log logger = LogFactory.getLog(TestsApiController.class);

	@Resource(name = "domainServiceImpl")
	private DomainService domainService;

	@Resource(name = "testsServiceImpl")
	private TestsService testsService;
	

	@Resource(name = "testReportServiceImpl")
	private TestReportService testReportService;
	

	@Resource(name = "testResultServiceImpl")
	private TestResultService testResultService;
	
	/**
	 * 
	 * <b>function:</b>获取sessionId
	 * 
	 * @project base
	 * @package com.xcj.api.oper.controller
	 * @fileName com.xcj.***
	 * @createDate 2015-3-18 上午11:43:34
	 * @return JSONObject
	 * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/getTestsSessionId", produces = "application/json;charset=UTF-8")
	public JSONObject getTestsSessionId(String objData) {

		logger.info("TestsApiController getTestsPublicId begin");

		if (ApiUtil.isParamNull(objData)) {
			logger.info("TestsApiController getTestsPublicId parameter null");
			return ApiUtil.returnParamNull();
		}

		JSONObject data = JSONObject.parseObject(objData);
		String useremail = data.getString("userEmail");
		String token = data.getString("token");
		String domainUsername = data.getString("domainUsername");
		if (ApiUtil.isParamNull(useremail, token, domainUsername)) {
			logger.info("TestsApiController getTestsPublicId parameter incomplete");
			return ApiUtil.returnParamNull();
		}

		data.clear();

		try {
			if (!domainService.judgeTestTokenIsTime(token, domainUsername, useremail)) {
				logger.info("TestsApiController getTestsSessionId token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}

			String sessionId = testsService.getTestsSessionId(useremail);
			
			HashMap<String, String> sessionIdMap =new HashMap<String, String>();
			sessionIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+useremail, sessionId);
			MemcachedUtil.putMemcacheIntanceByTime(sessionIdMap,Constants.XCJ_MEMCACHED_TOKEN_LOGIN_TIMING);
			
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			Map<String, String> sessionmap = new HashMap<String, String>();
			sessionmap.put("sessionId", sessionId);
			data.put(Constants.XCJ_RETURN_API_DATA, sessionmap);

		} catch (Exception e) {
			logger.error("TestsApiController getTestsSessionId exception :"+ e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		}
		return data;
	}

	/**
	 * 
	 * <b>function:</b>测评获取testsPublicId
	 * 
	 * @project base
	 * @package com.xcj.api.oper.controller
	 * @fileName com.xcj.***
	 * @createDate 2015-3-18 上午11:43:40
	 * @return JSONObject
	 * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/getTestsPublicId", produces = "application/json;charset=UTF-8")
	public JSONObject getTestsPublicId(String objData) {

		logger.info("TestsApiController getTestsPublicId begin");

		if (ApiUtil.isParamNull(objData)) {
			logger.info("TestsApiController getTestsPublicId parameter null");
			return ApiUtil.returnParamNull();
		}

		JSONObject data = JSONObject.parseObject(objData);
		String useremail = data.getString("userEmail");
		String token = data.getString("token");
		String domainUsername = data.getString("domainUsername");

		if (ApiUtil.isParamNull(domainUsername, useremail, token)) {
			logger.info("TestsApiController getTestsPublicId parameter incomplete");
			return ApiUtil.returnParamNull();
		}

		try {
			data.clear();
			if (!domainService.judgeTestTokenIsTime(token, domainUsername,useremail)) {
				logger.info("TestsApiController getTestsPublicId token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,
						APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,
						APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}

			String testsPublicId = "";
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			testsPublicId = (String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+useremail);
			// 优先从缓存中拿 缓存拿不到则生成一个
			if (StringUtil.isEmptyYl(testsPublicId)) {
				testsPublicId = testsService.getTestsPublicId(domainUsername,
						useremail);
				// 1、存放缓存每次从缓存获取 2、以标准json对象返回 注意交卷是销毁
				HashMap<String, String> testPublicIdMap =new HashMap<String, String>();
				testPublicIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+useremail, testsPublicId);
				MemcachedUtil.putMemcacheIntanceByTime(testPublicIdMap,Constants.XCJ_MEMCACHED_TOKEN_LOGIN_TIMING);
			}

			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			Map<String, String> testsPublicIdmap = new HashMap<String, String>();
			testsPublicIdmap.put("testsPublicId", testsPublicId);
			data.put(Constants.XCJ_RETURN_API_DATA, testsPublicIdmap);
		} catch (Exception e) {
			logger.error("TestsApiController getTestsPublicId exception :"+ e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		}
		logger.info("TestsApiController getTestsPublicId end");
		return data;
	}

	/**
	 * <b>function:</b> 测评开始方法 TestsApiController(暂时没有使用 用getHistoryData代替)
	 * 
	 * @createDate 2015-3-7 下午01:55:50
	 * @author yy.niu
	 * @email return type JSONObject
	 */
	@ResponseBody
	@RequestMapping(value = "/strartTest", produces = "application/json;charset=UTF-8")
	public JSONObject strartTest(HttpServletRequest request,
			HttpServletResponse response, String objData) {
		JSONObject obj = new JSONObject();
			// 此处需要处理数据交互相关、历史数据、相关记录、返回界面
			logger.info("Test strartTest begin");
			JSONObject data = JSONObject.parseObject(objData);
			String courseNumber = data.getString("courseNumber");
			String domainUsername = data.getString("domainUsername");
			String sessionId = data.getString("sessionId");
			String token = data.getString("token");
			String userEmail = data.getString("userEmail");
			String testsPublicId = data.getString("testsPublicId");
		try {
			if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail,domainUsername)) {
				logger.info("Test strartTest param null");
				return ApiUtil.returnParamNull();
			}

			data.clear();
			try {
				if (!domainService.judgeTestTokenIsTime(token, domainUsername,userEmail)) {
					logger.info("study exit token invalidate");
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
					return data;
				}
				if (!testsService.judgeTestsPublicIdValidate(testsPublicId,userEmail, domainUsername)) {
					logger.info("study exit testsPublicId invalidate");
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TESTPUBLICID_INVALIDATE);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TESTPUBLICID_INVALIDATE);
					return data;
				}
				if (!testsService.judgeSesssionIdValidate(sessionId, userEmail)) {
					logger.info("study exit sessionId invalidate");
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
					return data;
				}

				//可能为空 暂留 功能跟获取历史一样
				TestsResult testsResult = testsService.getTestsHistoryData(userEmail, domainUsername, testsPublicId, courseNumber);
				testsResult.setSessionId(sessionId);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
				data.put(Constants.XCJ_RETURN_API_DATA, testsResult);
			} catch (IOException e) {
				logger.error("user:"+userEmail+"courseNum:"+courseNumber+"Test strartTest MemcachedClient:"+ e.getMessage());
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
				return data;
			} catch (Exception e) {
				logger.error("user:"+userEmail+"courseNum:"+courseNumber+"Test strartTest studyService:" + e.getMessage());
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
				return data;
			}
			// 返回所需要的数据信息以及所请求考试的URL
			data.put("errCode", 0);
			data.put("errMsg", "the Request is OK");
			
			//TODO 同步----同步测评结果 -测评报告
			testResultService.syncTestResult(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			testReportService.syncTestReport(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
		} catch (Exception e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"开始测评出现错误"+e.getMessage());
			obj.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			obj.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return obj;
		}
		return obj;
	}

	/**
	 * 
	 * <b>function:</b>获取历史数据有则拿到数据开始，没有插一条数据开始
	 * 
	 * @project base
	 * @package com.xcj.api.oper.controller
	 * @fileName com.xcj.***
	 * @createDate 2015-3-18 上午11:43:48
	 * @return JSONObject
	 * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/getHistoryData", produces = "application/json;charset=UTF-8")
	public JSONObject getHistoryData(HttpServletRequest request,
			HttpServletResponse response, String objData) {

		logger.info("TestsApiController getHistoryData begin");

		if (ApiUtil.isParamNull(objData)) {
			logger.info("TestsApiController getHistoryData parameter null");
			return ApiUtil.returnParamNull();
		}

		JSONObject data = JSONObject.parseObject(objData);

		String testsPublicId = data.getString("testsPublicId");
		String courseNumber = data.getString("courseNumber");
		String domainUsername = data.getString("domainUsername");
		String userEmail = data.getString("userEmail");
		String token = data.getString("token");
		String sessionId = data.getString("sessionId");
		String testPostedId = data.getString("testPostedId");

		if (ApiUtil.isParamNull(testsPublicId, courseNumber, domainUsername,userEmail, token, sessionId, testPostedId)) {
			logger.info("TestsApiController getHistoryData parameter incomplete");
			return ApiUtil.returnParamNull();
		}

		data.clear();
		try {

			if (!domainService.judgeTestTokenIsTime(token, domainUsername,userEmail)) {
				logger.info("study exit token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!testsService.judgeTestsPublicIdValidate(testsPublicId,userEmail, domainUsername)) {
				logger.info("study exit testsPublicId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TESTPUBLICID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TESTPUBLICID_INVALIDATE);
				return data;
			}
			if (!testsService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("study exit sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}

			// 获取当前学习的历史数据
			TestsResult testsResult = testsService.getTestsHistoryData(userEmail,domainUsername,testsPublicId,courseNumber);
			if (null == testsResult) {
				TestLog testLog = new TestLog();
				testLog.setCourseNumber(courseNumber);
				testLog.setDomainUsername(domainUsername);
				testLog.setSessionId(sessionId);
				testLog.setTestsPublicId(testsPublicId);
				testLog.setUserEmail(userEmail);
				testLog.setTestsPostedId(Integer.parseInt(testPostedId));
				
				testsService.save(testLog, "init");
				testsResult = testsService.getTestsHistoryData(userEmail,domainUsername,testsPublicId,courseNumber);
			}
			testsResult.setTestsPostedId(Integer.parseInt(testPostedId));
			testsResult.setSessionId(sessionId);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			data.put(Constants.XCJ_RETURN_API_DATA, testsResult);
			
			//TODO 同步----同步测评结果 -测评报告
			testResultService.syncTestResult(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			testReportService.syncTestReport(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			
		} catch (Exception e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"TestsApiController getHistoryData exception :"+ e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		}
		data.put("token", token);
		logger.info("TestsApiController getHistoryData end");
		return data;
	}

	/**
	 * 
	 * <b>function:</b>提交测评数据
	 * 
	 * @project base
	 * @package com.xcj.api.oper.controller
	 * @fileName com.xcj.***
	 * @createDate 2015-3-18 上午11:44:08
	 * @return JSONObject
	 * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/commitTest", produces = "application/json;charset=UTF-8")
	public JSONObject commitTest(HttpServletRequest request,
			HttpServletResponse response, String objData) {

		logger.info("TestsApiController commitTest begin");

		if (ApiUtil.isParamNull(objData)) {
			logger.info("TestsApiController commitTest parameter null");
			return ApiUtil.returnParamNull();
		}

		JSONObject data = JSONObject.parseObject(objData);
		String courseNumber = data.getString("courseNumber");
		String sessionId = data.getString("sessionId");
		String domainUsername = data.getString("domainUsername");
		String token = data.getString("token");
		String userEmail = data.getString("userEmail");
		String iscomplete = data.getString("iscomplete");
		String ispass = data.getString("ispass");
		String progress = data.getString("progress");
		String score = data.getString("score");
		String character = data.getString("character");
		String receive = data.getString("receive");
		String actionTime = data.getString("actionTime");
		String testsPublicId = data.getString("testsPublicId");
		String testPostedId = data.getString("testPostedId");
		if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail,
				domainUsername, progress, score, testsPublicId, actionTime)) {
			logger.info("TestsApiController commitTest parameter incomplete");
			return ApiUtil.returnParamNull();
		}

		data.clear();
		try {
			if (!domainService.judgeTestTokenIsTime(token, domainUsername,userEmail)) {
				logger.info("study exit token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!testsService.judgeTestsPublicIdValidate(testsPublicId,userEmail, domainUsername)) {
				logger.info("study exit testsPublicId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TESTPUBLICID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TESTPUBLICID_INVALIDATE);
				return data;
			}
			if (!testsService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("study exit sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}

			TestLog testLog = new TestLog();
			testLog.setTestsPublicId(testsPublicId);
			if (!StringUtil.isEmptyYl(character)){
				testLog.setCharacterA(character);
				testLog.setCharacterC(JSONObject.parseObject(character).getString("param"));
			}
			testLog.setCourseNumber(courseNumber);
			testLog.setDomainUsername(domainUsername);
			testLog.setProgress(Float.parseFloat(progress));
			testLog.setReceive(receive);
			testLog.setScore(Float.parseFloat(score));
			testLog.setSessionId(sessionId);
			testLog.setUserEmail(userEmail);
			testLog.setActionTime(Integer.parseInt(actionTime));
			testLog.setTestsPostedId(Integer.parseInt(testPostedId));
			
			
			if (!StringUtil.isEmptyYl(iscomplete) && "1".equals(iscomplete)) {
				testLog.setIsComplete(Integer.parseInt(iscomplete));
			} else {
				testLog.setIsComplete(0);
			}
			if (!StringUtil.isEmptyYl(ispass) && "1".equals(ispass)) {
				testLog.setIsPass(Integer.parseInt(ispass));
			} else {
				testLog.setIsPass(0);
			}
			if (!testsService.save(testLog, "commit")) {
				logger.error("TestsApiController commitTest testsService.save error");
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
				return data;
			}

//			Runnable runnable = new Runnable() {
//	            public void run() {  
					try {
						TestsResult testsResult = testsService.getTestsResultBeforeSaveActionOrAnswer(courseNumber,
										domainUsername, userEmail, testsPublicId);
						if(!StringUtil.isEmptyYl(character)){
							if (!testsService.saveTestsActionAndQuestion(JSONObject.parseObject(character).getString("action"), 
									JSONObject.parseObject(character).getString("question"),testsResult)) {
								logger.info("TestsApiController commitTest testsService.getTestsResultBeforeSaveActionOrAnswer param null");
							}
						}
					} catch (Exception e) {
						logger.error("提交测评数据动作、问题出错"+e.getMessage());
					}
//	            }  
//	        };  
//	        new Thread(runnable).start();
			
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			//TODO 同步----同步测评结果 -测评报告
			testResultService.syncTestResult(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			testReportService.syncTestReport(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			
		} catch (Exception e) {
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"TestsApiController commitTest exception :"+ e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			return data;
		}

		logger.info("TestsApiController commitTest end");
		return data;
	}

	/**
	 * <b>function:</b> 交卷处理 TestsApiController
	 * 
	 * @project base
	 * @package com.xcj.api.oper.controller
	 * @fileName com.xcj.***
	 * @createDate 2015-3-16 下午02:35:32
	 * @return JSONObject
	 * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/commitPaperTest", produces = "application/json;charset=UTF-8")
	public JSONObject commitPaperTest(String objData) {

		// objData="{'token':'B23590988956FF01D84CC20403B1522A','testsPublicId':'123456','courseNumber':'wbt001','userEmail':'sujian@163.com','domainUsername':'admin','sessionId':'A3398FB54D23F02DA093D2D4CC6EBD2C','receive':'1','character':'123','progress':0,'score':0,'isComplete':0,'isPass':0}";
		logger.info("TestsApiController commitTest begin");
		if (ApiUtil.isParamNull(objData)) {
			logger.info("TestsApiController commitPaperTest parameter null");
			return ApiUtil.returnParamNull();
		}

		JSONObject data = JSONObject.parseObject(objData);
		String courseNumber = data.getString("courseNumber");
		String sessionId = data.getString("sessionId");
		String domainUsername = data.getString("domainUsername");
		String token = data.getString("token");
		String userEmail = data.getString("userEmail");
		String iscomplete = data.getString("iscomplete");
		String ispass = data.getString("ispass");
		String progress = data.getString("progress");
		String score = data.getString("score");
		String character = data.getString("character");
		String receive = data.getString("receive");
		String actionTime = data.getString("actionTime");
		String testsPublicId = data.getString("testsPublicId");
		String testPostedId = data.getString("testPostedId");

		if (ApiUtil.isParamNull(courseNumber, sessionId, token, userEmail,
				domainUsername, progress, score, testsPublicId, actionTime ,receive)) {
			logger.info("TestsApiController commitTest parameter incomplete");
			return ApiUtil.returnParamNull();
		}
		data.clear();
		try {
			//TODO 同步----同步测评结果 -测评报告
			testResultService.syncTestResult(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			testReportService.syncTestReport(userEmail,domainUsername,courseNumber,sessionId,testsPublicId);
			if (!domainService.judgeTestTokenIsTime(token, domainUsername,userEmail)) {
				logger.info("tests exit token invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_INVALIDATE);
				return data;
			}
			if (!testsService.judgeTestsPublicIdValidate(testsPublicId,userEmail, domainUsername)) {
				logger.info("tests exit testsPublicId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TESTPUBLICID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TESTPUBLICID_INVALIDATE);
				return data;
			}
			if (!testsService.judgeSesssionIdValidate(sessionId, userEmail)) {
				logger.info("tests exit sessionId invalidate");
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SESSIONID_INVALIDATE);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SESSIONID_INVALIDATE);
				return data;
			}

			TestLog testLog = new TestLog();
			testLog.setTestsPublicId(testsPublicId);
			if (!StringUtil.isEmptyYl(character)){
				testLog.setCharacterA(character);
				testLog.setCharacterC(JSONObject.parseObject(character).getString("param"));
			}
			testLog.setCourseNumber(courseNumber);
			testLog.setDomainUsername(domainUsername);
			testLog.setProgress(Float.parseFloat(progress));
			testLog.setReceive(receive);
			testLog.setScore(Float.parseFloat(score));
			testLog.setSessionId(sessionId);
			testLog.setUserEmail(userEmail);
			testLog.setActionTime(Integer.parseInt(actionTime));
			testLog.setTestsPostedId(Integer.parseInt(testPostedId));
			if (!StringUtil.isEmptyYl(iscomplete) && "1".equals(iscomplete)) {
				testLog.setIsComplete(Integer.parseInt(iscomplete));
			} else {
				testLog.setIsComplete(0);
			}
			if (!StringUtil.isEmptyYl(ispass) && "1".equals(ispass)) {
				testLog.setIsPass(Integer.parseInt(ispass));
			} else {
				testLog.setIsPass(0);
			}
			if (!testsService.save(testLog, "commit")) {
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_STUDY_SAVE_RECORD);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_STUDY_SAVE_RECORD);
				return data;
			}
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			//mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
			mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail);
			
//			Runnable runnable = new Runnable() {
//	            public void run() {  
					try {
						TestsResult testsResult = testsService.getTestsResultBeforeSaveActionOrAnswer(courseNumber,
								domainUsername, userEmail, testsPublicId);
						if(!testsService.saveTestsActionAndQuestion(JSONObject.parseObject(character).getString("action"), 
								JSONObject.parseObject(character).getString("question"),testsResult))  {
						}
					} catch (Exception e) {
						logger.error("user:"+userEmail+"courseNum:"+courseNumber+"测评交卷数据动作、问题出错"+e.getMessage());
					}
//	            }  
//	        };  
//	        new Thread(runnable).start();
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,Constants.XCJ_RETURN_SUCCESS);
			
		} catch (Exception e) {
			try {
				MemcachedClient mc1 = MemcachedClientFactory.getInstance();
				//mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
				mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail);
			} catch (IOException e1) {
				logger.error("user:"+userEmail+"courseNum:"+courseNumber+"测评交卷错误"+ e.getMessage());
			}
			logger.error("user:"+userEmail+"courseNum:"+courseNumber+"测评交卷错误"+ e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
		}
		return data;
	}
	
	/**
	 * 
	   * <b>function:</b> 退出测评删除本次测评的testsPublicId
	   * @project base
	   * @package com.xcj.api.oper.controller  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-17 下午01:19:27
	   * @return String
	   * @author dapeng_wu
	 */
	@ResponseBody
	@RequestMapping(value = "/exitTest")
	public String exitTest(String userEmail) {
		try {
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
//			String testPublicId = (String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
//			if(testPublicId != null){
//				List<TestsResult> testsResultList = testsService.getTestResultByTestPublicId(testPublicId);
//				for (TestsResult testsResult : testsResultList) {
//					testsResult.setIsExit(1);
//					testsService.update(testsResult);
//				}
//			}
			testsService.updateTestsResultIsexit(userEmail);
			mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
			mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME+"_"+userEmail);
		} catch (Exception e) {
			logger.error("最后测评交卷错误"+ e.getMessage());
		}
		
		return "1";
	}
}
