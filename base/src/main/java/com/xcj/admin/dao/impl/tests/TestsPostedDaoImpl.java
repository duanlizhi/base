package com.xcj.admin.dao.impl.tests;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tests.TestsPostedDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:54:09
 * @author dapeng_wu
 */
@Component("testsPostedDaoImpl")
public class TestsPostedDaoImpl extends BaseDaoImpl implements TestsPostedDao {
	private static final long serialVersionUID = 7902902722982857587L;

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends TestsPosted> List<T> getAllList()
			throws DataAccessException {
		String hql = "from TestsPosted";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> 根据paper_id得到分页
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> getByTestsPostedPage(Page<T> page, T entity,
			Integer paperId) throws DataAccessException {
		try {
			Session session = this.getSession();
			page.setResult(session.createQuery(
					"from " + entity.getClass().getName() + " where paper_id = "
							+ paperId + " order by id").setFirstResult(
					(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session.createQuery(
					"select count(id) as cnt from "
							+ entity.getClass().getName() + " where paper_id = "
							+ paperId).setMaxResults(1).uniqueResult()
					.toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * <b>function:</b> tests_posted测评发布---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from TestsPosted t where t.id=" + id);
	}

	/**
	 * <b>function:</b> 根据ids批量删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:41:14
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deleteTestsPosted(String ids) throws DataAccessException {
		String sql = "delete from tests_posted where paper_id in (" + ids + ")";
		super.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {},
				TestsPosted.class);
		return true;
	}

	/**
	 * <b>function:</b> 根据paper_id找到集合
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:41:14
	 * @return List<TestsPosted>·
	 * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperId(String ids)
			throws DataAccessException {
		String sql = "select id,test_name,release_time,answer_time,start_time,end_time,publisher,paper_id," +
							"create_date,modify_date,version,score_type,limit_count " +
							"from tests_posted where paper_id in ("+ ids + ")";
		List<TestsPosted> testsPostedList = super.findByJDBC(sql,
				new Object[] {}, TestsPosted.class);
		return testsPostedList;
	}
	
	/**
	   * <b>function:</b> 的到所有试卷的集合并且大于当前时间的发布记录集合
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-13 下午03:43:44
	   * @return List<TestsPosted>
	   * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperStartTime(String ids)
	throws DataAccessException {
		String sql = "select id,test_name,release_time,answer_time,start_time,end_time,publisher,paper_id," +
		"create_date,modify_date,version,score_type,limit_count " +
		"from tests_posted where paper_id in ("+ ids + ") and start_time < now() " +
		"and end_time > now()";
		//" UNIX_TIMESTAMP(NOW())-UNIX_TIMESTAMP(start_time)<=86400";
		List<TestsPosted> testsPostedList = super.findByJDBC(sql,
				new Object[] {}, TestsPosted.class);
		return testsPostedList;
	}
	
	/**
	 * 
	   * <b>function:</b> 根据测评id获取知识点
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午06:44:19
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getTestsPostedKp(String testsPostedId)throws DataAccessException{
		String sql = "select DISTINCT knowledge_point.* from  paper " +
				"join papers_course  on paper.id = papers_course.paper_id join course_ware on course_ware.id =  papers_course.course_id " +
				"join course_ware_kp on course_ware.id =  course_ware_kp.course_ware_id " +
				"join knowledge_point on course_ware_kp.kp_id = knowledge_point.id where paper.id = ?";
		return super.findByJDBC(sql, new Object[] {testsPostedId}, KnowledgePoint.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 获取wbt成绩
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午12:03:41
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getWbtScore(String paperId) throws DataAccessException{
		String sql = "select knowledge_point.id,sum(wbt_scores.rate*papers_course.score/100) as score from paper " +
				"join papers_course  on paper.id = papers_course.paper_id join course_ware on course_ware.id =  papers_course.course_id " +
				"join wbt_scores on course_ware.id = wbt_scores.course_ware_id join wbt_sbt_kp on wbt_scores.id = wbt_sbt_kp.sbt_wbt_id " +
                "join knowledge_point on knowledge_point.id = wbt_sbt_kp.kp_id where paper.id = ? and wbt_sbt_kp.type = 3  group by knowledge_point.id ";
		return super.findByJDBC(sql, new Object[] {paperId}, KnowledgePoint.class);
	}
	
	/**
	 * 
	   * <b>function:</b> 获取sbt成绩
	   * @project base
	   * @package com.xcj.admin.dao.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:18:16
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getSbtScore(String paperId) throws DataAccessException{
		String sql = "select knowledge_point.id,sum(sbt_scores.rate*papers_course.score/100) as score from paper " +
				"join papers_course  on paper.id = papers_course.paper_id join course_ware on course_ware.id =  papers_course.course_id " +                     
                "join sbt_fault on course_ware.id = sbt_fault.course_ware_id join sbt_scores on sbt_fault.id = sbt_scores.sbt_fault_id " +
                "join wbt_sbt_kp on sbt_scores.id = wbt_sbt_kp.sbt_wbt_id join knowledge_point on knowledge_point.id = wbt_sbt_kp.kp_id where paper.id = ? and wbt_sbt_kp.type = 2  group by knowledge_point.id";
		return super.findByJDBC(sql, new Object[] {paperId}, KnowledgePoint.class);
	}
}
