/** 
  * <dl>
  * <dt><span class="strong">类说明:</span></dt>
  * <dd>主要是实现了什么功能</dd>
  * </dl> 
  * <dl><dt><span class="strong">逻辑说明:</span></dt>
  *  <dd>1、xx(处理逻辑是什么样子的)</dd>
  *  <dd>2、xx</dd>
  *  <dd>3、xx</dd></dl>  
  *  <dl><dt><span class="strong">创建时间:</span></dt>
  *  <dd> 2015年10月14日 上午9:24:25</dd></dl> 
  * </dl> 
  * @author su_jian
  * @see {@linkplain java.lang.String }
  * @since 1.0
  */
package com.xcj.admin.entity.knowledgePoint;

import com.xcj.admin.base.BaseEntity;

/** 
 * <dl>
 * <dt><span class="strong">类说明:</span></dt>
 * <dd>主要是实现了什么功能</dd>
 * </dl> 
 * <dl><dt><span class="strong">逻辑说明:</span></dt>
 *  <dd>1、xx(处理逻辑是什么样子的)</dd>
 *  <dd>2、xx</dd>
 *  <dd>3、xx</dd></dl>  
 *  <dl><dt><span class="strong">创建时间:</span></dt>
 *  <dd> 2015年7月31日 上午10:06:36</dd></dl> 
 * </dl> 
 * @author su_jian
 * @see {@linkplain java.lang.String }
 * @since 1.0
 */
public class KnowledgePointVO extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = -5549550922140607025L;

	private Integer grade;// 等级
	
	private String code;//编码
	
	private String name;// name

	private Integer rank;// 排序

	private String url;// 链接
	private Integer pid;

	private Integer iconid;// 图标

	private java.util.Date createDate;// 创建日期

	private java.util.Date modifyDate;// 修改日期
	
	private String score;

	public Integer getGrade() {
		return grade;
	}

 


	public Integer getPid() {
		return pid;
	}




	public void setPid(Integer pid) {
		this.pid = pid;
	}




	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIconid() {
		return iconid;
	}

	public void setIconid(Integer iconid) {
		this.iconid = iconid;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	
}
