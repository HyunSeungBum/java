package com.sbhyun.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.sbhyun.vo.Person;

public interface PersonService {
	  public List<Person> getPersons() throws PersistenceException, SQLException;
	  public Person getById(int id);
	  public Person save(Person person);
	  public Person update(Person person);
	  public void delete(int id);
}
