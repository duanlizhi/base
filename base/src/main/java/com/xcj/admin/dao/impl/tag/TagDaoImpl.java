package com.xcj.admin.dao.impl.tag;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.tag.TagDao;
import com.xcj.admin.entity.tag.Tag;
import com.xcj.common.page.Page;

/**
 * 
 * <b>function:</b> tag标签管理表
 * 
 * @package com.xcj.admin.dao.impl.tag.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 11:19:53 CST 2015
 * @author name yang.yan
 */
@Component("tagDaoImpl")
@SuppressWarnings( { "hiding" })
public class TagDaoImpl extends BaseDaoImpl implements TagDao {
	private static final Logger log = LoggerFactory.getLogger(TagDaoImpl.class);

	/**
	 * 
	 * <b>function:</b> tag标签管理表---获取list方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:53 CST 2015
	 * @return List<T>
	 * @author name yang.yan
	 */
	public <T extends Tag> List<T> getAllList() throws DataAccessException {
		String hql = "from Tag";
		return super.getList(hql);
	}

	/**
	 * 
	 * <b>function:</b> tag标签管理表---根据id删除数据方法
	 * 
	 * @project base
	 * @package com.xcj.admin.dao.impl.tag.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 11:19:53 CST 2015
	 * @return void
	 * @author name yang.yan
	 */
	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from Tag t where t.id=" + id);
	}

	/**
	 * 根据order排序获取标签集合
	 */
	public Page<Tag> getByOrder(Page<Tag> page, Tag tag, String screening) throws Exception{
		try {
			Session session = this.getSession();
			if (screening == "" || screening == null) {
				page.setResult(session.createQuery("from " + tag.getClass().getName()
								+ " order by modifyDate,orders desc")
						.setFirstResult(
								(page.getCurrentPage() - 1)
										* page.getPageSize()).setMaxResults(
								page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ tag.getClass().getName()).setMaxResults(1)
						.uniqueResult().toString()));
			} else {
				page.setResult(session.createQuery(
						"from " + tag.getClass().getName()
								+ " where name like '%" + screening
								+ "%' order by modifyDate, orders   desc")
						.setFirstResult(
								(page.getCurrentPage() - 1)
										* page.getPageSize()).setMaxResults(
								page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ tag.getClass().getName()
								+ " where name like '%" + screening + "%'")
						.setMaxResults(1).uniqueResult().toString()));
			}
		} catch (Exception e) {
			log.error("标签管理表:获取分页对象出错：" + e.getMessage());
		}
		return page;
	}

	/**
	 * 根据id批量删除实体
	 */
	public void deleteByIds(String ids) throws Exception{
		super.delete("delete from Tag t where t.id in(" + ids + ")");
	}

}
