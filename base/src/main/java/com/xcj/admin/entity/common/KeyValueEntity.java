/**
 * 
 */
package com.xcj.admin.entity.common;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.admin.entity.common  
 * @fileName com.xcj.*
 * @createDate May 30, 2014 5:03:36 PM 
 * @author su_jian 
 */
public class KeyValueEntity implements java.io.Serializable{
	private Integer id;
	private String name;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
