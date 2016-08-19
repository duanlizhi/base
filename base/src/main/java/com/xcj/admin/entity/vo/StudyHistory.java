package com.xcj.admin.entity.vo;

import java.util.Date;

public class StudyHistory {
	
	private Integer id;
	private String courseName;
	private String domainName;
	private String completeTime;
	private String passTime;
	private String characterA;
	private String characterB;
	private String characterC;
	
	private String userEmail;
	private Integer topProgress;
	private Integer topScore;
	private Integer seconds;
	private Integer isComplete;
	private Integer isPass;
	
	private Date createDate;
	private Date modifyDate;
	private String sessionId;
	
	private String type;
	private String url;
	
	private String faultNumber;
	private String studyFlag;
	
	public String getFaultNumber() {
		return faultNumber;
	}
	public void setFaultNumber(String faultNumber) {
		this.faultNumber = faultNumber;
	}
	public String getStudyFlag() {
		return studyFlag;
	}
	public void setStudyFlag(String studyFlag) {
		this.studyFlag = studyFlag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

	public String getCharacterA() {
		return characterA;
	}

	public void setCharacterA(String characterA) {
		this.characterA = characterA;
	}

	public String getCharacterB() {
		return characterB;
	}

	public void setCharacterB(String characterB) {
		this.characterB = characterB;
	}

	public String getCharacterC() {
		return characterC;
	}

	public void setCharacterC(String characterC) {
		this.characterC = characterC;
	}


	public Integer getTopProgress() {
		return topProgress;
	}

	public void setTopProgress(Integer topProgress) {
		this.topProgress = topProgress;
	}

	public Integer getTopScore() {
		return topScore;
	}

	public void setTopScore(Integer topScore) {
		this.topScore = topScore;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainName() {
		return domainName;
	}

	@Override
	public String toString() {
		return "StudyHistory [characterA=" + characterA + ", characterB="
				+ characterB + ", characterC=" + characterC + ", completeTime="
				+ completeTime + ", courseName=" + courseName + ", createDate="
				+ createDate + ", domainName=" + domainName + ", id=" + id
				+ ", isComplete=" + isComplete + ", isPass=" + isPass
				+ ", modifyDate=" + modifyDate + ", passTime=" + passTime
				+ ", seconds=" + seconds + ", topProgress=" + topProgress
				+ ", topScore=" + topScore + ", type=" + type + ", userEmail="
				+ userEmail + "]";
	}

}
