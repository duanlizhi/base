package com.xcj.admin.dao.course;
import java.util.List;

import com.xcj.common.page.Page;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.base.BaseDao;

/**
 *
  * <b>function:</b>  course_ware_actioncourse_ware动作
  * @package com.xcj.admin.dao.course.
  * @fileName com.xcj.admin.*
  * @createDate Fri Oct 16 15:21:32 CST 2015
  * @author name su_jian
 */
public interface CourseWareActionDao extends BaseDao{

   /**
    *
     * <b>function:</b>  course_ware_actioncourse_ware动作---获取list方法
     * @project base
     * @package com.xcj.admin.dao.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:32 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public <T extends CourseWareAction> List<T> getAllList()	throws DataAccessException;

   /**
    *
     * <b>function:</b>  course_ware_actioncourse_ware动作---获取分页list方法
     * @project base
     * @package com.xcj.admin.dao.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:32 CST 2015
     * @return List<T>
  * @author name su_jian
    */
  public Page<CourseWareAction>  getByCourseWareActionPage(Page<CourseWareAction> ps,CourseWareAction courseWareAction)	throws DataAccessException;

   /**
    *
     * <b>function:</b>  course_ware_actioncourse_ware动作---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.dao.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:32 CST 2015
     * @return void
  * @author name su_jian
    */
  public void removeById(Integer id) throws DataAccessException;

/** 
  * <dl>
  * <dt><span class="strong">方法说明:</span></dt>
  * <dd>主要是实现了什么功能</dd>
  * </dl> 
  * <dl><dt><span class="strong">逻辑说明:</span></dt>
  *  <dd>1、xx(处理逻辑是什么样子的)</dd>
  *  <dd>2、xx</dd>
  *  <dd>3、xx</dd></dl>  
  *  <dl><dt><span class="strong">创建时间:</span></dt>
  *  <dd> 2015年10月16日 下午3:35:16</dd></dl> 
  *  <dt><span class="strong">author:</span></dt>
  *  <dd>su_jian </dd>
  * </dl> 
  * @param <T> 对象类型 
  * @param entity 对象 
  * @see {@linkplain java.lang.String }
  * @return boolean true/false
  * @since 1.0
  * @throws DataAccessException  数据库访问异常
  */
public List<CourseWareAction> getByCourseWareIds(String cwids)throws DataAccessException;

}
