package com.xcj.admin.entity.wx;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="WxActreg")
@Table(name="wx_actreg")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_wx_actreg")
public class WxActreg extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  name;//姓名

    private Integer  age;//年龄

    private String  poster;//职务

    private String  mobile;//手机号

    private java.util.Date  createDate;//

    private java.util.Date  modifyDate;//

    private String  description;//

    private String  agent;//手机标识符

    @Column(name="name",nullable = false, length=50)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="age",nullable = false, length=10)
    public Integer  getAge(){
       return age;
    }

    public  void  setAge(Integer age){
       this.age=age;
    }


    @Column(name="poster",nullable = false, length=100)
    public String  getPoster(){
       return poster;
    }

    public  void  setPoster(String poster){
       this.poster=poster;
    }


    @Column(name="mobile",nullable = false, length=10)
    public String  getMobile(){
       return mobile;
    }

    public  void  setMobile(String mobile){
       this.mobile=mobile;
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


    @Column(name="description",nullable = true, length=200)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="agent",nullable = true, length=1000)
    public String  getAgent(){
       return agent;
    }

    public  void  setAgent(String agent){
       this.agent=agent;
    }

}
