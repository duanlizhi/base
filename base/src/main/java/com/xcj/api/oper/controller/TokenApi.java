package com.xcj.api.oper.controller;

import java.util.HashMap;

import net.spy.memcached.MemcachedClient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
import com.xcj.common.util.FormatJsonUtil;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.DateUtil;
import com.xcj.common.util.common.HttpUtil;
import com.xcj.common.util.md5.MD5Uitl;

/**
 * 
  * <dl>
  * <dt><span class="strong">类说明:</span></dt>
  * <dd>跟内容中心交互获取token</dd>
  * </dl> 
  * <dl><dt><span class="strong">逻辑说明:</span></dt>
  *  <dd>1、跟内容中心交互获取token</dd></dl>  
  *  <dl><dt><span class="strong">创建时间:</span></dt>
  *  <dd> 2015年9月10日 下午5:29:45</dd></dl> 
  * </dl> 
  * @author wu_dapeng
  * @see {@linkplain java.lang.String }
  * @since 1.0
 */
@Controller("webTokenApiController")
@RequestMapping("web/api")
public class TokenApi {
	
	@ResponseBody
	@RequestMapping(value = "/token/getStudyTs",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String getStudyTs(String userEmail,String code,@RequestParam String callback) {
		JSONObject data = new JSONObject();
		try{
			if(ApiUtil.isParamNull(userEmail,code)){
				return ApiUtil.returnParamNull().toJSONString();
			}
			code = code.replaceAll("\\s*", ""); 
			userEmail = userEmail.replaceAll("\\s*", ""); 
			MemcachedClient mc1 = MemcachedClientFactory.getInstance();
			String studyFlag =(String)mc1.get(code);
			if(ApiUtil.isParamNull(studyFlag)){
				format(data, "20011","code错误");
				return formatJSONP(data, callback);
			}
			if(userEmail.equals((String)mc1.get(code+userEmail))?false:true){
				format(data, "20012", "userEmail错误");
				return formatJSONP(data, callback);
			}
			HashMap<String, String> map =new HashMap<String, String>();
			map.put("domainAccount", "contentadmin");
			map.put("domainPassword","admin");
			map.put("userEmail",userEmail);
			map.put("studyFlag", studyFlag);
			//String tokenUrlTsp = "/getTsp";
			String tokenUrlTsp = "http://content.xiaochejiang.com/api/oper/base/getTsp";
			data= JSONObject.parseObject(HttpUtil.doGet(tokenUrlTsp, map));
			mc1.delete(code);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
			return formatJSONP(data, callback);
		} catch (Exception e) {
			e.printStackTrace();
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return formatJSONP(data, callback);
		}
	}
	
	
	//临时测试用
	@ResponseBody
	@RequestMapping(value = "/getStudyCode",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String getStudyCode(String courseNumber,String email,String userCourseClassId,@RequestParam String callback) {
		JSONObject data = new JSONObject();
		if (ApiUtil.isParamNull(courseNumber,email, userCourseClassId)) {
			FormatJsonUtil.formatParamnull(data);
			return FormatJsonUtil.formatJSONP(data, callback);
		}
		try {
			JSONObject studyFlag = new JSONObject();
			studyFlag.put("id", userCourseClassId);
			String code = MD5Uitl.MD5Encode(Constants.XCJ_PT_NAME+DateUtil.getCurrentTime()+courseNumber+email+studyFlag.toJSONString());
			data.put("code", code);
			
			HashMap<String, String> mapStydyFlag =new HashMap<String, String>();
			mapStydyFlag.put(code,studyFlag.toJSONString());
			mapStydyFlag.put(code+email,email);
			MemcachedUtil.putMemcacheIntanceByTime(mapStydyFlag, Constants.XCJ_MEMCACHED_TOKEN_TIMING);
			FormatJsonUtil.formatSuccess(data);
			String formatJSONP = FormatJsonUtil.formatJSONP(data, callback);
//			System.out.println(formatJSONP);
			return formatJSONP;
		} catch (Exception e) {
			e.printStackTrace();
			FormatJsonUtil.formatFu1(data);
			return FormatJsonUtil.formatJSONP(data, callback);
		}
	}
	
	
	/**
	 * 
	   * <b>function:</b>成功的返回信息
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年10月9日 上午11:15:05
	   * @return void
	   * @author Hands
	 */
	public String formatJSONP(JSONObject data,String callback) {
		String tempStr = callback+"("+data.toJSONString()+")";
		String tempB ="";
		try {
			 tempB = tempStr;
			 //tempB = new String(tempStr.getBytes(),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempB;
    }
	
	
	/**
	 * 
	   * <b>function:</b> 根据错误码格式化返回值
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年7月2日 上午9:50:55
	   * @return void
	   * @author dapeng_wu
	 */
	public void format(JSONObject jsonObject,String errCode,String errValue) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,errCode);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,errValue);
	} 
	
}

