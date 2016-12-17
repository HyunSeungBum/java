package com.sbhyun.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.sbhyun.model.Person;

public class InitService {
	
	private static final Logger logger = LoggerFactory.getLogger(InitService.class);
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@SuppressWarnings("unused")
	private void init() {
		// Populate our MongoDB database
		logger.debug("Init MongoDB");
		
		// Drop existing collection
		//mongoTemplate.dropCollection("Person");
		
		// Create new object
		Person p = new Person();
		p.setId(UUID.randomUUID().toString());
		p.setFirstName("John");
		p.setLastName("Smith");
		p.setMoney(1000.0);
		
		// Insert to db
		mongoTemplate.insert(p);
		
		// Create new object
		p = new Person ();
	    p.setId(UUID.randomUUID().toString());
	    p.setFirstName("Jane");
	    p.setLastName("Adams");
	    p.setMoney(2000.0);
	   
	    // Insert to db
	    mongoTemplate.insert(p);
	     
	    // Create new object
	    p = new Person ();
	    p.setId(UUID.randomUUID().toString());
	    p.setFirstName("Jeff");
	    p.setLastName("Mayer");
	    p.setMoney(3000.0);
	   
	    // Insert to db
	    mongoTemplate.insert(p);
	}
}
