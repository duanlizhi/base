package com.xcj.common.top;

import java.util.ArrayList;
import java.util.List;

public class Meau implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;//

	private String url;//

	private String parentId;
	private String level;//
	private List<Meau> list = new ArrayList<Meau>();

	public List<Meau> getList() {
		return list;
	}

	public void setList(List<Meau> list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
