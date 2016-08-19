/**
 * 
 */
package com.xcj.common.service;

import java.util.HashMap;

/** 
 * <b>function:</b> 一些常用变量以及其他常用信息处理类
 * @project base_frame 
 * @package com.xcj.common.service.impl  
 * @fileName com.xcj.*
 * @createDate May 9, 2014 10:19:05 AM 
 * @author su_jian 
 */
public interface CommonService {
	/**
	 * <b>function:</b> 常用一些设置信息的获取
	 * @project base_frame
	 * @package com.xcj.common.service.impl  
	 * @fileName com.xcj.*** 
	 * @createDate May 9, 2014 10:38:24 AM
	 * @return List<Setting>
	 * @author su_jian
	 */
	public HashMap<String,String> getAllSetting();

}
