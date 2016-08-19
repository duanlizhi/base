package com.xcj.admin.controller.questionLibaray;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.service.questionLibaray.QuestionLibarayService;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.FileUpload;
import com.xcj.common.util.xmlUtil;
import com.xcj.common.util.common.StringUtil;

 /**
  * <b>function:</b>  questionLibaray
  * @package com.xcj.admin.controller.questionLibaray.
  * @fileName com.xcj.admin.*
  * @createDate Wed Feb 11 11:48:15 CST 2015
  * @author name yy.niu
 */
@Controller("questionLibarayController")
@RequestMapping("/admin/questionLibaray")
public class QuestionLibarayController extends BaseController{

 private static final Logger log = LoggerFactory.getLogger(QuestionLibarayController.class); 
   @Resource(name="questionLibarayServiceImpl")
   private QuestionLibarayService questionLibarayService;

   /**
	 * 
	 * <b>function:</b> course_ware课件基本信息:分页获取数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:16 CST 2015
	 * @return List<CourseWare>
	 * @author name niu_yy
	 **/
	@RequestMapping(value = "/questionLibarayList")
	public String questionLibarayList(Model model, Page<CourseWare> ps, String search) {
		try {
			 
			Page<CourseWare> p = questionLibarayService.getByPages(ps,search);
			model.addAttribute("page", p);
			if (search != null && !(search.equals(""))) {
				model.addAttribute("search", search);
			}
		} catch (Exception e) {
			log.error("题库:获取分页对象出错：" + e.getMessage());
		}
		return "/admin/questionLibaray/questionLibarayList_jsp";
	}
	
	
	/**
	 * 
	 * <b>function:</b> 上传WBT
	 * @project xcjedu
	 * @package com.xcj.api.controller.course  
	 * @fileName @param request
	 * @fileName @param fileUpload
	 * @fileName @return
	 * @createDate Jun 4, 2014 3:27:44 PM
	 * @author yy.niu
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addCourse")
	public  String addCourse(HttpServletResponse response,HttpServletRequest request, @RequestParam("file")
			MultipartFile file) throws Exception {
			 
		String msg = ""; //返回的提示信息
		try { 
			String saveDataIsSuccess = null;//sbtFault是否存在
			//调用上传临时文件方法，返回json数据
			JSONObject fileSuccess = FileUpload.fileUploadTemporary(request,file);
			JSONObject jsonObj = fileSuccess.getJSONObject("xmlFile");
			String errMs = jsonObj.get("errMsg").toString();
			String statue = jsonObj.get("statue").toString();
			//如果statue==1 正确 返回 路径 如果是0错误
			if(statue.equals("1")){
				String fielUrl = jsonObj.get("fileUrl").toString();
				String uploadPath = jsonObj.get("uploadPath").toString();
				JSONObject fileObj = xmlUtil.fileType(fielUrl);
				JSONObject fileData = fileObj.getJSONObject("fileData");
				String fileType = fileData.getString("types");
				String courseNumber = fileData.getString("courseNumber");
				String faultNumber = fileData.getString("faultNumber");
				//判断fileType是否为空
				if(fileType!=null	&&	!fileType.equals("")	&&	StringUtil.isTypeMatching(fileType)){
					//判断courseNumber是否为空
					if(courseNumber!=null	||	faultNumber!=null){
						//判断courseNumber是否为空
						saveDataIsSuccess = questionLibarayService.saveData(fileType,fielUrl,courseNumber,faultNumber,uploadPath);
						//如果saveDataIsSuccess等于true成功
						if(saveDataIsSuccess.equals("0")){
							msg=APIConstants.ERRMSG_ADD_SUCCESS;
						}else{
							if(saveDataIsSuccess.equals("1")){
								//courseNumber或type不能为空
								msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRMSG_COURSENUMBER_ERROR;
							}else if(saveDataIsSuccess.equals("2")){
								//写入数据失败
								msg=APIConstants.ERRCODE_WRITETEXT_ERROR+APIConstants.ERRMSG_WRITETEXT_ERROR;
							}else if(saveDataIsSuccess.equals("3")){
								//知识点没有关联
								msg=APIConstants.ERRCODE_KP_ERROR+APIConstants.ERRMSG_KP_ERROR;
							}else if(saveDataIsSuccess.equals("4")){
								//节点知识点没有关联
								msg=APIConstants.ERRCODE_KPS_ERROR+APIConstants.ERRMSG_KPS_ERROR;
							}else if(saveDataIsSuccess.equals("5")){
								//请先上传sbt课件
								msg=APIConstants.ERRCODE_UPLOADSBT_ERROR+APIConstants.ERRMSG_UPLOADSBT_ERROR;
							}else if(saveDataIsSuccess.equals("6")){
								//courseNumber错误
								msg=APIConstants.ERRCODE_NUMBER_ERROR+APIConstants.ERRMSG_NUMBER_ERROR;
							}
							//msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRMSG_DATA_ERROR;
						}
					}else{
						msg=APIConstants.ERRCODE_NUMBER_ERROR+APIConstants.ERRMSG_NUMBER_ERROR;
					}
				}else{
					msg=APIConstants.ERRCODE_TYPE_ERROR+APIConstants.ERRMSG_TYPE_ERROR;
				}
			}else{
				msg=errMs;
			}
		} catch (Exception e) {//808196727
			msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRMSG_DATA_ERROR; 
			log.error("题库:上传文件格式错误：" + e.getMessage());
		}
		response.getWriter().write(msg); 
		return null;
	}
	
	
	


	/**
	 * 
	 * <b>function:</b> course_ware课件基本信息---删除单条数据的方法
	 * 
	 * @project base
	 * @package com.xcj.admin.controller.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:18 CST 2015
	 * @return String
	 * @author name niu_yy
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody JSONObject delete(String ids,String courseNumber,String type) {
		JSONObject obj = new JSONObject();
		 
		try {
			String[] id = ids.split(",");
			String[] courseNumbers = courseNumber.split(",");
			String[] types = type.split(",");
			for(int i=0;i<id.length;i++){
				 
				boolean bo = questionLibarayService.queryStyResult(courseNumbers[i],id[i]);
				//判断是哪个类型
				if(bo){
					if(ids!=null	&&	!ids.equals("")){
						if(types[i]!=null	&&	!type.equals("")	&&	StringUtil.isTypeNull(types[i])){
							if(types[i].equals("sbt")){
								questionLibarayService.deleteSbtCourseWare(id[i]);
								obj.put("data", "success");
							}else if(types[i].equals("wbt")	||	types[i].equals("dmc")){
								questionLibarayService.deletewbtCourseWareAndTestAndDismantle(id[i]);
								obj.put("data", "success");
							} 
						}else{
							obj.put("data", APIConstants.ERRCODE_TYPENULL_ERROR+APIConstants.ERRMSG_TYPENULL_ERROR);
						}
					}else{
						obj.put("data", APIConstants.ERRCODE_IDNULL_ERROR+APIConstants.ERRMSG_IDNULL_ERROR);
					}
				}else{
					obj.put("data", "不能删除");
				}
			}
			
		} catch (Exception e) {
			log.error("题库:删除对象出错：" + e.getMessage());
			obj.put("data",APIConstants.ERRCODE_COURSEDELL_ERROR+APIConstants.ERRCODE_QUESTIONDELL_ERROR+APIConstants.ERRMSG_QUESTIONDELL_ERROR);
			return obj;
		}
		return obj;
	}

}
