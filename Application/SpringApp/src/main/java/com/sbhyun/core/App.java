package com.sbhyun.core;

import java.sql.SQLException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.sbhyun.service.PersonService;
import com.sbhyun.vo.Person;

public class App {
	
	public static void main(String[] args) throws PersistenceException, SQLException {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new ClassPathResource("SpringConfig.xml").getPath());
		
		PersonService personService = ctx.getBean(PersonService.class);
		
		System.out.println("Hello World!!");
		Iterable<Person> PersonList = personService.getPersons();
		
		System.out.println("Person List:");
		for (Person person : PersonList) {
			System.out.println(person);
		}

		ctx.close();

	}

}

