package org.tom.pfms.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.tom.pfms.common.utils.ContextUtil;

public class InitContextListener extends ContextLoaderListener {
	static Logger log = Logger.getLogger(InitContextListener.class);
	
	public InitContextListener(){
		super();
		log.info("**********************************************");
		log.info("*                                            *");
		log.info("* Personal Finance System is to start.       *");
		log.info("*                                            *");
		log.info("**********************************************");
		
	}
	
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		String config= event.getServletContext().getInitParameter("contextConfigLocation");
		ContextUtil.setContextConfig(config);
//		ApplicationContext context = new ClassPathXmlApplicationContext(config);
//		ContextUtil.setContext(context);
	}
}
