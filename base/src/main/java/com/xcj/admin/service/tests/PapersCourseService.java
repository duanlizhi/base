package com.xcj.admin.service.tests;

import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:50:52
 * @author dapeng_wu
 */
public interface PapersCourseService extends BaseService {

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:08 CST 2015
	 * @return List<PapersCourse>
	 * @author name dapeng_wu
	 */
	public Page<PapersCourse> getByPapersCoursePage(
			Page<PapersCourse> ps, PapersCourse papersCourse)
			throws Exception;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:08 CST 2015
	 * @return List<PapersCourse>
	 * @author name dapeng_wu
	 */
	List<PapersCourse> getAllList() throws Exception;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:08 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * <b>function:</b>批量保存
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 下午01:53:27
	 * @return void
	 * @author dapeng_wu
	 */
	public void savePapersCourse(Paper paper, String[] cIds ,String[] paperCourseWareScoreId,String[] courseWareScore)
			throws Exception;
	
	/**
	   * <b>function:</b>  根据试卷中的课件的ID和试卷的id的到分数
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-16 下午03:52:19
	   * @return Integer
	   * @author dapeng_wu
	 */
	public Integer getPapersCourseScore(String courseId,String paperId)throws Exception;
}
