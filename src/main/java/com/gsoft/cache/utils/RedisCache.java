package com.gsoft.cache.utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCache<K,V> implements ListOperations<K ,V>{
   
	 private  Logger logger = LoggerFactory.getLogger(getClass());

	
	 @Resource
	  private RedisTemplate<K, V> redisTemplate;
	 
	/**
    * 获取list缓存的内容
    * @param key 键
    * @param start 开始
    * @param end 结束 0 到 -1代表所有值
    * @return
	  */
	@Override
	public List<V> range(K key, long start, long end) {
		 try {
	         return redisTemplate.opsForList().range(key, start, end);
	      } catch (Throwable e) {
	         logger.error("redis操作异常:",e);
	         return null;
	      }
	}
   
	
	@Override
	public void set(K key, long index, V value) {
		// TODO Auto-generated method stub
		
	}
	
	  /**
	    * 将list放入缓存
	    * @param key 键
	    * @param value 值
	    * @return
	    */
	   public boolean set(K key, V value) {
	      try {
	         Long rightPush = redisTemplate.opsForList().rightPush(key, value);
	      } catch (Throwable e) {
	         logger.error("redis操作异常:",e);
	         return false;
	      }
		return true;
	   }
	
	
	
	
	
	
	@Override
	public void trim(K key, long start, long end) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long size(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long leftPush(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long leftPushAll(K key, V... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long leftPushAll(K key, Collection<V> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long leftPushIfPresent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long leftPush(K key, V pivot, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rightPush(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rightPushAll(K key, V... values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rightPushAll(K key, Collection<V> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rightPushIfPresent(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rightPush(K key, V pivot, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Long remove(K key, long count, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V index(K key, long index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V leftPop(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V leftPop(K key, long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V rightPop(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V rightPop(K key, long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V rightPopAndLeftPush(K sourceKey, K destinationKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V rightPopAndLeftPush(K sourceKey, K destinationKey, long timeout, TimeUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RedisOperations<K, V> getOperations() {
		// TODO Auto-generated method stub
		return null;
	}

}
