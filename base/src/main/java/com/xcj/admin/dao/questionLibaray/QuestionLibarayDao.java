package com.xcj.admin.dao.questionLibaray;
import java.io.Serializable;
import java.util.List;
import com.xcj.common.page.Page;
import org.springframework.dao.DataAccessException;
import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.course.SbtScores;
import com.xcj.admin.entity.course.WbtScores;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;

/**
 *
  * <b>function:</b>  questionLibaray
  * @package com.xcj.admin.dao.questionLibaray.
  * @fileName com.xcj.admin.*
  * @createDate Wed Feb 11 11:48:20 CST 2015
  * @author name yy.niu
 */
public interface QuestionLibarayDao extends BaseDao{

	/**
	 * <b>function:</b> course_ware课件基本信息---获取list方法
	 * @project base
	 * @package com.xcj.admin.dao.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:20 CST 2015
	 * @return List<T>
	 * @author name yy.niu
	 */
	public <T extends CourseWare> List<T> getAllList()
			throws DataAccessException;
	
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
     * <b>function:</b>查询StudyResult id
     * @project 
     * @package 
     * @fileName 
     * @createDate 2015.4.7.17:09
     * @author yy.niu
     */
	public List<WbtScores> querWbtScores(String id)throws DataAccessException;
	
	/**
	 * 查询 SbtScores id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtScores> querySbtScores(String id)throws DataAccessException;
	
	/**
	 * 查询SbtFault id
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public List<SbtFault> querySbtFault(String id)throws DataAccessException;
	
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
	public Page<CourseWare> getByCourseWarePage(Page<CourseWare> ps,
			CourseWare courseWare) throws DataAccessException;

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
	 * <b>function:</b> 题库
	 * @project base
	 * @package com.xcj.admin.dao.course
	 * @fileName com.xcj.***
	 * @createDate 2015-2-7 上午11:08:32
	 * @return Page<CourseWare>
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
	   * <b>function:</b> 保存courseware  返回带id的courseware 
	   * @project base
	   * @package com.xcj.admin.dao.course  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-24 下午01:43:43
	   * @return CourseWare
	   * @author hehujun
	 */
	public CourseWare saveCourseWare(CourseWare courseWare) throws DataAccessException;


}
