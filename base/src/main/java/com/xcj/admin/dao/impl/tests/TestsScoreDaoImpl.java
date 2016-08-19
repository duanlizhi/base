package com.xcj.admin.dao.impl.tests;

import java.util.List;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tests.TestsScoreDao;
import com.xcj.admin.entity.tests.TestsScore;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:54:16
 * @author dapeng_wu
 */
@Component("testsScoreDaoImpl")
@SuppressWarnings( { "hiding" })
public class TestsScoreDaoImpl extends BaseDaoImpl implements TestsScoreDao {
	private static final long serialVersionUID = 5333813005073954292L;

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends TestsScore> List<T> getAllList()
			throws DataAccessException {
		String hql = "from TestsScore";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	@SuppressWarnings("unchecked")
	public Page<TestsScore> getByTestsScorePage(Page<TestsScore> page,
			TestsScore testsScore) throws DataAccessException {
		try {
			Session session = this.getSession();
			if (testsScore.getAnswerMan() != null
					&& !testsScore.getAnswerMan().equals("")) {
				page.setResult(session.createQuery(
						"from " + testsScore.getClass().getName()
								+ " where name like '%"
								+ testsScore.getAnswerMan()
								+ "%' order  by orders").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ testsScore.getClass().getName()
								+ " where name like '%"
								+ testsScore.getAnswerMan() + "%'")
						.setMaxResults(1).uniqueResult().toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + testsScore.getClass().getName()
								+ " order by orders ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ testsScore.getClass().getName())
						.setMaxResults(1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * 
	 * <b>function:</b> tests_score得分表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from TestsScore t where t.id=" + id);
	}

	/**
	 * <b>function:</b> 根据tests_posted_id批量删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:48:03
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deleteTestsScore(String ids) throws DataAccessException {
		String sql = "delete from tests_score where tests_posted_id in (" + ids
				+ ")";
		super
				.saveOrUpdateOrDeleteByJDBC(sql, new Object[] {},
						TestsScore.class);
		return true;
	}
}
