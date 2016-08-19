package com.xcj.admin.service.tests;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:50:41
 * @author dapeng_wu
 */
public interface TestsPostedService extends BaseService {

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<TestsPosted>
	 * @author name dapeng_wu
	 */
	public Page<TestsPosted> getByTestsPostedPage(Page<TestsPosted> ps,
			TestsPosted testsPosted, Integer paperId) throws Exception;

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<TestsPosted>
	 * @author name dapeng_wu
	 */
	List<TestsPosted> getAllList() throws Exception;

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
	void removeById(Integer id) throws Exception;

	/**
	 * <b>function:</b> 查看发布记录
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午02:12:29
	 * @return Page<TestsPosted>
	 * @author dapeng_wu
	 */
	public Page<TestsPosted> issuePaper(Paper paper, Page<TestsPosted> page)
			throws Exception;

	/**
	 * <b>function:</b> 将page转换成JSONObject
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午02:39:14
	 * @return JSONObject
	 * @author dapeng_wu
	 */
	public JSONObject pageAsjson(Page<TestsPosted> page,
			HttpServletRequest request) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 的到所有试卷的集合并且大于当前时间的发布记录集合
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-13 下午03:52:01
	   * @return List<TestsPosted>
	   * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperStartTime(String ids)throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据测评id获取知识点
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午06:50:57
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getTestsPostedKp(String testsPostedId)throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷获取知识点以及总成绩
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:32:43
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getWbtScore(String paperId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷获取知识点以及总成绩
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:32:48
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getSbtScore(String paperId) throws Exception;
}
