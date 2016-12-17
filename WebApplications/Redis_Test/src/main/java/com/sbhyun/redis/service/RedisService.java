package com.sbhyun.redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public Object getValue(final String key) {
		return redisTemplate.opsForValue().get(key);
	}
	
	public void setValue(final String key, final String value) {
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, 5, TimeUnit.SECONDS);
	}
}
