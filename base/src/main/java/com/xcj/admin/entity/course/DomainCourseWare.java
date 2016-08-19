/**
 * 
 */
package com.xcj.admin.entity.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.admin.entity.course  
 * @fileName com.xcj.*
 * @createDate 2015-3-3 上午09:49:32 
 * @author hehujun 
 * @email hehujun@126.com
 */
@Entity(name="DomainCourseWare")
@Table(name="domain_course_ware")
@SequenceGenerator(name="sequenceGenerator",sequenceName="secquence_domain_course_ware")
public class DomainCourseWare extends BaseEntity{

	private static final long serialVersionUID = -1159872034470757580L;

	private Integer courseWareId;
	
	private Integer domainId;

	@Column(name="course_ware_id", nullable=false)
	public Integer getCourseWareId() {
		return courseWareId;
	}

	public void setCourseWareId(Integer courseWareId) {
		this.courseWareId = courseWareId;
	}

	@Column(name="domain_id", nullable=false)
	public Integer getDomainId() {
		return domainId;
	}

	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}
}
