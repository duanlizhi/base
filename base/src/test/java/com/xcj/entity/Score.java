/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年3月1日 下午4:15:08</dd></dl> 
  *</dl> 
  *@param <T> 对象类型 
  *@param entity 对象 
  *@return boolean true/false
  *@throws Exception  XX异常
  */
package com.xcj.entity;

import java.util.List;

/** 
 *<dl>
 *<dt><span class="strong">方法说明:</span></dt>
 *<dd>主要是实现了什么功能</dd>
 *</dl> 
 *<dl><dt><span class="strong">创建时间:</span></dt>
 *<dd> 2016年3月1日 下午4:15:08</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class Score {
	private Integer id;
	private String isRight;
	private String questionID;
	private List<Option> option;
	private List<HandleS> handle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIsRight() {
		return isRight;
	}
	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public List<Option> getOption() {
		return option;
	}
	public void setOption(List<Option> option) {
		this.option = option;
	}
	public List<HandleS> getHandle() {
		return handle;
	}
	public void setHandle(List<HandleS> handle) {
		this.handle = handle;
	}
	

}
