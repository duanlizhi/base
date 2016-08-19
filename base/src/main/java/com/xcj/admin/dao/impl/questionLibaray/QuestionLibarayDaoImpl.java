package com.xcj.admin.dao.impl.questionLibaray;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Statement;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.questionLibaray.QuestionLibarayDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.StringUtil;

/**
 *
  * <b>function:</b>  questionLibaray
  * @package com.xcj.admin.dao.impl.questionLibaray.
  * @fileName com.xcj.admin.*
  * @createDate Wed Feb 11 11:48:21 CST 2015
  * @author name yy.niu
 */
@Component("questionLibarayDaoImpl")
public class QuestionLibarayDaoImpl extends BaseDaoImpl implements QuestionLibarayDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
  	/**
	 * 
     * <b>function:</b> 读取SBT故障
     * @project base
     * @package com.xcj.common.util  
     * @fileName @return
     * @createDate February 16, 2015 3:05:04 PM
     * @author yy.niu
	 */
	private static final long serialVersionUID = -9060812214328418188L;

	/**
  	 * <b>function:</b> course_ware课件基本信息---获取list方法
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.courseDateBase.
  	 * @fileName com.xcj.admin.*
  	 * @createDate Mon Feb 02 16:47:20 CST 2015
  	 * @return List<T>
  	 * @author name su_jian
  	 */
  	public <T extends CourseWare> List<T> getAllList() throws DataAccessException {
  		String hql = "from CourseWare";
  		return super.getList(hql);
  	}
  	
  	/**
     * <b>function:</b>查询StudyResult id
     * @project 
     * @package 
     * @fileName 
     * @createDate 2015.4.7.17:09
     * @author yy.niu
     */
	public List<WbtScores> querWbtScores(String id)throws DataAccessException{
		String hql = "from WbtScores where courseWareId="+id+"";
		List<WbtScores> list = super.getList(hql);
		if(list!=null	&&	list.size()>0){
			return list;
		}
		return null;
	}
  	
  	/**
	 * 查询 SbtScores id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtScores> querySbtScores(String id)throws DataAccessException{
		String hql = "from SbtScores where sbtFaultId="+id+"";
		List<SbtScores> list = super.getList(hql);
		if(list!=null	&&	list.size()>0){
			return list;
		}
		return null;
		
	}
  	
  	/**
	 * 查询SbtFault id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtFault> querySbtFault(String id)throws DataAccessException{
		String hql = "from SbtFault where courseWareId="+id+"";
		List<SbtFault> list = super.getList(hql);
		if(list!=null	&&	list.size()>0){
			return list;
		}
		return null;
	}
  	
  	/**
       * <b>function:</b>根据courseNumber
       * @project 
       * @package 
       * @fileName 
       * @createDate 
       * @author yy.niu
  	 */
  	public boolean queryStyResult(String courseNumber,String id)throws DataAccessException{
  		String hql ="from StudyResult where courseNumber='"+courseNumber+"'";
  		String hqls = "from PapersCourse where courseId = '"+id+"'";
		List<PapersCourse> papersCourse = super.getList(hqls);
		List<StudyResult> studyResult = super.getList(hql);
			if(studyResult!=null	&&	studyResult.size()>0	||	papersCourse!=null	&&	papersCourse.size()>0){
			return false;
		}
		return true;
  	}
  	
  	/**
       * <b>function:</b> 查询知识点数据
       * @project 
       * @package 
       * @fileName 
       * @createDate 
       * @author yy.niu
  	 */
  	public KnowledgePoint queryKonwName(String name)throws DataAccessException{
  		String hql = "from KnowledgePoint where code='"+name+"'";
  		List<KnowledgePoint> konwLedge = super.getList(hql);
  		if(konwLedge!=null	&&	konwLedge.size()>0){
  			return konwLedge.get(0);
  		}
  		return null;
  	}
  	
  	/**
       * <b>function:</b>查询 sbt故障主键
       * @project 
       * @package 
       * @fileName 
       * @createDate 
       * @author yy.niu
  	 */
  	public List<SbtFault> querySbtFaultKey(String courseWareId)throws DataAccessException{
  		String hql = "from SbtFault where courseWareId="+courseWareId+"";
  		List<SbtFault> sbtKey = super.getList(hql);
  		if(sbtKey!=null	&&	sbtKey.size()>0){
  			return sbtKey;
  		}
  		return null;
  	}
  	
  	/**
  	 * 根据主键删除指定的实体和外键
  	 * @param <T>
  	 * @param pojo
  	 */
  	public <T> void deleteCourseById(Class<T> entityName,String keyId, Serializable id)throws DataAccessException {
  		delete("delete from "+entityName.getName()+" t where t."+keyId+"=" + id);
  		getSession().flush();
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
   
  	public List<CourseWare> queryCourseWareId(String courseNumber)throws DataAccessException {
  		String hql = "from CourseWare where courseNumber='" + courseNumber+ "'";
  		return super.getList(hql);
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
  	public CourseWare queryCourseNumbers(String courseNumber)throws DataAccessException {
  		String hql = "from CourseWare where courseNumber='" + courseNumber+ "'";
  		List<CourseWare> courseWare = super.getList(hql);
  		if(courseWare!=null	&&	courseWare.size()>0){
  			return courseWare.get(0);
  		}
  		return null;
  	}
  	
  	/**
	 * <b>function:</b>根据courseNumber查询数据是否存在
	 * @project 
	 * @package 
	 * @fileName 
	 * @createDate 
	 * @author yy.niu
  	 */
  	public boolean queryCourseNumber(String courseNumber)throws DataAccessException{
  		String hql = "from CourseWare where courseNumber='"+courseNumber+"'";
  		List<CourseWare> courseWare = super.getList(hql);
  		if(courseWare!=null	&&	courseWare.size()>0){
  			return false;
  		}
  		return true;
  	}
  	
	 /**
	   * <b>function:</b>根据faultNumber查询数据是否存在
	   * @project 
	   * @package 
	   * @fileName 
	   * @createDate 
	   * @author yy.niu
	   */
  	public boolean queryFaultNumber(String faultNumber)throws DataAccessException{
  		String hql = "from SbtFault where faultNumber='"+faultNumber+"'";
  		List<SbtFault> sbtFault = super.getList(hql);
  		if(sbtFault!=null	&&	sbtFault.size()>0){
  			return false;
  		}
  		return true;
  	}
  	
  	/**
  	 * <b>function:</b> course_ware课件基本信息---获取分页list方法
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.courseDateBase.
  	 * @fileName com.xcj.admin.*
  	 * @createDate Mon Feb 02 16:47:20 CST 2015
  	 * @return List<T>
  	 * @author name yy.niu
  	 */
  	@SuppressWarnings("unchecked")
  	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> page,CourseWare courseWare) throws DataAccessException {
  		try {
  			Session session = this.getSession();
  			if (courseWare.getType() != null
  					&& !courseWare.getType().equals("")) {
  				page.setResult(session.createQuery(
  						"from " + courseWare.getClass().getName()
  								+ " where type='"+courseWare.getType()+"'"
  								+ " and category="+courseWare.getCategory()
  								+ " order  by modifyDate").setFirstResult(
  						(page.getCurrentPage() - 1) * page.getPageSize())
  						.setMaxResults(page.getPageSize()).list());
  				page.setTotalsCount(Integer.parseInt(session.createQuery(
  						"select count(id) as cnt from "
  								+ courseWare.getClass().getName()
  								+ " where type='"+courseWare.getType()+"'"
  								+ " and category="+courseWare.getCategory()).setMaxResults(1).uniqueResult()
  						.toString()));
  			} else {
  				page.setResult(session.createQuery(
  						"from " + courseWare.getClass().getName()
   								+ " order by modifyDate ").setFirstResult(// orders改为id
  						(page.getCurrentPage() - 1) * page.getPageSize())
  						.setMaxResults(page.getPageSize()).list());
  				page.setTotalsCount(Integer.parseInt(session.createQuery(
  						"select count(id) as cnt from "
  								+ courseWare.getClass().getName())
  						.setMaxResults(1).uniqueResult().toString()));
  			}
  		} catch (Exception e) {
  			throw new RuntimeException(e);
  		}
  		return page;
  	}

  	/**
  	 * <b>function:</b> course_ware课件基本信息---根据id删除数据方法
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.courseDateBase.
  	 * @fileName com.xcj.admin.*
  	 * @createDate Mon Feb 02 16:47:20 CST 2015
  	 * @return void
  	 * @author name su_jian
  	 */
  	public void removeById(Integer id) throws DataAccessException {
  		super.delete("delete from CourseWare t where t.id=" + id);
  	}

  	/**
  	 * <b>function:</b>
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.course
  	 * @fileName com.xcj.***
  	 * @createDate 2015-2-7 上午11:12:18
  	 * @return
  	 * @author dapeng_wu
  	 */
  	@SuppressWarnings("unchecked")
  	public Page<CourseWare> getByPage(Page<CourseWare> page,
  			CourseWare courseWare, String search) throws DataAccessException {
  		Session session = this.getSession();
  		if (search != null && !(search.equals(""))) {
  			page.setResult(session
  					.createQuery(
  							"from " + courseWare.getClass().getName()
  									+ " where name like '%" + search
  									+ "%' order by id").setFirstResult(
  							(page.getCurrentPage() - 1) * page.getPageSize())
  					.setMaxResults(page.getPageSize()).list());
  			page.setTotalsCount(Integer.parseInt(session.createQuery(
  					"select count(id) as cnt from "
  							+ courseWare.getClass().getName()
  							+ " where name like '%" + search + "%'")
  					.setMaxResults(1).uniqueResult().toString()));
  		} else {
  			page.setResult(session.createQuery(
  					"from " + courseWare.getClass().getName() + " order by id")
  					.setFirstResult(
  							(page.getCurrentPage() - 1) * page.getPageSize())
  					.setMaxResults(page.getPageSize()).list());
  			page.setTotalsCount(Integer.parseInt(session.createQuery(
  					"select count(id) as cnt from "
  							+ courseWare.getClass().getName()).setMaxResults(1)
  					.uniqueResult().toString()));
  		}
  		return page;
  	}
  	
  	/**
  	 * <b>function:</b>题库
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.course
  	 * @fileName com.xcj.***
  	 * @createDate 2015-2-7 上午11:12:18
  	 * @return
  	 * @author yy.niu
  	 */
  	@SuppressWarnings("unchecked")
  	public Page<CourseWare> getByPages(Page<CourseWare> page,
  			CourseWare courseWare, String search) throws DataAccessException {
  		Session session = this.getSession();
  		if (search != null && !(search.equals(""))) {
  			page.setResult(session
  					.createQuery(
  							"from " + courseWare.getClass().getName()
  									+ " where category=2 and name like '%" + search
  									+ "%' order by id desc ").setFirstResult(
  							(page.getCurrentPage() - 1) * page.getPageSize())
  					.setMaxResults(page.getPageSize()).list());
  			page.setTotalsCount(Integer.parseInt(session.createQuery(
  					"select count(id) as cnt from "
  							+ courseWare.getClass().getName()
  							+ " where category=2 and name like '%" + search + "%'")
  					.setMaxResults(1).uniqueResult().toString()));
  		} else {
  			page.setResult(session.createQuery(
  					"from " + courseWare.getClass().getName() + " where category=2 order by id desc")
  					.setFirstResult(
  							(page.getCurrentPage() - 1) * page.getPageSize())
  					.setMaxResults(page.getPageSize()).list());
  			page.setTotalsCount(Integer.parseInt(session.createQuery(
  					"select count(id) as cnt from "
  							+ courseWare.getClass().getName()+" where category=2").setMaxResults(1)
  					.uniqueResult().toString()));
  		}
  		return page;
  	}

  	/**
  	 * <b>function:</b> 根据ids找到集合
  	 * @project base
  	 * @package com.xcj.admin.dao.impl.course
  	 * @fileName com.xcj.***
  	 * @createDate 2015-2-8 上午10:32:54
  	 * @return
  	 * @author dapeng_wu
  	 */
  	public List<CourseWare> getCourseWareByIds(String ids) throws DataAccessException {
  		String sql = "select id,model,model_version,name,course_number,type,purpose," +
  						"requires,keyword,description,duration,category,fileurl,is_enable," +
  						"create_date,modify_date from course_ware where id in ("+ids+")";
  		return super.findByJDBC(sql,  new Object[] {}, CourseWare.class);
  	}
   
  	/** 
  	 * <b>function:</b> 根据courseNumber获取课程信息
  	 * CourseWareDao
  	 * @createDate  2015-2-9 下午05:05:09
  	 * @author su_jian 
  	 * @email sujiansoft@163.com 
  	 * return type CourseWare 
  	 */ 
  	public CourseWare getByCourseNumber(String courseNumber)
  			throws DataAccessException {
  		String hql = "from CourseWare t where t.courseNumber='"+ StringUtil.trimAll(courseNumber)+"'";
  		List<CourseWare> list = super.getList(hql);
  		if (list != null && !list.isEmpty() && list.size() > 0) {
  			return (CourseWare) super.getList(hql).get(0);
  		} else {
  			return null;
  		}
  	}
  	
  	/**
	 * 
	   * <b>function:</b> 保存courseware  返回带id的courseware 
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午01:43:43
	   * @return CourseWare
	   * @author hehujun
	 */
	public CourseWare saveCourseWare(final CourseWare courseWare) throws DataAccessException {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				StringBuilder builder = new StringBuilder();
				builder.append("INSERT INTO course_ware(model, model_version, name, course_number, type, purpose, requires, keyword, description, duration, sum_score, entry, client, version, language, difficulty, category, fileurl, is_enable, create_date, modify_date) VALUES(")
				.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				PreparedStatement stmt = con.prepareStatement(builder.toString(),Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, null!=courseWare.getModel()?courseWare.getModel():null);
				stmt.setString(2, null!=courseWare.getModelVersion()?courseWare.getModelVersion():null);
				stmt.setString(3, null!=courseWare.getName()?courseWare.getName():null);
				stmt.setString(4, null!=courseWare.getCourseNumber()?courseWare.getCourseNumber():null);
				stmt.setString(5, null!=courseWare.getType()?courseWare.getType():null);
				stmt.setString(6, null!=courseWare.getPurpose()?courseWare.getPurpose():null);
				stmt.setString(7, null!=courseWare.getRequires()?courseWare.getRequires():null);
				stmt.setString(8, null!=courseWare.getKeyword()?courseWare.getKeyword():null);
				stmt.setString(9, null!=courseWare.getDescription()?courseWare.getDescription():null);
				stmt.setString(10, null!=courseWare.getDuration()?courseWare.getDuration():null);
				if(null!=courseWare.getSumScore()) {
					stmt.setInt(11, courseWare.getSumScore().intValue());
				} else {
					stmt.setNull(11, Types.NULL);
				}
				stmt.setString(12, null!=courseWare.getEntry()?courseWare.getEntry():null);
				stmt.setString(13, null!=courseWare.getClient()?courseWare.getClient():null);
				stmt.setString(14, null!=courseWare.getVersion()?courseWare.getVersion():null);
				stmt.setString(15, null!=courseWare.getLanguage()?courseWare.getLanguage():null);
				if(null!=courseWare.getDifficulty()) {
					stmt.setInt(16, courseWare.getDifficulty().intValue());
				} else {
					stmt.setNull(16, Types.NULL);
				}
				if(null!=courseWare.getCategory()) {
					stmt.setInt(17, courseWare.getCategory().intValue());
				} else {
					stmt.setNull(17, Types.NULL);
				}
				stmt.setString(18, null!=courseWare.getFileurl()?courseWare.getFileurl():null);
				if(null!=courseWare.getIsEnable()) {
					stmt.setInt(19, courseWare.getIsEnable().intValue());
				} else {
					stmt.setNull(19, Types.NULL);
				}
				stmt.setTimestamp(20, null!=courseWare.getCreateDate()?new Timestamp(courseWare.getCreateDate().getTime()):new Timestamp(System.currentTimeMillis()));
				stmt.setTimestamp(21, null!=courseWare.getModifyDate()?new Timestamp(courseWare.getModifyDate().getTime()):new Timestamp(System.currentTimeMillis()));
				return stmt;
			}
		}, keyHolder);
		courseWare.setId(keyHolder.getKey().intValue());
		return courseWare;
	}
}
