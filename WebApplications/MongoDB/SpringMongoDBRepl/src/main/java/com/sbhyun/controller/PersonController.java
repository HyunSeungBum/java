package com.sbhyun.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String Persons(Model model) {
		logger.info("call Person() ");
		
		//personDAO.clear();

		Person p = new Person();
		
		p.setId(null);
		p.setName("David");
		p.setAddress("SFO, USA");
		
		personDAO.create(p);
		
		Person p1 = personDAO.readById(p.getId());
		p1.setName("Sbhyun");
		p1.setAddress("Seoul, Korea");
		personDAO.update(p1); 
		
		List<Person> persons = personDAO.getAll();
		model.addAttribute("persons", persons);
		
		return "person";
	}
	
}
