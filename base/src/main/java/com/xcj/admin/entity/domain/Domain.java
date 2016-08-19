package com.xcj.admin.entity.domain;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="Domain")
@Table(name="domain")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_domain")
public class Domain extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  username;//账号

    private String  password;//密码

    private String  name;//名称

    private String  description;//描述

    private String  domainNumber;//域编号

    private String  url;//访问路径

    private Integer  enable;//是否可用

    private java.util.Date  createDate;//创建日期

    private java.util.Date  modifyDate;//修改时间

    @Column(name="username",nullable = false, length=20)
    public String  getUsername(){
       return username;
    }

    public  void  setUsername(String username){
       this.username=username;
    }


    @Column(name="password",nullable = false, length=20)
    public String  getPassword(){
       return password;
    }

    public  void  setPassword(String password){
       this.password=password;
    }


    @Column(name="name",nullable = false, length=100)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="description",nullable = true, length=500)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="domain_number",nullable = false, length=100)
    public String  getDomainNumber(){
       return domainNumber;
    }

    public  void  setDomainNumber(String domainNumber){
       this.domainNumber=domainNumber;
    }


    @Column(name="url",nullable = false, length=200)
    public String  getUrl(){
       return url;
    }

    public  void  setUrl(String url){
       this.url=url;
    }


    @Column(name="enable",nullable = false, length=10)
    public Integer  getEnable(){
       return enable;
    }

    public  void  setEnable(Integer enable){
       this.enable=enable;
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
