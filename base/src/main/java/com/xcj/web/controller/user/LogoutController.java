/**
 * 
 */
package com.xcj.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.user.User;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.WebUtil;

/** 
 * <b>function:</b> 
 * @project xcjpar 
 * @package com.xcj.web.controller.user  
 * @fileName com.xcj.*
 * @createDate Aug 22, 2014 5:45:58 PM 
 * @author su_jian 
 */
@Controller("logoutController")
@RequestMapping("logout")
public class LogoutController extends BaseController {
	/**
	 * 注销
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.removeAttribute(User.PRINCIPAL_ATTRIBUTE_NAME);
		WebUtil.removeCookie(request, response, Constants.XCJ_COOKIE_NAME);
		return "redirect:/web/index_jsp";
	}
}
