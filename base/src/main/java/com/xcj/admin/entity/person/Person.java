package com.xcj.admin.entity.person;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;
@Entity(name="Person")
@Table(name="person")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_person")
public class Person extends BaseEntity  implements java.io.Serializable{
	
	private static final long serialVersionUID = 8240474976943323782L;

	private String  username;//用户名

    private String  password;//密码

    private String  name;//用户真实姓名

    private String  photo;//头像

    private String  email;//邮箱

    private Integer  sex;//性别

    private String  address;//地址

    private String  mobile;//电话

    private String  qq;//QQ

    private String  identity;//身份证

    private Integer  integral;//积分

    private String  enterpriseInfo;//企业名称

    private String  scopeSercice;//服务范围

    private Integer  visitorVolume;//访问量

    private String  loginIp;//登陆ip

    private java.sql.Timestamp  loginDate;//登陆时间

    private java.sql.Timestamp  createDate;//创建时间

    private java.sql.Timestamp  modifyDate;//修改时间

    private Integer  userGradeId;//用户等级表的id

    private Integer  isTag;//区分个人0与企业1

    @Column(name="username",nullable = false, length=40)
    public String  getUsername(){
       return username;
    }

    public  void  setUsername(String username){
       this.username=username;
    }


    @Column(name="password",nullable = false, length=40)
    public String  getPassword(){
       return password;
    }

    public  void  setPassword(String password){
       this.password=password;
    }


    @Column(name="name",nullable = true, length=45)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="photo",nullable = true, length=45)
    public String  getPhoto(){
       return photo;
    }

    public  void  setPhoto(String photo){
       this.photo=photo;
    }


    @Column(name="email",nullable = true, length=40)
    public String  getEmail(){
       return email;
    }

    public  void  setEmail(String email){
       this.email=email;
    }


    @Column(name="sex",nullable = true, length=10)
    public Integer  getSex(){
       return sex;
    }

    public  void  setSex(Integer sex){
       this.sex=sex;
    }


    @Column(name="address",nullable = true, length=255)
    public String  getAddress(){
       return address;
    }

    public  void  setAddress(String address){
       this.address=address;
    }


    @Column(name="mobile",nullable = true, length=15)
    public String  getMobile(){
       return mobile;
    }

    public  void  setMobile(String mobile){
       this.mobile=mobile;
    }


    @Column(name="qq",nullable = true, length=15)
    public String  getQq(){
       return qq;
    }

    public  void  setQq(String qq){
       this.qq=qq;
    }


    @Column(name="identity",nullable = true, length=20)
    public String  getIdentity(){
       return identity;
    }

    public  void  setIdentity(String identity){
       this.identity=identity;
    }


    @Column(name="integral",nullable = false, length=10)
    public Integer  getIntegral(){
       return integral;
    }

    public  void  setIntegral(Integer integral){
       this.integral=integral;
    }


    @Column(name="enterprise_info",nullable = true, length=50)
    public String  getEnterpriseInfo(){
       return enterpriseInfo;
    }

    public  void  setEnterpriseInfo(String enterpriseInfo){
       this.enterpriseInfo=enterpriseInfo;
    }


    @Column(name="scope_sercice",nullable = true, length=255)
    public String  getScopeSercice(){
       return scopeSercice;
    }

    public  void  setScopeSercice(String scopeSercice){
       this.scopeSercice=scopeSercice;
    }


    @Column(name="visitor_volume",nullable = true, length=10)
    public Integer  getVisitorVolume(){
       return visitorVolume;
    }

    public  void  setVisitorVolume(Integer visitorVolume){
       this.visitorVolume=visitorVolume;
    }


    @Column(name="login_ip",nullable = true, length=20)
    public String  getLoginIp(){
       return loginIp;
    }

    public  void  setLoginIp(String loginIp){
       this.loginIp=loginIp;
    }


    @Column(name="login_date",nullable = true, length=19)
    public java.sql.Timestamp  getLoginDate(){
       return loginDate;
    }

    public  void  setLoginDate(java.sql.Timestamp loginDate){
       this.loginDate=loginDate;
    }


    @Column(name="create_date",nullable = true, length=19)
    public java.sql.Timestamp  getCreateDate(){
       return createDate;
    }

    public  void  setCreateDate(java.sql.Timestamp createDate){
       this.createDate=createDate;
    }


    @Column(name="modify_date",nullable = true, length=19)
    public java.sql.Timestamp  getModifyDate(){
       return modifyDate;
    }

    public  void  setModifyDate(java.sql.Timestamp modifyDate){
       this.modifyDate=modifyDate;
    }


    @Column(name="user_grade_id",nullable = false, length=10)
    public Integer  getUserGradeId(){
       return userGradeId;
    }

    public  void  setUserGradeId(Integer userGradeId){
       this.userGradeId=userGradeId;
    }


    @Column(name="is_tag",nullable = false, length=10)
    public Integer  getIsTag(){
       return isTag;
    }

    public  void  setIsTag(Integer isTag){
       this.isTag=isTag;
    }

}
