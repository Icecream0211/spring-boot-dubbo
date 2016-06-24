package com.bing.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisClusterConfig extends CachingConfigurerSupport {

	@Autowired
	private ClusterConfigurationProperties clusterProperties;

	@Bean
	public RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory(
				new RedisClusterConfiguration(clusterProperties.getNodes()));
		jedisFactory.setTimeout(clusterProperties.getTimeout());
		return jedisFactory;
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> rt = new RedisTemplate<>();
		rt.setConnectionFactory(connectionFactory());
		rt.setKeySerializer(new StringRedisSerializer());
		rt.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		return rt;
	}

	/**
	 * <!-- Serializer --> <beans:bean id="keySerializer" class=
	 * "org.springframework.data.redis.serializer.StringRedisSerializer" />
	 * <beans:bean id="valueSerializer" class=
	 * "org.springframework.data.redis.serializer.JacksonJsonRedisSerializer" />
	 * 
	 * @return
	 */

	/*
	 * @Primary
	 * 
	 * @Bean public CacheManager jdkCacheManager() { return new
	 * ConcurrentMapCacheManager("users"); }
	 */
	@Bean
	public CacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
		redisCacheManager.setDefaultExpiration(TimeUnit.MINUTES.toSeconds(30)); // 过期时间,单位是秒,设置的是半个小时
		return redisCacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}
}