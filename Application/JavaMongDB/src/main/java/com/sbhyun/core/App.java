package com.sbhyun.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.sbhyun.model.Person;
import com.sbhyun.service.PersonService;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new ClassPathResource("SpringConfig.xml").getPath());
		
		PersonService personService = ctx.getBean(PersonService.class);
		
		Iterable<Person> PersonList = personService.getAll();
		System.out.println("Person List:");
		for (Person person : PersonList) {
			System.out.println(person);
		}
		
		ctx.close();
	}

}
