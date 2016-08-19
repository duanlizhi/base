package com.xcj.admin.entity.admin;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;
@Entity(name="Role")
@Table(name="role")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_role")
public class Role extends BaseEntity  implements java.io.Serializable{
	private static final long serialVersionUID = -5832610054793238358L;

	private java.util.Date  createDate;//创建日期

    private java.util.Date  modifyDate;//修改日期

    private String  description;//描述信息

    private  boolean isSystem;//是否是超级1、是、0否

    private String  name;//角色名称
    public List<AdminRole> adminRoles = new ArrayList<AdminRole>();//角色下用哪些用户
    public List<RoleAuthority> roleAuthorities=new ArrayList<RoleAuthority>();//角色所具有的权限
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public List<RoleAuthority> getRoleAuthorities() {
		return roleAuthorities;
	}

	public void setRoleAuthorities(List<RoleAuthority> roleAuthorities) {
		this.roleAuthorities = roleAuthorities;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    public List<AdminRole> getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(List<AdminRole> adminRoles) {
		this.adminRoles = adminRoles;
	}
    @Column(name="create_date",nullable = false, length=19)
    public java.util.Date  getCreateDate(){
       return createDate;
    }

    public  void  setCreateDate(java.util.Date createDate){
       this.createDate=createDate;
    }


    @Column(name="modify_date",nullable = false, length=19)
    public java.util.Date  getModifyDate(){
       return modifyDate;
    }

    public  void  setModifyDate(java.util.Date modifyDate){
       this.modifyDate=modifyDate;
    }


    @Column(name="description",nullable = true, length=255)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="is_system",nullable = false, length=1)
    public boolean  getIsSystem(){
       return isSystem;
    }

    public  void  setIsSystem(boolean isSystem){
       this.isSystem=isSystem;
    }


    @Column(name="name",nullable = false, length=255)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }

}
