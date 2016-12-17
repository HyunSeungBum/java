package com.sbhyun.redis.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sbhyun.redis.domain.User;
import com.sbhyun.redis.repository.UserRepository;
import com.sbhyun.redis.service.RedisService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@Autowired
	UserRepository userRepository;

	
	@Autowired
	RedisService redisService;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		User user1 = new User("1", "user 1");
		User user2 = new User("2","user 2");
		  
		userRepository.put(user1);
		System.out.println(" Step 1 output : " + userRepository.getObjects());
		userRepository.put(user2);
		System.out.println(" Step 2 output : " + userRepository.getObjects());

		
		redisService.setValue("key", "hello world!");
		System.out.println("message: " + redisService.getValue("key"));
		return "home";
	}
	
}
