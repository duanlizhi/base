package com.xcj.admin.controller.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xcj.admin.base.BaseController;
import com.xcj.common.util.common.DateUtil;
/*** 
 * <b>function:</b> 测试类的control
 * <b>备注信息</b>  返回类型后面分为JSP和FTL 分别转向 不同的试图
 * @project Lgguo 
 * @package com.xcj.admin.controller  
 * @fileName TestController.java 
 * @createDate 2010-4-1 下午05:28:03 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */
@Controller("testController")
@RequestMapping("/admin/test")
public class TestController extends BaseController{
	
	/**
	 * <b>function:</b> 测试返回浏览器头信息
	 * TokenController
	 * @createDate  2015-2-5 下午03:56:56
	 * @author su_jian 
	 * @email sujiansoft@163.com 
	 * return type ResponseEntity<JSONObject>
	 */
    @RequestMapping(value="/a/{name}", method=RequestMethod.GET, produces="application/json")  
    public @ResponseBody ResponseEntity<JSONObject> test(@PathVariable String name) {  
		JSONObject data = new JSONObject();
		data.put("data", name);
		HttpHeaders headers = new HttpHeaders();  
		headers.setContentType(MediaType.APPLICATION_JSON);  
		headers.setLastModified(DateUtil.getCurrentYear());
		ResponseEntity<JSONObject> responseEntity = new ResponseEntity<JSONObject>(data,headers,HttpStatus.OK);
        return responseEntity;  
    }  
    
}
