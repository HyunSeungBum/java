package com.sbhyun.redis.domain;

public class User implements DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	public static final String OBJECT_KEY = "USER";
	
	public User() {}
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "User [id=" + this.id + ", name=" + this.name + "]";
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return OBJECT_KEY;
	}

}
