/**
 * 
 */
package com.xcj.common.util.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.spy.memcached.MemcachedClient;

import org.springframework.stereotype.Component;

import com.xcj.admin.entity.dict.Dict;
import com.xcj.admin.service.dict.DictService;
import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.Constants;

/**
 * <b>function:</b> 字典公共类
 * 
 * @project base_frame
 * @package com.xcj.common.util
 * @fileName com.xcj.*
 * @createDate May 8, 2014 2:11:04 PM
 * @author su_jian
 */

@Component("dictUtil")
public class DictUtil {

	@Resource(name = "dictServiceImpl")
	private DictService dictService;

	/**
	 * <b>function:</b> 字典获取工具类
	 * 
	 * @project base_frame
	 * @package com.xcj.common.util
	 * @fileName com.xcj.***
	 * @createDate May 8, 2014 2:21:47 PM
	 * @return List<Dict>
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	public List<Dict> getDictList(String typeCode) {
		MemcachedClient mc;
		List<Dict> list = null;
		try {
			mc = MemcachedClientFactory.getInstance();
			list = (List) mc.get(typeCode);
			if (list == null) {
				HashMap map = dictService.getDictMap();
				MemcachedUtil.putMemcacheIntance(map);
				list = (List) mc.get(typeCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	   * <b>function:</b> 根据键值 id从缓存里获取字典名称如果取不到则从数据库查
	   * @project base_frame
	   * @package com.xcj.common.util  
	   * @fileName com.xcj.*** 
	   * @createDate May 13, 2014 3:08:34 PM
	   * @return String
	   * @author su_jian
	 */
	public String  getDictNameByIdFromMem(String key){
		MemcachedClient mc;
		String val=null;
		try {
			mc = MemcachedClientFactory.getInstance();
			val = (String)mc.get(key);
			if (val == null) {
				HashMap<String, String> map=dictService.getKeyValueMap();
				MemcachedUtil.putMemcacheIntance(map);
				val=(String)mc.get(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return val;
	}

	/**
	   *此方法只能取缓存里的数据。存放的时候不能的注解进来不能进行new
	 */
	public static void main(String[] args) {
		
	/*	List<Dict> list = dictUtil.getDictList("hylx");
		System.out.println(JSON.toJSONString(list, true));*/
		DictUtil dictUtil = new DictUtil();
		System.out.println(dictUtil.getDictNameByIdFromMem(Constants.MEM_DICT_PREFIX+"1"));
		
	}
}
