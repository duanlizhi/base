package com.xcj.admin.service.impl.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.admin.AdminDao;
import com.xcj.admin.entity.admin.Admin;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.common.entity.Principal;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.page.Page;

/**
 * <b>function:</b> 超级管理员Service实现类
 * 
 * @project base_frame
 * @package com.xcj.admin.service.admin
 * @fileName com.xcj.*
 * @createDate May 13, 2014 9:52:39 AM
 * @author su_jian
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;

	public List<Admin> getSortList() throws Exception {
		return adminDao.getSortList();
	}

	public void removeById(Integer id) throws Exception {
		adminDao.removeById(id);
	}

	/**
	 * <b>function:</b> 获取当前用户名方法 <br/>
	 * 登录之后如果未登录则放回null
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.service.admin
	 * @fileName com.xcj.*
	 * @createDate May 13, 2014 9:52:39 AM
	 * @author su_jian
	 */
	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	/**
	 * <b>function:</b>是否已经授权
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.service.impl.admin
	 * @fileName com.xcj.***
	 * @createDate May 13, 2014 10:03:39 AM
	 * @return boolean
	 * @author su_jian
	 */
	public boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}

	/**
	 * 获取当前用户信息 <b>function:</b>
	 * 
	 * @project base_frame
	 * @package com.xcj.admin.service.impl.admin
	 * @fileName com.xcj.***
	 * @createDate May 13, 2014 10:05:38 AM
	 * @return Admin
	 * @author su_jian
	 */
	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				try {
					MemcachedClient mc1 = MemcachedClientFactory.getInstance();
					Admin admin = (Admin) mc1.get("current_username_"
							+ principal.getUsername());
					if (admin == null) {
						admin = adminDao.getbyUserName(principal.getUsername());
						MemcachedUtil.putMemcacheIntanceByTimeObject(
								"current_username_" + principal.getUsername(),
								admin, 72000);
						return admin;
					} else {
						return admin;
					}
				} catch (IOException e) {
					return null;
				}
			}
		}
		return null;
	}

	public Admin getbyUserName(String username) {
		return adminDao.getbyUserName(username);
	}

	/**
	 * 账户管理表---获取list方法
	 * 
	 * @throws Exception
	 */
	public Page<Admin> getByPageAdmin(Page<Admin> ps, Admin admin,
			String screening) throws Exception {
		return adminDao.getByPageAdmin(ps, admin, screening);
	}
	/**
	 * 账户管理表---删除方法
	 */
	public void deleteByIds(String ids) throws Exception {
		adminDao.deleteByIds(ids);
	}
	
	
	/** 
	 * <b>function:</b> 获取用户信息是否从CAS获取
	 * AdminService
	 * @createDate  2015-2-6 下午01:34:22
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type Admin 
	 */ 
	public Admin getByEmail(String emailName) throws Exception {
		return adminDao.getByEmail(emailName);
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
	public Admin getCurrentByPageParam(String loginname) throws Exception {
		return adminDao.getCurrentByPageParam(loginname);
	}
}
