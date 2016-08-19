package com.xcj.admin.entity.tests;
import java.util.Date;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;
@Entity(name="TestLog")
@Table(name="test_log")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_test_log")
public class TestLog extends BaseEntity  implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
    private String  courseNumber;//课件编号

    private String  userEmail;//用户邮箱

    private String  domainUsername;//域编号

    private String  sessionId;//会话id

    private String  testsPublicId;//一次考试唯一标示

    private String  receive;//接受内容

    private String  characterA;//状态字符串

    private String  characterB;//状态字符串

    private String  characterC;//状态字符串

    private String  send;//发送 内容

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private Integer  actionTime;//本次通信时间

    private Integer  isComplete;//是否完成

    private Integer  isPass;//是否通过

    private java.util.Date  completeTime;//完成时间

    private java.util.Date  passTime;//通过时间

    private Float  progress;//进度

    private Float  score;//得分
    
    private Integer testsPostedId;// 发布测评id
    
    private Date startTime;

    @Column(name="course_number",nullable = false, length=40)
    public String  getCourseNumber(){
       return courseNumber;
    }

    public  void  setCourseNumber(String courseNumber){
       this.courseNumber=courseNumber;
    }


    @Column(name="user_email",nullable = false, length=40)
    public String  getUserEmail(){
       return userEmail;
    }

    public  void  setUserEmail(String userEmail){
       this.userEmail=userEmail;
    }


    @Column(name="domain_username",nullable = false, length=40)
    public String  getDomainUsername(){
       return domainUsername;
    }

    public  void  setDomainUsername(String domainUsername){
       this.domainUsername=domainUsername;
    }


    @Column(name="session_id",nullable = false, length=50)
    public String  getSessionId(){
       return sessionId;
    }

    public  void  setSessionId(String sessionId){
       this.sessionId=sessionId;
    }


    @Column(name="tests_public_id",nullable = false, length=40)
    public String  getTestsPublicId(){
       return testsPublicId;
    }

    public  void  setTestsPublicId(String testsPublicId){
       this.testsPublicId=testsPublicId;
    }


	@Column(name="start_time",nullable = false, length=19)
    public Date  getStartTime(){
       return startTime;
    }

    public void setStartTime(Date startTime){
       this.startTime=startTime;
    }


    @Column(name="action_time",nullable = false, length=20)
    public Integer  getActionTime(){
       return actionTime;
    }

    public  void  setActionTime(Integer actionTime){
       this.actionTime=actionTime;
    }


    @Column(name="receive",nullable = false, length=2000)
    public String  getReceive(){
       return receive;
    }

    public  void  setReceive(String receive){
       this.receive=receive;
    }


    @Column(name="character_a",nullable = true)
    public String  getCharacterA(){
       return characterA;
    }

    public  void  setCharacterA(String characterA){
       this.characterA=characterA;
    }


    @Column(name="character_b",nullable = true)
    public String  getCharacterB(){
       return characterB;
    }

    public  void  setCharacterB(String characterB){
       this.characterB=characterB;
    }


    @Column(name="character_c",nullable = true)
    public String  getCharacterC(){
       return characterC;
    }

    public  void  setCharacterC(String characterC){
       this.characterC=characterC;
    }


    @Column(name="send",nullable = false, length=2000)
    public String  getSend(){
       return send;
    }

    public  void  setSend(String send){
       this.send=send;
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


    @Column(name="is_complete",nullable = false, length=10)
    public Integer  getIsComplete(){
       return isComplete;
    }

    public  void  setIsComplete(Integer isComplete){
       this.isComplete=isComplete;
    }


    @Column(name="is_pass",nullable = false, length=10)
    public Integer  getIsPass(){
       return isPass;
    }

    public  void  setIsPass(Integer isPass){
       this.isPass=isPass;
    }


    @Column(name="complete_time",nullable = true, length=19)
    public java.util.Date  getCompleteTime(){
       return completeTime;
    }

    public  void  setCompleteTime(java.util.Date completeTime){
       this.completeTime=completeTime;
    }


    @Column(name="pass_time",nullable = true, length=19)
    public java.util.Date  getPassTime(){
       return passTime;
    }

    public  void  setPassTime(java.util.Date passTime){
       this.passTime=passTime;
    }


    @Column(name="progress",nullable = true, length=10)
    public Float  getProgress(){
       return progress;
    }

    public  void  setProgress(Float progress){
       this.progress=progress;
    }


    @Column(name="score",nullable = true, length=10)
    public Float  getScore(){
       return score;
    }

    public  void  setScore(Float score){
       this.score=score;
    }
    
    @Column(name = "tests_posted_id", nullable = false, length = 10)
	public Integer getTestsPostedId() {
		return testsPostedId;
	}

	public void setTestsPostedId(Integer testsPostedId) {
		this.testsPostedId = testsPostedId;
	}

}
