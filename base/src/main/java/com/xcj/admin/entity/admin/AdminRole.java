package com.xcj.admin.entity.admin;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;
@Entity(name="AdminRole")
@Table(name="admin_role")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_admin_role")
public class AdminRole extends BaseEntity  implements java.io.Serializable{
	private static final long serialVersionUID = -5212721278927065828L;
	public Admin admin;//用户外键
	public Role role;//角色外键
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "roles")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "admins")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


}
