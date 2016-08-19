package com.xcj.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.xcj.common.page.Page;

public class FormatJsonUtil {
	
	/**
	 * 
	   * <b>function:</b> 参数格式不是（1,2,3,4）这种格式
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年7月2日 上午9:50:55
	   * @return void
	   * @author dapeng_wu
	 */
	public static void formatParamError(JSONObject jsonObject,String param) {
		if(param == null){
			return;
		}
		Pattern pattern = Pattern.compile("[^(\\d+(,{1}))|(\\d+)]");  
		Matcher matcher = pattern.matcher(param);
		if (matcher.find()) {
			jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.XCJ_DATE_PARAM_ERROR);
			jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_DATE_PARAM_ERROR_MSG);
			return;
		}
		pattern = Pattern.compile("^,|,$");  
		matcher = pattern.matcher(param);
		if (matcher.find()) {
			param = matcher.replaceAll("");
		}

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
	public static void format(JSONObject jsonObject,String errCode,String errValue) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,errCode);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,errValue);
	}  
	/**
	 * 
	   * <b>function:</b> 系统繁忙-1
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年7月2日 上午9:25:22
	   * @return void
	   * @author dapeng_wu
	 */
	public static void formatFu1(JSONObject jsonObject) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_FU1);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG__FU1);
	}  
	
	/**
	 * 
	   * <b>function:</b> 参数为空
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年7月2日 上午9:25:22
	   * @return void
	   * @author dapeng_wu
	 */
	public static void formatParamnull(JSONObject jsonObject) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.XCJ_RETURN_PARAMNULL);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.XCJ_RETURN_PARAMNULL_MSG);
	}
	
	/**
	 * 
	 * <b>function:</b> 成功返回
	 * @project service
	 * @package com.xcj.common.util  
	 * @fileName com.xcj.*** 
	 * @createDate 2015年7月2日 上午9:25:22
	 * @return void
	 * @author dapeng_wu
	 */
	public static void formatSuccess(JSONObject jsonObject) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
	}
	/**
	 * 
	   * <b>function:</b>  有分页成功的返回信息
	   * @project service
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate 2015年7月2日 上午9:29:05
	   * @return void
	   * @author dapeng_wu
	 */
	public static void format(JSONObject jsonObject,Page<?> page) {  
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
		jsonObject.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
		jsonObject.put(APIConstants.XCJ_RETURN_API_CURRENTPAGE,page.getCurrentPage());
		jsonObject.put(APIConstants.XCJ_RETURN_API_TOTALSPAGE,page.getTotalsPage());
		jsonObject.put(APIConstants.XCJ_RETURN_API_PAGESIZE,page.getPageSize());
		jsonObject.put(APIConstants.XCJ_RETURN_API_TOTALSCOUNT,page.getTotalsCount());
		jsonObject.put(APIConstants.XCJ_RETURN_API_DATA,page.getResult());
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
	public static void format(JSONObject data,Object obj) {
		data.put(APIConstants.XCJ_RETURN_API_ERRCODE,APIConstants.ERRCODE_SUCCESS);
		data.put(APIConstants.XCJ_RETURN_API_ERRMSG,APIConstants.ERRMSG_SUCCESS);
		data.put(APIConstants.XCJ_RETURN_API_DATA,obj);
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
	public static String formatJSONP(JSONObject data,String callback) {
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
	
	
}