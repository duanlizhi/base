/**
 * 
 */
package com.xcj.test.lt;

import com.xcj.common.redis.RedisUtil;

/** 
 * <b>function:</b> 
 * @project base 
 * @package com.xcj.test.lt  
 * @fileName com.xcj.*
 * @createDate Oct 24, 2014 11:46:54 AM 
 * @author su_jian 
 */
public class Test {
		public static void main(String[] args) {
			 RedisUtil redisUtil = new RedisUtil();
			 redisUtil.jedis.flushDB();
				
		}
}
