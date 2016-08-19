package com.xcj.admin.entity.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name = "AdminLog")
@Table(name = "admin_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_admin_log")
public class AdminLog extends BaseEntity implements java.io.Serializable {
	/** "日志内容"属性名称 */
	public static final String LOG_CONTENT_PROPERTIES_NAME = AdminLog.class.getName()+ ".CONTENT";
	private static final long serialVersionUID = 1L;
	private java.util.Date createDate;//

	private java.util.Date modifyDate;//

	private String content;//

	private String ip;//

	private String operation;//

	private String operator;//

	private String parameter;//

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

	@Column(name = "content", nullable = true, length = 2147483647)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ip", nullable = false, length = 255)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "operation", nullable = false, length = 255)
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name = "operator", nullable = true, length = 255)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "parameter", nullable = true, length = 2147483647)
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
