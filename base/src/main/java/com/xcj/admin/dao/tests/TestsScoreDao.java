package com.xcj.admin.dao.tests;

import java.util.List;
import com.xcj.common.page.Page;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.entity.tests.TestsScore;
import com.xcj.admin.base.BaseDao;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:53:29
 * @author dapeng_wu
 */
public interface TestsScoreDao extends BaseDao {

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends TestsScore> List<T> getAllList()
			throws DataAccessException;

	/**
	 * <b>function:</b> tests_score得分表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public Page<TestsScore> getByTestsScorePage(Page<TestsScore> ps,
			TestsScore testsScore) throws DataAccessException;

	/**
	 * <b>function:</b> tests_score得分表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:36 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * <b>function:</b> 根据tests_posted_id批量删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:49:43
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deleteTestsScore(String ids) throws DataAccessException;

}
