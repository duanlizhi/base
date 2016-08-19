/**
 * 
 */
package com.xcj.admin.dao.impl.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tests.TestsDao;
import com.xcj.admin.entity.tests.TestAction;
import com.xcj.admin.entity.tests.TestQuestion;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.common.util.common.StringUtil;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.dao.impl.tests  
 * @fileName com.xcj.*
 * @createDate 2015-3-16 上午11:15:22 
 * @author hehujun 
 * @email hehujun@126.com
 */
@Component("testsDaoImpl")
public class TestsDaoImpl extends BaseDaoImpl implements TestsDao {

	private static final long serialVersionUID = 8561125976416826847L;
	
	@Resource
	private JdbcTemplate jdbcTemplate;

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
	@SuppressWarnings("unchecked")
	public TestsResult getTestsHistoryData(final String userEmail, final String domainUserName, final String testsPublicId, final String courseNumber)
			throws DataAccessException {
		
		List<TestsResult> testsResults = this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT tr.id, tr.tests_posted_id, tr.course_number, tr.user_email, tr.domain_username, tr.session_id, tr.tests_public_id, tr.start_time, tr.progress, tr.score, tr.seconds, tr.character_a, tr.character_b, tr.character_c, tr.is_complete, tr.is_pass, tr.create_date, tr.modify_date, tr.complete_time, tr.pass_time, cw.type FROM test_result tr JOIN course_ware cw ON cw.course_number=tr.course_number")
					.append(" WHERE  tr.user_email = ? AND tr.domain_username = ? AND tr.tests_public_id = ? ");
				if (!StringUtil.isEmptyYl(courseNumber)) {
					sql.append("and tr.course_number = ? ");
				}
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				
				stmt.setString(1, userEmail);
				stmt.setString(2, domainUserName);
				stmt.setString(3, testsPublicId);
				
				if (!StringUtil.isEmptyYl(courseNumber)) {
					stmt.setString(4, courseNumber);
				}
				return stmt;
			}
		}, new RowMapper() {

			public TestsResult mapRow(ResultSet rs, int num) throws SQLException {
				TestsResult testsResult = new TestsResult();
				testsResult.setId(rs.getInt("id"));
				testsResult.setTestsPostedId(rs.getInt("tests_posted_id"));
				testsResult.setUserEmail(rs.getString("user_email"));
				testsResult.setCourseNumber(rs.getString("course_number"));
				testsResult.setDomainUsername(rs.getString("domain_username"));
				testsResult.setSessionId(rs.getString("session_id"));
				testsResult.setTestsPublicId(rs.getString("tests_public_id"));
				testsResult.setStartTime(rs.getDate("start_time"));
				testsResult.setProgress(rs.getFloat("progress"));
				testsResult.setScore(rs.getFloat("score"));
				testsResult.setSeconds(rs.getInt("seconds"));
				testsResult.setCharacterA(rs.getString("character_a"));
				testsResult.setCharacterB(rs.getString("character_b"));
				testsResult.setCharacterC(rs.getString("character_c"));
				testsResult.setIsComplete(rs.getInt("is_complete"));
				testsResult.setIsPass(rs.getInt("is_pass"));
				testsResult.setCreateDate(rs.getTimestamp("create_date"));
				testsResult.setModifyDate(rs.getTimestamp("modify_date"));
				testsResult.setCompleteTime(rs.getDate("complete_time"));
				testsResult.setPassTime(rs.getDate("pass_time"));
				testsResult.setType(rs.getString("type"));
				return testsResult;
			}
		});
		return testsResults.size() > 0 ? testsResults.get(0) : null;
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
	public TestsResult getTestsResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail ,String testsPublicId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, course_number, user_email, domain_username,tests_posted_id, session_id, tests_public_id, start_time, progress, score, seconds, character_a, character_b, character_c, is_complete, is_pass, create_date, modify_date, complete_time, pass_time FROM test_result");
		sql.append(" WHERE course_number=? AND domain_username=? AND user_email=? And tests_public_id=? ORDER BY id DESC LIMIT 1");
		return this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{courseNumer, domainUserName, userEmail ,testsPublicId}, ParameterizedBeanPropertyRowMapper.newInstance(TestsResult.class));
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
	public TestAction getTestActionByActionId(String actionId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, action, status, description, action_id, rank, create_date, modify_date, study_result_id FROM test_action WHERE action_id=?");
		return this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{actionId}, ParameterizedBeanPropertyRowMapper.newInstance(TestAction.class));
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
	public TestQuestion getTestQuestionByActionId(String quesionId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, question, answer, rank, description, question_id, create_date, modify_date, study_result_id FROM test_question WHERE question_id=?");
		return this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{quesionId}, ParameterizedBeanPropertyRowMapper.newInstance(TestQuestion.class));
	}
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午03:20:56
	   * @return List<TestReport>
	   * @author dapeng_wu
	 */
	public List<TestReport> getTestReportList(TestReport testReport) throws DataAccessException {
		String sql = "select * from test_report where course_number = ? and tests_posted_id = ? and tests_public_id = ? and question = ? and user_email = ?";
		return super.findByJDBC(sql, new Object[] {testReport.getCourseNumber(),testReport.getTestsPostedId(),testReport.getTestsPublicId(),testReport.getQuestion(),testReport.getUserEmail()},TestReport.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据id修改testReport
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午03:51:57
	   * @return 
	   * @author dapeng_wu
	 */
	public boolean updateTestReport(TestReport testReport,Integer id) throws DataAccessException{
		String sql = "update test_report set question=?,score=?,tests_posted_id=?,kp=?,kp_parent=?,course_number=?,user_email=? ," +
				" domain_username=?,session_id=?,tests_public_id=?,answer=?,right_answer=? where id = ? ";
		super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {testReport.getQuestion(),testReport.getScore(),testReport.getTestsPostedId(),
				testReport.getKp(),testReport.getKpParent(),testReport.getCourseNumber(),testReport.getUserEmail(),
				testReport.getDomainUsername(),testReport.getSessionId(),testReport.getTestsPublicId(),
				testReport.getAnswer(),testReport.getRightAnswer(),id},TestReport.class);
		return true;
	}
	
	/**
	 * 
	   * <b>function:</b> 根据testPublicId获取集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-21 上午11:23:13
	   * @return List<TestResult>
	   * @author dapeng_wu
	 */
	public List<TestsResult> getTestResultByTestPublicId(String testPublicId) throws DataAccessException {
		String sql = "select test_result.* from test_result where tests_public_id=? ";
		return super.findByJDBC(sql, new Object[] {testPublicId},TestsResult.class);
	}
	/**
	 * 
	   * <b>function:</b> 更新状态
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-6-9 下午05:59:33
	   * @return 
	   * @author dapeng_wu
	 */
	public boolean updateTestsResultIsexit(String userEmail)throws DataAccessException{
		String sql = "update test_result set is_exit = 1 where user_email=? and is_exit=0";
		super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {userEmail},TestsResult.class);
		return true;
	}
}
