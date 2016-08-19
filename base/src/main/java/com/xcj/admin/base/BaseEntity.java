package com.xcj.admin.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -5477219666413445210L;
	public Integer id;
	@Id
	// MySQL/SQLServer: @GeneratedValue(strategy = GenerationType.AUTO)
	// Oracle: @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
