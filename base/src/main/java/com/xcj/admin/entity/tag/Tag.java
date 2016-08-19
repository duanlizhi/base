package com.xcj.admin.entity.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.xcj.admin.base.BaseEntity;

@Entity(name = "Tag")
@Table(name = "tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "secquence_tag")
public class Tag extends BaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = -2723529417173182047L;

	private String name;// 标签名称

	private String icon;// 图标

	private String tagNumber;// 标签编号

	private Integer orders;// 权重等级

	private java.util.Date createDate;// 创建时间

	private java.util.Date modifyDate;// 修改时间

	@Column(name = "name", nullable = false, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "icon", nullable = true, length = 100)
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "tag_number", nullable = false, length = 40)
	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	@Column(name = "orders", nullable = false, length = 10)
	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
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
