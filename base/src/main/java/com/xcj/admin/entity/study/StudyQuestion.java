/**
 * 
 */
package com.xcj.admin.entity.study;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.entity.study  
 * @fileName com.xcj.*
 * @createDate 2015-3-6 下午02:11:53 
 * @author hehujun 
 * @email hehujun@126.com
 */
@Entity(name="StudyQuestion")
@Table(name="study_question")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_study_question")
public class StudyQuestion extends BaseEntity {

	private static final long serialVersionUID = -3199127579170991911L;

	private String question;
	private String answer;
	private String description;
	private String questionId;
	
	private Integer rank;
	private Integer studyResultId;
	
	private Date modifyDate;
	private Date createDate;
	
	@Column(name="question",nullable = true,length=200)
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(name="answer",nullable = true,length=200)
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Column(name="description",nullable = true,length=500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="question_id",nullable = true,length=20)
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	@Column(name="rank",nullable = false,length=11)
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	@Column(name="study_result_id",nullable = false,length=11)
	public Integer getStudyResultId() {
		return studyResultId;
	}
	public void setStudyResultId(Integer studyResultId) {
		this.studyResultId = studyResultId;
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
}
