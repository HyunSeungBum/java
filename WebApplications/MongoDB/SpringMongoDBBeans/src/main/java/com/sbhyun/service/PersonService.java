package com.sbhyun.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sbhyun.repositories.IPersonRepository;
import com.sbhyun.model.Person;

@Repository("personService")
public class PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
	
	@Autowired
	private IPersonRepository personRepository;
	
	public List<Person> getAll() {
		logger.debug("Retrieving all persons");
		List<Person> persons = personRepository.findAll();
		
		return persons;
	}
	
	public Person get(String id) {
		logger.debug("Retrieving an existing person");
		Person person = personRepository.findOne(id);
		
		return person;
	}
	
	public Boolean add(Person person) {
		logger.debug("Adding a new user");
		
		try {
			person.setId(UUID.randomUUID().toString());
			personRepository.save(person);
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new user", e);
			return false;
		}
	}
	
	public Boolean delete(String id) {
		logger.debug("Deleting existing person");
		try {
			personRepository.delete(personRepository.findOne(id));
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new user", e);
			return false;
		}
	}
	
	public Boolean edit(Person person) {
		logger.debug("Editing existing person");
		try {
			Person existingPerson = personRepository.findOne(person.getId());
			
			existingPerson.setFirstName(person.getFirstName());
			existingPerson.setLastName(person.getLastName());
			existingPerson.setMoney(person.getMoney());
			
			personRepository.save(existingPerson);
			
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing user", e);
			return false;
		}
	}
	
}
