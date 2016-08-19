package com.xcj.admin.service.tests;

import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.tests.TestAction;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.entity.tests.TestQuestion;
import com.xcj.admin.entity.tests.TestsResult;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:06
 * @author dapeng_wu
 */
public interface TestsService extends BaseService {
	
	/**
	   * <b>function:</b> 获取测评的sessionId
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-7 下午01:57:27
	   * @return String
	   * @author dapeng_wu
	 */
	public String getTestsSessionId(String useremail)throws Exception;
	
	/**
	   * <b>function:</b> 获取testsPublicId考试唯一标示
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-7 下午02:01:28
	   * @return String
	   * @author dapeng_wu
	 */
	public String getTestsPublicId(String domainUsername,String useremail)throws Exception;
	
	/**
	   * <b>function:</b> 校验测评sessionId
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:13:34
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeSesssionIdValidate(String sessionId, String useremail)throws Exception;
	
	/**
	   * <b>function:</b> 校验测评testsPublicId
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-16 上午11:12:03
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean judgeTestsPublicIdValidate(String testsPublicId, String useremail,String domainUsername)throws Exception;
	
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
	public TestsResult getTestsHistoryData(String userEmail, String domainUsername, String testsPublicId, String courseNumber) throws Exception;
	
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
	public boolean save(TestLog testLog, String method) throws Exception;
	
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
	public TestsResult getTestsResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail,String testsPublicId) throws Exception;
	
	
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
	public TestAction getTestActionByActionId(String actionId) throws Exception;
	
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
	public TestQuestion getTestQuestionByActionId(String quesionId) throws Exception;
	
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
	public boolean saveTestsActionAndQuestion(String action, String questions, TestsResult testsResult) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据testPublicId获取集合
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-21 上午11:25:39
	   * @return List<TestResult>
	   * @author dapeng_wu
	 */
	public List<TestsResult> getTestResultByTestPublicId(String testPublicId) throws Exception;

	 /**
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-6-9 下午05:57:43
	   * @return List<TestsResult>
	   * @author dapeng_wu
	   */
	public boolean updateTestsResultIsexit(String userEmail)throws Exception;
}
