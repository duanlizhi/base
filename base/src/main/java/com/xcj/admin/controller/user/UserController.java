package com.xcj.admin.controller.user;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.user.User;
import com.xcj.admin.service.user.UserService;

/**
 *
  * <b>function:</b>  user会员信息表
  * @package com.xcj.admin.controller.user.
  * @fileName com.xcj.admin.*
  * @createDate Sat Aug 23 17:32:26 CST 2014
  * @author name su_jian
 */
@Controller("userController")
@RequestMapping("/admin/user")
public class UserController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   @Resource(name="userServiceImpl")
   private UserService userService;

   /**
    *
     * <b>function:</b>  user会员信息表---init方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:27 CST 2014
     * @return ModelAndView
  * @author name su_jian
    */
   @RequestMapping(value="/userInit",method=RequestMethod.GET)
   public ModelAndView userInit(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
     return new ModelAndView("/admin/user/userlist_jsp",model);
   }

   /**
    *
     * <b>function:</b>  user会员信息表---获取数据的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:28 CST 2014
     * @return List<User> 
  * @author name su_jian
    */
   @RequestMapping(value="/userList",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody List<User> userList(Model model) {
   List<User> list=new ArrayList<User>(); 
      try{
		list=userService.getSortList(); 
	  }catch (Exception e) {
		  logger.error(UserController.class.getName()+" userList() exception: "+e.getMessage());
      }
     return  list;
   }


   /**
    *
     * <b>function:</b>  user会员信息表---保存初始化的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:29 CST 2014
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/save",method=RequestMethod.GET)
   public String save(Model model) {
         return "/admin/user/usersave_jsp";
   }

   /**
    *
     * <b>function:</b>  user会员信息表---保存的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:31 CST 2014
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String save(@Validated User user,Model model) {
      try{
          userService.save(user);
	  }catch (Exception e) {
		  logger.error(UserController.class.getName()+" save() exception: "+e.getMessage());
      }
         return "redirect:/admin/user/users_jsp";
     }

   /**
    *
     * <b>function:</b>  user会员信息表---查看详细信息的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:32 CST 2014
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/{id}",method=RequestMethod.GET)
   public String detail(@PathVariable Integer id,Model model) {
     User user=new User(); 
      try{
           user=userService.getById(User.class, id);
	  }catch (Exception e) {
		  logger.error(UserController.class.getName()+" detail() exception: "+e.getMessage());
      }
         return "/admin/user/userdetail_jsp";
     }



   /**
    *
     * <b>function:</b>  user会员信息表---更新初始化的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:34 CST 2014
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/{id}/update",method=RequestMethod.GET)
   public String update(@PathVariable Integer id,Model model) {
     User user=new User(); 
      try{
           user=userService.getById(User.class, id);
	     }catch (Exception e) {
	    	 logger.error(UserController.class.getName()+" update() exception: "+e.getMessage());
         }
          model.addAttribute("user",user); 
         return "/admin/user/useredit_jsp";
     }

   /**
    *
     * <b>function:</b>  user会员信息表---更新数据的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:35 CST 2014
     * @return User
  * @author name su_jian
    */
   @RequestMapping(value="/updata",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody User  updata(Model model,Integer id) {
     User user=new User(); 
      try{
           user=userService.getById(User.class, id);
	     }catch (Exception e) {
	    	 logger.error(UserController.class.getName()+" updata() exception: "+e.getMessage());
         }
         return user; 
     }

   /**
    *
     * <b>function:</b>  user会员信息表---删除单条数据的方法
     * @project base
     * @package com.xcj.admin.controller.user.
     * @fileName com.xcj.admin.*
     * @createDate Sat Aug 23 17:32:36 CST 2014
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
   public String update(@PathVariable Integer id) {
      try{
           userService.removeById(id);
	     }catch (Exception e) {
	    	 logger.error(UserController.class.getName()+" update() exception: "+e.getMessage());
         }
         return "redirect:/admin/user/users_jsp";
     }

}
