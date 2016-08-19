package com.xcj.common.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.xcj.common.service.CommonService;
import com.xcj.common.util.Constants;

/** 
 * <b>function:</b> 一些常用变量以及其他常用信息处理类
 * @project base_frame 
 * @package com.xcj.common.service.impl  
 * @fileName com.xcj.*
 * @createDate May 9, 2014 10:19:05 AM 
 * @author su_jian 
 */
@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService {

	/**
	 * <b>function:</b> 常用一些设置信息的获取
	 * @project base_frame
	 * @package com.xcj.common.service.impl  
	 * @fileName com.xcj.*** 
	 * @createDate May 9, 2014 10:38:24 AM
	 * @return List<Setting>
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,String> getAllSetting() {
		try {
			File appXmlFile = new ClassPathResource(Constants.APP_XML_PATH)
					.getFile();
			Document document = new SAXReader().read(appXmlFile);
			List<org.dom4j.Element> elements = document
					.selectNodes("/config/setting");
			HashMap<String,String> settings = new HashMap<String,String>();
			for (org.dom4j.Element element : elements) {
				String key = element.attributeValue("name");
				String value = element.attributeValue("value");
				settings.put(Constants.MEMCACHE_KEY_PREFIX+key,value);
			}
			return settings;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
