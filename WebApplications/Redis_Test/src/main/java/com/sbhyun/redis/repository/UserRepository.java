package com.sbhyun.redis.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.sbhyun.redis.domain.User;

public class UserRepository implements Repository<User> {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;
	
	public RedisTemplate<String, User> getRedisTemplate() {
		return redisTemplate;
	}
	
	public void setRedisTemplate(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void put(User user) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().put(user.getObjectKey(), user.getKey(), user);
	}

	@Override
	public User get(User key) {
		// TODO Auto-generated method stub
		return (User) redisTemplate.opsForHash().get(key.getObjectKey(), key.getKey());
	}

	@Override
	public void delete(User key) {
		// TODO Auto-generated method stub
		redisTemplate.opsForHash().delete(key.getObjectKey(), key.getKey());
	}
	

	@Override
	public List<User> getObjects() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		for (Object user : redisTemplate.opsForHash().values(User.OBJECT_KEY) ){
			users.add((User) user);
		}
		return users;
	}

}
