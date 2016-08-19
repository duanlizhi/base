package com.xcj.admin.dao.course;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.common.page.Page;

/**
 * <b>function:</b> course_ware课件基本信息
 * @package com.xcj.admin.dao.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:20 CST 2015
 * @author name yy_niu
 */
public interface CourseWareDao extends BaseDao {

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * @project base
	 * @package com.xcj.admin.dao.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:20 CST 2015
	 * @return List<T>
	 * @author name yy_niu
	 */
	public <T extends CourseWare> List<T> getAllList()
			throws DataAccessException;
	
	 
	
	/**
     * <b>function:</b>查询StudyResult id
     * @project 
     * @package 
     * @fileName 
     * @createDate 2015.4.7.17:09
     * @author yy.niu
     */
	public List<WbtScores> querWbtScores(String id)throws DataAccessException;
	
	/**
	 * 查询SbtFault id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtFault> querySbtFault(String id)throws DataAccessException;
	
	/**
	 * 查询 SbtScores id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtScores> querySbtScores(String id)throws DataAccessException;
	
	/**
     * <b>function:</b>根据courseNumber
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean queryStyResult(String courseNumber,String id)throws DataAccessException;
	
	/**
     * <b>function:</b>查询 sbt故障主键
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public List<SbtFault> querySbtFaultKey(String courseWareId)throws DataAccessException;
	
	/**
	 * 根据主键删除指定的实体和外键
	 * @param <T>
	 * @param pojo
	 */
	public <T> void deleteCourseById(Class<T> entityName,String keyId, Serializable id)throws DataAccessException;
	
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
	public CourseWare queryCourseNumbers(String courseNumber)throws DataAccessException;

	/**
     * <b>function:</b>根据courseNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean queryCourseNumber(String courseNumber)throws DataAccessException;
	
	/**
     * <b>function:</b>根据faultNumber查询数据是否存在
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public boolean queryFaultNumber(String faultNumber)throws DataAccessException;
	
	/**
     * <b>function:</b> 查询知识点数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public KnowledgePoint queryKonwName(String name)throws DataAccessException;

	/**
	 * <b>function:</b> course_ware课件基本信息---获取分页list方法
	 * @project base
	 * @package com.xcj.admin.dao.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:20 CST 2015
	 * @return List<T>
	 * @author name yy_niu
	 */
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> ps, CourseWare courseWare) throws DataAccessException;

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
	public List<CourseWare> queryCourseWareId(String courseNumber)throws DataAccessException;

	/**
	 * <b>function:</b> course_ware课件基本信息---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.dao.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:20 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException;

	/**
	 * <b>function:</b> 获取分页带搜索name条件
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午11:08:32
	 * @return Page<CourseWare>
	 * @author dapeng_wu
	 */
	public Page<CourseWare> getByPage(Page<CourseWare> page,
			CourseWare courseWare, String search) throws DataAccessException;
	
	/**
	 * 
	 * 
     * <b>function:</b>课程库分页
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public Page<CourseWare> getByPages(Page<CourseWare> page,
			CourseWare courseWare, String search) throws DataAccessException;

	/**
	 * <b>function:</b> 根据ids找到集合
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-8 上午10:32:03
	 * @return List<CourseWare>
	 * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareByIds(String ids)  throws DataAccessException;

	/** 
	 * <b>function:</b> 根据courseNumber获取课程信息
	 * CourseWareDao
	 * @createDate  2015-2-9 下午05:05:09
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type CourseWare 
	 */ 
	public CourseWare getByCourseNumber(String courseNumber)  throws DataAccessException;

	/**
	 * 
	   * <b>function:</b> 当前类别下已选择课程(分页)
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-2 下午06:24:29
	   * @return Page<CourseWare>
	   * @author hehujun
	 */
	public Page<CourseWare> getSelectedCoursesPage(Page<CourseWare> ps,Integer category, String search, Integer domainId) throws DataAccessException;
	
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
	public List<CourseWare> getAllSelectedCourses(Integer category, Integer domainId) throws DataAccessException;
	
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
	public boolean deleteDomainCourseWare(Integer domainId) throws DataAccessException;
	
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
	public boolean updateDomainCourseWare(Integer domainId, String courseWareIds) throws DataAccessException;
	
	/**
     * <b>function:</b>根据userName查询 courseWare数据
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	public List<?> getCourseWareList(String userName)throws DataAccessException;
	
	/**
	   * <b>function:</b> 根据域名称获取课件集合
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-6 下午05:35:21
	   * @return List<CourseWare>
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithDominUsername(String domainUsername,Integer category) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据域名称获取试卷paper集合
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午01:09:21
	   * @return List<Paper>
	   * @author dapeng_wu
	 */
	public List<Paper> getPaperListWithDominUsername(String domainUsername) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 根据paper获取已经发布的题库集合
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午02:29:32
	   * @return List<CourseWare>
	   * @author dapeng_wu
	 */
	public List<CourseWare> getCourseWareListWithPaper(Paper paper) throws DataAccessException;
	
	/**
	 * 
	   * <b>function:</b> 保存courseware  返回带id的courseware 
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午01:43:43
	   * @return CourseWare
	   * @author hehujun
	 */
	public CourseWare saveCourseWare(CourseWare courseWare) throws DataAccessException;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>sbt故障</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1、sbt故障</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2015年12月23日 上午9:54:06</dd></dl> 
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
	public void updateCourseWareByIds(String ids)throws DataAccessException;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据故障编号删除有效的故障</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>根据故障编号删除有效的故障</dd></dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年2月26日 下午8:36:13</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public void deleteSbtFault(String faultNumber) throws DataAccessException;
	
	/**
	 * 
	  * <dl>
	  * <dt><span class="strong">方法说明:</span></dt>
	  * <dd>根据故障编号查询到分数编号</dd>
	  * </dl> 
	  * <dl><dt><span class="strong">逻辑说明:</span></dt>
	  *  <dd>1.根据故障编号查询到分数编号</dd>/dl>  
	  *  <dl><dt><span class="strong">创建时间:</span></dt>
	  *  <dd> 2016年3月2日 下午8:13:55</dd></dl> 
	  *  <dt><span class="strong">author:</span></dt>
	  *  <dd>wu_dapeng </dd>
	  * </dl> 
	 */
	public List<SbtScores> getSbtScoreByFaultNumber(String faultNumber) throws DataAccessException;
	
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
	public List<CourseWare> getCourseWareListByids(String ids) throws DataAccessException;
}
