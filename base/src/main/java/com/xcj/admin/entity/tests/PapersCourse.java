package com.xcj.admin.entity.tests;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.xcj.admin.base.BaseEntity;

@Entity(name = "PapersCourse")
@Table(name = "papers_course")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_papers_course")
public class PapersCourse extends BaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 1290943492397897462L;

	private Integer paperId;//外键id

	private Integer courseId;//外键id
	
	private Integer score;//分数

	@Column(name = "paper_id", nullable = true, length = 10)
	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	@Column(name = "course_id", nullable = true, length = 10)
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "score", nullable = true, length = 10)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
}
