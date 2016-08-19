package com.xcj.admin.service.impl.tests;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.tests.PaperDao;
import com.xcj.admin.dao.tests.PapersCourseDao;
import com.xcj.admin.dao.tests.TestsPostedDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.PapersCourse;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.page.Page;
import com.xcj.common.page.PagerTag;
import com.xcj.common.util.Constants;

/**
 * <b>function:</b>
 * @project base
 * @package com.xcj.admin.service.impl.tests
 * @fileName com.xcj.*
 * @createDate 2015-2-5 下午05:51:33
 * @author dapeng_wu
 */
@Service("paperServiceImpl")
public class PaperServiceImpl extends BaseServiceImpl implements PaperService {

	@Resource(name = "paperDaoImpl")
	private PaperDao paperDao;

	@Resource(name = "papersCourseDaoImpl")
	private PapersCourseDao papersCourseDao;

	@Resource(name = "testsPostedDaoImpl")
	private TestsPostedDao testsPostedDao;

	/**
	 * 
	 * <b>function:</b> paper试卷---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<Paper>
	 * @author name dapeng_wu
	 */
	public List<Paper> getAllList() throws Exception {
		return paperDao.getAllList();
	}

	/**
	 * 
	 * <b>function:</b> paper试卷---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return List<Paper>
	 * @author name dapeng_wu
	 */
	public Page<Paper> getByPaperPage(Page<Paper> ps, Paper paper)
			throws Exception {
		return paperDao.getByPaperPage(ps, paper);
	}

	/**
	 * 
	 * <b>function:</b> paper试卷---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper.
	 * @fileName com.xcj.admin.*
	 * @createDate Tue Feb 03 15:55:13 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		paperDao.removeById(id);

	}

	/**
	 * <b>function:</b> page首页页面集合
	 * 
	 * @project base
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return void
	 * @author dapeng_wu
	 * @throws Exception
	 */
	public Page<Paper> getIndexPaperList(Page<Paper> page, String search)
			throws Exception {
		Page<Paper> pagePaper = paperDao.getByPage(page, new Paper(), search);
		for (int i = 0; i < pagePaper.getResult().size(); i++) {
			List<PapersCourse> papersCourse = papersCourseDao
					.getPapersCourseByPaperId(pagePaper.getResult().get(i)
							.getId().toString());
			pagePaper.getResult().get(i).setQuestionSize(papersCourse.size());
		}
		return pagePaper;
	}

	/**
	 * <b>function:</b> 试卷是否禁用
	 * 
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return boolean
	 * @author dapeng_wu
	 * @throws Exception
	 */
	public boolean updateDisable(String id, String state) throws Exception {
		return paperDao.updateDisable(id, state);
	}

	/**
	 * <b>function:</b> 试卷是否禁用
	 * 
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return boolean
	 * @author dapeng_wu
	 * @throws Exception
	 */
	public boolean deletePaper(String ids) throws Exception {
		// 注释是删除发布后的试卷和成绩
		// List<TestsPosted> testsPostedList =
		// testsPostedDao.getTestsPostedByTestId(ids);
		// if(testsPostedList != null && testsPostedList.size() != 0){
		// StringBuffer sbTestId = new StringBuffer();
		// StringBuffer sbId = new StringBuffer();
		// for (TestsPosted testsPosted : testsPostedList) {
		// sbTestId.append(testsPosted.getTestId()).append(",");
		// sbId.append(testsPosted.getId()).append(",");
		// }
		// testsScoreDao.deleteTestsScore(sbId.substring(0,sbId.length()-1));
		// testsPostedDao.deleteTestsPosted(sbTestId.substring(0,sbTestId.length()-1));
		// }
		return papersCourseDao.deletePapersCourse(ids) & paperDao.deletePaper(ids);
	}

	/**
	 * <b>function:</b> 获取是否发布
	 * 
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return ArrayList
	 * @author dapeng_wu
	 * @throws Exception
	 */
	public List<Integer> getIssue(Page<Paper> pageList) throws Exception {
		List<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < pageList.getResult().size(); i++) {
			List<TestsPosted> testsPostedList = testsPostedDao
					.getTestsPostedByPaperId(pageList.getResult().get(i).getId()
							.toString());
			arrayList.add(testsPostedList.size());
		}
		return arrayList;
	}

	/**
	 * <b>function:</b> 试卷编辑
	 * 
	 * @package com.xcj.admin.service.paper
	 * @fileName com.xcj.***
	 * @createDate 2015-2-3 下午05:04:36
	 * @return boolean
	 * @author dapeng_wu
	 * @throws Exception
	 */
	public boolean updatePaper(Paper paper) throws Exception {
		return paperDao.updatePaper(paper);
	}
	/**
	 * 获取分页的试卷列表
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<Paper> getListByPage(String currentPage, String pageSize) {
		return paperDao.getListByPage(currentPage, pageSize);
	}
	
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
	public Page<Paper> getSelectedPapersPage(Page<Paper> ps, String search, Integer domainId) throws Exception {
		return paperDao.getSelectedPapersPage(ps, search, domainId);
	}
	
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
	public List<Paper> getAllSelectedPapers(Integer domainId) throws Exception {
		return paperDao.getAllSelectedPapers(domainId);
	}
	
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
	public boolean deleteDomainCoursePaper(Integer domainId) throws Exception {
		return paperDao.deleteDomainCoursePaper(domainId);
	}
	
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
	public boolean updateDomainCoursePaper(Integer domainId, String paperIds) throws Exception{
		return paperDao.updateDomainCoursePaper(domainId, paperIds);
	}

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
	public Page<Paper> getByPage(Page<Paper> page, String search)
			throws Exception {
		return paperDao.getByPage(page, new Paper(), search);
	}
	
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
	public String pageAsString(Page<Paper> page, HttpServletRequest request) throws Exception {
		PagerTag tag = new PagerTag();
		return tag.doStartTagStringWithoutJs(page, request);
	}
	
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
	public String pageAsStringTwo(Page<Paper> page, HttpServletRequest request) throws Exception {
		PagerTag tag = new PagerTag();
		return tag.doStartTagTwoStringWithoutJS(page, request);
	}

	/**
	 * 
	   * <b>function:</b> 根据试卷名称获取试卷集合
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-19 下午02:03:56
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperByName(String paperName) throws Exception{
		return paperDao.getPaperByName(paperName);
	}
	
	/**
	   * <b>function:</b> 根据试卷名称获取courseware集合
	   * @project base
	   * @package com.xcj.admin.service.impl.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午03:30:57
	   * @return 
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByPaperId(Integer parseId) throws Exception{
		return paperDao.getCourseWareByPaperId(parseId);
	}
	
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
	public boolean judgeCombineTokenIsTime(String token,String domainName,String userEmail)throws Exception {
		if(token.length()!=32){
			return false;
		}
		MemcachedClient mc1 = MemcachedClientFactory.getInstance();
		return  (token.equals((String)mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_COMBINE_TOKEN+domainName+"_"+userEmail)));
	}
}
