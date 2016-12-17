package com.sbhyun.core;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sbhyun.config.AppConfig;
import com.sbhyun.model.Person;
import com.sbhyun.service.PersonService;

public class App {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class); 
		
		PersonService personService = ctx.getBean(PersonService.class);
		
		Iterable<Person> PersonList = personService.getAll();
		System.out.println("Person List:");
		for (Person person : PersonList) {
			System.out.println(person);
		}
		
		ctx.close();
	}

}
