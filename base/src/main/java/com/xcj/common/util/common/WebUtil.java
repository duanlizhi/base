/**
 * 
 */
package com.xcj.common.util.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.xcj.common.util.Constants;

/** 
 * <b>function:</b> 
 * @project xcjpar 
 * @package com.xcj.common.util  
 * @fileName com.xcj.*
 * @createDate Aug 21, 2014 3:45:45 PM 
 * @author su_jian 
 */
public class WebUtil {
	/**
	 * 不可实例化
	 */
	private WebUtil() {
	}
	
	/**
	 * <b>function:</b>
	 * 
	 * @project xcjpar
	 * @package com.xcj.common.util
	 * @fileName com.xcj.***
	 * @createDate Aug 21, 2014 5:20:56 PM
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 * @param secure
	 *            是否启用加密
	 * @author su_jian
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	 /**
	  * 
	    * <b>function:</b> 
	    * @project xcjpar
	    * @package com.xcj.common.util  
	    * @fileName com.xcj.*** 
	    * @createDate Aug 21, 2014 5:17:46 PM
	    * @param name
	    *            cookie名称
	    * @param value
	    *            cookie值
	    * @param maxAge
	    *            有效期(单位: 秒)
	    * @return void
	    * @author su_jian
	  */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
		addCookie(request, response, name, value, maxAge, Constants.XCJ_COOKIE_PATH, Constants.XCJ_COOKIE_DOAMIN, null);
	}
	 
	/**
	   * <b>function:</b> 若不存在则返回null
	   * @project xcjpar
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate Aug 21, 2014 5:22:29 PM
	   * @return String若不存在则返回null 
	   * @author su_jian
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	 /**
	    * <b>function:</b> 移除cookie
	    * @project xcjpar
	    * @package com.xcj.common.util  
	    * @fileName com.xcj.*** 
	    * @createDate Aug 21, 2014 5:26:39 PM
	    * @name  名称
	    * @path 路径
	    * @domain 作用域
	    * @return void
	    * @author su_jian
	  */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	 /**
	    * <b>function:</b> 移除cookie
	    * @project xcjpar
	    * @package com.xcj.common.util  
	    * @fileName com.xcj.*** 
	    * @createDate Aug 21, 2014 5:26:23 PM
	    * @return void
	    * @author su_jian
	  */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		removeCookie(request, response, name, Constants.XCJ_COOKIE_PATH, Constants.XCJ_COOKIE_DOAMIN);
	}
	
	
	
	
}
