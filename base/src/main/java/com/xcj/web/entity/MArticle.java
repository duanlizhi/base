/**
 * 
 */
package com.xcj.web.entity;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.web.entity  
 * @fileName com.xcj.*
 * @createDate Dec 6, 2014 6:06:24 PM 
 * @author su_jian 
 */
public class MArticle {
	private Integer id;
	 private String  title;//标题

    private String  subtitle;//子标题
    
    private String  image;//图像
    private Integer  hits;//点击 次数
    private String staticPath;
    private String qrcodePath;
    private String modifyDate;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the hits
	 */
	public Integer getHits() {
		return hits;
	}
	/**
	 * @param hits the hits to set
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	/**
	 * @return the staticPath
	 */
	public String getStaticPath() {
		return staticPath;
	}
	/**
	 * @param staticPath the staticPath to set
	 */
	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}
	/**
	 * @return the qrcodePath
	 */
	public String getQrcodePath() {
		return qrcodePath;
	}
	/**
	 * @param qrcodePath the qrcodePath to set
	 */
	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}
	/**
	 * @return the modifyDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * @param modifyDate the modifyDate to set
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
