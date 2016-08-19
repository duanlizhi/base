/**
 * 
 */
package com.xcj.admin.dao.tests;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.tests.TestAction;
import com.xcj.admin.entity.tests.TestQuestion;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.admin.entity.tests.TestsResult;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.dao.tests  
 * @fileName com.xcj.*
 * @createDate 2015-3-16 上午11:13:35 
 * @author hehujun 
 * @email hehujun@126.com
 */
public interface TestsDao extends BaseDao {
	
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
	public TestsResult getTestsHistoryData(String userEmail, String domainUsername, String testsPublicId, String courseNumber) throws DataAccessException;
	
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
	public TestsResult getTestsResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail ,String testsPublicId) throws DataAccessException;
	
	
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
	public TestAction getTestActionByActionId(String actionId) throws DataAccessException;
	
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
	public TestQuestion getTestQuestionByActionId(String quesionId) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据条件查询testReport
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午03:25:02
	   * @return List<TestReport>
	   * @author dapeng_wu
	 */
	public List<TestReport> getTestReportList(TestReport testReport) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据id修改测评报告表
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午03:30:16
	   * @return boolean
	   * @author dapeng_wu
	 */
	public boolean updateTestReport(TestReport testReport,Integer id) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据testPublicId获取集合
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-21 上午11:23:58
	   * @return List<TestResult>
	   * @author dapeng_wu
	 */
	public List<TestsResult> getTestResultByTestPublicId(String testPublicId) throws DataAccessException;

	 /**
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-6-9 下午05:58:53
	   * @return List<TestsResult>
	   * @author dapeng_wu
	   */
	public boolean updateTestsResultIsexit(String userEmail)throws DataAccessException;
}
