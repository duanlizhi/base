package com.xcj.common.util.common;
import com.alibaba.fastjson.JSONObject;
import com.xcj.common.util.APIConstants;
import com.xcj.common.util.Constants;
public class ApiUtil {
	
	/**
	 * 如果参数为空则返回参数为空的信息
	 * @return
	 */
	public static JSONObject returnParamNull(){
		JSONObject data = new JSONObject();
		data.put(Constants.XCJ_RETURN_API_ERRCODE, APIConstants.XCJ_RETURN_PARAMNULL);
		data.put(Constants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULLMSG);
		return data;
	}
	
	/**
	 * @param obj 判断传入参数是否为空，如果为空则返回false ，如果正确则返回true
	 * @return
	 */
	public static boolean isParamNull(Object... obj){
		for (Object object : obj) {
			if(object == null || "".equals(object.toString())){
				return true;
			}
		}
		return false;
	}
}
