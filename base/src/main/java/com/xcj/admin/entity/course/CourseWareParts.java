package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="CourseWareParts")
@Table(name="course_ware_parts")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_course_ware_parts")
public class CourseWareParts extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  partId;//part_id
    private String  stateId;//state_id

    private String  description;//描述

    private Integer  rank;//排序

    private String  code;//编号

    private String  type;//(1,sbt 2,wbt 3,emu,4dmc)

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  courseWareId;//课件id

    @Column(name="part_id",nullable = false, length=20)
    public String  getPartId(){
       return partId;
    }

    public  void  setPartId(String partId){
       this.partId=partId;
    }


    @Column(name="state_id",nullable = false, length=20)
    public String  getStateId(){
       return stateId;
    }

    public  void  setStateId(String stateId){
       this.stateId=stateId;
    }


    @Column(name="description",nullable = true, length=500)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="rank",nullable = false, length=10)
    public Integer  getRank(){
       return rank;
    }

    public  void  setRank(Integer rank){
       this.rank=rank;
    }


    @Column(name="code",nullable = false, length=100)
    public String  getCode(){
       return code;
    }

    public  void  setCode(String code){
       this.code=code;
    }


    @Column(name="type",nullable = false, length=100)
    public String  getType(){
       return type;
    }

    public  void  setType(String type){
       this.type=type;
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


    @Column(name="course_ware_id",nullable = false, length=10)
    public Integer  getCourseWareId(){
       return courseWareId;
    }

    public  void  setCourseWareId(Integer courseWareId){
       this.courseWareId=courseWareId;
    }

}
