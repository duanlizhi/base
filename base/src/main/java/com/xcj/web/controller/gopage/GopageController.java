package com.xcj.web.controller.gopage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.study.StudyService;

/**
 * 
 * 开始学习返回后 跳转页面 <b>function:</b>
 * @project base
 * @package com.xcj.web.controller.study
 * @fileName com.xcj.*
 * @createDate 2015-2-12 上午09:32:50
 * @author hehujun
 * @email hehujun@126.com
 */
@Controller("gopageController")
@RequestMapping("/api/gopage/")
public class GopageController extends BaseController {

	private static Log logger = LogFactory.getLog(GopageController.class);
	@Resource(name = "studyServiceImpl")
	private StudyService studyService;	
	
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	/**
	   * <b>function:</b> 跳转到sbt播放器
	   * @project base
	   * @package com.xcj.web.controller.gopage  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-5 下午05:27:10
	   * @return String
	   * @author dapeng_wu
	 */
	@RequestMapping(value = "/gosbtnew" ,method = RequestMethod.POST)
	public String gosbtnew(String objData,String currentCourseId, ModelMap map,HttpServletRequest request,String faultNumber) {
		JSONObject jsonObj = JSONObject.parseObject(objData);
		jsonObj.put("currentTime",new Date().getTime());
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJsonNew(request,jsonDataObj.get("courseName").toString(),faultNumber);
			map.addAttribute("data", jsonObj);
			map.addAttribute("xmljson", xmljson);
//			map.addAttribute("coursePath",courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString()).getEntry());
			String id=String.valueOf(courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString()).getId());
			List<SbtFault> querySbtFault = courseWareService.querySbtFault(id);
			for (SbtFault sbtFault : querySbtFault) {
				if(faultNumber.equals(sbtFault.getFaultNumber().toString())){
					map.addAttribute("coursePath",sbtFault.getEntry());
				}
			}
		} catch (Exception e) {
			logger.error("跳转到sbt播放器出错：" + e.getMessage());
		}
		return "/web/gopage/sbt_jsp";
	}
	
	//旧的sbt
	@RequestMapping(value = "/gosbt" ,method = RequestMethod.POST)
	public String gosbt(String objData,String currentCourseId, ModelMap map,HttpServletRequest request,String faultNumber) {
		JSONObject jsonObj = JSONObject.parseObject(objData);
		jsonObj.put("currentTime",new Date().getTime());
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,jsonDataObj.get("courseName").toString());
			map.addAttribute("data", jsonObj);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("coursePath", courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString())
					.getEntry());
		} catch (Exception e) {
			logger.error("跳转到sbt播放器出错：" + e.getMessage());
		}
		return "/web/gopage/sbt_jsp";
	}
	
	/**
	 * 
	 * 
     * <b>function:</b>wbt教学初始化
     * @project 
     * @package 
     * @fileName 
     * @createDate 
     * @author yy.niu
	 */
	@RequestMapping(value = "/gowbt", method = RequestMethod.POST)
	public String gowbt(ModelMap map,String currentCourseId, String objData,HttpServletRequest request) {
		JSONObject jsonObj = JSONObject.parseObject(objData);
		jsonObj.put("currentTime",new Date().getTime());
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,jsonDataObj.get("courseName").toString());
			map.addAttribute("data", jsonObj);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("coursePath", courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString())
					.getEntry());
		} catch (Exception e) {
			logger.error("跳转到wbt播放器出错：" + e.getMessage());
		}
		return "/web/gopage/wbtPlayer_jsp";
	} 
	
	

	@RequestMapping(value = "/goemu")
	public String goemu(String objData,String currentCourseId, ModelMap map,HttpServletRequest request) {

		logger.info("gopage goemu begin");
		JSONObject jsonObj = JSONObject.parseObject(objData);
		jsonObj.put("currentTime",new Date().getTime());
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,jsonDataObj.get("courseName").toString());
			map.addAttribute("data", jsonObj);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("coursePath", courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString())
					.getEntry());
		} catch (Exception e) {
			logger.error("跳转到emu播放器出错：" + e.getMessage());
		}
		return "/web/gopage/emu_jsp";
	}

	@RequestMapping(value = "/godmc")
	public String godmc(String objData,String currentCourseId, ModelMap map,HttpServletRequest request) {

		logger.info("gopage godmc begin");
		JSONObject jsonObj = JSONObject.parseObject(objData);
		jsonObj.put("currentTime",new Date().getTime());
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,jsonDataObj.get("courseName").toString());
			map.addAttribute("data", jsonObj);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("coursePath", courseWareService.getByCourseNumber(jsonDataObj.get("courseName").toString())
					.getEntry());
		} catch (Exception e) {
			logger.error("跳转到dmc播放器出错：" + e.getMessage());
		}
		return "/web/gopage/dmc_jsp";
	}
	
	/**
	 * 
	   * <b>function:</b> 
	   * @project base
	   * @package com.xcj.web.controller.gopage  
	   * @fileName com.xcj.*** 
	   * @createDate 2015-3-10 下午03:23:19
	   * @return JSONObject
	   * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/godmcJson")
	public JSONObject godmcJson(String objData, ModelMap map,HttpServletRequest request) {

		logger.info("gopage godmcJson begin");
		// JSONObject data = JSONObject.parseObject(objData);
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data").toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,jsonDataObj.get("courseName").toString());
			jsonObj.put("xmljson", xmljson);
		} catch (Exception e) {
			logger.error("GopageController godmcJson() exception: "+e.getMessage());
		}
		logger.info("gopage godmcJson end");
		return jsonObj;
	}

}