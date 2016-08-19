/**
 * 
 */
package com.xcj.test.memcache;

import java.io.IOException;
import java.util.HashMap;

import net.spy.memcached.MemcachedClient;

import com.xcj.common.memcached.MemcachedClientFactory;
import com.xcj.common.memcached.MemcachedUtil;
import com.xcj.common.util.Constants;

/** 
 * <b>function:</b> memcached 缓存
 * @project base_frame 
 * @package com.xcj.common.memcached  
 * @fileName com.xcj.*
 * @createDate Apr 15, 2014 10:22:12 AM 
 * @author su_jian 
 */
public class TestMcached {
	 @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		 MemcachedClient mc1=MemcachedClientFactory.getInstance();
		
		HashMap map =new HashMap<String,String>();
		map.put("111", "中华人民");
		map.put("222", "苏建测试");
		map.put("333", "王xx");
		MemcachedUtil.putMemcacheIntance(map);
		/*   mc1.delete("111");
		 mc1.delete("222");*/
		mc1.flush();
		 //System.out.println(mc1.get(Constants.XCJ_MEMCACHED_CONSTANTS+"111"));
		 System.out.println(mc1.get("111"));
		 //System.out.println(mc1.get("333"));
	 }
	/*
	public static void main(String[] args) throws IOException {
		//以下内容是存放的Map信息
		HashMap<String, String> map =new HashMap<String,String>();
		map.put("0", "china sujian");
		map.put("1", "test");
		map.put("2", "wangyuxiang");
		MemcachedUtil.putMemcacheIntance(map);
		MemcachedClient mc1=MemcachedClientFactory.getInstance();
		System.out.println(mc1.get("0"));
		 	//mc1.delete("0");
		List<Dict> list =(List)mc1.get("hyzt1");//得到字典信息
		Dict dict=list.get(0);
		//System.out.println(dict.getDictName());
		//mc1.delete("1");
		//MemcachedUtil.flushMemcache(); 刷新所有缓存
		
		//System.out.println(mc1.get(Constants.MEM_DICT_PREFIX+"1"));
		 
		 
		//以下信息是存放的对象信息
		TestUserInfo test=new TestUserInfo();
		test.setId(8);
		test.setUsername("www");
		test.setPassword("bbbb");
		MemcachedUtil.putMemcacheIntance(test);
		MemcachedClient mc1=MemcachedClientFactory.getInstance();
		//mc1.delete(test+"_xcj");
		TestUserInfo t1 = (TestUserInfo)(mc1.get(test+"_xcj"));
		System.out.println(test+"_xcj");
		System.out.println(t1.getUsername());
		System.out.println(t1.getPassword());
		System.out.println(t1.getId());
	 
		
	}
	 
	 **/
	


}
