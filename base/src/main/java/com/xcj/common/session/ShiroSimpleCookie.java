/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.common.session  
 * @fileName  ShiroSimpleCookie.java 
 * @createDate  2015-3-13 上午10:28:27 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */ 
package com.xcj.common.session;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import com.xcj.common.entity.Principal;

/**
 * @author com.xcj.common.session.ShiroSimpleCookie
 *
 */
public class ShiroSimpleCookie extends org.apache.shiro.web.servlet.SimpleCookie {
	    public ShiroSimpleCookie() {
	    	super();
	    	String name="";
	       super.setName(name);
	       super.setHttpOnly(true);
	       super.setMaxAge(180000);
	    }

	 

}
