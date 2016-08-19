package com.xcj.admin.service.admin;
import java.util.List;

import com.xcj.admin.base.BaseService;
import com.xcj.admin.entity.admin.Admin;
import com.xcj.common.page.Page;
/**
  * <b>function:</b> 超级管理员Service
  * @project base_frame 
  * @package com.xcj.admin.service.admin  
  * @fileName com.xcj.*
  * @createDate May 13, 2014 9:52:39 AM 
  * @author su_jian
 */
public interface AdminService extends BaseService{

   List<Admin> getSortList() throws Exception; 
   void removeById(Integer id)throws Exception;
   /**
	 * <b>function:</b> 获取当前用户名方法 <br/>
	 * 					登录之后如果未登录则放回null
	 * @project base_frame 
	 * @package com.xcj.admin.service.admin  
	 * @fileName com.xcj.*
	 * @createDate May 13, 2014 9:52:39 AM 
	 * @author su_jian
	*/
   public String getCurrentUsername();
   
   /**
	 * <b>function:</b>是否已经授权
	 * @project base_frame
	 * @package com.xcj.admin.service.impl.admin
	 * @fileName com.xcj.***
	 * @createDate May 13, 2014 10:03:39 AM
	 * @return boolean
	 * @author su_jian
	 */
	public boolean isAuthenticated();
	/**
	 * 获取当前用户信息 <b>function:</b>
	 * @project base_frame
	 * @package com.xcj.admin.service.impl.admin
	 * @fileName com.xcj.***
	 * @createDate May 13, 2014 10:05:38 AM
	 * @return Admin
	 * @author su_jian
	 */
	public Admin getCurrent();
	 /**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.admin.service.admin  
	   * @fileName com.xcj.*** 
	   * @createDate Jun 19, 2014 5:02:57 PM
	   * @return Admin
	   * @author su_jian
	   */
	Admin getbyUserName(String username)throws Exception ;
	// 获取分页List方法
	public Page<Admin> getByPageAdmin(Page<Admin> ps, Admin admin,
			String screening)throws Exception ;

	/**
	 * 账户管理表---删除方法
	 */
	void deleteByIds(String ids)throws Exception ;
	/** 
	 * <b>function:</b> 获取用户信息是否从CAS获取
	 * AdminService
	 * @createDate  2015-2-6 下午01:34:22
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type Admin 
	 */ 
	Admin getByEmail(String emailName)throws Exception ;
	
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
