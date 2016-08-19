package com.xcj.admin.service.test;

import java.util.List;

import org.apache.shiro.dao.DataAccessException;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.tests.TestsResult;
import com.xcj.common.page.Page;

/**
 *
 * <b>function:</b> test_result测评成绩
 * 
 * @package com.xcj.admin.service.test.
 * @fileName com.xcj.admin.*
 * @createDate Thu Oct 15 13:54:57 CST 2015
 * @author name su_jian
 */
public interface TestResultService extends BaseService {

	/**
	 *
	 * <b>function:</b> test_result测评成绩---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return List<TestResult>
	 * @author name su_jian
	 */
	public Page<TestsResult> getByTestResultPage(Page<TestsResult> ps,
			TestsResult testsResult) throws Exception;

	/**
	 *
	 * <b>function:</b> test_result测评成绩---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return List<TestResult>
	 * @author name su_jian
	 */
	List<TestsResult> getAllList() throws Exception;

	/**
	 *
	 * <b>function:</b> test_result测评成绩---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.test.
	 * @fileName com.xcj.admin.*
	 * @createDate Thu Oct 15 13:54:57 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>主要是实现了什么功能</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、xx(处理逻辑是什么样子的)</dd>
	 * <dd>2、xx</dd>
	 * <dd>3、xx</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年10月15日 下午2:01:22</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>su_jian</dd> </dl>
	 * 
	 * @param <T>
	 *            对象类型
	 * @param entity
	 *            对象
	 * @see {@linkplain java.lang.String }
	 * @return boolean true/false
	 * @since 1.0
	 * @throws DataAccessException
	 *             数据库访问异常
	 */
	public void syncTestResult(String userEmail, String domainUsername,
			String courseNumber, String sessionId, String testsPublicId)
			throws Exception;
}
