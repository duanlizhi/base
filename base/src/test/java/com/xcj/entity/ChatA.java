/** 
  *<dl>
  *<dt><span class="strong">方法说明:</span></dt>
  *<dd>主要是实现了什么功能</dd>
  *</dl> 
  *<dl><dt><span class="strong">创建时间:</span></dt>
  *<dd> 2016年3月1日 下午4:13:59</dd></dl> 
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
 *<dd> 2016年3月1日 下午4:13:59</dd></dl> 
 *</dl> 
 *@param <T> 对象类型 
 *@param entity 对象 
 *@return boolean true/false
 *@throws Exception  XX异常
 */
public class ChatA {
	private List<Progress> progress;
	
	private List<Score> score;
	
	private List<Handle> handle;
	public List<Progress> getProgress() {
		return progress;
	}

	public void setProgress(List<Progress> progress) {
		this.progress = progress;
	}

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}

	public List<Handle> getHandle() {
		return handle;
	}

	public void setHandle(List<Handle> handle) {
		this.handle = handle;
	}
}
