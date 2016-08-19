package com.xcj.admin.entity.course;
import java.util.Date;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;
@Entity(name="StudyResult")
@Table(name="study_result")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_study_result")
public class StudyResult extends BaseEntity  implements java.io.Serializable{
    /**
		 * 
	     * <b>function:</b> 读取SBT故障
	     * @project base
	     * @package com.xcj.common.util  
	     * @fileName @return
	     * @createDate February 16, 2015 3:05:04 PM
	     * @author yy.niu
		 */
	private static final long serialVersionUID = 385059621343990160L;

	private String  courseNumber;//课件编号

    private String  userEmail;//用户邮箱

    private String  sessionId;//会话id

    private Date  startTime;//会话开始时间

    private Integer  progress;//进度

    private Integer  score;//得分

    private Integer  seconds;//本次学习时间合计

    private String  characterA;//状态字符串编码

    private String  characterB;//状态字符串编码

    private String  characterC;//状态字符串编码

    private Integer  isComplete;//是否完成

    private Integer  isPass;//是否通过

    private java.util.Date  createDate;//创建时间

    private java.util.Date  modifyDate;//修改时间

    private String domainUsername;//域账号
    
	private Date completeTime; // 通过时间
	private Date passTime;	// 完成时间

	@Column(name="domain_username",nullable = false, length=20)
	public String getDomainUsername() {
		return domainUsername;
	}
	
	public void setDomainUsername(String domainUsername) {
		this.domainUsername = domainUsername;
	}
    @Column(name="user_email",nullable = false, length=30)
    public String  getUserEmail(){
       return userEmail;
    }

    @Column(name="course_number",nullable = false, length=30)
	public String getCourseNumber() {
		return courseNumber;
	}

	/**
	 * @param courseNumber the courseNumber to set
	 */
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public  void  setUserEmail(String userEmail){
       this.userEmail=userEmail;
    }


    @Column(name="session_id",nullable = false, length=40)
    public String  getSessionId(){
       return sessionId;
    }

    public  void  setSessionId(String sessionId){
       this.sessionId=sessionId;
    }


    @Column(name="progress",nullable = false, length=10)
    public Integer  getProgress(){
       return progress;
    }

    public  void  setProgress(Integer progress){
       this.progress=progress;
    }


    @Column(name="score",nullable = false, length=10)
    public Integer  getScore(){
       return score;
    }

    public  void  setScore(Integer score){
       this.score=score;
    }


    @Column(name="seconds",nullable = false, length=10)
    public Integer  getSeconds(){
       return seconds;
    }

    public  void  setSeconds(Integer seconds){
       this.seconds=seconds;
    }


    @Column(name="character_a",nullable = true, length=4000)
    public String  getCharacterA(){
       return characterA;
    }

    public  void  setCharacterA(String characterA){
       this.characterA=characterA;
    }


    @Column(name="character_b",nullable = true, length=4000)
    public String  getCharacterB(){
       return characterB;
    }

    public  void  setCharacterB(String characterB){
       this.characterB=characterB;
    }


    @Column(name="character_c",nullable = true, length=4000)
    public String  getCharacterC(){
       return characterC;
    }

    public  void  setCharacterC(String characterC){
       this.characterC=characterC;
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

    
	@Column(name="complete_time",nullable = true,length=19)
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	@Column(name="pass_time",nullable = true,length=19)
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	@Column(name="start_time",nullable = false, length=19)
    public Date  getStartTime(){
       return startTime;
    }

    public  void  setStartTime(Date startTime){
       this.startTime=startTime;
    }

}
