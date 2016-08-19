/**
 * 
 */
package com.xcj.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/** 
 * <b>function:</b> 
 * @project base_frame 
 * @package com.xcj.common.service  
 * @fileName com.xcj.*
 * @createDate May 28, 2014 1:57:50 PM 
 * @author su_jian 
 */
public interface FileService {

	 /**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.service  
	   * @fileName com.xcj.*** 
	   * @createDate May 28, 2014 1:58:46 PM
	   * @return List<FileInfo>
	   * @author su_jian
	   */
	JSONObject browser(String path, String fileType, String order,String dir);

	 /**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.service  
	   * @fileName com.xcj.*** 
	   * @createDate May 29, 2014 1:31:34 PM
	   * @return boolean
	   * @author su_jian
	   */
	boolean isValid(String dir, MultipartFile file);

	 /**
	   * <b>function:</b> 
	   * @project base_frame
	   * @package com.xcj.common.service  
	   * @fileName com.xcj.*** 
	   * @createDate May 29, 2014 1:47:48 PM
	   * @return String
	   * @author su_jian
	   */
	String upload(String dir, MultipartFile file, boolean b);

}
