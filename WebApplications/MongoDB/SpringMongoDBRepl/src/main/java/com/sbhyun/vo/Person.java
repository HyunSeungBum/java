package com.sbhyun.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Person")
public class Person implements Serializable {

	private static final long serialVersionUID = 201422829088192088L;

	@Id
	private String id;
	
	private String name;
	private String address;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString(){
		return id+"::"+name+"::"+address;
	}
	
}
