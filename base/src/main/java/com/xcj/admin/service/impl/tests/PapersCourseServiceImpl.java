package com.xcj.admin.service.impl.tests;

import java.util.List;
import javax.annotation.Resource;
import com.xcj.common.page.Page;
import com.xcj.common.util.common.DateUtil;

import org.springframework.stereotype.Service;
import com.xcj.admin.service.tests.PapersCourseService;
import com.xcj.admin.dao.tests.PaperDao;
import com.xcj.admin.dao.tests.PapersCourseDao;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.admin.base.BaseServiceImpl;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:42
 * @author dapeng_wu
 */
@Service("papersCourseServiceImpl")
public class PapersCourseServiceImpl extends BaseServiceImpl implements
		PapersCourseService {

	@Resource(name = "papersCourseDaoImpl")
	private PapersCourseDao papersCourseDao;

	@Resource(name = "paperDaoImpl")
	private PaperDao paperDao;

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<PapersCourse>
	 * @author name dapeng_wu
	 */
	public List<PapersCourse> getAllList() throws Exception {
		return papersCourseDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return List<PapersCourse>
	 * @author name dapeng_wu
	 */
	public Page<PapersCourse> getByPapersCoursePage(
			Page<PapersCourse> ps, PapersCourse papersCourse)
			throws Exception {
		return papersCourseDao.getByPapersCoursePage(ps, papersCourse);
	}

	/**
	 * 
	 * <b>function:</b> papers_course试卷题组中间表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 11:30:09 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		papersCourseDao.removeById(id);
	}

	/**
	 * 
	 * <b>function:</b> 批量添加
	 * 
	 * @project base
	 * @package com.xcj.admin.service.impl.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 下午01:57:16
	 * @return
	 * @author dapeng_wu
	 */
	public void savePapersCourse(Paper paper, String[] cIds ,String[] paperCourseWareScoreId,String[] courseWareScore) throws Exception {
		paper.setQuestionSize(cIds.length);
		paper.setCreateDate(DateUtil.getCurrentTimeByDate());
		paper.setModifyDate(DateUtil.getCurrentTimeByDate());
		Integer paperId = paperDao.saveAndGetIdByInteger(paper);
		papersCourseDao.savePapersCourse(paperId, cIds);
		papersCourseDao.updatePapersCourse(paperCourseWareScoreId,courseWareScore,paperId);
	}
	
	/**
	 * 
	   * <b>function:</b>  根据试卷中的课件的ID和试卷的id的到分数
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-4-16 下午03:53:07
	   * @return 
	   * @author dapeng_wu
	 */
	public Integer getPapersCourseScore(String courseId,String paperId)throws Exception{
		return papersCourseDao.getPapersCourseScore(courseId, paperId);
	}
}
