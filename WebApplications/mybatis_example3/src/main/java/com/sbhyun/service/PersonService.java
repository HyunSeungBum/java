package com.sbhyun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbhyun.mapper.PersonMapper;
import com.sbhyun.vo.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonMapper personMapper;
	
	public List<Person> getPersons() {
		return personMapper.getPersons();
	}

	public Person getById(Integer id) {
		return personMapper.getPerson(id);
	}

	@Transactional
	public Person save(Person person) {
		personMapper.savePerson(person);
		return person;
	}
	
	@Transactional
	public Person update(Person person) {
		personMapper.updatePerson(person);
		return person;
	}
	
	@Transactional
	public void delete(Integer id) {
		personMapper.deletePerson(id);
	}
}
