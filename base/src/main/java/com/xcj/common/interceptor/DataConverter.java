/**
 * 
 */
package com.xcj.common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/** 
 * <b>function:</b> 前台时间转换类
 * @project base_frame 
 * @package com.xcj.common.interceptor  
 * @fileName com.xcj.*
 * @createDate May 27, 2014 2:17:28 PM 
 * @author su_jian 
 */
public class DataConverter implements WebBindingInitializer {
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		/**
		 * 去掉所有参数空格的方法
		 */
		for (Iterator<String[]> iterator = request.getParameterMap()
				.values().iterator(); iterator.hasNext();) {
			String[] parames = iterator.next();
			for (int i = 0; i < parames.length; i++) {
				//parames[i]=StringUtil.trimAll(parames[i]);
			}
		}
	}

}
