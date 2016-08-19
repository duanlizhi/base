/**
 * 
 */
package com.xcj.web.controller.user;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.user.User;
import com.xcj.admin.service.user.UserService;
import com.xcj.common.entity.Principal;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.SettingUtil;
import com.xcj.common.util.common.WebUtil;

/** 
 * <b>function:</b> 
 * @project xcjpar 
 * @package com.xcj.web.controller.user  
 * @fileName com.xcj.*
 * @createDate Aug 21, 2014 3:02:05 PM 
 * @author su_jian 
 */
@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
	@Resource(name="userServiceImpl")
	private UserService userService;
	/**
	 * 登录提交
	 * @throws Exception 
	 */
	 @RequestMapping(value = "/submit",method=RequestMethod.POST)
	public @ResponseBody JSONObject submit(HttpServletResponse resp,HttpServletRequest req, HttpSession session,String webname, String password) throws Exception {
		JSONObject data = new JSONObject();
		String message="";
		session = req.getSession(true);
		if (StringUtils.isEmpty(webname) || StringUtils.isEmpty(password)) {
			 message="用户名或者密码为空";
			 data.put("code", "01");
			 data.put("message", message);
			 return data;
		}
		User user=null;
		if (webname.contains("@")) {
			List<User> users = userService.getlistByEmail(webname);
			if(users==null){
				user = null;
				message="没有找到此用户信息";
			    data.put("code", "02");
				data.put("message", message);
			}else{
				if (users.isEmpty()){
					user = null;
				} else if (users.size() == 1) {
					user = users.get(0);
				} else {
					message="没有找到此用户信息";
				    data.put("code", "02");
					data.put("message", message);
				}
			}
		} else {
			user = userService.getByUsername(webname);
		}
		if (user == null) {
			message="未知的用户名";
		    data.put("code", "03");
			data.put("message", message);
		}else{
			if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
				message="密码不正确";
			    data.put("code", "04");
				data.put("message", message);
			}else{
				//是否锁住。是否禁用。是否可以用Email登录。是否启用都在这里进行设置
				//可以从配置文件或者从数据库User表取得相关信息以后进行处理
				//登录失败的次数也在这里进行设置 。目前没有
				user.setLoginIp(req.getRemoteAddr());
				user.setLoginDate(DateUtil.getCurrentTimeByDate());
				userService.update(user);
				Map<String, Object> attributes = new HashMap<String, Object>();
				Enumeration<?> keys = session.getAttributeNames();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					attributes.put(key, session.getAttribute(key));
				}
				//session.invalidate();
				for (Entry<String, Object> entry : attributes.entrySet()) {
					session.setAttribute(entry.getKey(), entry.getValue());
				}
				message="登录成功";
			    data.put("code", "00");
				data.put("message", message);
				session.setAttribute(User.PRINCIPAL_ATTRIBUTE_NAME, new Principal(Long.valueOf(user.getId()), webname));
				WebUtil.addCookie(req, resp, Constants.XCJ_COOKIE_NAME, user.getUsername(),Constants.XCJ_COOKIE_TIME);
			}
		}
		return data;
	}
	 
	 @RequestMapping(value="")
	 public  String indexs(String redirectUrl,HttpServletRequest request,ModelMap model) {
		 SettingUtil util =new SettingUtil();
			if (redirectUrl != null && !redirectUrl.equalsIgnoreCase(util.getSetting("siteUrl")) && !redirectUrl.startsWith(request.getContextPath() + "/") && !redirectUrl.startsWith(util.getSetting("siteUrl") + "/")) {
				redirectUrl = null;
			}
			model.addAttribute("redirectUrl", redirectUrl);
		 return  "redirect:/index.jsp_jsp";
	 }
	
	 
}
