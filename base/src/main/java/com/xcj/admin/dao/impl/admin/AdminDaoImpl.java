package com.xcj.admin.dao.impl.admin;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.xcj.admin.base.BaseDaoImpl;
import com.xcj.admin.dao.admin.AdminDao;
import com.xcj.admin.entity.admin.Admin;
import com.xcj.common.page.Page;

/**
 * <b>function:</b>超级管理员Dao方法
 * @project base_frame
 * @package com.xcj.admin.dao.admin
 * @fileName com.xcj.*
 * @createDate May 13, 2014 9:49:18 AM
 * @author su_jian
 */
@Component("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl implements AdminDao {
	private static final Logger log = LoggerFactory
			.getLogger(AdminDaoImpl.class);
	private static final long serialVersionUID = 1L;

	public <T extends Admin> List<T> getSortList() throws DataAccessException {
		String hql = "from Admin";
		return super.getList(hql);
	}

	public void removeById(Integer id) throws DataAccessException {
		super.delete("delete from Admin t where t.id=" + id);
	}

	/**
	 * 根据用户名获取用户信息
	 */
	public Admin getbyUserName(String username) throws DataAccessException {
		String hql = "from Admin p where p.username='" + username + "'";
		List<Admin> list = super.getList(hql);
		if (list.size() <= 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	public Page<Admin> getByPageAdmin(Page<Admin> page, Admin admin,
			String screening) {
		try {
			Session session = this.getSession();
			if (screening == "" || screening == null) {
				page.setResult(session.createQuery(
						"from " + admin.getClass().getName()
								+ " order by modifyDate  desc").setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				page.setTotalsCount(Integer.parseInt(session.createQuery(
						"select count(id) as cnt from "
								+ admin.getClass().getName()).setMaxResults(1)
						.uniqueResult().toString()));
			} else {
				String sql = "select * from  admin where email like '%"
						+ screening + "%' or username like '%" + screening
						+ "%' or mobile like '%" + screening
						+ "%' order by mobile,username,email desc ";
				/* 设置结果集 */
				page.setResult(session.createSQLQuery(sql).addEntity(
						Admin.class).setFirstResult(
						(page.getCurrentPage() - 1) * page.getPageSize())
						.setMaxResults(page.getPageSize()).list());
				/* 设置总数量 */
				SQLQuery createSQLQuery = session.createSQLQuery(sql);
				page.setTotalsCount(createSQLQuery.list().size());
			}
		} catch (Exception e) {
			log.error("标签管理表:获取分页对象出错：" + e.getMessage());
		}
		return page;
	}

	/**
	 * 根据id批量删除实体
	 */
	public void deleteByIds(String ids) {
		super.delete("delete from Admin t where t.id in(" + ids + ")");
	}

	/** 
	 * <b>function:</b> 获取用户信息是否从CAS获取
	 * AdminDao
	 * @createDate  2015-2-6 下午01:36:01
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type Admin 
	 */ 
	public Admin getByEmail(String emailName) {
		String hql = "from Admin p where p.email='" + emailName + "'";
		List<Admin> list = super.getList(hql);
		if(list!=null&&!list.isEmpty()&&list.size()>0){
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	   * <b>function:</b> 通过页面传的参数获取用户信息
	   * @project casbase
	   * @package com.xcj.admin.service.admin  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-31 下午02:05:25
	   * @return Admin
	   * @author hehujun
	 */
	@SuppressWarnings("unchecked")
	public Admin getCurrentByPageParam(String loginname) throws Exception {
		Session session = this.getSession();
		List<Admin> admins = session.createSQLQuery("SELECT id, create_date, modify_date, department, email, is_enabled, is_locked, locked_date, login_date, login_failure_count, login_ip, name, PASSWORD, username, mobile,admin_number FROM admin a WHERE (a.username=? OR a.mobile=? OR a.email=?) AND a.is_enabled=1")
			.addEntity(Admin.class)
			.setString(0, loginname)
			.setString(1, loginname)
			.setString(2, loginname)
			.list();
		if (admins.size() > 0) {
			return admins.get(0);
		}
		return null;
	}
}
