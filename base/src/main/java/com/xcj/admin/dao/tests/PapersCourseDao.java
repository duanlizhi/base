package com.xcj.admin.dao.tests;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:53:03
 * @author dapeng_wu
 */
public interface PapersCourseDao extends BaseDao {

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends PapersCourse> List<T> getAllList()
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<PapersCourse> getByPapersCoursePage(
			Page<PapersCourse> ps, PapersCourse papersCourse)
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
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
	 * @createDate 2015-2-4 上午11:57:32
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deletePapersCourse(String ids) throws DataAccessException;

	/**
	 * <b>function:</b> 根据paper_id获得中间表集合
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 下午04:49:51
	 * @return List<PapersCourse>
	 * @author dapeng_wu
	 */
	public List<PapersCourse> getPapersCourseByPaperId(String id)
			throws DataAccessException;

	/**
	 * <b>function:</b>批量添加
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 下午01:59:13
	 * @return void
	 * @author dapeng_wu
	 */
	public void savePapersCourse(Integer paperId, String[] cIds)
			throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b>  根据课程编号修改分数
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-16 下午02:17:13
	   * @return void
	   * @author dapeng_wu
	 */
	public void updatePapersCourse(String[] paperCourseWareScoreId,
			String[] courseWareScore ,Integer paperId) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷中的课件的ID和试卷的id的到分数
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-16 下午03:49:48
	   * @return Integer
	   * @author dapeng_wu
	 */
	public Integer getPapersCourseScore(String courseId,String paperId)throws DataAccessException;
	

}
