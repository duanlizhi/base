package com.xcj.admin.service.impl.course;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.course.CourseWareKpDao;
import com.xcj.admin.entity.course.CourseWareKp;
import com.xcj.admin.service.course.CourseWareKpService;

/**
 *
  * <b>function:</b>  course_ware_kp课件和知识点的中间表
  * @package com.xcj.admin.service.impl.course.
  * @fileName com.xcj.admin.*
  * @createDate Thu Feb 12 17:27:00 CST 2015
  * @author name yang.yan
 */
@Service("courseWareKpServiceImpl")
public class CourseWareKpServiceImpl extends BaseServiceImpl implements CourseWareKpService{

	@Resource(name ="courseWareKpDaoImpl")
	private CourseWareKpDao courseWareKpDao;

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
		 *  <dd> 2015年10月16日 下午3:40:24</dd></dl> 
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
		return courseWareKpDao.getByCourseWareIds(cwids);
	}
}
