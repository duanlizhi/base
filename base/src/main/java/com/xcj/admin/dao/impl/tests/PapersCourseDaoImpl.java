package com.xcj.admin.dao.impl.tests;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tests.PapersCourseDao;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:53:57
 * @author dapeng_wu
 */
@Component("papersCourseDaoImpl")
public class PapersCourseDaoImpl extends BaseDaoImpl implements PapersCourseDao {
	private static final long serialVersionUID = 2572028267050052197L;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends PapersCourse> List<T> getAllList()
			throws DataAccessException {
		String hql = "from PapersCourse";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	@SuppressWarnings("unchecked")
	public Page<PapersCourse> getByPapersCoursePage(Page<PapersCourse> page,
			PapersCourse papersCourse) throws DataAccessException {
		try {
			Session session = this.getSession();
			if (papersCourse.getId() != null
					&& !papersCourse.getId().equals("")) {
				page.setResult(session.createQuery(
						"from " + papersCourse.getClass().getName()
								+ " where name like '%" + papersCourse.getId()
								+ "%' order  by orders").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ papersCourse.getClass().getName()
								+ " where name like '%" + papersCourse.getId()
								+ "%'").setMaxResults(1).uniqueResult()
						.toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + papersCourse.getClass().getName()
								+ " order by orders ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ papersCourse.getClass().getName())
						.setMaxResults(1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from PapersCourse t where t.id=" + id);
	}

	/**
	 * <b>function:</b> 根据paper_id批量删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:56:40
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deletePapersCourse(String ids) throws DataAccessException {
		String sql = "delete from papers_course where paper_id in (" + ids
				+ ")";
		return 0 != super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {},
				PapersCourse.class);
	}

	/**
	 * <b>function:</b> 根据paper_id获得中间表集合
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 下午04:48:51
	 * @return List<PapersCourse>
	 * @author dapeng_wu
	 */
	public List<PapersCourse> getPapersCourseByPaperId(String id)
			throws DataAccessException {
		String sql = "select * from papers_course where paper_id =?";
		return super.findByJDBC(sql, new Object[] { id }, PapersCourse.class);
	}

	/**
	 * 
	 * <b>function:</b>批量添加
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 下午02:00:23
	 * @return
	 * @author dapeng_wu
	 */
	public void savePapersCourse(Integer paperId, String[] cIds)
			throws DataAccessException {
		StringBuffer sql = new StringBuffer(
				"insert into papers_course (paper_id,course_id,score) values");
		for (int i = 0; i < cIds.length; i++) {
			sql.append("(");
			sql.append(paperId);
			sql.append(",");
			sql.append(Integer.valueOf(cIds[i]));
			sql.append(",");
			sql.append(0);
			sql.append("),");
		}
		super.saveOrUpdateOrDeleteByJDBC(sql.substring(0, sql.length() - 1),
				new Object[] {}, PapersCourse.class);
	}

	/**
	 * 
	 * <b>function:</b> 根据课程编号修改分数
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-4-16 下午02:10:48
	 * @return void
	 * @author dapeng_wu
	 */
	public void updatePapersCourse(String[] paperCourseWareScoreId,
			String[] courseWareScore ,Integer paperId) throws DataAccessException {
		String sql = "update  papers_course set score = ? where course_id = ? and paper_id = ?";
		for (int i = 0; i < courseWareScore.length; i++) {
			super.saveOrUpdateOrDeleteByJDBC(sql,new Object[] {courseWareScore[i],paperCourseWareScoreId[i],paperId}, PapersCourse.class);
		}
		
	}
	
	/**
	 * 
	   * <b>function:</b> 根据试卷中的课件的ID和试卷的id的到分数
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-16 下午03:48:38
	   * @return Integer
	   * @author dapeng_wu
	 */
	public Integer getPapersCourseScore(String courseId,String paperId)throws DataAccessException{
		String sql = "select score from papers_course where course_id = ? and paper_id = ?";
		List<PapersCourse> papersCourseList = super.findByJDBC(sql, new Object[] {courseId,paperId}, PapersCourse.class);
		if(papersCourseList != null && papersCourseList.size()>0){
			return papersCourseList.get(0).getScore();
		}
		return 0;
	}
	

}
