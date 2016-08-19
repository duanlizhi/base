package com.xcj.admin.service.questionLibaray;
 
import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.common.page.Page;

  /**
  *
  * <b>function:</b>  questionLibaray
  * @package com.xcj.admin.service.questionLibaray.
  * @fileName com.xcj.admin.*
  * @createDate Wed Feb 11 11:48:19 CST 2015
  * @author name su_jian
 */
public interface QuestionLibarayService extends BaseService{

	/**
	 * <b>function:</b> course_ware课件基本信息---获取分页List方法
	 * @project base
	 * @package com.xcj.admin.service.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:19 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
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
	public String saveData(String fileType,String fielUrl,String courseNumber,String faultNumber,String uploadPath)throws Exception;

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
	public String saveWebtCourseWare(String fielUrl,String uploadPath)throws Exception;

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
	 * <b>function:</b> 题库
	 * @project base
	 * @package com.xcj.admin.service.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午11:04:07
	 * @return Page<CourseWare>
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
}
