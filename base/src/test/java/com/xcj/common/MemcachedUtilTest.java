package com.xcj.common;

import static org.junit.Assert.fail;

import java.io.IOException;

import net.spy.memcached.MemcachedClient;

import org.junit.Test;

import com.xcj.common.memcached.MemcachedClientFactory;

/**
 * 
  * <b>function:</b> memcached spring管理测试 
  * @project newservice 
  * @package com.xcj.entity.admin  
  * @fileName com.xcj.*
  * @createDate 2015年7月21日 上午11:55:53 
  * @author hehujun 
  * @email hehujun@126.com
 */

public class MemcachedUtilTest {
	
	@Test
	public void test(){
		
		MemcachedClient memcachedUtil;
		try {
			//XXX 有待商榷
			memcachedUtil = MemcachedClientFactory.getInstance();
			memcachedUtil.flush();
		} catch (IOException e) {
			fail("刷新缓存");
		}
		
		

	}

}
