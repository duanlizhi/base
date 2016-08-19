package com.xcj.admin.dao.impl.tests;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tests.PaperDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.DomainCoursePaper;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:53:46
 * @author dapeng_wu
 */
@Component("paperDaoImpl")
public class PaperDaoImpl extends BaseDaoImpl implements PaperDao {

	private static final long serialVersionUID = -7104333170066011416L;
	
	@Resource
	private JdbcTemplate jdbcTemplate; 

	/**
	 * 
	 * <b>function:</b> paper试卷---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends Paper> List<T> getAllList() throws DataAccessException {
		String hql = "from Paper";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> paper试卷---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	@SuppressWarnings("unchecked")
	public Page<Paper> getByPaperPage(Page<Paper> page, Paper paper)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			if (paper.getPaperName() != null
					&& !paper.getPaperName().equals("")) {
				page.setResult(session.createQuery(
						"from " + paper.getClass().getName()
								+ " where name like '%" + paper.getPaperName()
								+ "%' order  by orders").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ paper.getClass().getName()
								+ " where name like '%" + paper.getPaperName()
								+ "%'").setMaxResults(1).uniqueResult()
						.toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + paper.getClass().getName()
								+ " order by orders ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ paper.getClass().getName()).setMaxResults(1)
						.uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * 
	 * <b>function:</b> paper试卷---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from Paper t where t.id=" + id);
	}

	/**
	 * <b>function:</b> 试卷是否禁用
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.person
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午09:41:46
	 * @return void
	 * @author dapeng_wu
	 */
	public boolean updateDisable(String id, String state)
			throws DataAccessException {
		if (state.equals("1")) {
			String sql = "update paper set state = 0 where id =?";
			return 0!=super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] { id },
					Paper.class);
		} else {
			String sql = "update paper set state = 1 where id =?";
			return 0!=super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] { id },
					Paper.class);
		}
	}

	/**
	 * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、page分页对象；返回查询后的list集合；
	 * 
	 * @createDate 2010-03-30 上午11:16:59
	 * @author su_jian
	 * @param queryHql
	 *            list集合结果查询
	 * @param queryCountHql
	 *            总记录调试查询
	 * @param page
	 *            分页对象
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> getByPage(Page<T> page, T entity, String search)
			throws DataAccessException {
		Session session = this.getSession();
		if (search != null && !(search.equals(""))) {
			page.setResult(session.createQuery(
					"from " + entity.getClass().getName()
							+ " where paper_name like '%" + search
							+ "%' order by id").setFirstResult(
					(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ entity.getClass().getName()
							+ " where paper_name like '%" + search + "%'")
					.setMaxResults(1).uniqueResult().toString()));
		} else {
			page.setResult(session.createQuery(
					"from " + entity.getClass().getName() + " order by id")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ entity.getClass().getName()).setMaxResults(1)
					.uniqueResult().toString()));
		}
		return page;
	}

	/**
	 * <b>function:</b> 试卷删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.person
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午09:41:46
	 * @return void
	 * @author dapeng_wu
	 */
	public boolean deletePaper(String ids) throws DataAccessException {
		String sql = "delete from paper where id in (" + ids + ")";
		String deldomainPaperSql = "delete from domain_course_paper where course_paper_id in (" + ids + ")";
		int deldomainPaper = super.saveOrUpdateOrDeleteByJDBC(deldomainPaperSql, new Object[] {}, DomainCoursePaper.class);
		int delPaper = super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {}, Paper.class);
		return 0 != deldomainPaper && 0 != delPaper;
	}

	/**
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 上午09:47:57
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean updatePaper(Paper paper) throws DataAccessException {
		String sql = "update paper set paper_name = ?,papers_type = ?,score = ?,total_time = ? where id = ?";
		return 0 != super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {
				paper.getPaperName(), paper.getPapersType(), paper.getScore(),
				paper.getTotalTime(), paper.getId() }, Paper.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据试卷名称获取集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-19 下午02:01:01
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperByName(String paperName) throws DataAccessException {
		String sql = "select p.id, p.paper_name, p.question_size, p.answer_number, " +
						"p.papers_type, p.score, p.state, p.modify_date, p.create_date, " +
						"p.description, p.version, p.total_time from paper p where paper_name=?";
		return super.findByJDBC(sql, new Object[] {paperName},Paper.class);
	}
	
	/**
	 * 获取分页的试卷列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Paper> getListByPage(String currentPage, String pageSize) {
		int current=(Integer.parseInt(currentPage)-1)*(Integer.parseInt(pageSize));
		String sql="select * from paper limit "+current+","+pageSize;
		List<Paper> paperList = super.findByJDBC(sql, new Object[]{}, Paper.class);
		return paperList;
	}
	
	/**
	 * 
	   * <b>function:</b> 获取当前域 已选的测评 分页
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:53:31
	   * @return Page<Paper>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public Page<Paper> getSelectedPapersPage(Page<Paper> page, String search, Integer domainId) throws DataAccessException {
		Session session = this.getSession();
		StringBuilder hql0 = new StringBuilder();
		StringBuilder hql1 = new StringBuilder();
		hql0.append("SELECT DISTINCT p.id, p.paper_name, p.question_size, p.answer_number, p.papers_type, p.score, p.state, p.modify_date, p.create_date, p.description, p.version, p.total_time")
			.append(" FROM paper p JOIN domain_course_paper dcp ON p.id=dcp.course_paper_id JOIN domain d ON dcp.domain_id=d.id ")
			.append(" WHERE dcp.domain_id =:domainId ");
		hql1.append("SELECT COUNT(1) AS cnt ")
			.append(" FROM paper p JOIN domain_course_paper dcp ON p.id=dcp.course_paper_id JOIN domain d ON dcp.domain_id=d.id ")
			.append(" WHERE dcp.domain_id =:domainId ");
		if (null!=search && !"".equals(search)) {
			hql0.append(" AND p.paper_name LIKE :search ");
			hql1.append(" AND p.paper_name LIKE :search ");
		}
		hql0.append(" ORDER BY p.id");
		
		if (null!=search && !"".equals(search)) {
			page.setResult(session.createSQLQuery(hql0.toString())
				.addEntity(Paper.class)
				.setString("search", "%"+search+"%")
				.setInteger("domainId", domainId)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createSQLQuery(hql1.toString())
				.setString("search", "%"+search+"%")
				.setInteger("domainId", domainId)
				.setMaxResults(1).uniqueResult().toString()));
		} else {
			page.setResult(session.createSQLQuery(hql0.toString())
				.addEntity(Paper.class)
				.setInteger("domainId", domainId)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize())
				.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createSQLQuery(hql1.toString())
				.setInteger("domainId", domainId)
				.setMaxResults(1).uniqueResult().toString()));
		}
		return page;
	}
	
	/**
	 * 
	   * <b>function:</b> 获取当前域所有已选择的测评
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:54:03
	   * @return List<Paper>
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public List<Paper> getAllSelectedPapers(Integer domainId) throws DataAccessException {
		Session session = this.getSession();
		StringBuilder hql0 = new StringBuilder();
		hql0.append("SELECT DISTINCT p.id, p.paper_name, p.question_size, p.answer_number, p.papers_type, p.score, p.state, p.modify_date, p.create_date, p.description, p.version, p.total_time")
			.append(" FROM paper p JOIN domain_course_paper dcp ON p.id=dcp.course_paper_id JOIN domain d ON dcp.domain_id=d.id ")
			.append(" WHERE dcp.domain_id =:domainId ");
		
		return session.createSQLQuery(hql0.toString()).addEntity(Paper.class).setInteger("domainId", domainId).list();
	}
	
	/**
	 * 
	   * <b>function:</b> 根据domainId 删除域与测评库关联表中的数据 
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:55:47
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCoursePaper(Integer domainId) throws DataAccessException {
		boolean bo = false;
		try {
			jdbcTemplate.execute("DELETE FROM domain_course_paper WHERE domain_id = "+domainId);
			bo = true;
		} catch (Exception e) {
			bo = false;
			throw new RuntimeException(e);
		}
		return bo;
	}
	
	/**
	 * 
	   * <b>function:</b> 批量插入domain_course_paper表
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:56:03
	   * @return boolean
	   * @author hehujun
	 */
	public boolean updateDomainCoursePaper(final Integer domainId, final String paperIds) throws DataAccessException {
		boolean flag = false;
		try {
			jdbcTemplate.batchUpdate("INSERT INTO domain_course_paper(domain_id,course_paper_id) VALUES(?,?)", new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement stmt, int i) throws SQLException {
					stmt.setInt(1, domainId);
					stmt.setInt(2, Integer.parseInt(paperIds.split(",")[i]));
				}
				
				public int getBatchSize() {
					return paperIds.split(",").length;
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
	   * <b>function:</b> 根据试卷名称获取courseware集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午03:38:31
	   * @return 
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByPaperId(Integer parseId)throws DataAccessException{
		String sql = "select course_ware.* from paper join papers_course on paper.id = papers_course.paper_id "+
							"join course_ware on course_ware.id = papers_course.course_id where paper.id=?";
		return super.findByJDBC(sql, new Object[] {parseId},CourseWare.class);
	}
}
