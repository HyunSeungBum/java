package com.sbhyun.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbhyun.dao.PersonDAO;
import com.sbhyun.vo.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDAO personDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Person> getPersons() throws PersistenceException, SQLException {
		return personDAO.getPersons();
	}

	@Override
	public Person getById(int id) {
		return personDAO.getPerson(id);
	}

	@Override
	public Person save(Person person) {
		personDAO.savePerson(person);
		return person;
	}

	@Override
	public Person update(Person person) {
		personDAO.updatePerson(person);
		return person;
	}

	@Override
	public void delete(int id) {
		personDAO.deletePerson(id);
	}
	

}
