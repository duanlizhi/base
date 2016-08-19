/**
 * 
 */
package com.xcj.common.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
 * <b>function:</b> 读取属性文件的工具类
 * @project base_frame 
 * @package com.xcj.common.util.properties  
 * @fileName com.xcj.common.util.properties.ReadProperties
 * @createDate Apr 15, 2014 3:30:15 PM 
 * @author su_jian 
 */
public class ReadPropertiesUtil {

	private static Properties props = null;

	private ReadPropertiesUtil() {
	}
	 /**
	    * <b>function:</b> 读取properties配置文件里边的属性方法<br>
	    *  根据Key 读取alue的值
	    * @project base_frame
	    * @package com.xcj.common.util.properties  
	    * @fileName com.xcj.common.util.properties.ReadPropertiesUtil
	    * @createDate Apr 15, 2014 3:48:21 PM
	    * @return 属性的值
	    * @author su_jian
	  */
	public static String getProperties(String str) {
		String propertiesFile = "/app-config.properties";
		props = new Properties();
		InputStream in = null;
		try {
			in = ReadPropertiesUtil.class.getResourceAsStream(propertiesFile);
			props.load(in);
			return props.getProperty(str);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("读取配置文件失败");
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("流关闭错误");
				}
			}
		}
	}
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		String ip = ReadPropertiesUtil.getProperties("memcached.ip");
		String port =  ReadPropertiesUtil.getProperties("memcached.port");
		System.out.println(ip + ":" + port);
	}
	

}
