package com.xcj.admin.entity.tests;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xcj.admin.base.BaseEntity;
import com.xcj.admin.entity.course.CourseWare;

@Entity(name = "Paper")
@Table(name = "paper")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_paper")
public class Paper extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 6330402458042478842L;

	private String paperName;// 试卷名称

	private Integer questionSize;// 题数量

	private Integer answerNumber;// 答题次数

	private String papersType;// 试卷类型

	private Integer score;// 分数

	private Integer state;// 状态

	private java.util.Date modifyDate;// 修改时间

	private java.util.Date createDate;// 数据创建时间

	private String description;//描述

	private String version;//版本

	private Integer totalTime;//时长
	
	private Boolean flag;//页面展示用

	private List<CourseWare> courseWareList;//试卷里的courseWare集合
	
	@Transient
	public List<CourseWare> getCourseWareList() {
		return courseWareList;
	}

	public void setCourseWareList(List<CourseWare> courseWareList) {
		this.courseWareList = courseWareList;
	}
		
	@Transient
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Column(name = "paper_name", nullable = false, length = 100)
	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	@Column(name = "question_size", nullable = false, length = 10)
	public Integer getQuestionSize() {
		return questionSize;
	}

	public void setQuestionSize(Integer questionSize) {
		this.questionSize = questionSize;
	}

	@Column(name = "answer_number", nullable = false, length = 10)
	public Integer getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}

	@Column(name = "papers_type", nullable = false, length = 20)
	public String getPapersType() {
		return papersType;
	}

	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}

	@Column(name = "score", nullable = false, length = 10)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "state", nullable = false, length = 10)
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "modify_date", nullable = false, length = 19)
	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name = "create_date", nullable = false, length = 19)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "description", nullable = true, length = 500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "version", nullable = true, length = 100)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "total_time", nullable = true, length = 10)
	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

}
