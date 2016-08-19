/**
 * 
 */
package com.xcj.common.file;

import java.util.Date;

/** 
 * <b>function:</b> 文件信息的类
 * @project base_frame 
 * @package com.xcj.common.file  
 * @fileName com.xcj.*
 * @createDate May 28, 2014 1:55:14 PM 
 * @author su_jian 
 */
public class FileInfo {
	/**
	 * 文件类型
	 */
	public enum FileType {

		/** 图片 */
		image,

		/** Flash */
		flash,

		/** 媒体 */
		media,

		/** 文件 */
		file
	}
	
	/**
	 * 排序类型
	 */
	public enum OrderType {

		/** 名称 */
		name,

		/** 大小 */
		size,

		/** 类型 */
		type
	}
	
	/** 名称 */
	private String filename;

	/** URL */
	private String url;
	private boolean is_photo;

	/** 是否为目录 */
	private Boolean is_dir;

	/** 大小 */
	private Long filesize;

	/** 最后修改日期 */
	private Date datetime;
	
	private String filetype;
	//是否有文件
	private boolean has_file;
	/**
	 * @return the fileName
	 */
 
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the is_photo
	 */
	public boolean isIs_photo() {
		return is_photo;
	}
	/**
	 * @param is_photo the is_photo to set
	 */
	public void setIs_photo(boolean is_photo) {
		this.is_photo = is_photo;
	}
	/**
	 * @return the is_dir
	 */
	public Boolean getIs_dir() {
		return is_dir;
	}
	/**
	 * @param is_dir the is_dir to set
	 */
	public void setIs_dir(Boolean is_dir) {
		this.is_dir = is_dir;
	}
	/**
	 * @return the filesize
	 */
	public Long getFilesize() {
		return filesize;
	}
	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}
	/**
	 * @return the datetime
	 */
	public Date getDatetime() {
		return datetime;
	}
	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	/**
	 * @return the has_file
	 */
	public boolean isHas_file() {
		return has_file;
	}
	/**
	 * @param has_file the has_file to set
	 */
	public void setHas_file(boolean has_file) {
		this.has_file = has_file;
	}
	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}
	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
