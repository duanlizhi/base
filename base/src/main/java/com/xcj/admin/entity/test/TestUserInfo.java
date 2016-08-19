package com.xcj.admin.entity.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

/**
 * Entity - 管理员
 * 
 * @author SJZl  Team
 * @version 1.1
 */
@Entity(name="testUserInfo")
@Table(name = "test_user_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_test_user_info")
public class TestUserInfo extends BaseEntity  implements Serializable{
	private static final long serialVersionUID = 6056249704662897714L;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	 @Column(name = "username", nullable = false, length = 36)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "password", nullable = false, length = 36)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * @param username
	 * @param password
	 */
	public TestUserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	/**
	 * 
	 */
	public TestUserInfo() {
		super();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	 

}
