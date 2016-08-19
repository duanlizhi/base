package com.xcj.admin.service.user;

import java.util.List;
import com.xcj.admin.entity.user.User;
import com.xcj.admin.base.BaseService;

/**
 *
 * <b>function:</b>  user会员信息表
 * @package com.xcj.admin.service.user.
 * @fileName com.xcj.admin.*
 * @createDate Sat Aug 23 17:32:37 CST 2014
 * @author name su_jian
 */
public interface UserService extends BaseService {

	/**
	 *
	 * <b>function:</b>  user会员信息表---获取list方法
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:40 CST 2014
	 * @return List<User>
	 * @author name su_jian
	 */
	List<User> getSortList() throws Exception;

	/**
	 *
	 * <b>function:</b>  user会员信息表---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:42 CST 2014
	 * @return void
	 * @author name su_jian
	 */
	void removeById(Integer id) throws Exception;
	/**
	 *
	 * <b>function:</b>  user会员信息表---获取当前用户信息
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:42 CST 2014
	 * @return void
	 * @author name su_jian
	 */
	public User getCurrent();
	/**
	 *
	 * <b>function:</b>  获取当前用户名
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:42 CST 2014
	 * @return void
	 * @author name su_jian
	 */
	public String getCurrentUsername();
	
	 /**根据邮箱获取用户信息
	   * <b>function:</b> 
	   * @project xcjpar
	   * @package com.xcj.web.service.user  
	   * @fileName com.xcj.*** 
	   * @createDate Aug 21, 2014 3:12:08 PM
	   * @return List<User>
	   * @author su_jian
	   */
	   List<User> getlistByEmail(String username)throws Exception;

	 /**
	   * <b>function:</b> 
	   * @project baseframe
	   * @package com.xcj.admin.service.user  
	   * @fileName com.xcj.*** 
	   * @createDate Aug 23, 2014 5:45:21 PM
	   * @return User
	   * @author su_jian
	   */
	User getByUsername(String webname);
}
