package com.xcj.admin.dao.tests;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:53:20
 * @author dapeng_wu
 */
public interface TestsPostedDao extends BaseDao {

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return List<T>
	 * @author name dapeng_wu
	 */
	public <T extends TestsPosted> List<T> getAllList()
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> tests_posted测评发布---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:00:26 CST 2015
	 * @return void
	 * @author name dapeng_wu
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * <b>function:</b> 根据paper_id删除
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:34:10
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deleteTestsPosted(String ids) throws DataAccessException;

	/**
	 * <b>function:</b> 根据paper_id找到集合
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午11:42:47
	 * @return List<TestsPosted>
	 * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperId(String ids)
			throws DataAccessException;

	/**
	 * <b>function:</b> 根据paper_id得到分页
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 下午03:00:41
	 * @return Page<T>
	 * @author dapeng_wu
	 */
	public <T> Page<T> getByTestsPostedPage(Page<T> page, T entity,
			Integer paperId) throws DataAccessException;
	
	/**
	   * <b>function:</b> 的到所有试卷的集合并且大于当前时间的发布记录集合
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-13 下午03:50:07
	   * @return List<TestsPosted>
	   * @author dapeng_wu
	 */
	public List<TestsPosted> getTestsPostedByPaperStartTime(String ids)
	throws DataAccessException ;

	/**
	 * 
	   * <b>function:</b> 根据测评id获取知识点
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-12 下午06:45:09
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getTestsPostedKp(String testsPostedId)throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷id获取知识点和这个知识点的总成绩
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:22:27
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getWbtScore(String paperId) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷id获取知识点和这个知识点的总成绩
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-13 下午01:22:31
	   * @return List<KnowledgePoint>
	   * @author dapeng_wu
	 */
	public List<KnowledgePoint> getSbtScore(String paperId) throws DataAccessException;
	
	
}
