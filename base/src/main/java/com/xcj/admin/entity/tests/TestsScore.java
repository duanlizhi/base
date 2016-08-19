package com.xcj.admin.entity.tests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;

@Entity(name = "TestsScore")
@Table(name = "tests_score")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_tests_score")
public class TestsScore extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 5418619651801179655L;

	private String answerMan;// 答题人

	private String score;// 得分

	private java.util.Date startTime;// 开始时间

	private java.util.Date endTime;// 结束时间

	private Integer testsPostedId;// 外键

	private java.util.Date createDate;// 创建时间

	private java.util.Date modifyDate;// 修改时间

	@Column(name = "answer_man", nullable = true, length = 40)
	public String getAnswerMan() {
		return answerMan;
	}

	public void setAnswerMan(String answerMan) {
		this.answerMan = answerMan;
	}

	@Column(name = "score", nullable = true, length = 10)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	@Column(name = "tests_posted_id", nullable = true, length = 10)
	public Integer getTestsPostedId() {
		return testsPostedId;
	}

	public void setTestsPostedId(Integer testsPostedId) {
		this.testsPostedId = testsPostedId;
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

}
