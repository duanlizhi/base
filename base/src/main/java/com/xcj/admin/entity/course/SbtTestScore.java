package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="SbtTestScore")
@Table(name="sbt_test_score")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_sbt_test_score")
public class SbtTestScore extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  model;//模式

    private String  modelVersion;//模式版本

    private String  name;//名称

    private String text;//文本内容

    private Float  score;//score 分数

    private String  courseNumber;//课程编号

    private String  faultNumber;//故障编号

    private Integer  isEnable;//是否禁用 1、启用 2、禁用

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  courseWareId;//课件id

    @Column(name="model",nullable = false, length=20)
    public String  getModel(){
       return model;
    }

    public  void  setModel(String model){
       this.model=model;
    }


    @Column(name="model_version",nullable = false, length=20)
    public String  getModelVersion(){
       return modelVersion;
    }

    public  void  setModelVersion(String modelVersion){
       this.modelVersion=modelVersion;
    }


    @Column(name="name",nullable = false, length=100)
    public String  getName(){
       return name;
    }

    public  void  setName(String name){
       this.name=name;
    }


    @Column(name="text",nullable = true, length=65535)
    public  String getText(){
       return text;
    }

    public  void  setText(String text){
       this.text=text;
    }


    @Column(name="score",nullable = false, length=10)
    public Float  getScore(){
       return score;
    }

    public  void  setScore(Float score){
       this.score=score;
    }


    @Column(name="course_number",nullable = false, length=40)
    public String  getCourseNumber(){
       return courseNumber;
    }

    public  void  setCourseNumber(String courseNumber){
       this.courseNumber=courseNumber;
    }


    @Column(name="fault_number",nullable = true, length=40)
    public String getFaultNumber() {
		return faultNumber;
	}

	public void setFaultNumber(String faultNumber) {
		this.faultNumber = faultNumber;
	}

    @Column(name="is_enable",nullable = false, length=10)
    public Integer  getIsEnable(){
       return isEnable;
    }

    public  void  setIsEnable(Integer isEnable){
       this.isEnable=isEnable;
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
