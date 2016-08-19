package com.xcj.web.controller.tests;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.controller.domain.DomainController;
import com.xcj.admin.entity.tests.Paper;
import com.xcj.admin.entity.tests.TestsPosted;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.admin.service.tests.PaperService;
import com.xcj.admin.service.tests.TestsPostedService;
import com.xcj.admin.service.tests.TestsService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.common.util.md5.MD5Uitl;
@Controller("paperListController")
@RequestMapping("/api/tests")
public class PaperListController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(DomainController.class);
	
	@Resource(name = "domainServiceImpl")
	private DomainService domainService;
	 
	@Resource(name = "courseWareServiceImpl")
	private CourseWareService courseWareService;
	
	@Resource(name = "testsPostedServiceImpl")
	private TestsPostedService testsPostedService;
	
	@Resource(name = "paperServiceImpl")
	private PaperService paperService;
	
	@Resource(name = "testsServiceImpl")
	private TestsService testsService;
	
	/**
	 * <b>function:</b> demo展示试卷列表
	 * @project base
	 * @package com.xcj.web.controller.tests  
	 * @fileName com.xcj.*** 
	 * @createDate 2015年4月13日 下午1:50:34
	 * @return String
	 * @author hehujun
	 */

	@RequestMapping(value = "/getDemoPaperList", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
	public String getDemoPaperList(Integer testsPostedId,String userEmail,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String domainUsername = "admin";
		String domainPassword = "admin";
		try {
			if(ApiUtil.isParamNull(domainUsername,domainPassword,userEmail)){
				if(domainPassword == null){
					domainPassword = "";
					if(domainUsername == null || userEmail == null){
						domainUsername = "";
						userEmail = "";	
					}
				}else{
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
			}
			//查询数据库是否能找到用户名和密码一样的信息
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			boolean flag=domainService.getByUsernameAndPassword(domainUsername,MD5Uitl.MD5Encode(domainPassword));
			String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainUsername+"_"+userEmail;
			String token="";

			//从缓存中获取信息，如果缓存没有则重新获取第一次则从缓存中获取
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey)!=null){
				token=(String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey);
			}else{
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("domainAccount", domainUsername);
				map.put("domainPassword",domainPassword);
				map.put("userEmail", userEmail);
				String str=HttpUtil.doGet(Constants.TOKEN_TESTS_URL, map);
				JSONObject data = JSONObject.parseObject(str);
				if(data.get("errCode").equals("0")||data.get("errCode").equals("20005")){
					token=(String) data.get("token");
					HashMap<String, String> mapMem =new HashMap<String, String>();
					mapMem.put(timeKey, token);
					MemcachedUtil.putMemcacheIntanceByTime(mapMem,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				}else{
					model.addAttribute("errmsg","用户名密码不正确2");
					return "/web/tests/errorTests_jsp";
				}
			}
			if(!flag){
				model.addAttribute("errmsg","用户名密码不正确1");
				return "/web/tests/errorTests_jsp";
			}
			/*	
			//判断是否是同一个用户访问
			String sessionKey=domainUsername+domainPassword+userEmail;
			HashMap<String, String> mapSessionId =new HashMap<String, String>();
			MemcachedUtil.putMemcacheIntanceByTime(mapSessionId,Constants.XCJ_MEMCACHED_TOKEN_TIMING);*/
			model.addAttribute("domainUsername", domainUsername);
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("token", token);
			List<Paper> paperList = courseWareService.getPaperListWithDominUsername(domainUsername);
			
			if(paperList != null && paperList.size() >0){
				TestsPosted minStartTimeTestsPosted = testsPostedService.getById(TestsPosted.class, testsPostedId);
				Paper TestsPostedPaper = paperService.getById(Paper.class, minStartTimeTestsPosted.getPaperId());	
				
				//生成当前testPublicId
				String testsPublicId = testsService.getTestsPublicId(domainUsername,userEmail);
				mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
				// 1、存放缓存每次从缓存获取 2、以标准json对象返回 注意交卷是销毁
				HashMap<String, String> testPublicIdMap =new HashMap<String, String>();
				testPublicIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail, testsPublicId);
				MemcachedUtil.putMemcacheIntanceByTime(testPublicIdMap,TestsPostedPaper.getTotalTime()*60+180);
				
				//开始时间
				HashMap<String, String> startTimeMap =new HashMap<String, String>();
				startTimeMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME+"_"+userEmail, (new Date().getTime())+"");
				MemcachedUtil.putMemcacheIntanceByTime(startTimeMap,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				
				//剩余多少时间开始考试
				model.addAttribute("surplusTime", (minStartTimeTestsPosted.getAnswerTime().getTime()-new Date().getTime())/1000);
				//离考试结束时间
				//model.addAttribute("surplusEndTime",minStartTimeTestsPosted.getEndTime().getTime()-new Date().getTime());
				//离考试试卷时长
				model.addAttribute("surplusEndTime",TestsPostedPaper.getTotalTime()*60*1000);
				model.addAttribute("testsPosted",minStartTimeTestsPosted);
				for (Paper paper : paperList){
					if(paper.getId() == minStartTimeTestsPosted.getPaperId()){
						paper.setCourseWareList(courseWareService.getCourseWareListWithPaper(paper));
						model.addAttribute("paper",paper);
						break;
					}
				}
				//判断考试次数
				Integer count=1;
				HashMap<String, String> countmap =new HashMap<String, String>();
				if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail) == null){
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}else{
					count=Integer.parseInt((String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail))+1;
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}
				if(count > minStartTimeTestsPosted.getLimitCount()){
					model.addAttribute("errmsg","已经登陆测评"+minStartTimeTestsPosted.getLimitCount()+"次");
					return "/web/tests/errorTests_jsp";
				}
			}
		} catch (Exception e) {
			log.error("没初始化出错"+e.getMessage());
			model.addAttribute("errmsg","程序异常"+e.getMessage());
			return "/web/tests/errorTests_jsp";
		}
		return "/web/tests/paperlist_jsp";
	}
	
	
	
	
	
	/**
	 * 
	   * <b>function:</b> 展示试卷列表
	   * @project base
	   * @package com.xcj.web.controller.tests  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年4月13日 下午1:50:34
	   * @return String
	   * @author hehujun
	 */
	@Deprecated
	@RequestMapping(value = "/start", produces = "application/json;charset=UTF-8")
	public String getPaperList(String domainUsername,String domainPassword,String userEmail,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		if(true){
			return "/web/study/errorStudy_jsp";
		}
		domainUsername = "admin";
		domainPassword = "admin";
		try {
			if(ApiUtil.isParamNull(domainUsername,domainPassword,userEmail)){
				if(domainPassword == null){
					domainPassword = "";
					if(domainUsername == null || userEmail == null){
						domainUsername = "";
						userEmail = "";	
					}
				}else{
					model.addAttribute("errmsg","参数为空");
	 				return "/web/study/errorStudy_jsp";
				}
			}
			//查询数据库是否能找到用户名和密码一样的信息
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			boolean flag=domainService.getByUsernameAndPassword(domainUsername,MD5Uitl.MD5Encode(domainPassword));
			String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainUsername+"_"+userEmail;
			String token="";
			//从缓存中获取信息，如果缓存没有则重新获取第一次则从缓存中获取
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey)!=null){
				token=(String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey);
			}else{
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("domainAccount", domainUsername);
				map.put("domainPassword",domainPassword);
				map.put("userEmail", userEmail);
				String str=HttpUtil.doGet(Constants.TOKEN_TESTS_URL, map);
				JSONObject data = JSONObject.parseObject(str);
				if(data.get("errCode").equals("0")||data.get("errCode").equals("20005")){
					token=(String) data.get("token");
					HashMap<String, String> mapMem =new HashMap<String, String>();
					mapMem.put(timeKey, token);
					MemcachedUtil.putMemcacheIntanceByTime(mapMem,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				}else{
					model.addAttribute("errmsg","用户名密码不正确");
					return "/web/tests/errorTests_jsp";
				}
			}
			if(!flag){
				model.addAttribute("errmsg","用户名密码不正确");
				return "/web/tests/errorTests_jsp";
			}
			//从缓存中获取信息，如果缓存没有则重新获取第一次则从缓存中获取
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey)!=null){
				token=(String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey);
			}else{
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("domainAccount", domainUsername);
				map.put("domainPassword",domainPassword);
				map.put("userEmail", userEmail);
				String str=HttpUtil.doGet(Constants.TOKEN_TESTS_URL, map);
				JSONObject data = JSONObject.parseObject(str);
				if(data.get("errCode").equals("0")||data.get("errCode").equals("20005")){
					token=(String) data.get("token");
					HashMap<String, String> mapMem =new HashMap<String, String>();
					mapMem.put(timeKey, token);
					MemcachedUtil.putMemcacheIntanceByTime(mapMem,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				}else{
					model.addAttribute("errmsg","用户名密码不正确2");
					return "/web/tests/errorTests_jsp";
				}
			}
			if(!flag){
				model.addAttribute("errmsg","用户名密码不正确1");
				return "/web/tests/errorTests_jsp";
			}
		/*	
			//判断是否是同一个用户访问
			String sessionKey=domainUsername+domainPassword+userEmail;
			HashMap<String, String> mapSessionId =new HashMap<String, String>();
			MemcachedUtil.putMemcacheIntanceByTime(mapSessionId,Constants.XCJ_MEMCACHED_TOKEN_TIMING);*/
			model.addAttribute("domainUsername", domainUsername);
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("token", token);
			List<Paper> paperList = courseWareService.getPaperListWithDominUsername(domainUsername);
			String paperIds = "";
			for (Paper paper : paperList) {
				paper.setCourseWareList(courseWareService.getCourseWareListWithPaper(paper));
				paperIds += paper.getId()+",";
			}
			//注意：展示的试卷是还没有考试的试卷并且离‘开始时间’最近的试卷
			if(!("".equals(paperIds))){
				List<TestsPosted> testsPostedList = 
					testsPostedService.getTestsPostedByPaperStartTime(paperIds.substring(0,paperIds.length()-1));
				//时间差
				if(testsPostedList != null && testsPostedList.size() > 0){
					TestsPosted minStartTimeTestsPosted = testsPostedList.get(0);
					for (TestsPosted testsPosted : testsPostedList){
						if(testsPosted.getStartTime().getTime() - minStartTimeTestsPosted.getStartTime().getTime() < 0){
							minStartTimeTestsPosted = testsPosted;
						}
					}
					//剩余多少时间开始考试
					model.addAttribute("surplusTime", minStartTimeTestsPosted.getAnswerTime().getTime()-new Date().getTime());
					model.addAttribute("surplusEndTime",minStartTimeTestsPosted.getEndTime().getTime()-new Date().getTime());
					model.addAttribute("testsPosted",minStartTimeTestsPosted);
					for (int i=0;i<paperList.size();i++) {
						if(paperList.get(i).getId() != minStartTimeTestsPosted.getPaperId()){
							paperList.remove(i--);
						}
					}
					if(paperList != null && paperList.size() > 0){
						model.addAttribute("paper", paperList.get(0));
					}
					
					//判断考试次数
					Integer count=1;
					HashMap<String, String> countmap =new HashMap<String, String>();
					if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail) == null){
						countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
						MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
					}else{
						count=Integer.parseInt((String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail))+1;
						countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
						MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
					}
					if(count > minStartTimeTestsPosted.getLimitCount()){
						model.addAttribute("errmsg","已经登陆测评"+minStartTimeTestsPosted.getLimitCount()+"次");
						return "/web/tests/errorTests_jsp";
					}
				}
			}
		} catch (Exception e) {
			log.error("没初始化出错"+e.getMessage());
			model.addAttribute("errmsg","程序异常"+e.getMessage());
			return "/web/tests/errorTests_jsp";
		}
		return "/web/tests/paperlist_jsp";
	}
	
	

	/**
	 * <b>function:</b> 测试用展示试卷列表-内容中心后台用
	 * @project base
	 * @package com.xcj.web.controller.tests  
	 * @fileName com.xcj.*** 
	 * @createDate 2015年4月13日 下午1:50:34
	 * @return String
	 * @author hehujun
	 */
	@Deprecated
	@RequestMapping(value = "/getTestDemoPaperList", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
	public String getTestDemoPaperList(Integer testsPostedId,String userEmail,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		String domainUsername = "contentadmin";
		String domainPassword = "admin";
		try {
			if(ApiUtil.isParamNull(domainUsername,domainPassword,userEmail)){
				if(domainPassword == null){
					domainPassword = "";
					if(domainUsername == null || userEmail == null){
						domainUsername = "";
						userEmail = "";	
					}
				}else{
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
			}
			//查询数据库是否能找到用户名和密码一样的信息
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			boolean flag=domainService.getByUsernameAndPassword(domainUsername,MD5Uitl.MD5Encode(domainPassword));
			String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainUsername+"_"+userEmail;
			String token="";
			String sessionId = "";
			String testsPublicId ="";
			//从缓存中获取信息，如果缓存没有则重新获取第一次则从缓存中获取
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("domainAccount", domainUsername);
				map.put("domainPassword",domainPassword);
				map.put("userEmail", userEmail);
				String str=HttpUtil.doGet(Constants.TOKEN_TESTS_URL_TSP, map);
				JSONObject data = JSONObject.parseObject(str);
				if(data.get("errCode").equals("0")||data.get("errCode").equals("20005")){
					token=(String) data.get("token");
					sessionId=(String) data.get("sessionId");
					testsPublicId=(String) data.get("testsPublicId");
				}else{
					model.addAttribute("errmsg","用户名密码不正确2");
					return "/web/tests/errorTests_jsp";
				}
			
			if(!flag){
				model.addAttribute("errmsg","用户名密码不正确1");
				return "/web/tests/errorTests_jsp";
			}
			/*	
			//判断是否是同一个用户访问
			String sessionKey=domainUsername+domainPassword+userEmail;
			HashMap<String, String> mapSessionId =new HashMap<String, String>();
			MemcachedUtil.putMemcacheIntanceByTime(mapSessionId,Constants.XCJ_MEMCACHED_TOKEN_TIMING);*/
			model.addAttribute("domainUsername", domainUsername);
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("token", token);
			model.addAttribute("sessionId", sessionId);
			model.addAttribute("testsPublicId", testsPublicId);
			List<Paper> paperList = courseWareService.getPaperListWithDominUsername(domainUsername);
			
			if(paperList != null && paperList.size() >0){
				TestsPosted minStartTimeTestsPosted = testsPostedService.getById(TestsPosted.class, testsPostedId);
				Paper TestsPostedPaper = paperService.getById(Paper.class, minStartTimeTestsPosted.getPaperId());	
				
				//生成当前testPublicId
				//String testsPublicId = testsService.getTestsPublicId(domainUsername,userEmail);
				mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
				// 1、存放缓存每次从缓存获取 2、以标准json对象返回 注意交卷是销毁
				HashMap<String, String> testPublicIdMap =new HashMap<String, String>();
				testPublicIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail, testsPublicId);
				MemcachedUtil.putMemcacheIntanceByTime(testPublicIdMap,TestsPostedPaper.getTotalTime()*60+180);
				
				//开始时间
				HashMap<String, String> startTimeMap =new HashMap<String, String>();
				startTimeMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME+"_"+userEmail, (new Date().getTime())+"");
				MemcachedUtil.putMemcacheIntanceByTime(startTimeMap,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				
				//剩余多少时间开始考试
				model.addAttribute("surplusTime", (minStartTimeTestsPosted.getAnswerTime().getTime()-new Date().getTime())/1000);
				//离考试结束时间
				//model.addAttribute("surplusEndTime",minStartTimeTestsPosted.getEndTime().getTime()-new Date().getTime());
				//离考试试卷时长
				model.addAttribute("surplusEndTime",TestsPostedPaper.getTotalTime()*60*1000);
				model.addAttribute("testsPosted",minStartTimeTestsPosted);
				for (Paper paper : paperList){
					if(paper.getId() == minStartTimeTestsPosted.getPaperId()){
						paper.setCourseWareList(courseWareService.getCourseWareListWithPaper(paper));
						model.addAttribute("paper",paper);
						break;
					}
				}
				//判断考试次数
				Integer count=1;
				HashMap<String, String> countmap =new HashMap<String, String>();
				if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail) == null){
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}else{
					count=Integer.parseInt((String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail))+1;
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}
				if(count > minStartTimeTestsPosted.getLimitCount()){
					model.addAttribute("errmsg","已经登陆测评"+minStartTimeTestsPosted.getLimitCount()+"次");
					return "/web/tests/errorTests_jsp";
				}
			}
		} catch (Exception e) {
			log.error("没初始化出错"+e.getMessage());
			model.addAttribute("errmsg","程序异常"+e.getMessage());
			return "/web/tests/errorTests_jsp";
		}
		return "/web/tests/testpaperlist_jsp";
	}
	
	

	/**
	 * <b>function:</b> 正式使用测评试卷列表 -培训社区使用
	 * @createDate 2015年4月13日 下午1:50:34
	 * @return String
	 * @author su_jian
	 */
	@RequestMapping(value = "/getTestPaperList", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
	public String getTestPaperList(Integer testsPostedId,String userEmail,Model model,
			HttpServletRequest request, HttpServletResponse response,String token,String sessionId,
			String testsPublicId,String domainUsername) {
		try {
			if(ApiUtil.isParamNull(testsPostedId,token,sessionId,testsPublicId,userEmail)){
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
			}
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			
			String tokenKey = Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainUsername+"_"+userEmail;
			String sessionIdKey = Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail;
			String testPublicIdKey = Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail;
			if(mc1.get(tokenKey)==null){
				model.addAttribute("errmsg","参数为空");
				return "/web/study/errorStudy_jsp";
			}else{
				if(!token.equals(String.valueOf(mc1.get(tokenKey)))){
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
			}
			
			if(mc1.get(sessionIdKey)==null){
				model.addAttribute("errmsg","参数为空");
				return "/web/study/errorStudy_jsp";
			}else{
				if(!sessionId.equals(String.valueOf(mc1.get(sessionIdKey)))){
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
			}
			
			if(mc1.get(testPublicIdKey)==null){
				model.addAttribute("errmsg","参数为空");
				return "/web/study/errorStudy_jsp";
			}else{
				if(!testsPublicId.equals(String.valueOf(mc1.get(testPublicIdKey)))){
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
			}
			
			/*	
			//判断是否是同一个用户访问
			String sessionKey=domainUsername+domainPassword+userEmail;
			HashMap<String, String> mapSessionId =new HashMap<String, String>();
			MemcachedUtil.putMemcacheIntanceByTime(mapSessionId,Constants.XCJ_MEMCACHED_TOKEN_TIMING);*/
				
			model.addAttribute("domainUsername", domainUsername);
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("token", token);
			model.addAttribute("sessionId", sessionId);
			model.addAttribute("testsPublicId", testsPublicId);
			List<Paper> paperList = courseWareService.getPaperListWithDominUsername(domainUsername);
			
			if(paperList != null && paperList.size() >0){
				TestsPosted minStartTimeTestsPosted = testsPostedService.getById(TestsPosted.class, testsPostedId);
				Paper TestsPostedPaper = paperService.getById(Paper.class, minStartTimeTestsPosted.getPaperId());	
				if(TestsPostedPaper==null){
					model.addAttribute("errmsg","参数为空");
					return "/web/study/errorStudy_jsp";
				}
				//生成当前testPublicId
				//String testsPublicId = testsService.getTestsPublicId(domainUsername,userEmail);
				mc1.delete(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC +"_"+userEmail);
				// 1、存放缓存每次从缓存获取 2、以标准json对象返回 注意交卷是销毁
				HashMap<String, String> testPublicIdMap =new HashMap<String, String>();
				testPublicIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail, testsPublicId);
				MemcachedUtil.putMemcacheIntanceByTime(testPublicIdMap,TestsPostedPaper.getTotalTime()*60+180);
				
				//开始时间
				HashMap<String, String> startTimeMap =new HashMap<String, String>();
				startTimeMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_STARTTIME+"_"+userEmail, (new Date().getTime())+"");
				MemcachedUtil.putMemcacheIntanceByTime(startTimeMap,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				
				//剩余多少时间开始考试
				model.addAttribute("surplusTime", (minStartTimeTestsPosted.getAnswerTime().getTime()-new Date().getTime())/1000);
				//离考试结束时间
				//model.addAttribute("surplusEndTime",minStartTimeTestsPosted.getEndTime().getTime()-new Date().getTime());
				//离考试试卷时长
				model.addAttribute("surplusEndTime",TestsPostedPaper.getTotalTime()*60*1000);
				model.addAttribute("testsPosted",minStartTimeTestsPosted);
				for (Paper paper : paperList){
					if(paper.getId() == minStartTimeTestsPosted.getPaperId()){
						paper.setCourseWareList(courseWareService.getCourseWareListWithPaper(paper));
						model.addAttribute("paper",paper);
						break;
					}
				}
				//判断考试次数
				Integer count=1;
				HashMap<String, String> countmap =new HashMap<String, String>();
				if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail) == null){
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}else{
					count=Integer.parseInt((String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail))+1;
					countmap.put(Constants.XCJ_MEMCACHED_CONSTANTS+"_"+minStartTimeTestsPosted.getId()+"_"+userEmail, count.toString());
					MemcachedUtil.putMemcacheIntanceByTime(countmap,Constants.XCJ_MEMCACHED_COURSE_COUNT_TIMING);
				}
				if(count > minStartTimeTestsPosted.getLimitCount()){
					model.addAttribute("errmsg","已经登陆测评"+minStartTimeTestsPosted.getLimitCount()+"次");
					return "/web/tests/errorTests_jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("没初始化出错"+e.getMessage());
			model.addAttribute("errmsg","程序异常"+e.getMessage());
			return "/web/tests/errorTests_jsp";
		}
		return "/web/tests/testpaperlist_jsp";
	}
	
	
	
	
	
}