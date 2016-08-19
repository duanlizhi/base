/** 
 * <b>function:</b> 
 * @project   base
 * @package  com.xcj.common.session  
 * @fileName  TTtest.java 
 * @createDate  2015-3-12 下午01:49:54 
 * @author su_jian 
 * @email sujiansoft@163.com 
 */
package com.xcj.common.session;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Repository;

import com.xcj.admin.service.admin.AdminService;

/**
 * 
 */
@Repository
public class TTtest extends org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator{
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	
	public Serializable generateId(Session session) {
		return UUID.randomUUID().toString();
	}
	 
	

}
