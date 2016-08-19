/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年3月1日 下午4:55:46</dd></dl> 
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
 *<dd> 2016年3月1日 下午4:55:46</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class Option {
	private Integer id;
	private List<Option> option;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Option> getOption() {
		return option;
	}

	public void setOption(List<Option> option) {
		this.option = option;
	}
	
}
