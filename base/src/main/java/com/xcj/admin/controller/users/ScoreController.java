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
import com.xcj.admin.entity.users.Score;
import com.xcj.admin.service.users.ScoreService;

/**
 *
  * <b>function:</b>  score
  * @package com.xcj.admin.controller.users.
  * @fileName com.xcj.admin.*
  * @createDate Mon Jan 19 14:27:12 CST 2015
  * @author name_**
 */
@Controller("scoreController")
@RequestMapping("/admin/score")
public class ScoreController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);

   @Resource(name="scoreServiceImpl")
   private ScoreService scoreService;

   /**
    *
     * <b>function:</b>  score---init方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return ModelAndView
     * @author name_**
    */
   @RequestMapping(value="/scoreInit",method=RequestMethod.GET)
   public ModelAndView scoreInit(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
     return new ModelAndView("/admin/users/scorelist_jsp",model);
   }

   /**
    *
     * <b>function:</b>  score---获取数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return List<Score> 
     * @author name_**
    */
   @RequestMapping(value="/scoreList",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody List<Score> scoreList(Model model) {
   List<Score> list=new ArrayList<Score>(); 
      try{
		list=scoreService.getSortList(); 
	  }catch (Exception e) {
		  logger.error(ScoreController.class.getName()+" scoreList() exception: "+e.getMessage());
      }
     return  list;
   }


   /**
    *
     * <b>function:</b>  score---保存初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.GET)
   public String save(Model model) {
         return "/admin/users/scoresave_jsp";
   }

   /**
    *
     * <b>function:</b>  score---保存的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String save(@Validated Score score,Model model) {
      try{
          scoreService.save(score);
	  }catch (Exception e) {
		  logger.error(ScoreController.class.getName()+" save() exception: "+e.getMessage());
      }
         return "redirect:/admin/users/scores_jsp";
     }

   /**
    *
     * <b>function:</b>  score---查看详细信息的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}",method=RequestMethod.GET)
   public String detail(@PathVariable Integer id,Model model) {
     Score score=new Score(); 
      try{
           score=scoreService.getById(Score.class, id);
	  }catch (Exception e) {
		  logger.error(ScoreController.class.getName()+" detail() exception: "+e.getMessage());
      }
         return "/admin/users/scoredetail_jsp";
     }



   /**
    *
     * <b>function:</b>  score---更新初始化的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/update",method=RequestMethod.GET)
   public String update(@PathVariable Integer id,Model model) {
     Score score=new Score(); 
      try{
           score=scoreService.getById(Score.class, id);
	     }catch (Exception e) {
	    	 logger.error(ScoreController.class.getName()+" update() exception: "+e.getMessage());
         }
          model.addAttribute("score",score); 
         return "/admin/users/scoreedit_jsp";
     }

   /**
    *
     * <b>function:</b>  score---更新数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return Score
     * @author name_**
    */
   @RequestMapping(value="/updata",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
   public @ResponseBody Score  updata(Model model,Integer id) {
     Score score=new Score(); 
      try{
           score=scoreService.getById(Score.class, id);
	     }catch (Exception e) {
	    	 logger.error(ScoreController.class.getName()+" updata() exception: "+e.getMessage());
         }
         return score; 
     }

   /**
    *
     * <b>function:</b>  score---删除单条数据的方法
     * @project xcjtest
     * @package com.xcj.admin.controller.users.
     * @fileName com.xcj.admin.*
     * @createDate Mon Jan 19 14:27:12 CST 2015
     * @return String
     * @author name_**
    */
   @RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
   public String update(@PathVariable Integer id) {
      try{
           scoreService.removeById(id);
	     }catch (Exception e) {
	    	 logger.error(ScoreController.class.getName()+" delete() exception: "+e.getMessage());
         }
         return "redirect:/admin/users/scores_jsp";
     }

}
