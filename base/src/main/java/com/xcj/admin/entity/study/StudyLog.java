package com.xcj.admin.entity.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name="StudyLog")
@Table(name="study_log")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_study_log")
public class StudyLog extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5797234365643669511L;
	
	private String courseNumber;
	private String domainUsername;
	private String sessionId;
	private String receive;
	private String characterA;
	private String characterB;
	private String characterC;
	private String send;

	private String userEmail;
	
	private Date modifyDate;
	private Date createDate;
	
	private Integer actionTime;
	private Float progress;
	private Float score;
	private Integer isComplete;
	private Integer isPass;
	
	private Date completeTime;
	private Date passTime;
	private Date startTime;
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
	
	@Column(name="session_id",nullable = false, length=50)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name="action_time",nullable = false, length=11)
	public Integer getActionTime() {
		return actionTime;
	}
	public void setActionTime(Integer actionTime) {
		this.actionTime = actionTime;
	}
	@Column(name="receive",nullable = false, length=2000)
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
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
	@Column(name="send",nullable = false, length=2000)
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	@Column(name="user_email",nullable = false, length=30)
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Column(name="modify_date",nullable = false, length=19)
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name="create_date",nullable = false, length=19)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="progress",nullable = true, length=11)
	public Float getProgress() {
		return progress;
	}
	public void setProgress(Float progress) {
		this.progress = progress;
	}
	@Column(name="score",nullable = true, length=11)
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
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
	@Column(name="domain_username",nullable = false, length=20)
	public String getDomainUsername() {
		return domainUsername;
	}
	public void setDomainUsername(String domainUsername) {
		this.domainUsername = domainUsername;
	}
	@Column(name="start_time",nullable = true,length=19)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		return "StudyLog [courseNumber=" + courseNumber + ", domainUsername="
				+ domainUsername + ", sessionId=" + sessionId + ", receive="
				+ receive + ", characterA=" + characterA + ", characterB="
				+ characterB + ", characterC=" + characterC + ", send=" + send
				+ ", userEmail=" + userEmail + ", modifyDate=" + modifyDate
				+ ", createDate=" + createDate + ", actionTime=" + actionTime
				+ ", progress=" + progress + ", score=" + score
				+ ", isComplete=" + isComplete + ", isPass=" + isPass
				+ ", completeTime=" + completeTime + ", passTime=" + passTime
				+ ", startTime=" + startTime + ", faultNumber=" + faultNumber
				+ ", studyFlag=" + studyFlag + "]";
	}
	
}
