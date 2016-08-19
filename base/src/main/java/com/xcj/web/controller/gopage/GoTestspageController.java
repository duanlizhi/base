package com.xcj.web.controller.gopage;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.study.StudyService;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.admin.service.tests.PapersCourseService;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.util.Constants;

/**
 * 
 * 开始学习返回后 跳转页面 <b>function:</b>
 * 
 * @project base
 * @package com.xcj.web.controller.study
 * @fileName com.xcj.*
 * @createDate 2015-2-12 上午09:32:50
 * @author hehujun
 * @email hehujun@126.com
 */
@Controller("goTestsPageController")
@RequestMapping("/api/page/tests")
public class GoTestspageController extends BaseController {

	private static Log logger = LogFactory.getLog(GoTestspageController.class);
	@Resource(name = "studyServiceImpl")
	private StudyService studyService;

	@Resource(name = "papersCourseServiceImpl")
	private PapersCourseService papersCourseService;

	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	
	@Resource(name = "testsPostedServiceImpl")
	private TestsPostedService testsPostedService;
	
	@Resource(name = "paperServiceImpl")
	private PaperService paperService;

	/**
	 * <b>function:</b> 跳转到sbt播放器
	 * 
	 * @project base
	 * @package com.xcj.web.controller.gopage
	 * @fileName com.xcj.***
	 * @createDate 2015-3-5 下午05:27:10
	 * @return String
	 * @author dapeng_wu
	 */
	@RequestMapping(value = "/gosbt", method = RequestMethod.POST)
	public String gosbt(String objData, String token, String paperId,
			String currentCourseId, String testsPostedId, ModelMap map,
			HttpServletRequest request,String sbtFaultId) {
		logger.info("gopage gosbt begin");
		// 1、xml 转换为JSON的数据格式。 课件传回来的时候不需要这个数据了 根据课程信息去路径下找JSON文件
		// 2、用户课程的一些信息。 需要往回传
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data")
				.toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,
					jsonDataObj.get("courseNumber").toString());
			map.addAttribute("data", objData);
			map.addAttribute("token", token);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("score", papersCourseService.getPapersCourseScore(
					currentCourseId, paperId));
			map.addAttribute("coursePath", courseWareService.getById(
					CourseWare.class, Integer.parseInt(currentCourseId))
					.getEntry());
			TestsPosted testPosted = testsPostedService.getById(TestsPosted.class, Integer.parseInt(testsPostedId));
			map.addAttribute("testsPosted",JSONObject.toJSON(testPosted));
			map.addAttribute("surplusTime",testPosted.getEndTime().getTime()-new Date().getTime());
		} catch (Exception e) {
			logger.error("跳转到sbt测评播放器出错：" + e.getMessage());
		}
		logger.info("gopage gosbt end");
		return "/web/goTestsPage/sbt_jsp";
	}

	/**
	 * 
	 * 
	 * <b>function:</b>wbt教学初始化
	 * 
	 * @project
	 * @package
	 * @fileName
	 * @createDate
	 * @author yy.niu
	 */
	@RequestMapping(value = "/gotestwbt", method = RequestMethod.POST)
	public String gowbt(String objData, String token, String paperId,
			String currentCourseId, String testsPostedId, ModelMap map,
			String domainUsername,String userEmail,
			HttpServletRequest request) {
		logger.info("gopage gowbt begin");
		// 1、xml 转换为JSON的数据格式。 课件传回来的时候不需要这个数据了 根据课程信息去路径下找JSON文件
		// 2、用户课程的一些信息。 需要往回传
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data")
				.toString());
		try {
			//临时---
			map.addAttribute("currentCourseId",currentCourseId);
			map.addAttribute("paperId",paperId);
			map.addAttribute("testsPostedId",testsPostedId);
			map.addAttribute("domainUsername",domainUsername);
			map.addAttribute("userEmail",userEmail);
			map.addAttribute("courseWare",paperService.getCourseWareByPaperId(Integer.parseInt(paperId)));
			//临时---
			String xmljson = studyService.getCourseWareJson(request,
					jsonDataObj.get("courseNumber").toString());
			map.addAttribute("data", objData);
			map.addAttribute("token", token);
			map.addAttribute("xmljson", xmljson);
			map.addAttribute("score", papersCourseService.getPapersCourseScore(
					currentCourseId, paperId));
			map.addAttribute("coursePath", courseWareService.getById(
					CourseWare.class, Integer.parseInt(currentCourseId))
					.getEntry());
			TestsPosted testPosted = testsPostedService.getById(TestsPosted.class, Integer.parseInt(testsPostedId));
			map.addAttribute("testsPosted",JSONObject.toJSON(testPosted));
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			long startTime = Long.parseLong((String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME+"_"+userEmail));
			map.addAttribute("surplusTime",paperService.getById(Paper.class, Integer.parseInt(paperId)).getTotalTime()*60*1000-(new Date().getTime()-startTime));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到wbt测评播放器出错：" + e.getMessage());
		}
		return "/web/goTestsPage/wbtTestPlayer_jsp";
	}
	
	@RequestMapping(value = "/goemu")
	public String goemu(String objData, ModelMap map, HttpServletRequest request) {

		logger.info("gopage goemu begin");
		// JSONObject data = JSONObject.parseObject(objData);
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data")
				.toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,
					jsonDataObj.get("courseName").toString());
			map.addAttribute("data", objData);
			map.addAttribute("xmljson", xmljson);
		} catch (Exception e) {
			logger.error("GoTestspageController goemu() exception: "+e.getMessage());
		}
		logger.info("gopage goemu end");
		return "/web/gopage/emu_jsp";
	}

	@RequestMapping(value = "/godmc")
	public String godmc(String objData, ModelMap map, HttpServletRequest request) {

		logger.info("gopage godmc begin");
		// JSONObject data = JSONObject.parseObject(objData);
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data")
				.toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,
					jsonDataObj.get("courseName").toString());
			map.addAttribute("data", objData);
			map.addAttribute("xmljson", xmljson);
		} catch (Exception e) {
			logger.error("GoTestspageController godmc() exception: "+e.getMessage());
		}
		logger.info("gopage godmc end");
		return "/web/gopage/dmc_jsp";
	}

	/**
	 * 
	 * <b>function:</b>
	 * 
	 * @project base
	 * @package com.xcj.web.controller.gopage
	 * @fileName com.xcj.***
	 * @createDate 2015-3-10 下午03:23:19
	 * @return JSONObject
	 * @author hehujun
	 */
	@ResponseBody
	@RequestMapping(value = "/godmcJson")
	public JSONObject godmcJson(String objData, ModelMap map,
			HttpServletRequest request) {

		logger.info("gopage godmcJson begin");
		// JSONObject data = JSONObject.parseObject(objData);
		JSONObject jsonObj = JSONObject.parseObject(objData);
		JSONObject jsonDataObj = JSONObject.parseObject(jsonObj.get("data")
				.toString());
		try {
			String xmljson = studyService.getCourseWareJson(request,
					jsonDataObj.get("courseName").toString());
			jsonObj.put("xmljson", xmljson);
		} catch (Exception e) {
			logger.error("GoTestspageController godmcJson() exception: "+e.getMessage());
		}
		logger.info("gopage godmcJson end");
		return jsonObj;
	}

}