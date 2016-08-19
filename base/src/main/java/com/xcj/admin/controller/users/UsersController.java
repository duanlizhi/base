package com.xcj.admin.controller.users;
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
import com.xcj.admin.entity.users.Users;
import com.xcj.admin.service.users.UsersService;

/**
 *
  * <b>function:</b>  users
  * @package com.xcj.admin.controller.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:39 CST 2015
  * @author name_**
 */
@Controller("usersController")
@RequestMapping("/admin/users")
public class UsersController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

   @Resource(name="usersServiceImpl")
   private UsersService usersService;

   /**
    *
     * <b>function:</b>  users---init方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:39 CST 2015
     * @return ModelAndView
     * @author name_**
    */
   @RequestMapping(value="/usersInit",method=RequestMethod.GET)
   public ModelAndView usersInit(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
     return new ModelAndView("/admin/users/userslist_jsp",model);
   }

   /**
    *
     * <b>function:</b>  users---获取数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:39 CST 2015
     * @return List<Users> 
     * @author name_**
    */
   @RequestMapping(value="/usersList",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody List<Users> usersList(Model model) {
   List<Users> list=new ArrayList<Users>(); 
      try{
		list=usersService.getSortList(); 
	  }catch (Exception e) {
		  logger.error(UsersController.class.getName()+" usersList() exception: "+e.getMessage());
      }
     return  list;
   }


   /**
    *
     * <b>function:</b>  users---保存初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:39 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.GET)
   public String save(Model model) {
         return "/admin/users/userssave_jsp";
   }

   /**
    *
     * <b>function:</b>  users---保存的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:39 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String save(@Validated Users users,Model model) {
      try{
          usersService.save(users);
	  }catch (Exception e) {
		  logger.error(UsersController.class.getName()+" save() exception: "+e.getMessage());
      }
         return "redirect:/admin/users/userss_jsp";
     }

   /**
    *
     * <b>function:</b>  users---查看详细信息的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}",method=RequestMethod.GET)
   public String detail(@PathVariable Integer id,Model model) {
     Users users=new Users(); 
      try{
           users=usersService.getById(Users.class, id);
	  }catch (Exception e) {
		  logger.error(UsersController.class.getName()+" detail() exception: "+e.getMessage());
      }
         return "/admin/users/usersdetail_jsp";
     }



   /**
    *
     * <b>function:</b>  users---更新初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/update",method=RequestMethod.GET)
   public String update(@PathVariable Integer id,Model model) {
     Users users=new Users(); 
      try{
           users=usersService.getById(Users.class, id);
	     }catch (Exception e) {
	    	 logger.error(UsersController.class.getName()+" update() exception: "+e.getMessage());
         }
          model.addAttribute("users",users); 
         return "/admin/users/usersedit_jsp";
     }

   /**
    *
     * <b>function:</b>  users---更新数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return Users
     * @author name_**
    */
   @RequestMapping(value="/updata",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody Users  updata(Model model,Integer id) {
     Users users=new Users(); 
      try{
           users=usersService.getById(Users.class, id);
	     }catch (Exception e) {
	    	 logger.error(UsersController.class.getName()+" updata() exception: "+e.getMessage());
         }
         return users; 
     }

   /**
    *
     * <b>function:</b>  users---删除单条数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:40 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
   public String update(@PathVariable Integer id) {
      try{
           usersService.removeById(id);
	     }catch (Exception e) {
	    	 logger.error(UsersController.class.getName()+" delete() exception: "+e.getMessage());
         }
         return "redirect:/admin/users/userss_jsp";
     }

}
