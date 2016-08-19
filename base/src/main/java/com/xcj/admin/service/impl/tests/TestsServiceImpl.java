package com.xcj.admin.service.impl.tests;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.knowledgePoint.KnowledgePointDao;
import com.xcj.admin.dao.study.StudyDao;
import com.xcj.admin.dao.tests.TestsDao;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.TestAction;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.entity.tests.TestQuestion;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.admin.service.tests.TestsService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.md5.MD5Uitl;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:33
 * @author dapeng_wu
 */
@Service("testsServiceImpl")
public class TestsServiceImpl extends BaseServiceImpl implements TestsService {
	
	@Resource(name="testsDaoImpl")
	private TestsDao testsDao;
	
	@Resource(name="studyDaoImpl")
	private StudyDao studyDao;
	
	@Resource(name="knowledgePointDaoImpl")
	private KnowledgePointDao knowledgePointDao;

	/**
	 * <b>function:</b> 获取测评的sessionId
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-7 下午01:57:37
	 * @return
	 * @author dapeng_wu
	 */
	public String getTestsSessionId(String useremail) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.getCurrentTime());
		builder.append(MD5Uitl.MD5Encode(useremail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		builder.append(APIConstants.XCJ_MESSAGE_API_TESTS);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString()))
				.toUpperCase();
	}

	/**
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-7 下午02:01:56
	 * @return
	 * @author dapeng_wu
	 */
	public String getTestsPublicId(String domainUsername, String useremail)
			throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(DateUtil.getCurrentTime());
		builder.append(domainUsername);
		builder.append(MD5Uitl.MD5Encode(useremail).toUpperCase());
		builder.append(APIConstants.XCJ_MESSAGE_API);
		builder.append(APIConstants.XCJ_MESSAGE_API_TESTS);
		return MD5Uitl.MD5Encode(MD5Uitl.MD5Encode(builder.toString())).toUpperCase();
	}

	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:07:46
	   * @return TestsResult
	   * @author hehujun
	 */
	public TestsResult getTestsHistoryData(String userEmail, String domainUsername, String testsPublicId, String courseNumber)
			throws Exception {
		return testsDao.getTestsHistoryData(userEmail, domainUsername, testsPublicId, courseNumber);
	}
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:56:43
	   * @return int
	   * @author hehujun
	 */
	public int getSessionTime(String domainUserName, String useremail) throws Exception {
		MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		String cachetime = (String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST + domainUserName +"_time_"+useremail);
		return (int)(System.currentTimeMillis() - Long.valueOf(cachetime)) / 1000;
	}
	
	/**
	 * 
	   * 保存testLog
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-11 下午03:41:10
	   * @return boolean
	   * @author hehujun
	 */
	public boolean save(TestLog testLog, String method) throws Exception {
		if ("init".equals(method)) {
			testLog.setActionTime(0);
			testLog.setCreateDate(new Date());
			testLog.setIsComplete(0);
			testLog.setIsPass(0);
			testLog.setModifyDate(new Date());
			testLog.setProgress(0f);
			testLog.setReceive("");
			testLog.setSend("");
			testLog.setScore(0f);
			testLog.setStartTime(new Date());
		} else if ("commit".equals(method)) {
			// TODO 暂时由课件计算
//			testLog.setActionTime(getSessionTime(testLog.getUserEmail()));
			
			testLog.setCreateDate(new Date());
			testLog.setModifyDate(new Date());
			testLog.setSend("");
			testLog.setStartTime(new Date());
			if (1 == testLog.getIsComplete().intValue()) {
				testLog.setCompleteTime(new Date());
			}
			if (1 == testLog.getIsPass().intValue()) {
				testLog.setPassTime(new Date());
			}
		}
		return save(testLog);
	}
	
	/**
	 * 
	   * <b>function:</b> 获取测评结果id 在保存测评动作或者测评过程之前
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 下午12:02:28
	   * @return StudyResult
	   * @author hehujun
	 */
	public TestsResult getTestsResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail ,String testsPublicId) throws Exception {
		return testsDao.getTestsResultBeforeSaveActionOrAnswer(courseNumer, domainUserName, userEmail , testsPublicId);
	}
	

	/**
	   * <b>function:</b> 校验sessionId
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:05:10
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeSesssionIdValidate(String sessionId, String useremail)
			throws Exception {
		try {
			if (sessionId.length() != 32) {
				return false;
			}
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			return (sessionId.equals((String) mc1
					.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+useremail)));
		} catch (IOException e) {
			return false;
		}
		
	}
	
	/**
	   * <b>function:</b> 校验testsPublicId
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:10:01
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeTestsPublicIdValidate(String testsPublicId, String useremail,String domainUsername)
	throws Exception {
		try {
			if (testsPublicId.length() != 32) {
				return false;
			}
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			return (testsPublicId.equals((String) mc1
					.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+useremail)));
		} catch (IOException e) {
			return false;
		}
		
	}
	
	/**
	 * 
	   * <b>function:</b> 根据actionId 获取TestAction
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 下午01:30:43
	   * @return TestAction
	   * @author hehujun
	 */
	public TestAction getTestActionByActionId(String actionId) throws Exception {
		return testsDao.getTestActionByActionId(actionId);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据questionId 获取TestQuestion
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 下午01:31:32
	   * @return TestQuestion
	   * @author hehujun
	 */
	public TestQuestion getTestQuestionByActionId(String quesionId) throws Exception {
		return testsDao.getTestQuestionByActionId(quesionId);
	}
	
	/**
	 * 
	   * <b>function:</b> 保存测评动作 测评结果
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 下午01:40:25
	   * @return boolean
	   * @author hehujun
	 */
	public boolean saveTestsActionAndQuestion(String action, String questions, TestsResult testsResult) throws Exception {
		boolean flag = false;
		if (!StringUtil.isEmptyYl(action)) {
			String[] actions = action.split("_");
			for (int i=0;i<actions.length;i++){
				TestAction testAction = new TestAction();
				testAction.setCreateDate(new Date());
				testAction.setRank(i+1);
				
				//TODO 时间问题  su_jian做的修改
				String tempStr=actions[i];
				if(tempStr.contains("#")){
					testAction.setActionId(tempStr.split("#")[0]);
					Long ss=Long.valueOf(tempStr.split("#")[1]);
					testAction.setModifyDate(new Date(ss));
				}else{
					testAction.setActionId(tempStr);
					testAction.setModifyDate(new Date());
				}
				
				testAction.setTestResultId(testsResult.getId());
				if (actions[i].startsWith("a")) {
					// 动作
					CourseWareAction courseWareAction = studyDao.getCourseWareActionByActionId(actions[i]);
					if(courseWareAction != null){
						testAction.setDescription(courseWareAction.getDescription());
					}
				}
				if (actions[i].startsWith("p")) {
					// 部件
					CourseWareParts courseWareParts = studyDao.getCourseWarePartsByActionId(actions[i]);
					if(courseWareParts != null){
						testAction.setDescription(courseWareParts.getDescription());
					}
				}
				if (actions[i].startsWith("t")) {
					// 工具
					CourseWareTool courseWareTool = studyDao.getCourseWareToolByActionId(actions[i]);
					if(courseWareTool != null){
						testAction.setDescription(courseWareTool.getDescription());
					}
				}
				this.save(testAction);
			}
			flag = true;
		}
		if (!StringUtil.isEmptyYl(questions)) {
			JSONArray jsonArray = JSONObject.parseArray(questions);
			for (int i=0;i<jsonArray.size();i++){
				JSONObject tmp = JSONObject.parseObject(String.valueOf(jsonArray.get(i)));
//				TestQuestion testQuestion = new TestQuestion();
//				testQuestion.setCreateDate(new Date());
//				testQuestion.setModifyDate(new Date());
//				testQuestion.setRank(i+1);
//				testQuestion.setQuestionId(tmp.getString("question"));
//				testQuestion.setTestResultId(testsResult.getId());
//				
//				testQuestion.setDescription(testsResult.getModifyDate().toString());
//				SbtQuestion sbtQuestion = studyDao.getSbtQuestionByQuestionId(tmp.getString("question"));
//				if (null != sbtQuestion && null!=sbtQuestion.getDescription()) {
//					testQuestion.setQuestion(sbtQuestion.getDescription());
//				}
//				testQuestion.setAnswer(tmp.getString("answer"));
//				this.save(testQuestion);
				
				TestReport testReport = new TestReport();
				testReport.setQuestion(tmp.getString("question"));
				testReport.setAnswer(tmp.getString("answer"));
				testReport.setRightAnswer(tmp.getString("rAnswer"));
				testReport.setScore(tmp.getString("score"));
				List<KnowledgePoint> listByCode = knowledgePointDao.getListByCode(tmp.getString("kp"));
				if(listByCode != null && listByCode.size() >0){
					testReport.setKp(listByCode.get(0).getId().toString());
					testReport.setKpParent(knowledgePointDao.getParentListById(listByCode.get(0).getId().toString()).get(0).getId().toString());
				}
				testReport.setCourseNumber(testsResult.getCourseNumber());
				testReport.setCreateDate(new Date());
				testReport.setDomainUsername(testsResult.getDomainUsername());
				testReport.setUserEmail(testsResult.getUserEmail());
				testReport.setSessionId(testsResult.getSessionId());
				testReport.setTestsPostedId(testsResult.getTestsPostedId());
				testReport.setTestsPublicId(testsResult.getTestsPublicId());
				List<TestReport> testReportList = testsDao.getTestReportList(testReport);
				if(testReportList != null && testReportList.size() >0){
					testsDao.updateTestReport(testReport,testReportList.get(0).getId());
				}else{
					this.save(testReport);
				}
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	   * <b>function:</b> 根据testPublicId获取测评结果
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-21 上午11:26:45
	   * @return 
	   * @author dapeng_wu
	 */
	public List<TestsResult> getTestResultByTestPublicId(String testPublicId) throws Exception{
		return testsDao.getTestResultByTestPublicId(testPublicId);
	}
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-6-9 下午05:58:44
	   * @return 
	   * @author dapeng_wu
	 */
	public boolean updateTestsResultIsexit(String userEmail)throws Exception{
		return testsDao.updateTestsResultIsexit(userEmail);
	}
}
