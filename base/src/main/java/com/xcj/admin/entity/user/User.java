package com.xcj.admin.entity.user;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
import com.xcj.common.interceptor.UserInterceptor;
@Entity(name="User")
@Table(name="user")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_user")
public class User extends BaseEntity  implements java.io.Serializable{
	public static final String PRINCIPAL_ATTRIBUTE_NAME = UserInterceptor.class.getName() + ".PRINCIPAL";
   private static final long serialVersionUID = 1L;
    private String  name;//姓名

    private String  username;//用户名

    private String  email;//电子邮箱

    private String  password;//学员密码

    private Integer  sex;//性别：1、男 2、女

    private String  qq;//QQ号码

    private String  weixin;//微信号码

    private String  mobile;//手机号码

    private String  logo;//头像

    private String  address;//地址

    private String  loginIp;//登录IP

    private String  regDate;//注册时间

    private java.util.Date  loginDate;//登录时间

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    @Column(name="name",nullable = false, length=200)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="username",nullable = false, length=50)
    public String  getUsername(){
       return username;
    }

    public  void  setUsername(String username){
       this.username=username;
    }


    @Column(name="email",nullable = false, length=100)
    public String  getEmail(){
       return email;
    }

    public  void  setEmail(String email){
       this.email=email;
    }


    @Column(name="password",nullable = false, length=40)
    public String  getPassword(){
       return password;
    }

    public  void  setPassword(String password){
       this.password=password;
    }


    @Column(name="sex",nullable = true, length=10)
    public Integer  getSex(){
       return sex;
    }

    public  void  setSex(Integer sex){
       this.sex=sex;
    }


    @Column(name="qq",nullable = true, length=100)
    public String  getQq(){
       return qq;
    }

    public  void  setQq(String qq){
       this.qq=qq;
    }


    @Column(name="weixin",nullable = true, length=100)
    public String  getWeixin(){
       return weixin;
    }

    public  void  setWeixin(String weixin){
       this.weixin=weixin;
    }


    @Column(name="mobile",nullable = true, length=12)
    public String  getMobile(){
       return mobile;
    }

    public  void  setMobile(String mobile){
       this.mobile=mobile;
    }


    @Column(name="logo",nullable = true, length=200)
    public String  getLogo(){
       return logo;
    }

    public  void  setLogo(String logo){
       this.logo=logo;
    }


    @Column(name="address",nullable = true, length=255)
    public String  getAddress(){
       return address;
    }

    public  void  setAddress(String address){
       this.address=address;
    }


    @Column(name="login_ip",nullable = true, length=20)
    public String  getLoginIp(){
       return loginIp;
    }

    public  void  setLoginIp(String loginIp){
       this.loginIp=loginIp;
    }


    @Column(name="reg_date",nullable = false, length=50)
    public String  getRegDate(){
       return regDate;
    }

    public  void  setRegDate(String regDate){
       this.regDate=regDate;
    }


    @Column(name="login_date",nullable = false, length=19)
    public java.util.Date  getLoginDate(){
       return loginDate;
    }

    public  void  setLoginDate(java.util.Date loginDate){
       this.loginDate=loginDate;
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

}
