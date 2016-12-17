package com.sbhyun.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
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
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		
		HttpServletRequest request = attr.getRequest();
		
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		String cPath = request.getContextPath();
		String sPath = request.getServletPath();
		
		String nodeId = System.getProperty("jboss.server.name");
		String hostName = System.getProperty("java.rmi.server.hostname");
		
		String variableString = (String)session.getAttribute("SESSION_TEST_VARIABLE");
		int count = 0;
		if(variableString != null)
		{
			count = Integer.parseInt(variableString);
			count++;
		}
		session.setAttribute("SESSION_TEST_VARIABLE", String.valueOf(count));
		
		model.addAttribute("basePath", basePath);
		model.addAttribute("cPath", cPath);
		model.addAttribute("sPath", sPath);
		
		model.addAttribute("sessionId", session.getId() );
		model.addAttribute("session", session);
		model.addAttribute("nodeId", nodeId);
		model.addAttribute("hostName", hostName);
		model.addAttribute("count", count);
		
		return "home";
	}
	
}
