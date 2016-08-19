package com.xcj.admin.entity.admin;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name = "RoleAuthority")
@Table(name = "role_authority")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_role_authority")
public class RoleAuthority extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 4878273475797130838L;
	public Role role;//角色id
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "role")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	private String authorities;// 权限信息

	@Column(name = "authorities", nullable = true, length = 255)
	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
