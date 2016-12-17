package com.sbhyun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbhyun.dao.PersonDAO;
import com.sbhyun.vo.Person;

@Controller
@RequestMapping("/v1")
public class PersonController {
	@Autowired
	PersonDAO personDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	// this method responses to GET request http://localhost:8080/api/vi/person
	// return Person object as json
	@RequestMapping(value = "/person", method = RequestMethod.GET)
	@ResponseBody
	public void Persons() {
		logger.info("call Person() ");
		
		Person p = new Person();
		
		p.setId(null);
		p.setName("David");
		p.setAddress("SFO, USA");
		
		personDAO.create(p);
		
		
	}
	
}
