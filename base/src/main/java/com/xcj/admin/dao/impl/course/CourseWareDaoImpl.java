package com.xcj.admin.dao.impl.course;

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
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Statement;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.course.CourseWareDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.SbtTestScore;
import com.xcj.admin.entity.course.StudyResult;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.StringUtil;
 

/**
 * 
 * <b>function:</b> course_ware课件基本信息
 * 
 * @package com.xcj.admin.dao.impl.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:20 CST 2015
 * @author name yy.niu
 */
@Component("courseWareDaoImpl")
public class CourseWareDaoImpl extends BaseDaoImpl implements CourseWareDao {

	/**
		 * 
	     * <b>function:</b> 读取SBT故障
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = -4480471803163002229L;
	
	@Resource
	private JdbcTemplate jdbcTemplate; 

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * @project base
	 * @package com.xcj.admin.dao.impl.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:20 CST 2015
	 * @return List<T>
	 * @author name yy.niu
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
		if(studyResult!=null	&&	studyResult.size()>0	||	
			papersCourse!=null	&&	papersCourse.size()>0){
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
		String hql = "from CourseWare where courseNumber='" + courseNumber+ "' and isEnable=1" ;
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
		String hql = "from CourseWare where is_enable=1 and courseNumber='" + courseNumber+ "'";
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
		String hql = "from CourseWare where is_enable=1 and courseNumber='"+courseNumber+"'";
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
		String hql = "from SbtFault where is_enable=1 and faultNumber='"+faultNumber+"'";
		List<SbtFault> sbtFault = super.getList(hql);   //SBT_VER_ES_EC_001_FAULT
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
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> page,CourseWare courseWare) throws DataAccessException {
		    try {
		    	//TODO 当前用户是否是某一个域下面的信息
		    	StringBuffer sql=new StringBuffer();
				sql.append(" select c.* from course_ware c left join domain_course_ware d on c.id=d.course_ware_id ");
		    	sql.append(" where d.domain_id='"+courseWare.getId()+"' ");
		    	sql.append(" and c.category=").append(courseWare.getCategory());
		    	sql.append(" and c.is_enable=1 ");
		    	if(!ApiUtil.isParamNull(courseWare.getCourseNumber())){
		    		sql.append(" and c.course_number = '").append(courseWare.getCourseNumber()).append("'");
		    	}
				List<CourseWare> courseWares = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<CourseWare>(CourseWare.class));  
			    page.setResult(courseWares);
			    page.setTotalsCount(courseWares.size());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		/*try {
			Session session = this.getSession();
			//TODO 需要修改的方法
			if (courseWare.getType() != null
					&& !courseWare.getType().equals("")) {
				page.setResult(session.createQuery(
						"from " + courseWare.getClass().getName()+" DomainCourseWare "
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
						"from " + courseWare.getClass().getName()+ " where category="+courseWare.getCategory()
 								+ " order by modifyDate ").setFirstResult(// orders改为id
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ courseWare.getClass().getName() + " where category="+courseWare.getCategory())
						.setMaxResults(1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
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
									+ " where category=2 and name like '%" + search
									+ "%' order by id").setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ courseWare.getClass().getName()
							+ " where category=2 and name like '%" + search + "%'")
					.setMaxResults(1).uniqueResult().toString()));
		} else {
			page.setResult(session.createQuery(
					"from " + courseWare.getClass().getName() + " where category=2 order by id")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ courseWare.getClass().getName() +" where category=2 ").setMaxResults(1)
					.uniqueResult().toString()));
		}
		return page;
	}

	
	/**
	 * 
	 * 
     * <b>function:</b>课程库分页
     * @project 
     * @package 
     * @fileName 
     * @createDate 
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
									+ " where category=1 and is_enable=1 and name like '%" + search
									+ "%' order by id desc").setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ courseWare.getClass().getName()
							+ " where category=1 and is_enable=1 and name like '%" + search + "%'")
					.setMaxResults(1).uniqueResult().toString()));
		} else {
			page.setResult(session.createQuery(
					"from " + courseWare.getClass().getName() + " where category=1 and is_enable=1  order by id desc")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ courseWare.getClass().getName()+" where category=1 and is_enable=1 ").setMaxResults(1)
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
		String sql = "select * from course_ware where is_enable=1 and category=2 and id in ("+ids+")";
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
		String hql = "from CourseWare t where t.isEnable=1 and t.courseNumber='"+ StringUtil.trimAll(courseNumber)+"'";
		List<CourseWare> list = super.getList(hql);
		if (list != null && !list.isEmpty() && list.size() > 0) {
			return (CourseWare) super.getList(hql).get(0);
		} else {
			return null;
		}
	}

	/**
	 * 
	   * <b>function:</b> 当前类别下已选择课程(分页)
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-2 下午06:24:29
	   * @return Page<CourseWare>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public Page<CourseWare> getSelectedCoursesPage(Page<CourseWare> page,Integer category, String search, Integer domainId) throws DataAccessException {
		Session session = this.getSession();
		StringBuilder hql0 = new StringBuilder();
		StringBuilder hql1 = new StringBuilder();
		hql0.append("SELECT DISTINCT cw.id, cw.model, cw.model_version, cw.name, cw.course_number, cw.type, cw.purpose, cw.requires, cw.keyword, cw.description, cw.duration, cw.sum_score, cw.entry, cw.client, cw.version, cw.language, cw.difficulty, cw.category, cw.fileurl, cw.is_enable, cw.create_date, cw.modify_date,cw.pass_condition,cw.complete_condition ")
			.append(" FROM course_ware cw JOIN domain_course_ware dcw ON cw.id=dcw.course_ware_id JOIN domain d ON dcw.domain_id=d.id ")
			.append(" WHERE cw.category=:category AND dcw.domain_id =:domainId AND cw.is_enable=1 ");
		hql1.append("SELECT COUNT(1) AS cnt ")
			.append(" FROM course_ware cw JOIN domain_course_ware dcw ON cw.id=dcw.course_ware_id JOIN domain d ON dcw.domain_id=d.id ")
			.append(" WHERE cw.category=:category AND dcw.domain_id =:domainId AND cw.is_enable=1 ");
		if (null!=search && !"".equals(search)) {
			hql0.append(" AND cw.name LIKE :search ");
			hql1.append(" AND cw.name LIKE :search ");
		}
		hql0.append(" ORDER BY cw.id");
		
		if (null!=search && !"".equals(search)) {
			page.setResult(session.createSQLQuery(hql0.toString())
				.addEntity(CourseWare.class)
				.setString("search", "%"+search+"%")
				.setInteger("domainId", domainId)
				.setInteger("category", category)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createSQLQuery(hql1.toString())
				.setString("search", "%"+search+"%")
				.setInteger("domainId", domainId)
				.setInteger("category", category)
				.setMaxResults(1).uniqueResult().toString()));
		} else {
			page.setResult(session.createSQLQuery(hql0.toString())
				.addEntity(CourseWare.class)
				.setInteger("domainId", domainId)
				.setInteger("category", category)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createSQLQuery(hql1.toString())
				.setInteger("domainId", domainId)
				.setInteger("category", category)
				.setMaxResults(1).uniqueResult().toString()));
		}
		return page;
	}



	/**
	 * 
	   * <b>function:</b> 获取当前类别下所有已选的课程
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-3 下午01:20:33
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public List<CourseWare> getAllSelectedCourses(Integer category, Integer domainId) throws DataAccessException {
		
		Session session = this.getSession();
		StringBuilder hql0 = new StringBuilder();
		hql0.append("SELECT DISTINCT cw.id, cw.model, cw.model_version, cw.name, cw.course_number, cw.type, cw.purpose, cw.requires, cw.keyword, cw.description, cw.duration, cw.sum_score, cw.entry, cw.client, cw.version, cw.language, cw.difficulty, cw.category, cw.fileurl, cw.is_enable, cw.create_date, cw.modify_date")
			.append(" FROM course_ware cw JOIN domain_course_ware dcw ON cw.id=dcw.course_ware_id JOIN domain d ON dcw.domain_id=d.id ")
			.append(" WHERE cw.category=? AND dcw.domain_id =? AND cw.is_enable=1");
		return super.findByJDBC(hql0.toString(), new Object[]{category,domainId}, CourseWare.class);
		//return session.createSQLQuery(hql0.toString()).addEntity(CourseWare.class).setInteger("domainId", domainId).setInteger("category", category).list();
	}



	/**
	 * 
	   * <b>function:</b> 根据domainId 删除域与课程库关联表中的数据 
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 上午10:10:06
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCourseWare(Integer domainId)
			throws DataAccessException {
		boolean bo = false;
		try {
			jdbcTemplate.execute("DELETE FROM domain_course_ware WHERE domain_id = "+domainId);
			bo = true;
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}

	/**
	 * 
	   * <b>function:</b> 批量插入domain_course_ware表
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 上午11:19:07
	   * @return boolean
	   * @author hehujun
	 */
	public boolean updateDomainCourseWare(final Integer domainId, final String courseWareIds)
			throws DataAccessException {
		boolean flag = false;
		try {
			jdbcTemplate.batchUpdate("INSERT INTO domain_course_ware(domain_id,course_ware_id) VALUES(?,?)", new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement stmt, int i) throws SQLException {
					stmt.setInt(1, domainId);
					stmt.setInt(2, Integer.parseInt(courseWareIds.split(",")[i]));
				}
				
				public int getBatchSize() {
					return courseWareIds.split(",").length;
				}
			});
			flag = true;			
		} catch (Exception e) {
			flag = false;
			throw new RuntimeException(e);
		}
		
		return flag;
	}
	
	/**
     * <b>function:</b>根据userName查询 courseWare数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public List<?> getCourseWareList(String userName)throws DataAccessException{
		String sql = "SELECT T5.id,T5.course_number,T5.type FROM " +
					 "domain T,domain_course_paper T2," +
					 "paper T3,papers_course T4,course_ware T5 " +
					 "WHERE T.id=T2.domain_id AND T3.id=T2.course_paper_id AND " +
					 "T3.id=T4.paper_id AND T4.course_id=T5.id  " +
					 "AND T.username=? And T5.category=?";
		return super.findByJDBC(sql,  new Object[]{userName,2}, CourseWare.class);
	}
	
	/**
	   * <b>function:</b> 根据域名称获取课件集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午05:37:01
	   * @return 
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithDominUsername(String domainUsername,Integer category) throws DataAccessException{
		String sql = "select course_ware.* from course_ware join domain_course_ware on domain_course_ware.course_ware_id = course_ware.id"+
                          " join domain on domain.id = domain_course_ware.domain_id where domain.username = ? and course_ware.category = ?";
		return super.findByJDBC(sql,  new Object[]{domainUsername,category}, CourseWare.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据域名称获取试卷paper集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 上午11:55:05
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperListWithDominUsername(String domainUsername) throws DataAccessException{
		String sql = "select distinct paper.* from domain join domain_course_paper on domain.id = domain_course_paper.domain_id "+
					"join paper on domain_course_paper.course_paper_id = paper.id " +
					"join tests_posted on tests_posted.paper_id = paper.id where domain.username = ?";
		return super.findByJDBC(sql, new Object[]{domainUsername}, Paper.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据paper获取已经发布的题库集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午02:26:28
	   * @return List<CourseWare>
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithPaper(Paper paper) throws DataAccessException{
		String sql = "select distinct course_ware.* from paper join papers_course on paper.id = papers_course.paper_id "+
                    "join course_ware on course_ware.id = papers_course.course_id "+                    
                    "join tests_posted on tests_posted.paper_id = paper.id "+                     
                    "where tests_posted.paper_id=? and paper.id=?";
		return super.findByJDBC(sql,  new Object[]{paper.getId(),paper.getId()}, CourseWare.class);
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
				builder.append("INSERT INTO course_ware  (`model`, `model_version`, `name`, `course_number`, `type`, `purpose`, `requires`, `keyword`, `description`, `duration`,`sum_score`, `entry`, `client`, `version`, `language`,`difficulty`, `category`, `fileurl`,`is_enable`, `create_date`, `modify_date`,`pass_condition`,`complete_condition`) VALUES (")
				.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
				stmt.setInt(22, null!=courseWare.getPassCondition()?courseWare.getPassCondition():0);
				stmt.setInt(23, null!=courseWare.getCompleteCondition()?courseWare.getCompleteCondition():0);
				return stmt;
			}
		}, keyHolder);
		courseWare.setId(keyHolder.getKey().intValue());
		return courseWare;
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>sbt故障</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、sbt故障</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年12月23日 上午9:54:06</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<SbtFault> getSbtFaultByCourseWareIds(String cwids)throws DataAccessException {
		String hql = "from SbtFault where courseWareId in ("+cwids+")";
		return super.getList(hql);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>修改课件禁用</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、修改课件禁用</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月25日 下午4:43:57</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void updateCourseWareByIds(String ids)throws DataAccessException{
		String sql="update course_ware set is_enable=0 where id in ("+ids+")";
		String sql2="update sbt_fault set is_enable=0 where course_ware_id in ("+ids+")";
		String sql3="update sbt_test_score set is_enable=0 where course_ware_id in ("+ids+")";
		super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{}, SbtFault.class);
		super.saveOrUpdateOrDeleteByJDBC(sql2, new Object[]{}, CourseWare.class);
		super.saveOrUpdateOrDeleteByJDBC(sql3, new Object[]{}, SbtTestScore.class);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据故障编号删除有效的故障</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>根据故障编号删除有效的故障</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月26日 下午8:36:13</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void deleteSbtFault(String faultNumber) throws DataAccessException{
		String sql="delete from sbt_fault where fault_number=? and is_enable=1";
		super.saveOrUpdateOrDeleteByJDBC(sql, new Object[]{faultNumber}, SbtFault.class);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据故障编号查询到分数编号</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1.根据故障编号查询到分数编号</dd>/dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年3月2日 下午8:13:55</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<SbtScores> getSbtScoreByFaultNumber(String faultNumber) throws DataAccessException{
		String sql = "select DISTINCT sbt_scores.* from sbt_scores,sbt_fault where sbt_scores.sbt_fault_id=sbt_fault.id  and sbt_fault.fault_number=?";
		return super.findByJDBC(sql, new Object[]{faultNumber}, SbtScores.class);
	}
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据ids获取courseware集合</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、根据ids获取courseware集合</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年3月31日 下午1:53:26</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<CourseWare> getCourseWareListByids(String ids) throws DataAccessException{
		String sql = "select * from course_ware where is_enable=1 and id in ("+ids+")";
		return super.findByJDBC(sql,  new Object[] {}, CourseWare.class);
	} 
}

