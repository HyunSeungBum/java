package com.sbhyun.dao;

import java.sql.SQLException;
import java.util.List;
import com.sbhyun.vo.Person;
import org.apache.ibatis.exceptions.PersistenceException;

public interface PersonDAO {
  public List<Person> getPersons() throws PersistenceException, SQLException;
  public Person getPerson(int id) throws PersistenceException;
  public Person savePerson(Person person) throws PersistenceException;
  public Person updatePerson(Person person) throws PersistenceException;
  public void deletePerson(int id) throws PersistenceException;
}
