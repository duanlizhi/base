/**
 * 
 */
package com.xcj.web.controller.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xcj.admin.service.user.UserService;

/** 
 * <b>function:</b> 
 * @project xcjpar 
 * @package com.xcj.web.controller.user  
 * @fileName com.xcj.*
 * @createDate Aug 21, 2014 3:02:05 PM 
 * @author su_jian 
 */
@Controller("indexController")
@RequestMapping("/web/index")
public class IndexController {
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	 @RequestMapping(value="")
	 public  ModelAndView indexs(HttpServletRequest request,HttpServletResponse response,ModelMap model) {  
		 return new ModelAndView("/web/index_jsp",model);
	 }
	
}
