/**
 * 
 */
package com.xcj.web.util;

import java.util.HashMap;

import com.xcj.common.util.md5.MD5Uitl;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.web.util  
 * @fileName com.xcj.*
 * @createDate Dec 7, 2014 9:22:15 PM 
 * @author su_jian 
 */
public class ChatUtil {
	
	
	/**
	 * 存储用户名和密码
	 * <b>function:</b> 
	 * @project base
	 * @package com.xcj.web.util  
	 * @fileName com.xcj.*** 
	 * @createDate Dec 7, 2014 9:16:35 PM
	 * @return HashMap<String,String>
	 * @author su_jian
	 */
	public static String getUandP(String username,String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sujian",MD5Uitl.MD5Encode("sujianaini123"));
		map.put("sujian1",MD5Uitl.MD5Encode("sujianaini124"));
		map.put("sujian2",MD5Uitl.MD5Encode("sujianaini125"));
		map.put("sujian3",MD5Uitl.MD5Encode("sujianaini126"));
		map.put("sujian4",MD5Uitl.MD5Encode("sujianaini127"));
		map.put("sujian5",MD5Uitl.MD5Encode("sujianaini128"));
		map.put("sujian6",MD5Uitl.MD5Encode("sujianaini129"));
		if(map.get(username)==null){
			return "10002";//不存在的用户名
		}
		if(!map.get(username).equals(password)){
			return "10003";//用户名或者密码不正确
		};
		return "0";
	}

}
