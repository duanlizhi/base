/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.api.wx  
 * @fileName  WxActregController.java 
 * @createDate  2015-5-21 下午04:28:01 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */ 
package com.xcj.api.wx;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.entity.wx.WxActreg;
import com.xcj.admin.service.wx.WxActregService;
import com.xcj.api.oper.controller.TestsTokenController;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.common.ApiUtil;
import com.xcj.common.util.common.DateUtil;

/**
 * @author su_jian
 *
 */
@Controller("wxAPIActregController")
@RequestMapping("/wx/api/")
public class WxAPIActregController {
	
	@Resource(name = "wxActregServiceImpl")
	private WxActregService wxActregService;
	private static final Logger logger = LoggerFactory.getLogger(WxAPIActregController.class);
	
	/**
	  *  
	  * @param domainAccount 用户名 
	  * @param domainPassword 密码
	  * @param userEmail 用户邮箱
	  * @param request
	  * @param response
	  * @return  返回字符窜
	  */
	@ResponseBody
	@RequestMapping(value = "/subdata", produces = "application/json;charset=UTF-8")
	public JSONObject getToken(String name, String age,String poster,String mobile,String agent,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject data = new JSONObject();
		try {
			if(ApiUtil.isParamNull(name,age,poster,mobile,agent)){
				logger.error("微信活动参数为空！！");
				return ApiUtil.returnParamNull();
			}
			boolean flagm=wxActregService.getWxActregByMobile(mobile);
			if(flagm){
				 data.put(APIConstants.XCJ_RETURN_API_ERRCODE, "30008");
				 data.put(APIConstants.XCJ_RETURN_API_ERRMSG, "手机号已经重复");
				 return data;
			} 
			 WxActreg  wxActreg =new WxActreg();
			 wxActreg.setAge(Integer.valueOf(age));
			 wxActreg.setAgent(agent);
			 wxActreg.setCreateDate(DateUtil.getCurrentTimeByDate());
			 wxActreg.setDescription("描述信息");
			 wxActreg.setMobile(mobile);
			 wxActreg.setModifyDate(DateUtil.getCurrentTimeByDate());
			 wxActreg.setName(name);
			 wxActreg.setPoster(poster);
			 wxActregService.save(wxActreg);
			 data.put(APIConstants.XCJ_RETURN_API_ERRCODE, APIConstants.ERRCODE_SUCCESS);
			 data.put(APIConstants.XCJ_RETURN_API_ERRMSG, APIConstants.ERRMSG_SUCCESS);
		} catch (Exception e) {
			logger.error(TestsTokenController.class.getName()+"微信提交数据出错: "+e.getMessage());	
			data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRCODE_FU1);
			data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRMSG__FU1);
			return data;
		}
		return data;
	}

}
