package com.xcj.admin.dao.admin;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.xcj.admin.base.BaseDao;
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
public interface AdminDao extends BaseDao {

	public <T extends Admin> List<T> getSortList() throws DataAccessException;

	public void removeById(Integer id) throws DataAccessException;

	 /**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.admin.dao.admin  
	   * @fileName com.xcj.*** 
	   * @createDate Jun 19, 2014 5:03:35 PM
	   * @return Admin
	   * @author su_jian
	   */
	public Admin getbyUserName(String username)throws DataAccessException;

	// account---获取list方法

	public Page<Admin> getByPageAdmin(Page<Admin> ps, Admin account,
			String screening)throws Exception;

	/**
	 * 账户管理表---删除方法
	 */

	public void deleteByIds(String ids)throws Exception;

	/** 
	 * <b>function:</b> 获取用户信息是否从CAS获取
	 * AdminDao
	 * @createDate  2015-2-6 下午01:36:01
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type Admin 
	 */ 
	public Admin getByEmail(String emailName);
	
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
	public Admin getCurrentByPageParam(String loginname) throws Exception;
}
