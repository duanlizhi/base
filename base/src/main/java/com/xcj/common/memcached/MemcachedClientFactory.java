/**
 * 
 */
package com.xcj.common.memcached;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.auth.AuthDescriptor;
import net.spy.memcached.auth.PlainCallbackHandler;

import com.xcj.common.util.properties.ReadPropertiesUtil;

/**
 * <b>function:</b> MemberCache缓存单例实例只打开一个实例
 * 
 * @project base_frame
 * @package com.xcj.common.memcached
 * @fileName com.xcj.*
 * @createDate Apr 15, 2014 12:26:24 PM
 * @author su_jian
 */
public class MemcachedClientFactory {
	private volatile static MemcachedClient instance;
	private MemcachedClientFactory() {
	};
	/**
	   * <b>function:</b> 防止Mc创建更多的实例，故采用单例模式<br>
	   * 	放到正式环境阿里云服务区则把注释的放开
	   * @project base_frame
	   * @package com.xcj.common.memcached  
	   * @fileName com.xcj.common.memcached.MemcachedClientFactory 
	   * @createDate Apr 15, 2014 2:49:34 PM
	   * @return MemcachedClient
	   * @author su_jian
	 */
	public static MemcachedClient getInstance() throws IOException {
		if (instance == null) {
			synchronized (MemcachedClient.class) {
				if (instance == null) {
					//TODO 放到正式正式服务器放开 username passowrd 
				  AuthDescriptor ad = new AuthDescriptor(new String[]{"PLAIN"}, new PlainCallbackHandler("c57ed378664511e4", "XCJasdfghjkl123")); // 用户名，密码 
					instance = new MemcachedClient(
							new ConnectionFactoryBuilder().setProtocol(
									Protocol.BINARY) // 指定使用Binary协议
									.setOpTimeout(800222312)// 设置超时时间为100ms
									//TODO 放到正式正式服务器放开
									//.setAuthDescriptor(ad)
									.build(), AddrUtil
									.getAddresses(ReadPropertiesUtil.getProperties("memcached.ip")+":"+ReadPropertiesUtil.getProperties("memcached.port")));
				}
			}
		}
		return instance;
	}

}
