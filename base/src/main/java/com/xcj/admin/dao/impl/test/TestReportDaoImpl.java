package com.xcj.admin.dao.impl.test;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.test.TestReportDao;
import com.xcj.admin.entity.tests.TestReport;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.common.page.Page;

/**
 *
 * <b>function:</b> test_report测评报表
 * 
 * @package com.xcj.admin.dao.impl.test.
 * @fileName com.xcj.admin.*
 * @createDate Thu Oct 15 13:55:12 CST 2015
 * @author name su_jian
 */
@Component("testReportDaoImpl")
@SuppressWarnings({ "hiding" })
public class TestReportDaoImpl extends BaseDaoImpl implements TestReportDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 *
	 * <b>function:</b> test_report测评报表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:12 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends TestReport> List<T> getAllList()
			throws DataAccessException {
		String hql = "from TestReport";
		return super.getList(hql);
	}

	/**
	 *
	 * <b>function:</b> test_report测评报表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:12 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<TestReport> getByTestReportPage(Page<TestReport> page,
			TestReport testReport) throws DataAccessException {
		try {
			Session session = this.getSession();

			page.setResult(session
					.createQuery(
							"from " + testReport.getClass().getName()
									+ " order by orders ")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session
					.createQuery(
							"select count(id) as cnt from "
									+ testReport.getClass().getName())
					.setMaxResults(1).uniqueResult().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 *
	 * <b>function:</b> test_report测评报表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:55:12 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from TestReport t where t.id=" + id);
	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>根据五个参数获取测试分析结果</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年10月15日 下午2:10:36</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>su_jian</dd> </dl>
	 * 
	 * @param <T>
	 *            对象类型
	 * @param entity
	 *            对象
	 * @see {@link org.springframework.dao.DataAccessException}
	 * @return boolean true/false
	 * @since 1.0
	 * @throws Exception
	 *             XX异常
	 */
	public List<TestReport> getResultByFiveParam(String userEmail,
			String domainUsername, String courseNumber, String sessionId,
			String testsPublicId) throws DataAccessException {
		StringBuilder sb =new StringBuilder();
		sb.append(" from TestReport ");
		sb.append(" where courseNumber ='").append(courseNumber).append("'");
		sb.append(" and userEmail ='").append(userEmail).append("'");
		sb.append(" and domainUsername ='").append(domainUsername).append("'");
		//sb.append(" and sessionId ='").append(sessionId).append("'");
		sb.append(" and testsPublicId ='").append(testsPublicId).append("'");
		List<TestReport> list= super.getList(sb.toString());
		if(list==null||list.size()<=0){
			return null;
		}else{
			return list;
		}
	}

}
