package com.xcj.admin.service.tests;

import java.util.List;
import com.xcj.common.page.Page;
import com.xcj.admin.entity.tests.TestsScore;
import com.xcj.admin.base.BaseService;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:50:23
 * @author dapeng_wu
 */
public interface TestsScoreService extends BaseService {

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:35 CST 2015
	 * @return List<TestsScore>
	 * @author name dapeng_wu
	 */
	public Page<TestsScore> getByTestsScorePage(Page<TestsScore> ps,
			TestsScore testsScore) throws Exception;

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:35 CST 2015
	 * @return List<TestsScore>
	 * @author name dapeng_wu
	 */
	List<TestsScore> getAllList() throws Exception;

	/**
	 * 
	 * <b>function:</b> tests_score得分表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:35 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;
}
