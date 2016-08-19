package com.xcj.admin.controller.course;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.service.admin.AdminService;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.user.UserService;
import com.xcj.common.entity.Principal;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.FileUpload;
//import com.xcj.common.util.FormatJsonUtil;
import com.xcj.common.util.xmlUtil;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.common.util.common.StringUtil;
import com.xcj.common.util.md5.MD5Uitl;
 
 

/**
 * <b>function:</b> course_ware课件基本信息
 * 
 * @package com.xcj.admin.controller.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:16 CST 2015
 * @author name niu_yy
 */
@Controller("courseWareController")
@RequestMapping("/admin/course")
public class CourseWareController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CourseWareController.class);
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	
	@Autowired
	private AdminService adminService;

	@Resource(name = "userServiceImpl")
	private UserService userService;
	
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
	@RequestMapping(value = "/courseWareList")
	public String courseWareList(Model model, Page<CourseWare> ps, String search) {
		try {
			Page<CourseWare> p = courseWareService.getByPages(ps,search);
			model.addAttribute("page", p);
			if (search != null && !(search.equals(""))) {
				model.addAttribute("search", search);
			}
			//以下获取token session
			Subject currentUser = SecurityUtils.getSubject();
			Principal principal = (Principal) currentUser.getPrincipal();
			String email = adminService.getCurrentByPageParam(principal.getUsername()).getEmail();
			HashMap<String, String> map =new HashMap<String, String>();
			map.put("domainAccount", "contentadmin");
			map.put("domainPassword","admin");
			map.put("userEmail",email);
			JSONObject data = JSONObject.parseObject(HttpUtil.doPost(Constants.TOKEN_URL_TSP, map));
			data.put("userEmail",email);
			data.put("domainAccount", "contentadmin");
			model.addAttribute("tsp", data.toJSONString());
		} catch (Exception e) {
			log.error("课程库:获取分页对象出错：" + e.getMessage());
		}
		return "/admin/course/courseWareList_jsp";
	}
	
	
	@RequestMapping(value = "/getcourseTsp")
	@ResponseBody
	public JSONObject getcourseTsp() {
		JSONObject jsonObject = new JSONObject();
		try {
			//以下获取token session
			Subject currentUser = SecurityUtils.getSubject();
			Principal principal = (Principal) currentUser.getPrincipal();
			String email = adminService.getCurrentByPageParam(principal.getUsername()).getEmail();
			HashMap<String, String> map =new HashMap<String, String>();
			map.put("domainAccount", "contentadmin");
			map.put("domainPassword","admin");
			map.put("userEmail",email);
			JSONObject data = JSONObject.parseObject(HttpUtil.doPost(Constants.TOKEN_URL_TSP, map));
			data.put("userEmail",email);
			data.put("domainAccount", "contentadmin");
			jsonObject.put("tsp", data.toJSONString());
		} catch (Exception e) {
			log.error("课程库:getcourseTsp出错：" + e.getMessage());
		}
		return jsonObject;
	}

	
	@RequestMapping(value = "/getSbtFault")
	@ResponseBody
	public JSONObject getSbtFault(String courseId) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<SbtFault> querySbtFault = courseWareService.querySbtFault(courseId);
			jsonObject.put("sbtFault", querySbtFault);
		} catch (Exception e) {
			log.error("课程库:getSbtFault()出错：" + e.getMessage());
		}
		return jsonObject;
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
						String realPath = request.getSession().getServletContext().getRealPath("");
						saveDataIsSuccess = courseWareService.saveData(fileType,fielUrl,courseNumber,faultNumber,uploadPath,realPath);
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
			e.printStackTrace();
			msg=APIConstants.ERRCODE_DATA_ERROR+APIConstants.ERRMSG_DATA_ERROR; 
			log.error("课程库:上传文件格式错误：" + e.getMessage());
		}
		response.getWriter().write(msg); //40002COURSENUMBER已存在    40011节点知识点没有关联
		return null;
	}
	

	/**
	 * <b>function:</b> course_ware课件基本信息---删除单条数据的方法
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
			
			courseWareService.updateCourseWareByIds(ids);
			obj.put("data", "success");
			
			/**
			String[] id = ids.split(",");
			String[] courseNumbers = courseNumber.split(",");
			String[] types = type.split(",");
			for(int i=0;i<id.length;i++){
				//styResult  如果存在数据不删除
				boolean bo = courseWareService.queryStyResult(courseNumbers[i],id[i]);
				if(bo){
					//判断是哪个类型
					if(ids!=null	&&	!ids.equals("")){
						if(types[i]!=null	&&	!type.equals("")	&&	StringUtil.isTypeNull(types[i])){
							if(types[i].equals("sbt") || types[i].equals("emu")){
								//sbt 要删除两个文件，一个是 courseWare里的还有sbt故障里的
								courseWareService.deleteSbtCourseWare(id[i]);
								obj.put("data", "success");
							}else if(types[i].equals("wbt")	||	types[i].equals("dmc")){
								courseWareService.deletewbtCourseWareAndTestAndDismantle(id[i]);
								obj.put("data", "success");
							} 
						}else{
							obj.put("data", APIConstants.ERRCODE_TYPENULL_ERROR+APIConstants.ERRMSG_TYPENULL_ERROR);
						}
					}else{
						obj.put("data", APIConstants.ERRCODE_IDNULL_ERROR+APIConstants.ERRMSG_IDNULL_ERROR);
					}
				}else{
					obj.put("data", "不能删除"+id[i]);
				}
			}
			*/
		} catch (Exception e) {//808196727
			log.error("课程库:删除对象出错：" + e.getMessage());
			obj.put("data",APIConstants.ERRMSG_COURSEDELL_ERROR);
			return obj;
		}
		return obj;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getStudyCode", method = RequestMethod.POST)
	public JSONObject getStudyCode(String courseId,String courseNumber) {
		JSONObject data = new JSONObject();
		if (ApiUtil.isParamNull(courseId,courseNumber)) {
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.XCJ_RETURN_PARAMNULL);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
			return data;
		}
		try {
			JSONObject studyFlag = new JSONObject();
			studyFlag.put("courseId", courseId);
			String userName = "admin";
			String code = MD5Uitl.MD5Encode("contentadmin"+courseNumber+userName+courseId);
			data.put("code", code);
			data.put("email",userName);
			HashMap<String, String> mapStydyFlag =new HashMap<String, String>();
			mapStydyFlag.put(code,studyFlag.toJSONString());
			mapStydyFlag.put(code+userName,userName);
			MemcachedUtil.putMemcacheIntanceByTime(mapStydyFlag, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
		} catch (Exception e) {
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
			log.error("课程库:getcourseTsp出错：" + e.getMessage());
		}
		return data;  //{"code":"770a5c467cf90055a9af22e1b243bed9","email":"admin","errCode":"0","errMsg":"请求成功"}
	}

}
