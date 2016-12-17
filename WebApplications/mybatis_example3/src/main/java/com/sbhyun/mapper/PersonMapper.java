package com.sbhyun.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.sbhyun.vo.Person;

@Repository(value="personMapper")
public interface PersonMapper {
	public List<Person> getPersons();
	public Person getPerson(int id);
	public Person savePerson(Person person);
	public Person updatePerson(Person person);
	public void deletePerson(int id);
}
