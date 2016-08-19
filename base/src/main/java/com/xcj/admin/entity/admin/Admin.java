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
@Entity(name="Admin")
@Table(name="admin")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_admin")
public class Admin extends BaseEntity  implements java.io.Serializable{
	private static final long serialVersionUID = -2283973582122360676L;
	public static final String PRINCIPAL_ATTRIBUTE_NAME = Admin.class.getName() + ".PRINCIPAL";
    private java.util.Date  createDate;//

    private java.util.Date  modifyDate;//

    private String  department;//

    private String  email;//

    private  Integer isEnabled;//

    private  Integer isLocked;//

    private java.util.Date  lockedDate;//

    private java.util.Date  loginDate;//

    private Integer  loginFailureCount;//

    private String  loginIp;//

    private String  name;//

    private String  password;//

    private String  username;//
    private String  adminNumber;//

    private String  mobile;//
	public List<AdminRole> adminRoles = new ArrayList<AdminRole>();//用户具有的角色
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "admin")
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


    @Column(name="department",nullable = true, length=255)
    public String  getDepartment(){
       return department;
    }

    public  void  setDepartment(String department){
       this.department=department;
    }


    @Column(name="email",nullable = false, length=255)
    public String  getEmail(){
       return email;
    }

    public  void  setEmail(String email){
       this.email=email;
    }


    @Column(name="is_enabled",nullable = false, length=1)
    public Integer  getIsEnabled(){
       return isEnabled;
    }

    public  void  setIsEnabled(Integer isEnabled){
       this.isEnabled=isEnabled;
    }


    @Column(name="is_locked",nullable = false, length=1)
    public Integer  getIsLocked(){
       return isLocked;
    }

    public  void  setIsLocked(Integer isLocked){
       this.isLocked=isLocked;
    }


    @Column(name="locked_date",nullable = true, length=19)
    public java.util.Date  getLockedDate(){
       return lockedDate;
    }

    public  void  setLockedDate(java.util.Date lockedDate){
       this.lockedDate=lockedDate;
    }


    @Column(name="login_date",nullable = true, length=19)
    public java.util.Date  getLoginDate(){
       return loginDate;
    }

    public  void  setLoginDate(java.util.Date loginDate){
       this.loginDate=loginDate;
    }


    @Column(name="login_failure_count",nullable = false, length=10)
    public Integer  getLoginFailureCount(){
       return loginFailureCount;
    }

    public  void  setLoginFailureCount(Integer loginFailureCount){
       this.loginFailureCount=loginFailureCount;
    }


    @Column(name="login_ip",nullable = true, length=255)
    public String  getLoginIp(){
       return loginIp;
    }

    public  void  setLoginIp(String loginIp){
       this.loginIp=loginIp;
    }


    @Column(name="name",nullable = true, length=255)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="password",nullable = false, length=255)
    public String  getPassword(){
       return password;
    }

    public  void  setPassword(String password){
       this.password=password;
    }


    @Column(name="username",nullable = false, length=100)
    public String  getUsername(){
       return username;
    }

    public  void  setUsername(String username){
       this.username=username;
    }
    @Column(name="admin_number",nullable = true, length=100)
    public String  getAdminNumber(){
       return adminNumber;
    }

    public  void  setAdminNumber(String adminNumber){
       this.adminNumber=adminNumber;
    }


    @Column(name="mobile",nullable = true, length=100)
    public String  getMobile(){
       return mobile;
    }

    public  void  setMobile(String mobile){
       this.mobile=mobile;
    }
}
