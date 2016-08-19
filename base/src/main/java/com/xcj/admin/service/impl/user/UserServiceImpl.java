package com.xcj.admin.service.impl.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xcj.admin.base.BaseServiceImpl;
import com.xcj.admin.dao.user.UserDao;
import com.xcj.admin.entity.user.User;
import com.xcj.admin.service.user.UserService;
import com.xcj.common.entity.Principal;

/**
 *
 * <b>function:</b>  user会员信息表
 * @package com.xcj.admin.service.impl.user.
 * @fileName com.xcj.admin.*
 * @createDate Sat Aug 23 17:32:43 CST 2014
 * @author name su_jian
 */
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Resource(name = "userDaoImpl")
	private UserDao userDao;

	/**
	 *
	 * <b>function:</b>  user会员信息表---获取list方法
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:44 CST 2014
	 * @return List<User>
	 * @author name su_jian
	 */
	public List<User> getSortList() throws Exception {
		return userDao.getSortList();
	}

	/**
	 *
	 * <b>function:</b>  user会员信息表---根据id删除数据方法
	 * @project base
	 * @package com.xcj.admin.service.user.
	 * @fileName com.xcj.admin.*
	 * @createDate Sat Aug 23 17:32:45 CST 2014
	 * @return void
	 * @author name su_jian
	 */
	public void removeById(Integer id) throws Exception {
		userDao.removeById(id);
		//baseDao.delete(baseDao.getById(User,id));
	}
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
	public User getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(User.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return userDao.getById(User.class,Integer.valueOf(principal.getId().toString()));
			}
		}
		return null;
	}
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
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(User.PRINCIPAL_ATTRIBUTE_NAME);
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}
	/**
	 * 根据邮箱获取用户信息
	 */
	public List<User> getlistByEmail(String username) throws Exception {
		return userDao.getlistByEmail(username);
	}
 
	public User getByUsername(String webname) {
		return userDao.getByUserName(webname);
	}
	
}
