/**
 * 
 */
package com.xcj.common.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.xcj.common.entity.LogConfig;
import com.xcj.common.service.LogConfigService;
import com.xcj.common.util.Constants;

/** 
 * <b>function:</b> 从配置文件取得configuration日志信息
 * @project base_frame 
 * @package com.xcj.common.service.impl  
 * @fileName com.xcj.*
 * @createDate May 8, 2014 4:13:46 PM 
 * @author su_jian 
 */
@Service("logConfigServiceImpl")
public class LogConfigServiceImpl implements LogConfigService{
	
	@SuppressWarnings("unchecked")
	public List<LogConfig> getAll() {
		try {
			File appXmlFile = new ClassPathResource(Constants.APP_XML_PATH).getFile();
			Document document = new SAXReader().read(appXmlFile);
			List<org.dom4j.Element> elements = document.selectNodes("/config/logConfig");
			List<LogConfig> logConfigs = new ArrayList<LogConfig>();
			for (org.dom4j.Element element : elements) {
				String operation = element.attributeValue("operation");
				String urlPattern = element.attributeValue("urlPattern");
				LogConfig logConfig = new LogConfig();
				logConfig.setOperation(operation);
				logConfig.setUrlPattern(urlPattern);
				logConfigs.add(logConfig);
			}
			return logConfigs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
