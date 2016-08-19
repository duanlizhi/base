/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.common.session  
 * @fileName  MemcachedShiroRepository.java 
 * @createDate  2015-3-20 上午11:44:12 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */ 
package com.xcj.common.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * @author Administrator
 *
 */
public class MemcachedShiroRepository implements ShiroSessionRepository {

	public void deleteSession(Serializable sessionId) {
		System.out.println("删除");

	}
 
	public Collection<Session> getAllSessions() {
		
		return null;
	}
 
	public Session getSession(Serializable sessionId) {
		System.out.println("获取Session信息");
		return null;
	}

	 
	public void saveSession(Session session) {
		System.out.println("保存Session信息");
	}

}
