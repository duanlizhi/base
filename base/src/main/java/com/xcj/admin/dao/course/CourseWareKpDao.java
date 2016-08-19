package com.xcj.admin.dao.course;
import java.util.List;

import com.xcj.admin.base.BaseDao;
import com.xcj.admin.entity.course.CourseWareKp;

/**
 *
  * <b>function:</b>  course_ware_kp课件和知识点的中间表
  * @package com.xcj.admin.dao.course.
  * @fileName com.xcj.admin.*
  * @createDate Thu Feb 12 17:27:00 CST 2015
  * @author name yang.yan
 */
public interface CourseWareKpDao extends BaseDao{

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
	  *  <dd> 2015年10月16日 下午3:41:27</dd></dl> 
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
	List<CourseWareKp> getByCourseWareIds(String cwids);


}
