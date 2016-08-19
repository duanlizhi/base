package com.xcj.admin.entity.tests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;

@Entity(name = "TestsPosted")
@Table(name = "tests_posted")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_tests_posted")
public class TestsPosted extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2896986000919418008L;

	private String testName;// 试卷名称

	private java.util.Date releaseTime;// 发布时间

	private java.util.Date answerTime;// 答题时间

	private java.util.Date startTime;// 开始时间

	private java.util.Date endTime;// 结束时间

	private String publisher;// 发布人

	private Integer paperId;// 试卷Id

	private java.util.Date createDate;// 创建时间

	private java.util.Date modifyDate;// 修改时间

	private String version;// 版本

	private Integer scoreType;// 计分方式

	private Integer limitCount;// 限制次数

	private Integer score;//分数
	
	private String kp;//知识点
	
	private String kpScore;//知识点成绩
	
	@Column(name = "kp_score", nullable = true, length = 255)
	public String getKpScore() {
		return kpScore;
	}

	public void setKpScore(String kpScore) {
		this.kpScore = kpScore;
	}

	@Column(name = "kp", nullable = true, length = 255)
	public String getKp() {
		return kp;
	}

	public void setKp(String kp) {
		this.kp = kp;
	}

	@Column(name = "score", nullable = false, length = 10)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "test_name", nullable = false, length = 100)
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Column(name = "release_time", nullable = false, length = 19)
	public java.util.Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(java.util.Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Column(name = "answer_time", nullable = false, length = 19)
	public java.util.Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(java.util.Date answerTime) {
		this.answerTime = answerTime;
	}

	@Column(name = "start_time", nullable = false, length = 19)
	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", nullable = false, length = 19)
	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "publisher", nullable = false, length = 100)
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Column(name = "paper_id", nullable = false, length = 10)
	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	@Column(name = "create_date", nullable = false, length = 19)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "modify_date", nullable = false, length = 19)
	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "version", nullable = true, length = 200)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "scoreType", nullable = true, length = 10)
	public Integer getScoreType() {
		return scoreType;
	}

	public void setScoreType(Integer scoreType) {
		this.scoreType = scoreType;
	}

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

}
