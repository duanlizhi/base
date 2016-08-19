package com.xcj.admin.service.course;
import java.util.List;

import com.xcj.common.page.Page;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.base.BaseService;

  /**
  *
  * <b>function:</b>  course_ware_toolcourse_ware工具
  * @package com.xcj.admin.service.course.
  * @fileName com.xcj.admin.*
  * @createDate Fri Oct 16 15:22:05 CST 2015
  * @author name su_jian
 */
public interface CourseWareToolService extends BaseService{

   /**
    *
     * <b>function:</b>  course_ware_toolcourse_ware工具---获取分页List方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:22:05 CST 2015
     * @return List<CourseWareTool>
  * @author name su_jian
    */
 public  Page<CourseWareTool>  getByCourseWareToolPage(Page<CourseWareTool> ps,CourseWareTool courseWareTool) throws Exception;

   /**
    *
     * <b>function:</b>  course_ware_toolcourse_ware工具---获取所有数据方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:22:05 CST 2015
     * @return List<CourseWareTool>
  * @author name su_jian
    */
   List<CourseWareTool>  getAllList()  throws Exception;

   /**
    *
     * <b>function:</b>  course_ware_toolcourse_ware工具---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:22:05 CST 2015
     * @return void
  * @author name su_jian
    */
   void removeById(Integer id)throws Exception;

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
  *  <dd> 2015年10月16日 下午3:38:06</dd></dl> 
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
public List<CourseWareTool> getByCourseWareIds(String cwids);
}
