package com.xcj.web.controller.study;

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

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.admin.controller.domain.DomainController;
import com.xcj.admin.entity.course.CourseWare;
import com.xcj.admin.service.course.CourseWareService;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.common.util.md5.MD5Uitl;
@Controller("startStudyController")
@RequestMapping("/api/study")
public class StartStudyController extends BaseController {
	 private static final Logger log = LoggerFactory.getLogger(DomainController.class); 
	 @Resource(name = "domainServiceImpl")
	 private DomainService domainService;
	 
	 @Resource(name = "courseWareServiceImpl")
	 private CourseWareService courseWareService;
	 
	 
	/**
	 * <b>function:</b>开始初始化学习界面 
	 * domainPassword 为明文
	 * StartStudyController
	 * @createDate  2015-2-12 上午09:26:46
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type String
	 */
	@RequestMapping(value = "/start")
	public String hits(String domainUsername,String domainPassword,String userEmail,Model model,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if(true){
				return "/web/study/errorStudy_jsp";
			}
			if(ApiUtil.isParamNull(domainUsername,domainPassword,userEmail)){
 				return "/web/study/errorStudy_jsp";
			}
			//查询数据库是否能找到用户名和密码一样的信息
			boolean flag=domainService.getByUsernameAndPassword(domainUsername,MD5Uitl.MD5Encode(domainPassword));
			String timeKey=domainUsername+userEmail+domainPassword+APIConstants.XCJ_REQUEST_TIMES+DateUtil.today()+"_client";
			String token="";
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			//从缓存中获取信息，如果缓存没有则重新获取第一次则从缓存中获取
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey)!=null){
				token=(String) mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+timeKey);
			}else{
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("domainAccount", domainUsername);
				map.put("domainPassword",domainPassword);
				map.put("userEmail", userEmail);
				String str=HttpUtil.doGet(Constants.TOKEN_URL, map);
				JSONObject data = JSONObject.parseObject(str);
				if(data.get("errCode").equals("0")||data.get("errCode").equals("20005")){
					token=(String) data.get("token");
					HashMap<String, String> mapMem =new HashMap<String, String>();
					mapMem.put(timeKey, token);
					MemcachedUtil.putMemcacheIntanceByTime(mapMem,Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				}else{
					return "/web/study/errorStudy_jsp";
				}
			}
			if(!flag){
				return "/web/study/errorStudy_jsp";
			}
		/*	
			//判断是否是同一个用户访问
			String sessionKey=domainUsername+domainPassword+userEmail;
			HashMap<String, String> mapSessionId =new HashMap<String, String>();
			MemcachedUtil.putMemcacheIntanceByTime(mapSessionId,Constants.XCJ_MEMCACHED_TOKEN_TIMING);*/
			
			
			model.addAttribute("domainUsername", domainUsername);
			model.addAttribute("userEmail", userEmail);
			model.addAttribute("token", token);
			//下面参数1代表“课”
			List<CourseWare> courseWareList = courseWareService.getCourseWareListWithDominUsername(domainUsername,1);
			model.addAttribute("courseWareList", courseWareList);
		} catch (Exception e) {
			log.error("也没初始化出错"+e.getMessage());
			return "/web/study/errorStudy_jsp";
		}
		return  "/web/study/study_jsp";
	}
	

}