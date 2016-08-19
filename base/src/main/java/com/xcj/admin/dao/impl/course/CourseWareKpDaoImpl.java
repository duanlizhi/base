package com.xcj.admin.dao.impl.course;
import java.util.List;

import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.course.CourseWareKpDao;
import com.xcj.admin.entity.course.CourseWareKp;

/**
 *
  * <b>function:</b>  course_ware_kp课件和知识点的中间表
  * @package com.xcj.admin.dao.impl.course.
  * @fileName com.xcj.admin.*
  * @createDate Thu Feb 12 17:27:01 CST 2015
  * @author name yang.yan
 */
@Component("courseWareKpDaoImpl")
 
public class CourseWareKpDaoImpl extends BaseDaoImpl implements CourseWareKpDao {
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
		 *  <dd> 2015年10月16日 下午3:41:37</dd></dl> 
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
	public List<CourseWareKp> getByCourseWareIds(String cwids) {
		String hql = "from CourseWareKp where courseWareId in ("+cwids+")";
		return super.getList(hql);
	}


}
