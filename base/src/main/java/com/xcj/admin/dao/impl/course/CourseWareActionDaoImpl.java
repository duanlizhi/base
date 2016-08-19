package com.xcj.admin.dao.impl.course;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.course.CourseWareActionDao;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.common.page.Page;

/**
 *
 * <b>function:</b> course_ware_actioncourse_ware动作
 * 
 * @package com.xcj.admin.dao.impl.course.
 * @fileName com.xcj.admin.*
 * @createDate Fri Oct 16 15:21:33 CST 2015
 * @author name su_jian
 */
@Component("courseWareActionDaoImpl")
@SuppressWarnings({ "hiding" })
public class CourseWareActionDaoImpl extends BaseDaoImpl implements
		CourseWareActionDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:33 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public <T extends CourseWareAction> List<T> getAllList()
			throws DataAccessException {
		String hql = "from CourseWareAction";
		return super.getList(hql);
	}

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---获取分页list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:33 CST 2015
	 * @return List<T>
	 * @author name su_jian
	 */
	public Page<CourseWareAction> getByCourseWareActionPage(
			Page<CourseWareAction> page, CourseWareAction courseWareAction)
			throws DataAccessException {
		try {
			Session session = this.getSession();

			page.setResult(session
					.createQuery(
							"from " + courseWareAction.getClass().getName()
									+ " order by orders ")
					.setFirstResult(
							(page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize()).list());
			page.setTotalsCount(Integer.parseInt(session
					.createQuery(
							"select count(id) as cnt from "
									+ courseWareAction.getClass().getName())
					.setMaxResults(1).uniqueResult().toString()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 *
	 * <b>function:</b> course_ware_actioncourse_ware动作---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.course.
	 * @fileName com.xcj.admin.*
	 * @createDate Fri Oct 16 15:21:33 CST 2015
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from CourseWareAction t where t.id=" + id);
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
	 * <dd>2015年10月16日 下午3:35:33</dd>
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
			throws DataAccessException {
		String hql = "from CourseWareAction where courseWareId in ("+cwids+")";
		return super.getList(hql);
	}

}
