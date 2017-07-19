package com.sbhyun.dao;

import java.util.List;
import com.sbhyun.vo.Person;

public interface PersonDAO {
  public List<Person> getPersons();
  public Person getPerson(int id);
  public Person savePerson(Person person);
  public Person updatePerson(Person person);
  public void deletePerson(int id);
}
