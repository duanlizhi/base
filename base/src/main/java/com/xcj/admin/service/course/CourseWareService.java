package com.xcj.admin.service.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.common.page.Page;

/**
 * <b>function:</b> course_ware课件基本信息
 * @package com.xcj.admin.service.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:19 CST 2015
 * @author name yy.niu
 */
public interface CourseWareService extends BaseService {

	/**
	 * <b>function:</b> course_ware课件基本信息---获取分页List方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name yy.niu
	 */
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> ps,
			CourseWare courseWare) throws Exception;
	
	/**
	 * <b>function:</b>根据id查询CourseWare数据返回实体
	 * @project xcjedu
	 * @package com.xcj.api.controller.course
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public CourseWare queryCourseNumbers(String courseNumber)throws Exception;
	
	/**
     * <b>function:</b>添加xml数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public String saveData(String fileType,String fielUrl,String courseNumber,String faultNumber,String uploadPath,String realPath)throws Exception;

	/**
     * <b>function:</b>根据courseNumber
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean queryStyResult(String courseNumber,String id)throws Exception;
	
	
	
	/**
     * <b>function:</b>根据courseNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean queryCourseNumber(String courseNumber)throws Exception;
	
	/**
	 * <b>function:</b>读取sbtxml添加sbt数据
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveSbtCourseWare(String fielUrl,String uploadPath)throws Exception;

	/**
     * <b>function:</b> 读取拆装课程描述xml
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public String saveDisassemblingCourseDescription(String fielUrl,String uploadPath)throws Exception;
	
	/**
     * <b>function:</b>删除wbt课件、测评、拆装
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean deletewbtCourseWareAndTestAndDismantle (String ids)throws Exception;
	
	/**
     * <b>function:</b>删除sbt课程
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean deleteSbtCourseWare(String id)throws Exception;
	
	/**
	 * 
	 * 
     * <b>function:</b> 删除sbt故障数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean deleteSbtFault(String id)throws Exception;
	
	/**
	 * <b>function:</b> 读取sbt故障添加sbt数据
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveSbtFault(String fielUrl,String uploadPath)throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 读取emu故障添加emu数据
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-5-4 下午02:47:50
	   * @return String
	   * @author dapeng_wu
	 */
	public String saveEmuFault(String fielUrl,String uploadPath)throws Exception;

	/**
	 * <b>function:</b>读取wbt测评xml
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveWbtTests(String fielUrl,String uploadPath)throws Exception;

	/**
	 * <b>function:</b>读取wbt课件
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	public String saveWbtCourseWare(String fielUrl,String uploadPath)throws Exception;

	/**
     * <b>function:</b>根据faultNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
     */
	public boolean queryFaultNumber(String faultNumber)throws Exception;

	/**
	 * <b>function:</b>根据id查询CourseWare数据
	 * @project xcjedu
	 * @package com.xcj.api.controller.course
	 * @fileName @param courseWare
	 * @fileName @param model
	 * @fileName @return
	 * @createDate Jun 4, 2014 4:07:57 PM
	 * @author yy.niu
	 */
	public List<CourseWare> queryCourseWareId(String courseNumber)throws Exception;

	/**
	 * <b>function:</b> course_ware课件基本信息---获取所有数据方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
	 */
	List<CourseWare> getAllList() throws Exception;

	/**
	 * <b>function:</b> course_ware课件基本信息---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;

	/**
	 * <b>function:</b> 获取题组集合带分页
	 * @project base
	 * @package com.xcj.admin.service.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午11:04:07
	 * @return Page<CourseWare>
	 * @author dapeng_wu
	 */
	public Page<CourseWare> getByPage(Page<CourseWare> page, String search)
			throws Exception;
	
	/**
	 * 
	 * 
     * <b>function:</b>课程库集合
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public Page<CourseWare> getByPages(Page<CourseWare> page, String search)
	throws Exception;

	/**
	 * <b>function:</b>根据ids找到集合
	 * @project base
	 * @package com.xcj.admin.service.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 上午10:30:30
	 * @return List<CourseWare>
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByIds(String ids) throws Exception;

	/** 
	 * <b>function:</b> 根据courseNumber获取课程信息
	 * CourseWareService
	 * @createDate  2015-2-9 下午05:01:35
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type CourseWare 
	 */ 
	public CourseWare getByCourseNumber(String courseNumber)  throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 当前类别下已选择课程(分页)
	   * <br>比如：课程类别、题类别
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-2 下午06:24:29
	   * @return Page<CourseWare>
	   * @author hehujun
	 */
	public Page<CourseWare> getSelectedCoursesPage(Page<CourseWare> ps, Integer category, String search, Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 获取当前类别下所有已选的课程
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-3 下午01:20:33
	   * @return List<CourseWare>
	   * @author hehujun
	 */
	public List<CourseWare> getAllSelectedCourses(Integer category, Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据domainId 删除域与课程库关联表中的数据 
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 上午10:10:06
	   * @return boolean
	   * @author hehujun
	 */
	public boolean deleteDomainCourseWare(Integer domainId) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 批量插入domain_course_ware表
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 上午11:19:07
	   * @return boolean
	   * @author hehujun
	 */
	public boolean updateDomainCourseWare(Integer domainId, String courseWareIds) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 下午06:04:55
	   * @return String
	   * @author hehujun
	 */
	public String pageAsString(Page<CourseWare> page, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-4 下午06:04:55
	   * @return String
	   * @author hehujun
	 */
	public String pageAsStringTwo(Page<CourseWare> page, HttpServletRequest request) throws Exception;
	
	/**
     * <b>function:</b>根据userName查询 courseWare数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public List<?> getCourseWareList(String userName)throws Exception;
	
	/**
	   * <b>function:</b> 根据域名称获取课件集合
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午05:31:12
	   * @return List<CourseWare>
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithDominUsername(String domainUsername,Integer category) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据域名称获取试卷paper集合
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午01:24:41
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperListWithDominUsername(String domainUsername) throws Exception;
	
	/**
	 * 
	   * <b>function:</b> 根据paper获取已经发布的题库集合
	   * @project base
	   * @package com.xcj.admin.service.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午02:30:22
	   * @return List<CourseWare>
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithPaper(Paper paper) throws Exception;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>获取故障集合</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、获取故障集合</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年12月21日 上午10:04:22</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<SbtFault> querySbtFault(String courseWareId)throws Exception;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>同步sbtFault</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、同步sbtFault</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年12月23日 上午9:59:26</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<SbtFault> getSbtFaultByCourseWareIds(String cwids)throws DataAccessException;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>修改课件禁用</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、修改课件禁用</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月25日 下午4:43:57</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void updateCourseWareByIds(String ids)throws Exception;
	
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据ids获取courseware集合</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、根据ids获取courseware集合</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年3月31日 下午1:53:26</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<CourseWare> getCourseWareListByids(String ids) throws Exception;
	
	public void syncCourseWare(String coursewareArray) throws Exception;
	
	public void syncSbtFault(String sbtFaultArray) throws Exception;
	
	public void syncTestPosted(String testPostedArray) throws Exception;
	
	public void syncKnowledgePoint(String knowledgePointArray) throws Exception;
}
