package com.xcj.admin.dao.tests;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.dao.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:52:56
 * @author dapeng_wu
 */
public interface PaperDao extends BaseDao {

	/**
	 * 
	 * <b>function:</b> paper试卷---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends Paper> List<T> getAllList() throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> paper试卷---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<Paper> getByPaperPage(Page<Paper> ps, Paper paper)
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> paper试卷---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * <b>function:</b> 试卷是否禁用
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午09:40:17
	 * @return void
	 * @author dapeng_wu
	 */
	public boolean updateDisable(String id, String state) throws Exception;

	/**
	 * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、page分页对象；返回查询后的list集合；
	 * 
	 * @createDate 按照id分页
	 * @author su_jian
	 * @param queryHql
	 *            list集合结果查询
	 * @param queryCountHql
	 *            总记录调试查询
	 * @param page
	 *            分页对象
	 * @throws DataAccessException
	 */
	public <T> Page<T> getByPage(Page<T> page, T entity, String search)
			throws DataAccessException;

	/**
	 * <b>function:</b> 删除试卷
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午10:52:57
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deletePaper(String ids) throws DataAccessException;

	/**
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 上午09:42:31
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean updatePaper(Paper paper) throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> 根据试卷名称获取试卷集合
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-19 下午02:02:12
	 * @return List<Paper>
	 * @author dapeng_wu
	 */
	public List<Paper> getPaperByName(String paperName) throws DataAccessException;

	/**
	 * 获取分页的试卷列表
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Paper> getListByPage(String currentPage, String pageSize);

	/**
	 * 
	 * <b>function:</b> 获取当前域 已选的测评 分页
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-9 上午11:53:31
	 * @return Page<Paper>
	 * @author hehujun
	 */
	public Page<Paper> getSelectedPapersPage(Page<Paper> ps, String search,
			Integer domainId) throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> 获取当前域所有已选择的测评
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-9 上午11:54:03
	 * @return List<Paper>
	 * @author hehujun
	 */
	public List<Paper> getAllSelectedPapers(Integer domainId)
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> 根据domainId 删除域与测评库关联表中的数据
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-9 上午11:55:47
	 * @return boolean
	 * @author hehujun
	 */
	public boolean deleteDomainCoursePaper(Integer domainId)
			throws DataAccessException;

	/**
	 * 
	 * <b>function:</b> 批量插入domain_course_paper表
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-3-9 上午11:56:03
	 * @return boolean
	 * @author hehujun
	 */
	public boolean updateDomainCoursePaper(Integer domainId, String paperIds)
			throws DataAccessException;

	 /**
	   * <b>function:</b> 根据试卷名称获取courseware集合
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午03:31:06
	   * @return List<CourseWare>
	   * @author dapeng_wu
	   */
	public List<CourseWare> getCourseWareByPaperId(Integer parseId)throws DataAccessException;
}
