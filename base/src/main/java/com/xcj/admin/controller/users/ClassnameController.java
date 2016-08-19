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
import com.xcj.admin.entity.users.Classname;
import com.xcj.admin.service.users.ClassnameService;

/**
 *
  * <b>function:</b>  classname
  * @package com.xcj.admin.controller.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:26:57 CST 2015
  * @author name_**
 */
@Controller("classnameController")
@RequestMapping("/admin/classname")
public class ClassnameController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ClassnameController.class);

   @Resource(name="classnameServiceImpl")
   private ClassnameService classnameService;

   /**
    *
     * <b>function:</b>  classname---init方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return ModelAndView
     * @author name_**
    */
   @RequestMapping(value="/classnameInit",method=RequestMethod.GET)
   public ModelAndView classnameInit(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
     return new ModelAndView("/admin/users/classnamelist_jsp",model);
   }

   /**
    *
     * <b>function:</b>  classname---获取数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return List<Classname> 
     * @author name_**
    */
   @RequestMapping(value="/classnameList",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody List<Classname> classnameList(Model model) {
   List<Classname> list=new ArrayList<Classname>(); 
      try{
		list=classnameService.getSortList(); 
	  }catch (Exception e) {
		  logger.error(ClassnameController.class.getName()+" classnameList() exception: "+e.getMessage());
      }
     return  list;
   }


   /**
    *
     * <b>function:</b>  classname---保存初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.GET)
   public String save(Model model) {
         return "/admin/users/classnamesave_jsp";
   }

   /**
    *
     * <b>function:</b>  classname---保存的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String save(@Validated Classname classname,Model model) {
      try{
          classnameService.save(classname);
	  }catch (Exception e) {
		  logger.error(ClassnameController.class.getName()+" save() exception: "+e.getMessage());
      }
         return "redirect:/admin/users/classnames_jsp";
     }

   /**
    *
     * <b>function:</b>  classname---查看详细信息的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}",method=RequestMethod.GET)
   public String detail(@PathVariable Integer id,Model model) {
     Classname classname=new Classname(); 
      try{
           classname=classnameService.getById(Classname.class, id);
	  }catch (Exception e) {
		  logger.error(ClassnameController.class.getName()+" detail() exception: "+e.getMessage());
      }
         return "/admin/users/classnamedetail_jsp";
     }



   /**
    *
     * <b>function:</b>  classname---更新初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/update",method=RequestMethod.GET)
   public String update(@PathVariable Integer id,Model model) {
     Classname classname=new Classname(); 
      try{
           classname=classnameService.getById(Classname.class, id);
	     }catch (Exception e) {
	    	 logger.error(ClassnameController.class.getName()+" update() exception: "+e.getMessage());
         }
          model.addAttribute("classname",classname); 
         return "/admin/users/classnameedit_jsp";
     }

   /**
    *
     * <b>function:</b>  classname---更新数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return Classname
     * @author name_**
    */
   @RequestMapping(value="/updata",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody Classname  updata(Model model,Integer id) {
     Classname classname=new Classname(); 
      try{
           classname=classnameService.getById(Classname.class, id);
	     }catch (Exception e) {
	    	 logger.error(ClassnameController.class.getName()+" updata() exception: "+e.getMessage());
         }
         return classname; 
     }

   /**
    *
     * <b>function:</b>  classname---删除单条数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:26:57 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
   public String update(@PathVariable Integer id) {
      try{
           classnameService.removeById(id);
	     }catch (Exception e) {
	    	 logger.error(ClassnameController.class.getName()+" delete() exception: "+e.getMessage());
         }
         return "redirect:/admin/users/classnames_jsp";
     }

}
