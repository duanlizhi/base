package com.xcj.admin.entity.knowledgePoint;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.xcj.admin.base.BaseEntity;

@Entity(name = "KnowledgePoint")
@Table(name = "knowledge_point")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_knowledge_point")
public class KnowledgePoint extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -5549550922140607025L;

	private Integer grade;// 等级
	
	private String code;//编码
	
	private String name;// name

	private Integer rank;// 排序

	private String url;// 链接

	private KnowledgePoint knowledgePoint;// 父节点

	private Integer iconid;// 图标

	private java.util.Date createDate;// 创建日期

	private java.util.Date modifyDate;// 修改日期

	private List<KnowledgePoint> knowledgePoints = new ArrayList<KnowledgePoint>();//孩子节点
	
	private String score;
	
	@Transient
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "knowledgePoint")
	public List<KnowledgePoint> getKnowledgePoints() {
		return knowledgePoints;
	}

	public void setKnowledgePoints(List<KnowledgePoint> knowledgePoints) {
		this.knowledgePoints = knowledgePoints;
	}

	@Column(name = "grade", nullable = true, length = 10)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "code", nullable = true, length = 10)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "name", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "rank", nullable = true, length = 10)
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "url", nullable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "iconid", nullable = true, length = 10)
	public Integer getIconid() {
		return iconid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", nullable = true)
	public KnowledgePoint getKnowledgePoint() {
		return knowledgePoint;
	}

	public void setKnowledgePoint(KnowledgePoint knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}

	public void setIconid(Integer iconid) {
		this.iconid = iconid;
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
