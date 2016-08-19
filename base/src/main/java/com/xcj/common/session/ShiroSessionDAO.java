/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.common.session  
 * @fileName  ShiroSessionDAO.java 
 * @createDate  2015-3-11 下午01:55:49 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */
package com.xcj.common.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import net.spy.memcached.MemcachedClient;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;

/**
 * @author su_jian
 * 
 */
public class ShiroSessionDAO extends AbstractSessionDAO {
	 private final static Logger log = LoggerFactory.getLogger(ShiroSessionDAO.class);
	 
	/* 
	 * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
	 * @param session
	 * @return
	 */
	protected Serializable doCreate(Session session) {
		    Serializable sessionId = generateSessionId(session);  
	        assignSessionId(session, sessionId);  
	        try {   
	        	HashMap<String, Object> map =new HashMap<String, Object>();
	        	map.put(sessionId.toString(), session);
	        	MemcachedUtil.putMemcacheIntanceByObject(map,(int) session.getTimeout() / 1000);
	        } catch (Exception e) {  
	            log.error(e.getMessage());  
	        }  
	        return sessionId;  
	}

	 
	protected Session doReadSession(Serializable sessionId) {
		 Session session = null;  
	        try {  
	        	MemcachedClient mc1=MemcachedClientFactory.getInstance();
	            session = (Session)mc1.get(sessionId.toString());  
	        } catch (Exception e) {  
	            log.error(e.getMessage());  
	        }  
	        return session;  
	}
	
	 
	public void delete(Session session) {
		try {  
			MemcachedClient mc1=MemcachedClientFactory.getInstance();
			mc1.delete(session.getId().toString());  
        } catch (Exception e) {  
            log.error(e.getMessage());  
        }  
		
	}

	 /**
	  * TODO 此处获取用户当前在线的Session信息没有做处理
	  */
	public Collection<Session> getActiveSessions() {
		 return Collections.emptySet();  
	}

	public void update(Session session) throws UnknownSessionException {
		 try {  
			 MemcachedClient mc1=MemcachedClientFactory.getInstance();
			 mc1.replace(session.getId().toString(), (int) session.getTimeout() / 1000, session);  
	        } catch (Exception e) {  
	            log.error(e.getMessage());  
	        }  
		
	}
 
}
