package com.xcj.admin.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.admin.Admin;
import com.xcj.admin.entity.admin.AdminRole;
import com.xcj.admin.entity.admin.Role;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.admin.service.admin.RoleAuthorityService;
import com.xcj.common.base.GenerateNumber;
import com.xcj.common.entity.Principal;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.HttpCasUtil;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.common.util.common.StringUtil;
import net.spy.memcached.MemcachedClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * <b>function:</b> 测试类的control <b>备注信息</b> 返回类型后面分为JSP和FTL 分别转向 不同的试图
 * 
 * @project Lgguo
 * @package com.xcj.admin.AdminController
 * @fileName TestController.java
 * @createDate 2010-4-3 下午05:28:03
 * @author su_jian
 * @email sujiansoft@163.com
 */
@Controller("adminController")
@RequestMapping("/admin")
public class AdminController extends BaseController {
	private static final Logger log = LoggerFactory
			.getLogger(AdminController.class);
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	@Resource(name = "sessionManager")
	org.apache.shiro.web.session.mgt.DefaultWebSessionManager sessionManager;
	@Resource(name = "sessionDAO")
	com.xcj.common.session.ShiroSessionDAO sessionDAO;
	@Resource(name="roleAuthorityServiceImpl")
	private RoleAuthorityService roleAuthorityService;
	
	/***
	 * <b>function:</b> 登录方法执行
	 * @project Lgguo
	 * @package com.xcj.admin.controller
	 * @fileName TestController.java
	 * @createDate 2010-4-1 下午05:28:03
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/sublogin", method = RequestMethod.POST)
	public String login(@Validated String username, String password,
			String isRememberUsername, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		 String message = "false";
		 
		 //TODO 需要本地登录则把此处注释
		 //从CAS同步账户信息到本地 start
		/** String tgt=HttpCasUtil.getTicketGrantingTicket(APIConstants.CAS_URL_PREFIX_REST+"/v1/tickets",
		 username, password);
		 if(tgt==null){
			 message = "CAS登录不成功";
			 return "redirect:/admin/login.jsp_jsp"; 
		 }else{
			 message="";
			 //WebUtil.addCookie(request,response,"CASTGC",tgt,Constants.XCJ_COOKIE_TIME);
		 }
		 try {
			 Map<String, String> map = new HashMap<String, String>();
			 map.put("loginname", username);
			 String strJson = HttpUtil.doGet(APIConstants.CAS_BASE_URL, map);
				Map<String, String> mapJson = (Map<String, String>) JSON
						.parse(strJson);
			 Admin admin = adminService.getCurrentByPageParam(username);
			 if(admin!=null){
				 admin.setUsername(mapJson.get("username"));
				 admin.setEmail(mapJson.get("email"));
				 admin.setModifyDate(DateUtil.getCurrentTimeByDate());
				 admin.setMobile(mapJson.get("mobile"));
				 admin.setName(mapJson.get("name"));
				 admin.setPassword(mapJson.get("password"));
				 adminService.update(admin);
			 }else{
					Admin adminNew = new Admin();
				 	adminNew.setAdminNumber(GenerateNumber.getInstance().getAdminNumber());
					adminNew.setCreateDate(DateUtil.getCurrentTimeByDate());
					adminNew.setDepartment("小车匠");
					adminNew.setEmail(mapJson.get("email"));
					adminNew.setIsEnabled(1);
					adminNew.setIsLocked(0);
					adminNew.setLoginDate(DateUtil.getCurrentTimeByDate());
					adminNew.setLoginFailureCount(0);
					adminNew.setLoginIp("127.0.0.1");
					adminNew.setMobile(mapJson.get("mobile"));
					adminNew.setModifyDate(DateUtil.getCurrentTimeByDate());
					adminNew.setName(mapJson.get("name"));
					adminNew.setPassword(mapJson.get("password"));
					adminNew.setUsername(mapJson.get("username"));
					adminService.save(adminNew);
					AdminRole adminRole = new AdminRole();
					adminRole.setAdmin(adminNew);
					Role role = roleAuthorityService.getById(Role.class, 2);
					adminRole.setRole(role); 
					roleAuthorityService.save(adminRole);
			 }
		 } catch (Exception e3) {
			 message = "系统异常";
				return "redirect:/admin/login.jsp_jsp";
		 }
		 //从CAS同步账户信息到本地 end
		 **/
		org.apache.shiro.subject.Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		if (isRememberUsername != null) {
			token.setRememberMe(true);
		}
		try {
			user.login(token);
			message = "";
		} catch (UnsupportedTokenException e) {// 验证码输入错误
			message = "验证码错误";
			log.error(AdminController.class.getName()+" login() exception: "+e.getMessage());
		} catch (UnknownAccountException e1) {// /此账号不存在
			message = "帐号不存在";
			log.error(AdminController.class.getName()+" login() exception: "+e1.getMessage());
		} catch (DisabledAccountException e2) {// /此账号已被禁用
			message = "帐号被禁用";
			log.error(AdminController.class.getName()+" login() exception: "+e2.getMessage());
		} catch (IncorrectCredentialsException e4) {// 密码错误，若连续{0}次密码错误账号将被锁定
			message = "输入密码错误";
			log.error(AdminController.class.getName()+" login() exception: "+e4.getMessage());
			// TODO需要做的事情
			/*
			 * if (1==1) {//帐号锁定的话 //锁定 } else { //用户名或密码错误 }
			 */
		} catch (AuthenticationException e5) {// 账号认证失败
			message = "帐号认证失败";
			log.error(AdminController.class.getName()+" login() exception: "+e5.getMessage());
		}
		if (message.equals("")) {
			return "redirect:/admin/common/main_jsp";
		} else {
			return "redirect:/admin/login.jsp_jsp";
		}

	}

	/**
	 * 
	 * <b>function:</b> admin:保存初始化的方法
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:03 CST 2015
	 * @return String
	 * @author name su_jian
	 **/
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "/admin/login.jsp_jsp";
	}

	// 注销登录
	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logout(@Validated String username, String password,
			String isRememberUsername, ModelMap model, HttpServletRequest request) {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils
				.getSubject();
		
		try {
			if(null!=currentUser){
				Principal principal = (Principal) currentUser.getPrincipal();
				if (null!=principal) {
					MemcachedClient mc1 = MemcachedClientFactory.getInstance();
					if(null!=(mc1.get(Constants.XCJ_MEMCACHED_USER_NAME + "_authroit_"
							+ principal.getUsername()))){
						mc1.delete(Constants.XCJ_MEMCACHED_USER_NAME + "_authroit_"
								+ principal.getUsername());
					}
					if(null != mc1.get(Constants.XCJ_MEMCACHED_USER_NAME + "_role_"
							+ principal.getUsername())) {
						mc1.delete(Constants.XCJ_MEMCACHED_USER_NAME + "_role_"
								+ principal.getUsername());
					}
					if(null != mc1.get("current_username_" + username)) {
						mc1.delete("current_username_" + username);
					}
				}
			}
		} catch (IOException e) {
			log.error(AdminController.class.getName()+" logout() exception: "+e.getMessage());
		}
		if (null != currentUser) {
			if (currentUser.isAuthenticated()) {
				currentUser.logout();
			}
		}
		
		return "redirect:/admin/login_jsp";
	}

	// 首页跳转
	@RequestMapping(value = "/")
	public String home(ModelMap model) {
		return "redirect:/admin/common/main_jsp";
	}

	/**
	 * 
	 * <b>function:</b> admin:分页获取数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:03 CST 2015
	 * @return List<admin>
	 * @author name su_jian
	 **/
	@RequestMapping(value = "/admin/adminList")
	public String accountList(Model model, Page<Admin> ps, String screening) {
		try {
			Page<Admin> p = adminService.getByPageAdmin(ps, new Admin(),
					screening);
			model.addAttribute("page", p);
			model.addAttribute("screening", screening);
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" accountList() exception: "+e.getMessage());
		}
		return "/admin/admin/adminList_jsp";
	}

	/**
	 * 
	 * <b>function:</b> admin:保存初始化的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:03 CST 2015
	 * @return String
	 * @author name su_jian
	 **/
	@RequestMapping(value = "/admin/save", method = RequestMethod.GET)
	public String save(Model model) {
		return "/admin/admin/adminsave_jsp";
	}

	/**
	 * <b>function:</b> *admin:保存的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:03 CST 2015
	 * @return String
	 * @author name su_jian
	 **/
	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String save(@Validated Admin admin, Model model) {
		try {
			Admin oldAdmin = adminService.getByEmail(admin.getEmail());
			oldAdmin.setModifyDate(DateUtil.getCurrentTimeByDate());
			adminService.update(oldAdmin);
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" save() exception: "+e.getMessage());
		}
		return "redirect:/admin/admin/adminList_jsp";
	}

	/**
	 * 标签管理表:更新初始化的方法
	 */
	@RequestMapping(value = "/admin/updateById", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject updateById(Integer id, Model model) {
		JSONObject json = new JSONObject();
		try {
			// 根据id获取当前标签
			Admin admin = adminService.getById(Admin.class, id);
			json.put("data", admin);
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" updateById() exception: "+e.getMessage());
		}
		return json;
	}

	/**
	 * 
	 * <b>function:</b> admin:更新数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:04 CST 2015
	 * @return admin
	 * @author name su_jian
	 */
	@RequestMapping(value = "/admin/updata", method = RequestMethod.POST)
	public String updata(@Validated Admin admin, Model model) {
		Admin oldadmin = new Admin();
		try {
			oldadmin = adminService.getById(Admin.class, admin.getId());
			admin.setCreateDate(oldadmin.getCreateDate());
			admin.setModifyDate(DateUtil.getCurrentTimeByDate());
			adminService.update(admin);
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" updata() exception: "+e.getMessage());
		}
		return "redirect:/admin/admin/adminlist_jsp";
	}

	/**
	 * 
	 * <b>function:</b> 启用禁用功能
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.admin.
	 * @fileName com.xcj.admin.*
	 * @createDate Wed Feb 04 13:24:04 CST 2015
	 * @return admin
	 * @author name su_jian
	 */
	@RequestMapping(value = "/admin/adminIsAbled", method = RequestMethod.GET)
	public String adminIsAbled(Integer id) {
		try {
			Admin admin = adminService.getById(Admin.class, id);
			Integer isEnabled = admin.getIsEnabled();
			Map<String, String> params = new HashMap<String, String>();
			params.put("email", admin.getEmail());
			params.put("uname", admin.getUsername());
			HttpUtil.doPost(APIConstants.CAS_UPDATE_ADMIN_URL_ISABLED, params);
			
			if (isEnabled == 1) {
				admin.setIsEnabled(0);
			} else {
				admin.setIsEnabled(1);
			}
			adminService.update(admin);
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" adminIsAbled() exception: "+e.getMessage());
		}
		return "redirect:/admin/admin/adminList_jsp";
	}

	/**
	 * 账户管理表---删除方法
	 */
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject delete(String ids, String unames) {
		JSONObject json = new JSONObject();
		try {
			if (ids != "") {
				// 根据ID的1,2形式执行sql id in(1,2)格式删除标签
				adminService.deleteByIds(ids);
				// 返回页面删除结果
				json.put("data", "删除成功");

				if (!StringUtil.isEmptyYl(unames)) {
					Map<String, String> params = new HashMap<String, String>();
					params.put("unames", unames);
					HttpUtil.doPost(APIConstants.CAS_UPDATE_ADMIN_URL_DEL_USERS, params);
				}
			} else {
				json.put("data", "请选择要删除的项");
			}
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" delete() exception: "+e.getMessage());
		}
		return json;
	}

	/**
	 * <b>function:</b> 获取用户信息是否从CAS获取 AdminController
	 * 
	 * @createDate 2015-2-6 下午01:34:04
	 * @author su_jian
	 * @email sujiansoft@163.com return type JSONObject
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/emailValidate", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject emailValidate(String emailName, Model model) {
		JSONObject data = new JSONObject();
		try {
			if (StringUtil.isEmptys(emailName)) {
				data.put("flag", "0");// 用户名不能为空
				return data;
			}
			// TODO所有的数据目前每一次都从CAS同步
			Admin admin = adminService.getByEmail(emailName);
			if (admin != null) {
				data.put("mobile", admin.getMobile());
				data.put("name", admin.getName());
				data.put("username", admin.getUsername());
				return data;
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("loginname", emailName);
				map.put("method", "get");
				String strJson = HttpUtil.doGet(APIConstants.CAS_BASE_URL, map);
				Map<String, String> map1 = (Map<String, String>) JSON
						.parse(strJson);
				if (String.valueOf(map1.get("errcode")).equals("20004")
						|| String.valueOf(map1.get("errcode")).equals("20003")
						&& map1.size()>0) {
					data.put("flag", "1");// CAS系统中不存在此用户
					return data;
				} else {
					Admin adminNew = new Admin();
					adminNew.setAdminNumber(GenerateNumber.getInstance()
							.getAdminNumber());
					adminNew.setCreateDate(DateUtil.getCurrentTimeByDate());
					adminNew.setDepartment("小车匠");
					adminNew.setEmail(map1.get("email"));
					adminNew.setIsEnabled(1);
					adminNew.setIsLocked(0);
					adminNew.setLoginDate(DateUtil.getCurrentTimeByDate());
					adminNew.setLoginFailureCount(0);
					adminNew.setLoginIp("");
					adminNew.setMobile(map1.get("mobile"));
					adminNew.setModifyDate(DateUtil.getCurrentTimeByDate());
					adminNew.setName(map1.get("name"));
					adminNew.setPassword(map1.get("password"));
					adminNew.setUsername(map1.get("username"));
					adminService.save(adminNew);
					
					//TODO需要授权
					AdminRole adminRole = new AdminRole();
					adminRole.setAdmin(adminNew);
					Role role = roleAuthorityService.getById(Role.class, 2);
					adminRole.setRole(role); 
					roleAuthorityService.save(adminRole);
					
					data.put("mobile", map1.get("mobile"));
					data.put("name", map1.get("name"));
					data.put("username", map1.get("username"));
				}
			}
		} catch (Exception e) {
			log.error(AdminController.class.getName()+" emailValidate() exception: "+e.getMessage());
		}
		return data;
	}

}
