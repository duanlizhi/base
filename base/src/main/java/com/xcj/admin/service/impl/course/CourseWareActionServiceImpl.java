package com.xcj.admin.service.impl.course;

import java.util.List;

import javax.annotation.Resource;

import com.xcj.common.page.Page;

import org.springframework.stereotype.Service;

import com.xcj.admin.service.course.CourseWareActionService;
import com.xcj.admin.dao.course.CourseWareActionDao;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.base.BaseServiceImpl;

/**
 *
 * <b>function:</b> course_ware_actioncourse_ware动作
 * 
 * @package com.xcj.admin.service.impl.course.
 * @fileName com.xcj.admin.*
 * @createDate Fri Oct 16 15:21:32 CST 2015
 * @author name su_jian
 */
@Service("courseWareActionServiceImpl")
public class CourseWareActionServiceImpl extends BaseServiceImpl implements
		CourseWareActionService {

	@Resource(name = "courseWareActionDaoImpl")
	private CourseWareActionDao courseWareActionDao;

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:32 CST 2015
	 * @return List<CourseWareAction>
	 * @author name su_jian
	 */
	public List<CourseWareAction> getAllList() throws Exception {
		return courseWareActionDao.getAllList();
	}

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:32 CST 2015
	 * @return List<CourseWareAction>
	 * @author name su_jian
	 */
	public Page<CourseWareAction> getByCourseWareActionPage(
			Page<CourseWareAction> ps, CourseWareAction courseWareAction)
			throws Exception {
		return courseWareActionDao.getByCourseWareActionPage(ps,
				courseWareAction);
	}

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.service.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:32 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		courseWareActionDao.removeById(id);

	}

	/**
	 * <dl>
	 * <dt><span class="strong">方法说明:</span></dt>
	 * <dd>主要是实现了什么功能</dd>
	 * </dl>
	 * <dl>
	 * <dt><span class="strong">逻辑说明:</span></dt>
	 * <dd>1、xx(处理逻辑是什么样子的)</dd>
	 * <dd>2、xx</dd>
	 * <dd>3、xx</dd>
	 * </dl>
	 * 
	 * <dl>
	 * <dt><span class="strong">创建时间:</span></dt>
	 * <dd>2015年10月16日 下午3:34:46</dd>
	 * </dl>
	 * <dt><span class="strong">author:</span></dt> <dd>su_jian</dd> </dl>
	 * 
	 * @param <T>
	 *            对象类型
	 * @param entity
	 *            对象
	 * @see {@link org.springframework.dao.DataAccessException}
	 * @return boolean true/false
	 * @since 1.0
	 * @throws Exception
	 *             XX异常
	 */
	public List<CourseWareAction> getByCourseWareIds(String cwids)
			throws Exception {
		return courseWareActionDao.getByCourseWareIds(cwids);
	}
}
