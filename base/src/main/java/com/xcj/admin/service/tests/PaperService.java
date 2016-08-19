package com.xcj.admin.service.tests;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.common.page.Page;
/**
 * <b>function:</b>
 * 
 * @project base
 * @package com.xcj.admin.service.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:06
 * @author dapeng_wu
 */
public interface PaperService extends BaseService {

	/**
	 * <b>function:</b> paper试卷---获取分页List方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:12 CST 2015
	 * @return List<Paper>
	 * @author name dapeng_wu
	 */
	public Page<Paper> getByPaperPage(Page<Paper> ps, Paper paper)
			throws Exception;

	/**
	 * 
	 * <b>function:</b> paper试卷---获取所有数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:12 CST 2015
	 * @return List<Paper>
	 * @author name dapeng_wu
	 */
	List<Paper> getAllList() throws Exception;

	/**
	 * 
	 * <b>function:</b> paper试卷---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:12 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * <b>function:</b> page首页页面
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return void
	 * @author dapeng_wu
	 */
	public Page<Paper> getIndexPaperList(Page<Paper> page, String search)
			throws Exception;

	/**
	 * <b>function:</b> 试卷是否禁用
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午09:37:44
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean updateDisable(String id, String state) throws Exception;

	/**
	 * <b>function:</b> 删除试卷
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 上午10:50:47
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean deletePaper(String ids) throws Exception;

	/**
	 * <b>function:</b> 查看'是否发布'集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-4 下午05:38:18
	 * @return ArrayList
	 * @author dapeng_wu
	 */
	public List<Integer> getIssue(Page<Paper> pageList) throws Exception;

	/**
	 * <b>function:</b> 试卷的编辑
	 * 
	 * @project base
	 * @package com.xcj.admin.service.tests
	 * @fileName com.xcj.***
	 * @createDate 2015-2-5 上午09:37:08
	 * @return boolean
	 * @author dapeng_wu
	 */
	public boolean updatePaper(Paper paper) throws Exception;

	/**
	 * 获取分页的试卷列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Paper> getListByPage(String currentPage, String pageSize);
	
	/**
	 * 
	   * <b>function:</b> 获取当前域 已选的测评 分页
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:53:31
	   * @return Page<Paper>
	   * @author hehujun
	 */
	public Page<Paper> getSelectedPapersPage(Page<Paper> ps, String search, Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 获取当前域所有已选择的测评
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:54:03
	   * @return List<Paper>
	   * @author hehujun
	 */
	public List<Paper> getAllSelectedPapers(Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据domainId 删除域与测评库关联表中的数据 
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:55:47
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCoursePaper(Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 批量插入domain_course_paper表
	   * @project base
	   * @package com.xcj.admin.dao.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 上午11:56:03
	   * @return boolean
	   * @author hehujun
	 */
	public boolean updateDomainCoursePaper(Integer domainId, String paperIds) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 传入查询语句和查询总条数（总记录）的hql语句、page分页对象；返回查询后的list集合；
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午01:34:46
	   * @return Page<T>
	   * @author hehujun
	 */
	public Page<Paper> getByPage(Page<Paper> page, String search) throws Exception;

	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午01:44:10
	   * @return String
	   * @author hehujun
	 */
	public String pageAsString(Page<Paper> page, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-9 下午01:44:21
	   * @return String
	   * @author hehujun
	 */
	public String pageAsStringTwo(Page<Paper> page, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据试卷名称获取试卷集合
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-19 下午02:04:52
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperByName(String paperName) throws Exception;

	 /**
	   * <b>function:</b> 根据试卷名称获取courseware集合
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午03:29:07
	   * @return List<CourseWare>
	   * @author dapeng_wu
	   */
	public List<CourseWare> getCourseWareByPaperId(Integer parseId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 判断试卷token
	   * @project base
	   * @package com.xcj.admin.service.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年4月7日 下午3:49:48
	   * @return boolean
	   * @author hehujun
	 */
	@Deprecated
	public boolean judgeCombineTokenIsTime(String token,String domainName,String userEmail)throws Exception;
}
