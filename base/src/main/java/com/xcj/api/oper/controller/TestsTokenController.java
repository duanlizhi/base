/**
 * 
 */
package com.xcj.api.oper.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.service.domain.DomainService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.md5.MD5Uitl;

/**
 * <b>function:</b> 获取考试TOken方法
 * 
 * @project base
 * @package com.xcj.web.controller.m
 * @fileName com.xcj.***
 * @createDate
 * @return String
 * @author su_jian
 **/
@Controller("testsTokenController")
@RequestMapping("/api/oper/tests/")
public class TestsTokenController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestsTokenController.class);
	
	@Resource(name = "domainServiceImpl")
	private DomainService domainService;

	/**
	  *获取Token和SessionID和testpublicID 方法
	  * @param domainAccount 用户名 
	  * @param domainPassword 密码
	  * @param userEmail 用户邮箱
	  * @param request
	  * @param response
	  * @return  返回字符窜
	  */
	@ResponseBody
	@RequestMapping(value = "/getTsp", produces = "application/json;charset=UTF-8")
	public JSONObject getTsp(String domainAccount, String domainPassword,String userEmail,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		try {
			if(ApiUtil.isParamNull(domainAccount,userEmail)){
				return ApiUtil.returnParamNull();
			}
			//查询数据库是否能找到用户名和密码一样的信息
			boolean flag=domainService.getByUsernameAndPassword(domainAccount,MD5Uitl.MD5Encode(domainPassword));
			if(!flag){
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_20004);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_20004);
				return data;
			}
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			
			//此处需要控制请求次数的限制
			//String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+"_"+domainPassword+"_"+domainAccount+"_"+userEmail;
			String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+"_"+domainAccount+"_"+userEmail+"_third";
			HashMap<String, String> mapTimes =new HashMap<String, String>();
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey)!=null){
				String times=String.valueOf(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey).toString());
				Integer xx=Integer.parseInt(times)+1;
				mapTimes.put(timeKey, String.valueOf(xx));
				MemcachedUtil.putMemcacheIntanceByTime(mapTimes,Constants.XCJ_MEMCACHED_TOKEN_TIMING_TEST_TIMES);
				if(Integer.parseInt(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey).toString())>=1000000000){
					 data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_UP_TIMES);
					 data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_UP_TIMES);
					 return data;
				}
			}else{
				mapTimes.put(timeKey, "1");
				MemcachedUtil.putMemcacheIntanceByTime(mapTimes,Constants.XCJ_MEMCACHED_TOKEN_TIMING_TEST_TIMES);
			}
			
			//此处需要加是否多次请求，假如有token 的话，如何重新请求
			 if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail)!=null
					 &&mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail)!=null
					 &&mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail)!=null){
				 data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_RECHONGFU);
				 data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_RECHONGFU);
				 data.put("token",mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail));
				 String sessionId = domainService.generateTestSessionId(userEmail,String.valueOf(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail)));
				 //此处也需要放在缓存，缓存始终放的最新的SessionID
				 HashMap<String, String> mapSession1 =new HashMap<String, String>();
				 mapSession1.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail,sessionId);
				 MemcachedUtil.putMemcacheIntanceByTime(mapSession1, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
				 
				 data.put("sessionId",sessionId);
				 data.put("testsPublicId",mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail));
				 return data;
			 }
			//此处生成token  验证通过了
			String token=domainService.generateTestToken(domainAccount,domainPassword,userEmail);
			 String sessionId = domainService.generateTestSessionId(userEmail,token);
			String testsPublicId = domainService.generateTestPublicId(domainAccount, userEmail,sessionId);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
			data.put("token",token);
			data.put("sessionId",sessionId);
			data.put("testsPublicId",testsPublicId);
			
			
			
			//1、此处将token放到缓存里边去
			HashMap<String, String> map =new HashMap<String, String>();
			map.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail,token);
			MemcachedUtil.putMemcacheIntanceByTime(map, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			
			//2、此处将sessionid放到缓存里边去
			HashMap<String, String> mapSession =new HashMap<String, String>();
			mapSession.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_SESSION+"_"+userEmail,sessionId);
			MemcachedUtil.putMemcacheIntanceByTime(mapSession, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			
			
			// 3、存放缓存每次从缓存获取 2、以标准json对象返回 注意交卷是销毁 将testpublicID放到
			HashMap<String, String> testPublicIdMap =new HashMap<String, String>();
			testPublicIdMap.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_PUBLIC+"_"+userEmail, testsPublicId);
			MemcachedUtil.putMemcacheIntanceByTime(testPublicIdMap,Constants.XCJ_MEMCACHED_TOKEN_LOGIN_TIMING);

			//  如果别的地方需要用到token则用一下方法从缓存里调用token
			//  Constants.XCJ_MEMCACHED_CONSTANTS_TEST+username+"_"+userEmail根据KEY取缓存里获取。
		} catch (Exception e) {
			logger.error(TestsTokenController.class.getName()+" getTsp() exception: "+e.getMessage());	
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}
	
	
	
	/**
	  * @param domainAccount 用户名 
	  * @param domainPassword 密码
	  * @param userEmail 用户邮箱
	  * @param request
	  * @param response
	  * @return  返回字符窜
	  */
	@ResponseBody
	@RequestMapping(value = "/getToken", produces = "application/json;charset=UTF-8")
	public JSONObject getToken(String domainAccount, String domainPassword,String userEmail,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		try {
			if(ApiUtil.isParamNull(domainAccount,userEmail)){
				return ApiUtil.returnParamNull();
			}
			
			//查询数据库是否能找到用户名和密码一样的信息
			boolean flag=domainService.getByUsernameAndPassword(domainAccount,MD5Uitl.MD5Encode(domainPassword));
			if(!flag){
				data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_20004);
				data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_20004);
				return data;
			}
			
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			 //此处需要控制请求次数的限制
			String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+"_"+domainPassword+"_"+domainAccount+"_"+userEmail;
			//String timeKey=Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+"_"+domainAccount+"_"+userEmail;
			HashMap<String, String> mapTimes =new HashMap<String, String>();
			if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey)!=null){
				String times=String.valueOf(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey).toString());
				Integer xx=Integer.parseInt(times)+1;
				mapTimes.put(timeKey, String.valueOf(xx));
				MemcachedUtil.putMemcacheIntanceByTime(mapTimes,Constants.XCJ_MEMCACHED_TOKEN_TIMING_TEST_TIMES);
				if(Integer.parseInt(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST+timeKey).toString())>=1000000000){
					 data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_UP_TIMES);
					 data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_UP_TIMES);
					 return data;
				}
			}else{
				mapTimes.put(timeKey, "1");
				MemcachedUtil.putMemcacheIntanceByTime(mapTimes,Constants.XCJ_MEMCACHED_TOKEN_TIMING_TEST_TIMES);
			}
			
			//此处需要加是否多次请求，加入有token 的话，如何重新请求
			 if(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail)!=null){
				 data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_RECHONGFU);
				 data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_RECHONGFU);
				 data.put("token",mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail));
				 return data;
			 }
			//此处生成token  验证通过了
			String token=domainService.generateTestToken(domainAccount,domainPassword,userEmail);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
			data.put("token",token);
			//此处将token放到缓存里边去
			HashMap<String, String> map =new HashMap<String, String>();
			map.put(Constants.XCJ_MEMCACHED_CONSTANTS_TEST_TOKEN+domainAccount+"_"+userEmail,token);
			MemcachedUtil.putMemcacheIntanceByTime(map, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			//  如果别的地方需要用到token则用一下方法从缓存里调用token
			
			//  Constants.XCJ_MEMCACHED_CONSTANTS_TEST+username+"_"+userEmail根据KEY取缓存里获取。
		} catch (Exception e) {
			logger.error(TestsTokenController.class.getName()+" getToken() exception: "+e.getMessage());	
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}
	
}
