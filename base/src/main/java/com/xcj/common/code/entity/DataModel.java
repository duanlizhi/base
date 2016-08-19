/**
 * 
 */
package com.xcj.common.code.entity;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.common.code.entity  
 * @fileName com.xcj.*
 * @createDate Apr 16, 2014 4:53:45 PM 
 * @author su_jian 
 */
public class DataModel {
	private  String fieldname;
	private String fieldtype;
	private Integer fieldLength;
	private String remarks;
	private boolean nullable;
	/**
	 * @return the fieldname
	 */
	public String getFieldname() {
		return fieldname;
	}
	/**
	 * @param fieldname the fieldname to set
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	/**
	 * @return the fieldtype
	 */
	public String getFieldtype() {
		return fieldtype;
	}
	/**
	 * @param fieldtype the fieldtype to set
	 */
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	/**
	 * @return the fieldLength
	 */
	public Integer getFieldLength() {
		return fieldLength;
	}
	/**
	 * @param fieldLength the fieldLength to set
	 */
	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the nullable
	 */
	public boolean getNullable() {
		return nullable;
	}
	/**
	 * 重写Set方法设置 数字转换为 true false;
	 */
	public void setNullable(Integer nullable) {
		if(nullable==0){
			this.nullable=false;
		}else{
			this.nullable=true;
		}
	}
}
