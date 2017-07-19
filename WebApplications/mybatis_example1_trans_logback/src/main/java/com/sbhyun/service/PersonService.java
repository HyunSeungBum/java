package com.sbhyun.service;

import java.util.List;

import com.sbhyun.vo.Person;

public interface PersonService {
	  public List<Person> getPersons();
	  public Person getById(int id);
	  public Person save(Person person);
	  public Person update(Person person);
	  public void delete(int id);
}
