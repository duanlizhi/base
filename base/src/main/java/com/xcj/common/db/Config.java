package com.xcj.common.db;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
	private static Log log = LogFactory.getLog(Config.class);
	private static String FILE_PATH = "/app-config.properties";
	private static Properties props = new Properties();
	static {
		try {
			props.load(Config.class.getResourceAsStream(FILE_PATH));
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Read jdbc.properties file error!", e);
			}
		}
	}

	public static String getConfig(String name) {
		return props.getProperty(name);
	}
}