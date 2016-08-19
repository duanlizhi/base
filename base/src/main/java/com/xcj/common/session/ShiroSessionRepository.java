/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.common.session  
 * @fileName  ShiroSessionRepository.java 
 * @createDate  2015-3-20 上午11:37:24 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */ 
package com.xcj.common.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * @author su_jian
 *
 */
public interface ShiroSessionRepository {
		void saveSession(Session session);  
	  
	    void deleteSession(Serializable sessionId);  
	  
	    Session getSession(Serializable sessionId);  
	  
	    Collection<Session> getAllSessions();  
}
