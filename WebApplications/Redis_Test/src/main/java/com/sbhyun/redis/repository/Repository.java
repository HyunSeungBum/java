package com.sbhyun.redis.repository;

import java.util.List;

import com.sbhyun.redis.domain.DomainObject;

public interface Repository<V extends DomainObject> {
	void put(V ojb);
	
	V get(V key);
	
	void delete(V key);
	
	List<V> getObjects();
}
