package com.xcj.admin.dao.impl.study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.study.StudyDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.SbtQuestion;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.vo.StudyHistory;
import com.xcj.common.util.common.StringUtil;

/**
 * 
  * <b>function:</b> 
  * @project base 
  * @package com.xcj.admin.dao.impl.study  
  * @fileName com.xcj.*
  * @createDate 2015-2-6 下午01:21:49 
  * @author hehujun 
  * @email hehujun@126.com
 */
@Component("studyDaoImpl")
public class StudyDaoImpl extends BaseDaoImpl implements StudyDao {
	
	private static final long serialVersionUID = -8791418515451529873L;

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	   * <b>function:</b> getCourseWares()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-6 下午01:21:34
	   * @return List<T>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public <T extends CourseWare> List<T> getCourseWares(final String domainName, final String type) {
		
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select cw.id, cw.model, cw.model_version, cw.name, cw.course_number, cw.type, cw.purpose, cw.requires, cw.keyword, cw.description, cw.duration, cw.sum_score, cw.entry, cw.client, cw.version, cw.language, cw.difficulty, cw.category, cw.fileurl, cw.is_enable, cw.create_date, cw.modify_date from domain d left join domain_course_ware dcp on d.id = dcp.domain_id join course_ware cw on dcp.course_ware_id = cw.id ");
				
				if (!StringUtil.isEmptyYl(type) && StringUtil.isEmptyYl(domainName)) {
					sql.append(" where ");
					sql.append(" cw.type = ? ");
				}
				if (!StringUtil.isEmptyYl(domainName) && StringUtil.isEmptyYl(type)) {
					sql.append(" where ");
					sql.append(" d.username = ? ");
				}
				if (!StringUtil.isEmptyYl(domainName) && !StringUtil.isEmptyYl(type)) {
					sql.append(" where ");
					sql.append(" cw.type = ? ");
					sql.append(" and ");
					sql.append(" d.username = ? ");
				}
				
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				if (!StringUtil.isEmptyYl(type) && StringUtil.isEmptyYl(domainName)) {
					stmt.setString(1, type);
				}
				if (!StringUtil.isEmptyYl(domainName) && StringUtil.isEmptyYl(type)) {
					stmt.setString(1, domainName);
				}
				if (!StringUtil.isEmptyYl(domainName) && !StringUtil.isEmptyYl(type)) {
					stmt.setString(1, type);
					stmt.setString(2, domainName);
				}
				return stmt;
			}
		}, new RowMapper() {
			
			public CourseWare mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseWare courseWare = new CourseWare();
				courseWare.setCategory(rs.getInt("category"));
				courseWare.setClient(rs.getString("client"));
				courseWare.setCourseNumber(rs.getString("course_number"));
				courseWare.setCreateDate(rs.getTimestamp("create_date"));
				courseWare.setDescription(rs.getString("description"));
				courseWare.setDifficulty(rs.getInt("difficulty"));
				courseWare.setDuration(rs.getString("duration"));
				courseWare.setEntry(rs.getString("entry"));
				courseWare.setFileurl(rs.getString("fileUrl"));
				courseWare.setId(rs.getInt("id"));
				courseWare.setIsEnable(rs.getInt("is_enable"));
				courseWare.setKeyword(rs.getString("keyword"));
				courseWare.setLanguage(rs.getString("language"));
				courseWare.setModel(rs.getString("model"));
				courseWare.setModelVersion(rs.getString("model_version"));
				courseWare.setModifyDate(rs.getTimestamp("modify_date"));
				courseWare.setName(rs.getString("name"));
				courseWare.setSumScore(rs.getInt("sum_score"));
				courseWare.setPurpose(rs.getString("purpose"));
				courseWare.setRequires(rs.getString("requires"));
				courseWare.setType(rs.getString("type"));
				courseWare.setVersion(rs.getString("version"));
				return courseWare;
			}
		});
	}

	/**
	 * 
	   * <b>function:</b> getStudyTotalInfos()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-6 下午01:21:38
	   * @return StudyHistory
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public StudyHistory getStudyTotalInfos(final String userEmail, final String courseNum,final String faultNumber,final String studyFlag)
			throws DataAccessException {
		
		List<StudyHistory> studyHistorys = this.jdbcTemplate.query(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select tsi.id, tsi.course_number, tsi.user_email, tsi.domain_username, tsi.top_progress, tsi.top_score, tsi.seconds, tsi.is_complete, tsi.is_pass, tsi.complete_time, tsi.pass_time, tsi.character_a, tsi.character_b, tsi.character_c, tsi.create_date, tsi.modify_date,cw.type, tsi.fault_number fault_number, tsi.study_flag study_flag from total_study_info tsi left join course_ware cw on tsi.course_number = cw.course_number ");
				sql.append(" where tsi.user_email = ?  ");
				sql.append(" and tsi.course_number = ? ");
				sql.append(" and tsi.fault_number = ? ");
				sql.append(" and tsi.study_flag = ? ");
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, userEmail);
				stmt.setString(2, courseNum);
				stmt.setString(3, faultNumber);
				stmt.setString(4, studyFlag);

				return stmt;
			}
		}, new RowMapper() {

			public StudyHistory mapRow(ResultSet rs, int num) throws SQLException {
				StudyHistory studyHistory = new StudyHistory();
				studyHistory.setId(rs.getInt("id"));
				studyHistory.setCharacterA(rs.getString("character_a"));
				studyHistory.setCharacterB(rs.getString("character_b"));
				studyHistory.setCharacterC(rs.getString("character_c"));
				studyHistory.setCompleteTime(rs.getString("complete_time"));
				studyHistory.setCourseName(rs.getString("course_number"));
				studyHistory.setCreateDate(rs.getTimestamp("create_date"));
				studyHistory.setDomainName(rs.getString("domain_username"));
				studyHistory.setIsComplete(rs.getInt("is_complete"));
				studyHistory.setIsPass(rs.getInt("is_pass"));
				studyHistory.setModifyDate(rs.getTimestamp("modify_date"));
				studyHistory.setPassTime(rs.getString("pass_time"));
				studyHistory.setSeconds(rs.getInt("seconds"));
				studyHistory.setTopProgress(rs.getInt("top_progress"));
				studyHistory.setTopScore(rs.getInt("top_score"));
				studyHistory.setType(rs.getString("type"));
				studyHistory.setUserEmail(rs.getString("user_email"));
				studyHistory.setFaultNumber(rs.getString("fault_number"));
				studyHistory.setStudyFlag(rs.getString("study_flag"));
				return studyHistory;
			}
		});
		return studyHistorys.size() > 0 ? studyHistorys.get(0) : null;
	}

	/**
	 * 
	   * <b>function:</b> getAndSaveStudyScores()
	   * @project base
	   * @package com.xcj.admin.dao.study  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-2-8 上午09:50:31
	   * @return List<T>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public List<StudyResult> getAndSaveStudyScores(final List<StudyResult> results)
			throws DataAccessException {
		
		List<StudyResult> studyScores = new ArrayList<StudyResult>();
		
		for (int i=0;i<results.size();i++) {
			final int x = i;
			List<StudyResult> queryScores = (List<StudyResult>)jdbcTemplate.query(new PreparedStatementCreator() {
				
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					StringBuilder sql = new StringBuilder();
					sql.append(" SELECT `id`, `course_number`, `user_email`, `domain_name`, `session_id`, `start_time`, `progress`, `score`, `seconds`,`character_a`,`character_b`,`character_c`, `is_complete`, `is_pass`, `create_date`, `modify_date`, `complete_time`, `pass_time` from study_result where course_number=? and user_email=? and session_id=? ");
					PreparedStatement stmt = conn.prepareStatement(sql.toString());
					stmt.setString(1, results.get(x).getCourseNumber());
					stmt.setString(2, results.get(x).getUserEmail());
					stmt.setString(3, results.get(x).getSessionId());
					return stmt;
				}
			}, new RowMapper() {

				public StudyResult mapRow(ResultSet rs, int num) throws SQLException {
					StudyResult score = new StudyResult();
					score.setCharacterA(rs.getString("character_a"));
					score.setCharacterB(rs.getString("character_b"));
					score.setCharacterC(rs.getString("character_c"));
					score.setCompleteTime(rs.getTimestamp("complete_time"));
					score.setCourseNumber(rs.getString("course_number"));
					score.setCreateDate(rs.getTimestamp("create_date"));
					score.setDomainUsername(rs.getString("domain_username"));
					score.setId(rs.getInt("id"));
					score.setIsComplete(rs.getInt("is_complete"));
					score.setIsPass(rs.getInt("is_pass"));
					score.setModifyDate(rs.getTimestamp("modify_date"));
					score.setPassTime(rs.getTimestamp("pass_time"));
					score.setProgress(rs.getInt("progress"));
					score.setScore(rs.getInt("score"));
					score.setSeconds(rs.getInt("seconds"));
					score.setSessionId(rs.getString("session_id"));
					score.setStartTime(rs.getTimestamp("start_time"));
					score.setUserEmail(rs.getString("user_email"));
					return score;
				}
			});
			
			// TODO  1 数据返回计算后的 	2 更新库
			if (queryScores.size() > 0) {
				final StudyResult score = queryScores.get(0);
				score.setProgress(score.getProgress()+results.get(x).getProgress());
				score.setScore(score.getScore()+results.get(x).getScore());
				score.setSeconds(score.getSeconds()+results.get(x).getSeconds());
				studyScores.add(score);
				
				this.jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						StringBuilder sql = new StringBuilder();
						sql.append(" update study_result set progress=?,score=?,seconds=? where course_number=? and user_email=? and session_id=? ");
						PreparedStatement stmt = conn.prepareStatement(sql.toString());
						stmt.setInt(1, score.getProgress());
						stmt.setInt(2, score.getScore());
						stmt.setInt(3, score.getSeconds());
						stmt.setString(4, score.getCourseNumber());
						stmt.setString(5, score.getUserEmail());
						stmt.setString(6, score.getSessionId());
						return stmt;
					}
				});
			} else {
				studyScores.add(results.get(x));
				final StudyResult score = results.get(x);
				this.jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn)
							throws SQLException {
						StringBuilder sql = new StringBuilder();
						sql.append(" insert into study_result set course_number=?, user_email=?, domain_number=?, session_id=?, start_time=?, progress=?, score=?, seconds=?,character_a=?,character_b=?,character_c=?, is_complete=?, is_pass=?, complete_time=?, pass_time=? ");
						PreparedStatement stmt = conn.prepareStatement(sql.toString());
						stmt.setString(1, score.getCourseNumber());
						stmt.setString(2, score.getUserEmail());
						stmt.setString(3, score.getDomainUsername());
						stmt.setString(4, score.getSessionId());
						stmt.setTimestamp(5, new Timestamp(score.getStartTime().getTime()));
						stmt.setInt(6, score.getProgress());
						stmt.setInt(7, score.getScore());
						stmt.setInt(8, score.getSeconds());
						stmt.setString(9, score.getCharacterA());
						stmt.setString(10, score.getCharacterB());
						stmt.setString(11, score.getCharacterC());
						stmt.setInt(12, score.getIsComplete());
						stmt.setInt(13, score.getIsPass());
						stmt.setTimestamp(14, new Timestamp(score.getCompleteTime().getTime()));
						stmt.setTimestamp(15, new Timestamp(score.getPassTime().getTime()));
						return stmt;
					}
				});
			}
		}
		return studyScores;
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
	public StudyResult getStudyResultBeforeSaveActionOrAnswer(String courseNumer, String domainUserName, String userEmail, String sessionId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, course_number, user_email, domain_username, session_id, start_time, progress, score, seconds, character_a, character_b, character_c, is_complete, is_pass, create_date, modify_date, complete_time, pass_time FROM study_result");
		sql.append(" WHERE course_number=? AND domain_username=? AND user_email=? AND session_id=? ORDER BY id DESC LIMIT 1");
		return this.jdbcTemplate.queryForObject(sql.toString(), new Object[]{courseNumer, domainUserName, userEmail, sessionId}, ParameterizedBeanPropertyRowMapper.newInstance(StudyResult.class));
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
	public CourseWareAction getCourseWareActionByActionId(String actionId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, action_id, description, rank, code, TYPE, create_date, modify_date, course_ware_id FROM course_ware_action WHERE action_id=? limit 1");
		List<CourseWareAction> courseWareActionList = super.findByJDBC(sql.toString(), new Object[]{actionId},CourseWareAction.class);
		if(courseWareActionList != null && courseWareActionList.size()>0){
			return courseWareActionList.get(0);
		}
		return null;
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
	public CourseWareParts getCourseWarePartsByActionId(String partId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, part_id, state_id, description, rank, code, TYPE, create_date, modify_date, course_ware_id FROM course_ware_parts WHERE state_id=?");
		List<CourseWareParts> courseWarePartsList = super.findByJDBC(sql.toString(), new Object[]{partId},CourseWareParts.class);
		if(courseWarePartsList != null && courseWarePartsList.size()>0){
			return courseWarePartsList.get(0);
		}
		return null;
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
	public CourseWareTool getCourseWareToolByActionId(String partId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, tool_id, state_id, description, rank, code, `type`, create_date, modify_date, course_ware_id FROM course_ware_tool WHERE state_id=?");
		List<CourseWareTool> courseWareToolList = super.findByJDBC(sql.toString(), new Object[]{partId},CourseWareTool.class);
		if(courseWareToolList != null && courseWareToolList.size()>0){
			return courseWareToolList.get(0);
		}
		return null;
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
	public SbtQuestion getSbtQuestionByQuestionId(String questionId) throws DataAccessException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, question_id, description, rank, create_date, modify_date, sbt_fault_id FROM sbt_question WHERE question_id=?");
		List<SbtQuestion> sbtQuestionList = super.findByJDBC(sql.toString(), new Object[]{questionId},SbtQuestion.class);
		if(sbtQuestionList != null && sbtQuestionList.size()>0){
			return sbtQuestionList.get(0);
		}
		return null;
	}
	
}