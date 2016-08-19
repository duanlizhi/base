package com.xcj.api.oper.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.entity.course.CourseWareAction;
import com.xcj.admin.entity.course.CourseWareKp;
import com.xcj.admin.entity.course.CourseWareParts;
import com.xcj.admin.entity.course.CourseWareTool;
import com.xcj.admin.entity.course.SbtFault;
import com.xcj.admin.entity.domain.Domain;
import com.xcj.admin.service.course.CourseWareActionService;
import com.xcj.admin.service.course.CourseWareKpService;
import com.xcj.admin.service.course.CourseWarePartsService;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.course.CourseWareToolService;
import com.xcj.admin.service.course.SbtTestScoreService;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.page.Page;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.common.ApiUtil;
/**
 * <b>function:</b> 同步课程信息 -从域中获取代理相关信息
 * @package com.xcj.admin.controller.courseDateBase.
 * @fileName com.xcj.admin.*
 * @createDate Mon Feb 02 16:47:16 CST 2015
 * @author name su_jian
 */
@Controller("syncCourseWareAPIController")
@RequestMapping("/api/oper/course")
public class SyncCourseWareAPIController extends BaseController {
	

	private static final Logger log = LoggerFactory.getLogger(SyncCourseWareAPIController.class);
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	 
    @Resource(name="domainServiceImpl")
    private DomainService domainService;

    
    @Resource(name ="courseWareActionServiceImpl")
	private CourseWareActionService courseWareActionService;

    @Resource(name ="courseWarePartsServiceImpl")
	private CourseWarePartsService courseWarePartsService;


    @Resource(name ="courseWareKpServiceImpl")
   	private CourseWareKpService courseWareKpService;

    @Resource(name ="courseWareToolServiceImpl")
   	private CourseWareToolService courseWareToolService;
    
    @Resource(name ="sbtTestScoreServiceImpl")
    private SbtTestScoreService sbtTestScoreService;
	
	/**
	 * <b>function:</b> 分页获取所有课程的方法
	 * @param category 课程类型 1、课  2、题 
	 * @param type 课件分类  SBT WBT EMU DMC 
	 * @param currentPage 当前页
	 * @param pageSize 每页大小
	 * @param token token
	 * @param domainAccount 当前页
	 * @param userEmail 用户名
	 * @package com.xcj.admin.controller.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:16 CST 2015
	 * @return {@link JSON}
	 * @author su_jian
	 **/
	@ResponseBody
	@RequestMapping(value = "/getAllCourse", produces = "application/json;charset=UTF-8")
	public JSONObject getAllCourse(Integer currentPage,Integer pageSize,
			String token,String domainAccount,String userEmail,Integer category,String type,
			HttpServletRequest request, HttpServletResponse response,String courseNumber) {
		pageSize = 10000;
		currentPage = 1;
		JSONObject data = new JSONObject();
		try {
			if (ApiUtil.isParamNull(currentPage, pageSize, token,domainAccount,userEmail,category)) {
				return ApiUtil.returnParamNull();
			}
			if(type == null){
				type="";
			}
			//TODO 需要调试讲此方法注释
			 if(!domainService.judgeTokenIsTime(token, domainAccount, userEmail)){
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
					return data;
			}
			Page<CourseWare> ps =new Page<CourseWare>();
			ps.setCurrentPage(currentPage);
			ps.setPageSize(pageSize);
			CourseWare courseWare = new CourseWare();
			courseWare.setCategory(category);
			courseWare.setType(type);
			Domain domain=domainService.getByUsername(domainAccount);
			courseWare.setId(domain.getId());
			courseWare.setCourseNumber(courseNumber);
			Page<CourseWare> p = courseWareService.getByCourseWarePage(ps,courseWare);
			if(p.getResult()==null||p.getResult().isEmpty()){
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_EMPTY_RECORD);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG_EMPTY_RECORD);
				data.put("totalCount", p.getTotalsCount());
				data.put("pageSize", pageSize);
				data.put("totalPage", p.getTotalsPage());
				return data;
			}
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG_SUCCESS);
			data.put("totalCount", p.getTotalsCount());
			data.put("pageSize", pageSize);
			data.put("totalPage", p.getTotalsPage());
			data.put("info", p.getResult());
			
			//TODO同步sbtTestScore
			if (!ApiUtil.isParamNull(courseNumber)) {
				sbtTestScoreService.syncSbtTestScore(courseNumber);
			}
		} catch (Exception e) {
			log.error("获取课程接口出错"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}
	/**
	 * <b>function:</b> 获取四个子表数据的方法
	 * @project base
	 * @package com.xcj.admin.controller.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:16 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
	 **/
	@ResponseBody
	@RequestMapping(value = "/getAllChild", produces = "application/json;charset=UTF-8")
	public JSONObject getAllChild(String token,String domainAccount,String userEmail,String cwids,Integer category,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		if (ApiUtil.isParamNull(token, domainAccount, userEmail,cwids)) {
			return ApiUtil.returnParamNull();
		}
		try {
			//TODO 需要调试讲此方法注释
			 if(!domainService.judgeTokenIsTime(token, domainAccount, userEmail)){
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
					return data;
			}
			List<CourseWareAction> listCwa =courseWareActionService.getByCourseWareIds(cwids);
			List<CourseWareTool> listCwt =courseWareToolService.getByCourseWareIds(cwids);
			List<CourseWareKp> listCwkp =courseWareKpService.getByCourseWareIds(cwids);
			List<CourseWareParts> listCwp =courseWarePartsService.getByCourseWareIds(cwids);
			List<SbtFault> listSbtFalt = courseWareService.getSbtFaultByCourseWareIds(cwids);
			data.put("action", listCwa);
			data.put("tool", listCwt);
			data.put("kp", listCwkp);
			data.put("parts", listCwp);
			data.put("falut", listSbtFalt);
			return data;
		} catch (Exception e) {
			log.error("获取课程下面其他子表出错"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
	}
	
	/**
	 * <b>function:</b> 获取单个课程的接口方法
	 * @project base
	 * @package com.xcj.admin.controller.courseDateBase.
	 * @fileName com.xcj.admin.*
	 * @createDate Mon Feb 02 16:47:16 CST 2015
	 * @return List<CourseWare>
	 * @author name su_jian
	 **/
	@ResponseBody
	@RequestMapping(value = "/getCourse", produces = "application/json;charset=UTF-8")
	public JSONObject getCourse(String courseNumber,String token,String domainAccount,String userEmail,
			Integer category,HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		try {
			if (ApiUtil.isParamNull(courseNumber, domainAccount, token,userEmail)) {
				return ApiUtil.returnParamNull();
			}
			//TODO 需要调试讲此方法注释
			//判断tokenid是否失效
			//TODO 需要调试讲此方法注释
			 if(!domainService.judgeTokenIsTime(token, domainAccount, userEmail)){
					data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
					data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_TOKEN_ISTIMING);
					return data;
			}
			CourseWare courseWare=courseWareService.getByCourseNumber(courseNumber);
			 if(courseWare==null){
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_TOKEN_ISTIMING);
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG_EMPTY_RECORD);
				return data;
			} 
			 data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
			 data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG_SUCCESS);

			 data.put("info",courseWare);
 		} catch (Exception e) {
			log.error("获取课程接口出错"+e.getMessage());
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}

}
