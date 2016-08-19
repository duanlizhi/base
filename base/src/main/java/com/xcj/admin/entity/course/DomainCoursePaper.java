package com.xcj.admin.entity.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name="DomainCoursePaper")
@Table(name="domain_course_paper")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_domain_course_paper")
public class DomainCoursePaper extends BaseEntity {

	private static final long serialVersionUID = 1603661117641005937L;

	private Integer coursePaperId;
	
	private Integer domainId;

	@Column(name="course_paper_id", nullable=false)
	public Integer getCoursePaperId() {
		return coursePaperId;
	}

	public void setCoursePaperId(Integer coursePaperId) {
		this.coursePaperId = coursePaperId;
	}

	@Column(name="domain_id", nullable=false)
	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

}
