package com.xcj.admin.service.impl.tests;

import java.util.List;
import javax.annotation.Resource;
import com.xcj.common.page.Page;
import org.springframework.stereotype.Service;
import com.xcj.admin.service.tests.TestsScoreService;
import com.xcj.admin.dao.tests.TestsScoreDao;
import com.xcj.admin.entity.tests.TestsScore;
import com.xcj.admin.base.BaseServiceImpl;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:52:34
 * @author dapeng_wu
 */
@Service("testsScoreServiceImpl")
public class TestsScoreServiceImpl extends BaseServiceImpl implements
		TestsScoreService {

	@Resource(name = "testsScoreDaoImpl")
	private TestsScoreDao testsScoreDao;

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:35 CST 2015
	 * @return List<TestsScore>
	 * @author name dapeng_wu
	 */
	public List<TestsScore> getAllList() throws Exception {
		return testsScoreDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> tests_score得分表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 10:58:35 CST 2015
	 * @return List<TestsScore>
	 * @author name dapeng_wu
	 */
	public Page<TestsScore> getByTestsScorePage(Page<TestsScore> ps,
			TestsScore testsScore) throws Exception {
		return testsScoreDao.getByTestsScorePage(ps, testsScore);
	}

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
	public void removeById(Integer id) throws Exception {
		testsScoreDao.removeById(id);

	}
}
