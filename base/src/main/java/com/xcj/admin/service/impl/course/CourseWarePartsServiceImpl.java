package com.xcj.admin.service.impl.course;
import java.util.List;

import javax.annotation.Resource;

import com.xcj.common.page.Page;

import org.springframework.stereotype.Service;

import com.xcj.admin.service.course.CourseWarePartsService;
import com.xcj.admin.dao.course.CourseWarePartsDao;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
  * <b>function:</b>  course_ware_partscourse_ware零件
  * @package com.xcj.admin.service.impl.course.
  * @fileName com.xcj.admin.*
  * @createDate Fri Oct 16 15:21:46 CST 2015
  * @author name su_jian
 */
@Service("courseWarePartsServiceImpl")
public class CourseWarePartsServiceImpl extends BaseServiceImpl implements CourseWarePartsService{

	@Resource(name ="courseWarePartsDaoImpl")
	private CourseWarePartsDao courseWarePartsDao;

   /**
    *
     * <b>function:</b>  course_ware_partscourse_ware零件---获取list方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:46 CST 2015
     * @return List<CourseWareParts>
  * @author name su_jian
    */
  public List<CourseWareParts> getAllList() throws Exception { 
    return courseWarePartsDao.getAllList(); 
   }

   /**
    *
     * <b>function:</b>  course_ware_partscourse_ware零件---获取list方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:46 CST 2015
     * @return List<CourseWareParts>
  * @author name su_jian
    */
  public Page<CourseWareParts>  getByCourseWarePartsPage(Page<CourseWareParts> ps,CourseWareParts courseWareParts)  throws Exception { 
    return courseWarePartsDao.getByCourseWarePartsPage(ps,courseWareParts); 
   }

   /**
    *
     * <b>function:</b>  course_ware_partscourse_ware零件---根据id删除数据方法
     * @project base
     * @package com.xcj.admin.service.course.
     * @fileName com.xcj.admin.*
     * @createDate Fri Oct 16 15:21:46 CST 2015
     * @return void
  * @author name su_jian
    */
 public void removeById(Integer id) throws Exception {
   courseWarePartsDao.removeById(id);

}

/** 
	 *<dl>
	 * 	<dt><span class="strong">方法说明:</span></dt>
	 *  <dd>主要是实现了什么功能</dd>
	 * </dl> 
	 * 	<dl><dt><span class="strong">逻辑说明:</span></dt>
	 *  <dd>1、xx(处理逻辑是什么样子的)</dd>
	 *  <dd>2、xx</dd>
	 *  <dd>3、xx</dd></dl> 
	 *  
	 * 	<dl><dt><span class="strong">创建时间:</span></dt>
	 *  <dd> 2015年10月16日 下午3:42:22</dd></dl> 
	 * 	<dt><span class="strong">author:</span></dt>
	 *  <dd>su_jian </dd>
	 * </dl> 
	 * @param <T> 对象类型 
	 * @param entity 对象 
	 * @see {@link org.springframework.dao.DataAccessException}
	 * @return boolean true/false
	 * @since 1.0
	 * @throws Exception  XX异常
	 */
public List<CourseWareParts> getByCourseWareIds(String cwids) throws Exception {
	return courseWarePartsDao.getByCourseWareIds(cwids);
} 
}
