package com.xcj.admin.controller.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xcj.admin.base.BaseController;

/*** 
 * <b>function:</b> 测试类的control
 * <b>备注信息</b>  返回类型后面分为JSP和FTL 分别转向 不同的试图
 * @project Lgguo 
 * @package com.xcj.admin.AdminController  
 * @fileName TestController.java 
 * @createDate 2010-4-3 下午05:28:03 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */
@Controller("demoController")
@RequestMapping("/admin/demo")
public class DemoController extends BaseController {
	/*** 
	 * <b>function:</b> 初始化
	 * @project Lgguo 
	 * @package com.xcj.admin.controller  
	 * @fileName TestController.java 
	 * @createDate 2010-4-1 下午05:28:03 
	 * @author su_jian 
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView userlist(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		return new ModelAndView("/admin/demo/userlist_jsp", model);
	}

	/*** 
	 * <b>function:</b> 新增初始化
	 * @project Lgguo 
	 * @package com.xcj.admin.controller  
	 * @fileName TestController.java 
	 * @createDate 2010-4-1 下午05:28:03 
	 * @author su_jian 
	 */
	@RequestMapping(value = "/usersave", method = RequestMethod.GET)
	public ModelAndView usersave(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		return new ModelAndView("/admin/demo/usersave_jsp", model);
	}

	/*** 
	 * <b>function:</b> 更新初始化
	 * @project Lgguo 
	 * @package com.xcj.admin.controller  
	 * @fileName TestController.java 
	 * @createDate 2010-4-1 下午05:28:03 
	 * @author su_jian 
	 */
	@RequestMapping(value = "/userupdate", method = RequestMethod.GET)
	public ModelAndView userupdate(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		return new ModelAndView("/admin/demo/useredit_jsp", model);
	}

}
