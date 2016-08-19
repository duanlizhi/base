package com.xcj.admin.controller.domain;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.DomainCourseWare;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.admin.entity.knowledgePoint.KnowledgePoint;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.admin.service.knowledgePoint.KnowledgePointService;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.common.base.GenerateNumber;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.md5.MD5Uitl;

 /**
  * <b>function:</b>  domain域管理表
  * @package com.xcj.admin.controller.domain.
  * @fileName com.xcj.admin.*
  * @createDate Mon Feb 02 17:39:30 CST 2015
  * @author name su_jian
 */
@Controller("domainController")
@RequestMapping("/admin/domain")
public class DomainController extends BaseController{

   private static final Logger log = LoggerFactory.getLogger(DomainController.class); 
   @Resource(name="domainServiceImpl")
   private DomainService domainService;
   
   @Resource(name="courseWareServiceImpl")
   private CourseWareService courseWareService;
   
   @Resource(name="paperServiceImpl")
   private PaperService paperService;
   
   @Resource(name="testsPostedServiceImpl")
   private TestsPostedService testsPostedService;
   
   @Resource(name="knowledgePointServiceImpl")
   private KnowledgePointService knowledgePointService;

    /**
     *
     * <b>function:</b>  domain域管理表:分页获取数据的方法
    * @project base
    * @package com.xcj.admin.controller.domain.
    * @fileName com.xcj.admin.*
    * @createDate Mon Feb 02 17:39:30 CST 2015
    * @return List<Domain> 
  * @author name su_jian
     **/
   @RequestMapping(value="/domainList")
   public String   domainList(Model model,Page<Domain> ps,String inname) {
      try{

		Domain domain= new Domain(); 
        domain.setName(inname);  
		Page<Domain> p=domainService.getByDomainPage(ps,domain); 
		model.addAttribute("page", p); 
		model.addAttribute("inname", inname); 
	  }catch (Exception e) {
          log.error("域管理表:获取分页对象出错：" + e.getMessage()); 
      }
     return  "/admin/domain/domainList_jsp";
   }


   /**
    *
     * <b>function:</b>  domain域管理表:保存初始化的方法
     * @project base
     * @package com.xcj.admin.controller.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:30 CST 2015
     * @return String
  * @author name su_jian
     **/
   @RequestMapping(value="/save",method=RequestMethod.GET)
   public String save(Model model) {
         return "/admin/domain/domainsave_jsp";
   }

    /**
     *
     * <b>function:</b>     *domain域管理表:保存的方法
     * @project base
     * @package com.xcj.admin.controller.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:30 CST 2015
     * @return String
  * @author name su_jian
     **/
   @RequestMapping(value="/save",method=RequestMethod.POST)
   public String save(@Validated Domain domain,Model model) {
      try{
    	  domain.setEnable(1);
    	  domain.setPassword(MD5Uitl.MD5Encode(domain.getPassword()));
    	  domain.setDomainNumber(GenerateNumber.getInstance().getDoaminNumber());
    	  domain.setCreateDate(DateUtil.getCurrentTimeByDate());  
    	  domain.setModifyDate(DateUtil.getCurrentTimeByDate());  
          domainService.save(domain);
	  }catch (Exception e) {
          log.error("域管理表:保存对象出错：" + e.getMessage()); 

      }
     return  "redirect:/admin/domain/domainList_jsp";
     }

   /**
    *
     * <b>function:</b>  domain域管理表---查看详细信息的方法
     * @project base
     * @package com.xcj.admin.controller.domain.
     * @fileName com.xcj.admin.*
     * @createDate Mon Feb 02 17:39:30 CST 2015
     * @return String
  * @author name su_jian
    */
   @RequestMapping(value="/{id}",method=RequestMethod.GET)
   public String detail(@PathVariable Integer id,Model model) {
     Domain domain=new Domain(); 
      try{
           domain=domainService.getById(Domain.class, id);
			model.addAttribute("domain", domain); 
	  }catch (Exception e) {
          log.error("域管理表:查看对象出错：" + e.getMessage()); 
      }
         return "/admin/domain/domaindetail_jsp";
     }



     /**
      *
      * <b>function:</b>      domain域管理表:更新数据的方法
      * @project base
      * @package com.xcj.admin.controller.domain.
      * @fileName com.xcj.admin.*
      * @createDate Mon Feb 02 17:39:31 CST 2015
      * @return Domain
  * @author name su_jian
      */
   @RequestMapping(value="/update",method=RequestMethod.POST)
   public String update(@Validated Domain domain) {
	   try{
		   Domain oldDomain = domainService.getById(Domain.class, domain.getId());
		   oldDomain.setModifyDate(DateUtil.getCurrentTimeByDate());  
		   oldDomain.setUrl(domain.getUrl());
		   oldDomain.setPassword(MD5Uitl.MD5Encode(domain.getPassword()));
//		   oldDomain.setDomainNumber(domain.getDomainNumber());
		   oldDomain.setName(domain.getName());
		   oldDomain.setUsername(domain.getUsername());
           domainService.update(oldDomain); 
	   }catch (Exception e) {
             log.error("域管理表:更新对象出错：" + e.getMessage()); 
	   }
	   return  "redirect:/admin/domain/domainList_jsp";     
   }

   /**
    * 
      * 验证域名称validateName
      * <b>function:</b> 
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-2-9 下午01:40:00
      * @return String
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value="/validateName")
   public JSONObject validateName(String inname, String id) {
	   
	   JSONObject data = new JSONObject();
	   try{
		   log.info("domain validateName success name:"+inname);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, domainService.validateName(inname, id) ? APIConstants.ERRCODE_FU1 : APIConstants.ERRCODE_SUCCESS);
	   }catch (Exception e) {
		   log.info("domain validateName error name:"+inname);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }

     /**
      * <b>function:</b> 验证域账号
      * @return String
      * @project base
      * @package com.xcj.admin.controller.domain
      * @fileName com.xcj.***
      * @createDate 2015-2-9 下午01:40:00
      * @author hehujun
      */
     @ResponseBody
     @RequestMapping(value = "/validateUsername")
     public JSONObject validateUsername(String inname, String id) {

         JSONObject data = new JSONObject();
         try {
             log.info("domain validateName success name:" + inname);
             data.put(Constants.XCJ_RETURN_API_ERRCODE, domainService.validateUsername(inname, id) ? APIConstants.ERRCODE_FU1 : APIConstants.ERRCODE_SUCCESS);
         } catch (Exception e) {
             log.info("domain validateName error name:" + inname);
             data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
             data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG__FU1);
         }
         return data;
     }
   
   /**
    * 
      * 批量删除
      * <b>function:</b> delete()
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-2-9 下午04:32:40
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody	
   @RequestMapping(value = "/delete", method = RequestMethod.GET)
   public JSONObject delete(String ids) {
   		
	   JSONObject data = new JSONObject();
   		
	   if(StringUtil.isEmptyYl(ids)) {
		   log.info("domain delete ids null");
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
	   }
	   
	   try {
		    boolean flag = domainService.deleteDomainAllByDomainId(ids);
   			domainService.deleteEntityByIds(Domain.class, ids);
   			log.info("domain delete success ids:"+ids);
   			data.put("flag", flag);
   			data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
	   } catch (Exception e) {
		   log.error("domain delete error ids:"+ids);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
   		
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 删除之前查询关联表中是否有数据
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-2-9 下午04:32:40
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody	
   @RequestMapping(value = "/getCourseWares4Delete", method = RequestMethod.GET)
   public JSONObject getCourseWares4Delete(String ids) {
   		
	   JSONObject data = new JSONObject();
   		
	   if(StringUtil.isEmptyYl(ids)) {
		   log.info("domain getCourseWares4Delete ids null");
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
	   }
	   
	   try {
   			List<DomainCourseWare> domainCourseWares = domainService.getCourseWares4Delete(ids);
   			if (domainCourseWares.size()>0) {
   				data.put("flag", true);
   			} else {
   				data.put("flag", false);
			}
   			data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
   			log.info("domain getCourseWares4Delete success ids:"+ids);
	   } catch (Exception e) {
		   log.error("domain getCourseWares4Delete error ids:"+ids);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
   		
	   return data;
   }
   
   /**
    * 
      * 编辑域
      * <b>function:</b> getDomainById()
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-2-9 下午04:56:03
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/editDomain", method = RequestMethod.GET)
   public JSONObject editDomain(Integer id){
	   
	   JSONObject data = new JSONObject();
	   
	   if (id==null) {
		   log.info("domain editDomain id null");
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
	   }
	   
	   Domain domain = new Domain();
	   try {
		   domain = domainService.getById(Domain.class, id);
		   log.info("domain editDomain success id:"+id);
	   } catch (Exception e) {
		   log.error("domain editDomain error id:"+id);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   
	   data.put(Constants.XCJ_RETURN_API_DATA, domain);
	   return data;
   }
   
   /**
    * 
      * 修改域状态
      * <b>function:</b> updateStatus()
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-2-9 下午05:40:04
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
   public JSONObject updateStatus(Integer id, Integer status){
	   
	   JSONObject data = new JSONObject();
	   
	   if (null==id || null==status) {
		   log.info("domain updateStatus parameter null");
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
	   }
	   
	   try {
		   Domain domain = domainService.getById(Domain.class, id);
		   domain.setEnable(status);
		   domain.setModifyDate(DateUtil.getCurrentTimeByDate());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, domainService.update(domain) ? APIConstants.ERRCODE_SUCCESS : APIConstants.ERRCODE_FU1);
		   log.info("domain updateStatus parameter success id:"+id+" , enabled:"+status);
	   } catch (Exception e) {
		   log.info("domain updateStatus parameter error id:"+id+" , enabled:"+status);
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 获取课程库信息
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-2 下午05:17:25
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getCourseWares", method = RequestMethod.GET)
   public JSONObject getCourseWares(Model model, Page<CourseWare> ps,Integer category, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   // 1 分页查询未选课程  2 分页查询选择课程	
	   try {
		   
		   List<CourseWare> allSelectedCourseWares = new ArrayList<CourseWare>();
		   Page<CourseWare> selectedPage = new Page<CourseWare>();
		   Page<CourseWare> allPage = new Page<CourseWare>();
		   List<CourseWare> returnCourseWares = new ArrayList<CourseWare>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   allPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   
		   selectedPage.setUri("getCourseWaresSelectedPage");
		   
		   allPage.setUri("getCourseWaresPage");
		   if (null==category || category.intValue() == 1) {
			   selectedPage.setFormName("selectedCoursePage");
			   allPage.setFormName("allCoursePage");
			   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage,1, inname, domain.getId());
			   allSelectedCourseWares = courseWareService.getAllSelectedCourses(1,domain.getId());
			   allPage = courseWareService.getByPages(allPage, inname);
		   }
		   if (null!=category && category.intValue() == 2) {
			   selectedPage.setFormName("selectedTestsPage");
			   allPage.setFormName("allTestsPage");
			   allSelectedCourseWares = courseWareService.getAllSelectedCourses(2,domain.getId());
			   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage,2, inname, domain.getId());
			   allPage = courseWareService.getByPage(allPage, inname);
		   }
		   
		   if (null!=allPage && null!=allPage.getResult() && allPage.getResult().size()>0) {
			   for(CourseWare courseWare : allPage.getResult()) {
				   for(CourseWare courseWare1 : allSelectedCourseWares) {
					   if(courseWare.getId().equals(courseWare1.getId())) {
						   courseWare.setFlag(true);
						   break;
					   }
				   }
				   returnCourseWares.add(courseWare);
			   }
		   }
		   allPage.setResult(returnCourseWares);
		   data.put("selectedPage", selectedPage); 
		   data.put("allPage", allPage); 
		   data.put("allPageStr", courseWareService.pageAsString(allPage, request)); 
		   data.put("selectedPageStr", courseWareService.pageAsStringTwo(selectedPage, request)); 
		   data.put("inname", inname); 
		   log.info("domain getContents success selectedPage size:"+selectedPage.getResult().size()+", allPage size:"+allPage.getResult().size());
	   } catch (Exception e) {
		   e.printStackTrace();
		   log.error("domain getContents exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 获取测评信息
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-9 下午01:46:25
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getPapers", method = RequestMethod.GET)
   public JSONObject getPapers(Model model, Page<Paper> ps, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   // 1 分页查询未选课程  2 分页查询选择课程	
	   try {
		   
		   List<Paper> allSelectedCoursePapers = new ArrayList<Paper>();
		   Page<Paper> selectedPage = new Page<Paper>();
		   Page<Paper> allPage = new Page<Paper>();
		   List<Paper> returnPapers = new ArrayList<Paper>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   allPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   selectedPage.setFormName("selectedPapersPage");
		   selectedPage.setUri("getCoursePapersSelectedPage");
		   allPage.setFormName("allPapersPage");
		   allPage.setUri("getCoursePapersPage");
		   allSelectedCoursePapers = paperService.getAllSelectedPapers(domain.getId());
		   selectedPage = paperService.getSelectedPapersPage(selectedPage, inname, domain.getId());
		   allPage = paperService.getByPage(allPage, inname);
		   
		   if (null!=allPage && null!=allPage.getResult() && allPage.getResult().size()>0) {
			   for(Paper paper : allPage.getResult()) {
				   for(Paper paper1 : allSelectedCoursePapers) {
					   if(paper.getId().equals(paper1.getId())) {
						   paper.setFlag(true);
						   break;
					   }
				   }
				   returnPapers.add(paper);
			   }
		   }
		   allPage.setResult(returnPapers);
		   data.put("selectedPapersPage", selectedPage); 
		   data.put("allPapersPage", allPage); 
		   data.put("allPapersPageStr", paperService.pageAsString(allPage, request)); 
		   data.put("selectedPapersPageStr", paperService.pageAsStringTwo(selectedPage, request)); 
		   data.put("inname", inname); 
		   log.info("domain getPapers success selectedPage size:"+selectedPage.getResult().size()+", allPage size:"+allPage.getResult().size());
	   } catch (Exception e) {
		   log.error("domain getPapers exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 获取所有课程库信息分页
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-2 下午05:17:25
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getCourseWaresPage")
   public JSONObject getCourseWaresPage(Model model, Page<CourseWare> ps, Integer category, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   try {
		   
		   List<CourseWare> allSelectedCourseWares = new ArrayList<CourseWare>();
		   Page<CourseWare> allPage = new Page<CourseWare>();
		   List<CourseWare> returnCourseWares = new ArrayList<CourseWare>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   allPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   
		   allPage.setUri("getCourseWaresPage");
		   
		   if (null==category || category.intValue() == 1) {
			   allSelectedCourseWares = courseWareService.getAllSelectedCourses(1,domain.getId());
			   allPage.setFormName("allCoursePage");
			   allPage = courseWareService.getByPages(allPage, inname);
		   }
		   if (null!=category && category.intValue() == 2) {
			   allSelectedCourseWares = courseWareService.getAllSelectedCourses(2,domain.getId());
			   allPage.setFormName("allTestsPage");
			   allPage = courseWareService.getByPage(allPage, inname);
		   }
		   
		   if (null!=allPage && null!=allPage.getResult() && allPage.getResult().size()>0) {
			   for(CourseWare courseWare : allPage.getResult()) {
				   for(CourseWare courseWare1 : allSelectedCourseWares) {
					   if(courseWare.getId().equals(courseWare1.getId())) {
						   courseWare.setFlag(true);
						   break;
					   }
				   }
				   returnCourseWares.add(courseWare);
			   }
		   }
		   allPage.setResult(returnCourseWares);
		   data.put("allPage", allPage); 
		   data.put("allPageStr", courseWareService.pageAsString(allPage, request)); 
		   data.put("inname", inname); 
	   } catch (Exception e) {
		   log.error("domain getContents exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 获取所有测评库信息分页
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-9 下午01:50:26
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getCoursePapersPage", method = RequestMethod.GET)
   public JSONObject getCoursePapersPage(Model model, Page<CourseWare> ps, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   try {
		   
		   List<Paper> allSelectedCourseWares = new ArrayList<Paper>();
		   Page<Paper> allPage = new Page<Paper>();
		   List<Paper> returnCourseWares = new ArrayList<Paper>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   allPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   allPage.setFormName("allPapersPage");
		   allPage.setUri("getCoursePapersPage");
		   
		   allSelectedCourseWares = paperService.getAllSelectedPapers(domain.getId());
		   allPage = paperService.getByPage(allPage, inname);
		   
		   if (null!=allPage && null!=allPage.getResult() && allPage.getResult().size()>0) {
			   for(Paper paper : allPage.getResult()) {
				   for(Paper paper1 : allSelectedCourseWares) {
					   if(paper.getId().equals(paper1.getId())) {
						   paper.setFlag(true);
						   break;
					   }
				   }
				   returnCourseWares.add(paper);
			   }
		   }
		   allPage.setResult(returnCourseWares);
		   data.put("allPapersPage", allPage); 
		   data.put("allPapersPageStr", paperService.pageAsString(allPage, request)); 
		   data.put("inname", inname); 
	   } catch (Exception e) {
		   log.error("domain getContents exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 获取所有已选课程库信息分页
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-2 下午05:17:25
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getCourseWaresSelectedPage", method = RequestMethod.GET)
   public JSONObject getCourseWaresSelectedPage(Model model, Page<CourseWare> ps, Integer category, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   try {
		   Page<CourseWare> selectedPage = new Page<CourseWare>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   selectedPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   
		   selectedPage.setUri("getCourseWaresSelectedPage");
		   
		   if (null==category || category.intValue() == 1) {
			   selectedPage.setFormName("selectedCoursePage");
			   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 1, inname, domain.getId());
		   }
		   if (null!=category && category.intValue() == 2) {
			   selectedPage.setFormName("selectedTestsPage");
			   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 2, inname, domain.getId());
		   }
		   
		   data.put("selectedPage", selectedPage); 
		   data.put("selectedPageStr", courseWareService.pageAsStringTwo(selectedPage, request)); 
		   data.put("inname", inname); 
	   } catch (Exception e) {
		   log.error("domain getContents exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   /**
    * 
      * <b>function:</b> 
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-9 下午01:47:22
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/getCoursePapersSelectedPage", method = RequestMethod.GET)
   public JSONObject getCoursePapersSelectedPage(Model model, Page<CourseWare> ps, Domain domain,String currentPage, String inname, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   try {
		   Page<Paper> selectedPage = new Page<Paper>();
		   if (!StringUtil.isEmptyYl(currentPage)) {
			   selectedPage.setCurrentPage(Integer.parseInt(currentPage));
		   }
		   selectedPage.setFormName("selectedPapersPage");
		   selectedPage.setUri("getCoursePapersSelectedPage");
		   
		   selectedPage = paperService.getSelectedPapersPage(selectedPage, inname, domain.getId());
		   
		   data.put("selectedPapersPage", selectedPage); 
		   data.put("selectedPapersPageStr", paperService.pageAsStringTwo(selectedPage, request)); 
		   data.put("inname", inname); 
	   } catch (Exception e) {
		   log.error("domain getContents exception :"+e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
   
   
   /**
    * 
      * <b>function:</b> 修改内容权限
      * @project base
      * @package com.xcj.admin.controller.domain  
      * @fileName com.xcj.*** 
      * @createDate 2015-3-4 上午10:53:17
      * @return JSONObject
      * @author hehujun
    */
   @ResponseBody
   @RequestMapping(value = "/updateContentPrivilege", method = RequestMethod.POST)
   public JSONObject updateContentPrivilege(Integer domainId, String courseWareIds, String testsIds, String paperIds,Integer category, HttpServletRequest request) {
	   
	   JSONObject data = new JSONObject();
	   
	   if (null==domainId) {
		   log.info("domaincourseware updateContentPrivilege parameter null");
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
		   return data;
	   }
	   String totalIds = "";
	   if (StringUtil.isEmptyYl(courseWareIds) && !StringUtil.isEmptyYl(testsIds)) {
		   totalIds = testsIds;
	   }
	   if (!StringUtil.isEmptyYl(courseWareIds) && StringUtil.isEmptyYl(testsIds)) {
		   totalIds = courseWareIds; 
	   }
	   if (!StringUtil.isEmptyYl(courseWareIds) && !StringUtil.isEmptyYl(testsIds)) {
		   totalIds = courseWareIds + "," + testsIds;
	   }
	   try {
		   if (courseWareService.deleteDomainCourseWare(domainId)) {
			   if (!StringUtil.isEmptyYl(totalIds)) {
				   if (!courseWareService.updateDomainCourseWare(domainId, totalIds)){
					   log.error("domaincourseware updateContentPrivilege courseWareService.updateDomainCourseWare("+domainId+","+totalIds+") false");
					   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_DOMAINCOURSEWARE_BATCHFAILE_ERROR);
					   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_DOMAINCOURSEWARE_BATCHFAILE_ERROR);
				   } else {
					   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
					   
					   // TODO 更新完 查询  影响效率 暂时保留
					   Page<CourseWare> selectedPage = new Page<CourseWare>();
					   selectedPage.setFormName("selectedPage");
					   selectedPage.setUri("getCourseWaresSelectedPage");
					   
					   if (null==category || category.intValue() == 1) {
						   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 1, "", domainId);
						   data.put("course", category);
						   data.put("category", category);
					   }
					   if (null!=category && category.intValue() == 2) {
						   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 2, "", domainId);
						   data.put("tests", category);
						   data.put("category", category);
					   }
					   data.put("selectedPage", selectedPage); 
					   data.put("selectedPageStr", courseWareService.pageAsStringTwo(selectedPage, request)); 
				   }
			   }
		   } else {
			   log.error("domaincourseware updateContentPrivilege courseWareService.deleteDomainCourseWare("+domainId+") false");
			   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_DOMAINCOURSEWARE_DELETEFAILE_ERROR);
			   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_DOMAINCOURSEWARE_DELETEFAILE_ERROR);
		   }
		   
		   if (paperService.deleteDomainCoursePaper(domainId)) {
			   if (!StringUtil.isEmptyYl(paperIds)) {
				   if (!paperService.updateDomainCoursePaper(domainId, paperIds)){
					   log.error("domaincourseware updateContentPrivilege paperService.updateDomainCoursePaper("+domainId+","+totalIds+") false");
					   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_DOMAINCOURSEWARE_BATCHFAILE_ERROR);
					   data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_DOMAINCOURSEWARE_BATCHFAILE_ERROR);
				   } else {
					   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
					   
					   // TODO 更新完 查询  影响效率 暂时保留
					   Page<Paper> selectedPage = new Page<Paper>();
					   selectedPage.setFormName("selectedPage");
					   selectedPage.setUri("getCoursePapersSelectedPage");
					   
					   selectedPage = paperService.getSelectedPapersPage(selectedPage, "", domainId);
					   data.put("paper", 3);
					   data.put("selectedPapersPage", selectedPage); 
					   data.put("selectedPapersPageStr", paperService.pageAsStringTwo(selectedPage, request));
					   data.put("category", category);
				   }
			   }
		   }
		   
		   if(StringUtil.isEmptyYl(courseWareIds) && StringUtil.isEmptyYl(testsIds)){
			   
			   if (null==category || category.intValue() == 1) {
				   // TODO 更新完 查询  影响效率 暂时保留
				   Page<CourseWare> selectedPage = new Page<CourseWare>();
				   selectedPage.setFormName("selectedPage");
				   selectedPage.setUri("getCourseWaresSelectedPage");
				   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 1, "", domainId);
				   data.put("course", category);
				   data.put("selectedPage", selectedPage); 
				   data.put("selectedPageStr", courseWareService.pageAsStringTwo(selectedPage, request));
				   data.put("category", category);
			   } else if (null!=category && category.intValue() == 2) {
				   // TODO 更新完 查询  影响效率 暂时保留
				   Page<CourseWare> selectedPage = new Page<CourseWare>();
				   selectedPage.setFormName("selectedPage");
				   selectedPage.setUri("getCourseWaresSelectedPage");
				   selectedPage = courseWareService.getSelectedCoursesPage(selectedPage, 2, "", domainId);
				   data.put("tests", category);
				   data.put("selectedPage", selectedPage); 
				   data.put("selectedPageStr", courseWareService.pageAsStringTwo(selectedPage, request));
				   data.put("category", category);
			   } else {
				   // TODO 更新完 查询  影响效率 暂时保留
				   Page<Paper> selectedPage = new Page<Paper>();
				   selectedPage.setFormName("selectedPage");
				   selectedPage.setUri("getCoursePapersSelectedPage");
				   
				   selectedPage = paperService.getSelectedPapersPage(selectedPage, "", domainId);
				   data.put("paper", 3);
				   data.put("selectedPapersPage", selectedPage); 
				   data.put("selectedPapersPageStr", paperService.pageAsStringTwo(selectedPage, request));
				   data.put("category", category);
			   }
		   }
		   
		   //TODO 同步demo2q
		   Domain domain = domainService.getById(Domain.class,domainId);
		   if (domain !=null && "demo2q".equals(domain.getUsername()) && !StringUtil.isEmptyYl(totalIds)) {
			   if(StringUtil.isEmptyYl(totalIds)){
				   totalIds="-1";
			   }
			   List<CourseWare> courseWareList = courseWareService.getCourseWareListByids(totalIds);
			   List<SbtFault> sbtFault = courseWareService.getSbtFaultByCourseWareIds(totalIds);
			   courseWareService.syncCourseWare(JSON.toJSONString(courseWareList));
			   courseWareService.syncSbtFault(JSON.toJSONString(sbtFault));
		   }
		   
		   if (domain !=null && "demo2q".equals(domain.getUsername())&& !StringUtil.isEmptyYl(paperIds)) {
			   if(StringUtil.isEmptyYl(paperIds)){
				   paperIds="-1";
			   }
			   //试卷没必要同步 同步测评了testposted----> TestsController saveTestsPosted()
			  
		   }
		   
		   if (domain !=null && "demo2q".equals(domain.getUsername()) && !StringUtil.isEmptyYl(paperIds)) {
			   List<KnowledgePoint> knowledgePointList = knowledgePointService.getList(KnowledgePoint.class);
			   JSONArray jsonArray = new JSONArray();
			   for (KnowledgePoint knowledgePoint : knowledgePointList) {
				   JSONObject obj = new JSONObject();
				   obj.put("id", knowledgePoint.getId());
				   obj.put("code", knowledgePoint.getCode());
				   obj.put("grade", knowledgePoint.getGrade());
				   obj.put("name", knowledgePoint.getName());
				   obj.put("rank", knowledgePoint.getRank());
				   obj.put("url", knowledgePoint.getUrl());
				   obj.put("pid", knowledgePoint.getKnowledgePoint()!=null?knowledgePoint.getKnowledgePoint().getId():"");
				   obj.put("iconid", knowledgePoint.getIconid());
				   obj.put("createDate", knowledgePoint.getCreateDate());
				   obj.put("modifyDate", knowledgePoint.getModifyDate());
				   jsonArray.add(obj);
			   }
			   courseWareService.syncKnowledgePoint(jsonArray.toJSONString());
		   }
		   
	   } catch (Exception e) {
		   log.error("domaincourseware updateContentPrivilege exception" + e.getMessage());
		   data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_FU1);
		   data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	   }
	   return data;
   }
}
