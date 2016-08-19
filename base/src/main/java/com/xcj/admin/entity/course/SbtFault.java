package com.xcj.admin.entity.course;
import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="SbtFault")
@Table(name="sbt_fault")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_sbt_fault")
public class SbtFault extends BaseEntity  implements java.io.Serializable{
    /**
		 * 
	     * <b>function:</b> 读取SBT故障
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = -3792628449252704014L;

	private String  model;//模式

    private String  modelVersion;//模式版本

    private String  name;//名称

    private String  courseNumber;//课程编号

    private String  faultNumber;//故障编号

    private String  type;//类型

    private String  purpose;//目的

    private String  requires;//需求

    private String  keyword;//关键字

    private String  description;//描述

    private String  duration;//持续时间

    private Integer  sumScore;//通过分数

    private String  entry;//入口

    private String  client;//依托平台

    private String  version;//版本

    private String  language;//语言

    private Integer  difficulty;//难度
    
    private String  fileurl;//xmlurl

    private Integer  isEnable;//

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  courseWareId;//课件id
    
    private Integer passCondition;//通过条件
    
    private Integer completeCondition;//完成条件

    @Column(name="model",nullable = true, length=20)
    public String  getModel(){
       return model;
    }

    public  void  setModel(String model){
       this.model=model;
    }


    @Column(name="model_version",nullable = true, length=20)
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


    @Column(name="course_number",nullable = false, length=20)
    public String  getCourseNumber(){
       return courseNumber;
    }

    public  void  setCourseNumber(String courseNumber){
       this.courseNumber=courseNumber;
    }


    @Column(name="fault_number",nullable = true, length=20)
    public String  getFaultNumber(){
       return faultNumber;
    }

    public  void  setFaultNumber(String faultNumber){
       this.faultNumber=faultNumber;
    }


    @Column(name="type",nullable = false, length=20)
    public String  getType(){
       return type;
    }

    public  void  setType(String type){
       this.type=type;
    }


    @Column(name="purpose",nullable = true, length=100)
    public String  getPurpose(){
       return purpose;
    }

    public  void  setPurpose(String purpose){
       this.purpose=purpose;
    }


    @Column(name="requires",nullable = true, length=100)
    public String  getRequires(){
       return requires;
    }

    public  void  setRequires(String requires){
       this.requires=requires;
    }


    @Column(name="keyword",nullable = true, length=100)
    public String  getKeyword(){
       return keyword;
    }

    public  void  setKeyword(String keyword){
       this.keyword=keyword;
    }


    @Column(name="description",nullable = true, length=200)
    public String  getDescription(){
       return description;
    }

    public  void  setDescription(String description){
       this.description=description;
    }


    @Column(name="duration",nullable = true, length=20)
    public String  getDuration(){
       return duration;
    }

    public  void  setDuration(String duration){
       this.duration=duration;
    }

    @Column(name="sum_score",nullable = true, length=10)
    public Integer getSumScore() {
		return sumScore;
	}

	public void setSumScore(Integer sumScore) {
		this.sumScore = sumScore;
	}

    @Column(name="entry",nullable = true, length=40)
    public String  getEntry(){
       return entry;
    }

    public  void  setEntry(String entry){
       this.entry=entry;
    }


    @Column(name="client",nullable = true, length=40)
    public String  getClient(){
       return client;
    }

    public  void  setClient(String client){
       this.client=client;
    }


    @Column(name="version",nullable = false, length=20)
    public String  getVersion(){
       return version;
    }

    public  void  setVersion(String version){
       this.version=version;
    }


    @Column(name="language",nullable = true, length=20)
    public String  getLanguage(){
       return language;
    }

    public  void  setLanguage(String language){
       this.language=language;
    }


    @Column(name="difficulty",nullable = true, length=10)
    public Integer  getDifficulty(){
       return difficulty;
    }

    public  void  setDifficulty(Integer difficulty){
       this.difficulty=difficulty;
    }

    @Column(name="fileurl",nullable = false, length=200)
    public String  getFileurl(){
       return fileurl;
    }

    public  void  setFileurl(String fileurl){
       this.fileurl=fileurl;
    }

    @Column(name="is_enable",nullable = true, length=10)
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

    @Column(name="pass_condition",nullable = false, length=10)
	public Integer getPassCondition() {
		return passCondition;
	}

	public void setPassCondition(Integer passCondition) {
		this.passCondition = passCondition;
	}

    @Column(name="complete_condition",nullable = false, length=10)
	public Integer getCompleteCondition() {
		return completeCondition;
	}

	public void setCompleteCondition(Integer completeCondition) {
		this.completeCondition = completeCondition;
	}

}
