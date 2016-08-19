/**
 * 
 */
package com.xcj.common.util.common;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Component;

import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.service.CommonService;
import com.xcj.common.util.Constants;

/** 
 * <b>function:</b> 系统设置一些工具类
 * @project base_frame 
 * @package com.xcj.common.util  
 * @fileName com.xcj.*
 * @createDate May 9, 2014 11:18:56 AM 
 * @author su_jian 
 */
@Component("settingUtil")
public class SettingUtil {
	@Resource(name = "commonServiceImpl")
	private CommonService commonService;
	@SuppressWarnings("unchecked")
	/** 
	 * <b>function:</b> 获取缓存中的内容如果没有则从配置文件读取
	 * @project base_frame 
	 * @package com.xcj.common.util  
	 * @fileName com.xcj.*
	 * @createDate May 9, 2014 11:18:56 AM 
	 * @author su_jian 
	 */
	public String getSetting(String key) {
		MemcachedClient mc;
		HashMap<String,String> map = null;
		String value=null;
		try {
			  mc = MemcachedClientFactory.getInstance();
			  if(mc!=null){
				  value=(String)mc.get(Constants.MEMCACHE_KEY_PREFIX+key);
				  if (value == null) {
						 map = commonService.getAllSetting();
						 MemcachedUtil.putMemcacheIntance(map);
						  value =(String)mc.get(Constants.MEMCACHE_KEY_PREFIX+key);
					}
			  }else{
				  map = commonService.getAllSetting();
				  MemcachedUtil.putMemcacheIntance(map);
				  value =(String)mc.get(Constants.MEMCACHE_KEY_PREFIX+key);
			  }
		} catch (IOException e) {
			e.printStackTrace();
			  System.out.println("xxx");
			  map = commonService.getAllSetting();
			return map.get(Constants.MEMCACHE_KEY_PREFIX+key);
		}
		return value;
	}

	public static void main(String[] args) {
		 SettingUtil util =new SettingUtil();
		 System.out.println(util.getSetting("address"));
	}

}
