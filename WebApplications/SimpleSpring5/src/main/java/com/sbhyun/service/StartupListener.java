package com.sbhyun.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartupListener implements ServletContextListener {

    // Omitted for brevity
	
	private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		logger.info("--------------- Context Initialized -----------------");
        // If want to get a bean
//        ServletContext context = event.getServletContext();
//        ApplicationContext ctx = (ApplicationContext) WebApplicationContextUtils.getRequiredWebApplicationContext(context);
//        MyBean bean = (MyBean) ctx.getBean("myBean");
	}

}
