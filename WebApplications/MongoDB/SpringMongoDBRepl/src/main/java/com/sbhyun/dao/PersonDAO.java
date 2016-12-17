package com.sbhyun.dao;

import java.util.List;

import com.sbhyun.vo.Person;

public interface PersonDAO {
	public List<Person> getAll();
	public void create(Person p);
	public Person readById(String id);
	public void update(Person p);
	public int deleteById(String id);
	public void clear();
}
