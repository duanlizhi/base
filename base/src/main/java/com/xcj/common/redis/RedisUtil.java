package com.xcj.common.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.xcj.common.util.properties.ReadPropertiesUtil;

/** 
 * <b>function:</b> redis KEY Value 数据库缓存工具类
 * @project base_frame 
 * @package com.xcj.common.redis  
 * @fileName com.xcj.*
 * @createDate Apr 16, 2014 2:44:01 PM 
 * @author su_jian 
 */
public class RedisUtil {

	public Jedis jedis;//非切片额客户端连接
	public JedisPool jedisPool;//非切片连接池
	public ShardedJedis shardedJedis;//切片额客户端连接
	public ShardedJedisPool shardedJedisPool;//切片连接池

	public RedisUtil() {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置 
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		jedisPool = new JedisPool(config,ReadPropertiesUtil.getProperties("redis.ip"),
				Integer.valueOf(ReadPropertiesUtil.getProperties("redis.port")));
	}

	/** 
	 * 初始化切片池 
	 */
	private void initialShardedPool() {
		// 池基本配置 
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(1000l);
		config.setTestOnBorrow(false);
		// slave链接 
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(ReadPropertiesUtil.getProperties("redis.ip"),
				Integer.valueOf(ReadPropertiesUtil.getProperties("redis.port")),
				ReadPropertiesUtil.getProperties("redis.db")));

		// 构造池 
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	/**
	 * 默认调用的方法
	 * <b>function:</b> 
	 * @project base_frame
	 * @package com.xcj.common.redis  
	 * @fileName com.xcj.*** 
	 * @createDate Apr 16, 2014 3:17:53 PM
	 * @return void
	 * @author su_jian
	 */
	public void show() {
		jedisPool.returnResource(jedis);
		shardedJedisPool.returnResource(shardedJedis);
	}

}
