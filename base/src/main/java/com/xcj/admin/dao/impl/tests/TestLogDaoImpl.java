package com.xcj.admin.dao.impl.tests;
import java.util.List;
import org.hibernate.Session;
import com.xcj.common.page.Page;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.entity.tests.TestLog;
import com.xcj.admin.dao.tests.TestLogDao;

/**
 *
  * <b>function:</b>  test_log测试日志表
  * @package com.xcj.admin.dao.impl.tests.
  * @fileName com.xcj.admin.*
  * @createDate Mon Mar 16 10:21:45 CST 2015
  * @author name su_jian
 */
@Component("testLogDaoImpl")
@SuppressWarnings({"hiding"})
public class TestLogDaoImpl extends BaseDaoImpl implements TestLogDao {

  @Resource
  private JdbcTemplate jdbcTemplate;

   /**
    *
     * <b>function:</b>  test_log测试日志表---获取list方法
     * @project base
     * @package com.xcj.admin.dao.impl.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:45 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends TestLog> List<T> getAllList() throws DataAccessException  {
		String hql = "from TestLog";
		return super.getList(hql); 
  }

	/**
	 * 
	 * <b>function:</b> test_log测试日志表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Mar 16 10:21:45 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<TestLog> getByTestLogPage(Page<TestLog> page, TestLog testLog)
			throws DataAccessException {
		try {
			Session session = this.getSession();
			if (testLog.getDomainUsername() != null && !testLog.getDomainUsername().equals("")) {
				page.setResult(session.createQuery(
						"from " + testLog.getClass().getName()
								+ " where name like '%" + testLog.getDomainUsername()
								+ "%' order  by orders").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ testLog.getClass().getName()
								+ " where name like '%" + testLog.getDomainUsername()
								+ "%'").setMaxResults(1).uniqueResult()
						.toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + testLog.getClass().getName()
								+ " order by orders ").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ testLog.getClass().getName())
						.setMaxResults(1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

   /**
    *
     * <b>function:</b>  test_log测试日志表---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.impl.tests.
     * @fileName com.xcj.admin.*
     * @createDate Mon Mar 16 10:21:45 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException { 
		super.delete("delete from TestLog t where t.id="+id);
  }

}
