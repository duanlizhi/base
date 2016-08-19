package com.xcj.admin.entity.users;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="Users")
@Table(name="users")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_users")
public class Users extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  name;//

    private Integer  classnameId;//

    private Integer  avgScore;//

    private java.util.Date  createDate;//

    private java.util.Date  modifyDate;//
    
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
    
    @Column(name="name",nullable = true, length=255)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="classname_id",nullable = true, length=10)
    public Integer  getClassnameId(){
       return classnameId;
    }

    public  void  setClassnameId(Integer classnameId){
       this.classnameId=classnameId;
    }


    @Column(name="avg_score",nullable = true, length=10)
    public Integer  getAvgScore(){
       return avgScore;
    }

    public  void  setAvgScore(Integer avgScore){
       this.avgScore=avgScore;
    }

}
