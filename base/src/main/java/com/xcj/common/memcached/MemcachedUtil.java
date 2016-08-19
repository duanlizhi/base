package com.xcj.common.memcached;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import com.xcj.common.util.Constants;
import com.xcj.common.util.properties.ReadPropertiesUtil;

/**
 * <b>function:</b> Membercache缓存工具类
 * 
 * @project base_frame
 * @package com.xcj.common.memcached
 * @fileName com.xcj.common.memcached.MemcachedUtil
 * @createDate Apr 15, 2014 2:45:52 PM
 * @author su_jian
 */
public class MemcachedUtil {
	
  /**
   * <b>function:</b> Membercache 将对象放到缓存工具类
   * @project base_frame
   * @package com.xcj.common.memcached  
   * @fileName com.xcj.*** 
   * @createDate Apr 15, 2014 3:58:13 PM
   * @return MemcachedClient
   * @author su_jian
   */
	@SuppressWarnings("unchecked")
	public static MemcachedClient putMemcacheIntance(HashMap<String, String> map) {
		MemcachedClient mc = null;
		try {
			// 指定验证机制，推荐PLAIN，
			// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
			mc = MemcachedClientFactory.getInstance();
			OperationFuture future = null;
			for (Map.Entry entry : map.entrySet()) {
				future = mc.set(entry.getKey()+"",Integer.valueOf(ReadPropertiesUtil.getProperties("memcached.exptime")), entry
						.getValue()); // 异步接口，注意！返回的是Future
			}
			future.get(); // future.get() 确保之前(mc.set())操作已经结束
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			if (mc != null) {
			}
			//mc.shutdown(); // 关闭，释放资源
		}
		return mc;
	}
	
	/**
	 * <b>function:</b> Membercache 将对象放到缓存工具类
	 * @project base_frame
	 * @package com.xcj.common.memcached  
	 * @fileName com.xcj.*** 
	 * @createDate Apr 15, 2014 3:58:13 PM
	 * @return MemcachedClient
	 * @author su_jian
	 */
	@SuppressWarnings("unchecked")
	public static MemcachedClient putMemcacheIntanceByObject(HashMap<String, Object> map,Integer time) {
		MemcachedClient mc = null;
		try {
			// 指定验证机制，推荐PLAIN，
			// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
			mc = MemcachedClientFactory.getInstance();
			OperationFuture future = null;
			for (Map.Entry entry : map.entrySet()) {
				future = mc.set(entry.getKey()+"",time, entry
						.getValue()); // 异步接口，注意！返回的是Future
			}
			future.get(); // future.get() 确保之前(mc.set())操作已经结束
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			if (mc != null) {
			}
			//mc.shutdown(); // 关闭，释放资源
		}
		return mc;
	}
	
	
	
	/**
	   * <b>function:</b> Membercache 将对象放到缓存工具类
	   * @project base_frame
	   * @package com.xcj.common.memcached  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 15, 2014 3:58:13 PM
	   * @return MemcachedClient
	   * @author su_jian
	   */
		@SuppressWarnings("unchecked")
		public static MemcachedClient putMemcacheIntanceByTime(HashMap<String, String> map,Integer time) {
			MemcachedClient mc = null;
			try {
				// 指定验证机制，推荐PLAIN，
				// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
				mc = MemcachedClientFactory.getInstance();
				OperationFuture future = null;
				for (Map.Entry entry : map.entrySet()) {
					future = mc.set(entry.getKey()+"",time, entry
							.getValue()); // 异步接口，注意！返回的是Future
				}
				future.get(); // future.get() 确保之前(mc.set())操作已经结束
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				if (mc != null) {
				}
				//mc.shutdown(); // 关闭，释放资源
			}
			return mc;
		}
		
	
	 /**
	   * <b>function:</b> Membercache 将对象放到缓存工具类
	   * @project base_frame
	   * @package com.xcj.common.memcached  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 15, 2014 3:58:13 PM
	   * @return MemcachedClient
	   * @author su_jian
	   */
		@SuppressWarnings("unchecked")
		public static MemcachedClient putMemcacheIntanceDefaultKey(HashMap<String, String> map) {
			MemcachedClient mc = null;
			try {
				// 指定验证机制，推荐PLAIN，
				// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
				mc = MemcachedClientFactory.getInstance();
				OperationFuture future = null;
				for (Map.Entry entry : map.entrySet()) {
					future = mc.set(Constants.XCJ_MEMCACHED_CONSTANTS+entry.getKey()+"",Integer.valueOf(ReadPropertiesUtil.getProperties("memcached.exptime")), entry
							.getValue()); // 异步接口，注意！返回的是Future
				}
				future.get(); // future.get() 确保之前(mc.set())操作已经结束
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				if (mc != null) {
				}
				//mc.shutdown(); // 关闭，释放资源
			}
			return mc;
		}
	
		
	
	 /**
	   * <b>function:</b> Membercache 将对象放到缓存工具类
	   * @project base_frame
	   * @package com.xcj.common.memcached  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 15, 2014 3:58:13 PM
	   * @return MemcachedClient
	   * @author su_jian
	   */
		@SuppressWarnings("unchecked")
		public static MemcachedClient putMemcacheIntanceDefaultKeyByTime(HashMap<String, String> map,Integer time) {
			MemcachedClient mc = null;
			try {
				// 指定验证机制，推荐PLAIN，
				// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
				mc = MemcachedClientFactory.getInstance();
				OperationFuture future = null;
				for (Map.Entry entry : map.entrySet()) {
					future = mc.set(Constants.XCJ_MEMCACHED_CONSTANTS+entry.getKey() + "",time, entry
							.getValue()); // 异步接口，注意！返回的是Future
				}
				future.get(); // future.get() 确保之前(mc.set())操作已经结束
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				if (mc != null) {
				}
				// mc.shutdown(); // 关闭，释放资源
			}
			return mc;
		}
		
		/**
		 * 
		   * <b>function:</b> Membercache 将对象放到缓存工具类 自定义前缀
		   * @project base
		   * @package com.xcj.common.memcached  
		   * @fileName com.xcj.*** 
		   * @createDate 2015-3-16 下午03:32:38
		   * @return MemcachedClient
		   * @author hehujun
		 */
		@SuppressWarnings("unchecked")
		public static MemcachedClient putMemcacheIntanceDefaultKeyByTime(HashMap<String, String> map,String prefixion, Integer time) {
			MemcachedClient mc = null;
			try {
				// 指定验证机制，推荐PLAIN，
				// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
				mc = MemcachedClientFactory.getInstance();
				OperationFuture future = null;
				for (Map.Entry entry : map.entrySet()) {
					future = mc.set(prefixion + entry.getKey() + "", time, entry.getValue()); // 异步接口，注意！返回的是Future
				}
				future.get(); // future.get() 确保之前(mc.set())操作已经结束
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				if (mc != null) {
				}
				// mc.shutdown(); // 关闭，释放资源
			}
			return mc;
		}
	
	/**
	   * <b>function:</b> Membercache 将对象放到缓存工具类
	   * @project base_frame
	   * @package com.xcj.common.memcached  
	   * @fileName com.xcj.*** 
	   * @createDate Apr 15, 2014 3:58:13 PM
	   * @return MemcachedClient
	   * @author su_jian
	   */
		@SuppressWarnings("unchecked")
		public static void putMemcacheIntanceDefaultKey(Object obj) {
			MemcachedClient mc = null;
			try {
				// 指定验证机制，推荐PLAIN，
				// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
				mc = MemcachedClientFactory.getInstance();
				OperationFuture future = null;
					future = mc.add(Constants.XCJ_MEMCACHED_CONSTANTS+obj+"",Integer.valueOf(ReadPropertiesUtil.getProperties("memcached.exptime")), obj); // 异步接口，注意！返回的是Future
				future.get(); // future.get() 确保之前(mc.set())操作已经结束
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				if (mc != null) {
				}
				// mc.shutdown(); // 关闭，释放资源
			}
		}
		/**
		 * <b>function:</b>刷新缓存没有时间
		 * @project base_frame
		 * @package com.xcj.common.memcached
		 * @fileName com.xcj.***
		 * @createDate May 12, 2014 2:38:14 PM
		 * @return void
		 * @author su_jian
		 */
		public static void flushMemcache() {
			MemcachedClient mc = null;
			try {
				mc = MemcachedClientFactory.getInstance();
				mc.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/**
		 * <b>function:</b>刷新缓存延迟时间
		 * @project base_frame
		 * @param  int 延迟时间
		 * @package com.xcj.common.memcached
		 * @fileName com.xcj.***
		 * @createDate May 12, 2014 2:38:14 PM
		 * @return void
		 * @author su_jian
		 */
		public static void flushMemcache(int delay) {
			MemcachedClient mc = null;
			try {
				mc = MemcachedClientFactory.getInstance();
				mc.flush(delay);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		   * <b>function:</b> Membercache 将对象放到缓存工具类
		   * @project base_frame
		   * @package com.xcj.common.memcached  
		   * @fileName com.xcj.*** 
		   * @createDate Apr 15, 2014 3:58:13 PM
		   * @return MemcachedClient
		   * @author su_jian
		   */
			@SuppressWarnings("unchecked")
			public static MemcachedClient putMemcacheIntanceByTimeObject(String key,Object obj,Integer time) {
				MemcachedClient mc = null;
				try {
					// 指定验证机制，推荐PLAIN，
					// 部分客户端存在协议BUG，只能使用PLAIN协议(PlainCallbackHandler)
					mc = MemcachedClientFactory.getInstance();
					OperationFuture future = null;
						future = mc.set(key,time,obj); // 异步接口，注意！返回的是Future
					future.get(); // future.get() 确保之前(mc.set())操作已经结束
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} finally {
					if (mc != null) {
					}
					//mc.shutdown(); // 关闭，释放资源
				}
				return mc;
			}
		
}
