package com.xcj.admin.service.impl.tests;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.tests.TestsPostedDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.common.page.Page;
import com.xcj.common.page.PagerTag;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:56
 * @author dapeng_wu
 */
@Service("testsPostedServiceImpl")
public class TestsPostedServiceImpl extends BaseServiceImpl implements
		TestsPostedService {

	@Resource(name = "testsPostedDaoImpl")
	private TestsPostedDao testsPostedDao;

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<TestsPosted>
	 * @author name dapeng_wu
	 */
	public List<TestsPosted> getAllList() throws Exception {
		return testsPostedDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<TestsPosted>
	 * @author name dapeng_wu
	 */
	public Page<TestsPosted> getByTestsPostedPage(Page<TestsPosted> ps,
			TestsPosted testsPosted, Integer paperId) throws Exception {
		return testsPostedDao.getByTestsPostedPage(ps, testsPosted, paperId);
	}

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		testsPostedDao.removeById(id);

	}

	/**
	 * <b>function:</b> 查看发布记录
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午02:11:41
	 * @return Page<TestsPosted>
	 * @author dapeng_wu
	 */
	public Page<TestsPosted> issuePaper(Paper paper, Page<TestsPosted> page)
			throws Exception {
		page.setPageSize(5);
		Page<TestsPosted> pageTestsPosted = testsPostedDao
				.getByTestsPostedPage(page, new TestsPosted(), paper.getId());
		return pageTestsPosted;
	}

	/**
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午02:26:21
	 * @return JSONObject
	 * @author dapeng_wu
	 */
	public JSONObject pageAsjson(Page<TestsPosted> page,
			HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("currentPage", page.getCurrentPage());
		jsonObject.put("totalsPage", page.getTotalsPage());
		jsonObject.put("result", page.getResult());
		PagerTag tag = new PagerTag();
		String doStartTagString = tag.doStartTagString(page, request);
		jsonObject.put("htmlFiled", doStartTagString);
		return jsonObject;
	}
	
	/**
	 * 
	   * <b>function:</b> 的到所有试卷的集合并且大于当前时间的发布记录集合
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-13 下午03:51:22
	   * @return List<TestsPosted>
	   * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperStartTime(String ids)throws Exception{
		return testsPostedDao.getTestsPostedByPaperStartTime(ids);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据测评id获取知识点
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午06:49:56
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getTestsPostedKp(String testsPostedId)throws Exception{
		return testsPostedDao.getTestsPostedKp(testsPostedId);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据试卷id获取知识点以及总成绩
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:31:15
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getWbtScore(String paperId) throws Exception{
		return testsPostedDao.getWbtScore(paperId);
	}
	
	/**
	 * 
	   * <b>function:</b> 根据试卷id获取知识点以及总成绩
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:31:11
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getSbtScore(String paperId) throws Exception{
		return testsPostedDao.getSbtScore(paperId);
	}
}
