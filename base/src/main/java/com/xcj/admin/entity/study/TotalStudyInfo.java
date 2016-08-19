package com.xcj.admin.entity.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name="TotalStudyInfo")
@Table(name="total_study_info")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_total_study_info")
public class TotalStudyInfo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8275268884113840623L;
	
	private String courseNumber;
	private String domainUsername;
	private String characterA;
	private String characterB;
	private String characterC;
	private String userEmail;
	
	private Float topProgress;
	private Float topScore;
	private Integer seconds;
	private Integer isComplete;
	private Integer isPass;
	
	private Date createDate;
	private Date modifyDate;
	private Date completeTime;
	private Date passTime;
	private Integer frequency;
	
	private String faultNumber;
	private String studyFlag;
	
	@Column(name="study_flag",nullable = true,length=499)
	public String getStudyFlag() {
		return studyFlag;
	}
	public void setStudyFlag(String studyFlag) {
		this.studyFlag = studyFlag;
	}
	
	@Column(name="fault_number",nullable = true,length=39)
	public String getFaultNumber() {
		return faultNumber;
	}
	public void setFaultNumber(String faultNumber) {
		this.faultNumber = faultNumber;
	}
	
	@Column(name="frequency",nullable = true,length=10) 
	public Integer getFrequency() {
		return frequency;
	}
	 
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
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
	
	@Column(name="course_number",nullable = false, length=40)
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}
	@Column(name="character_a",nullable = true)
	public String getCharacterA() {
		return characterA;
	}
	public void setCharacterA(String characterA) {
		this.characterA = characterA;
	}
	@Column(name="character_b",nullable = true)
	public String getCharacterB() {
		return characterB;
	}
	public void setCharacterB(String characterB) {
		this.characterB = characterB;
	}
	@Column(name="character_c",nullable = true)
	public String getCharacterC() {
		return characterC;
	}
	public void setCharacterC(String characterC) {
		this.characterC = characterC;
	}
	@Column(name="user_email",nullable = false, length=30)
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Column(name="top_progress",nullable = false, length=11)
	public Float getTopProgress() {
		return topProgress;
	}
	public void setTopProgress(Float topProgress) {
		this.topProgress = topProgress;
	}
	@Column(name="top_score",nullable = false, length=11)
	public Float getTopScore() {
		return topScore;
	}
	public void setTopScore(Float topScore) {
		this.topScore = topScore;
	}
	@Column(name="seconds",nullable = false, length=11)
	public Integer getSeconds() {
		return seconds;
	}
	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
	@Column(name="is_complete",nullable = false, length=11)
	public Integer getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}
	@Column(name="is_pass",nullable = false, length=11)
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	@Column(name="create_date",nullable = false, length=19)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	 @Column(name="modify_date",nullable = false, length=19)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name="domain_username",nullable = false, length=20)
	public String getDomainUsername() {
		return domainUsername;
	}
	public void setDomainUsername(String domainUsername) {
		this.domainUsername = domainUsername;
	}
	
}
