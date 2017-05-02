package org.tom.pfms.web.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.tom.pfms.common.utils.ContextUtil;

public class InitContextListener extends ContextLoaderListener {
	static Logger log = Logger.getLogger(InitContextListener.class);
	
	public InitContextListener(){
		super();
		log.info("*********************************************************************************");
		log.info("*                                                                               *");
		log.info("* Personal Finance System is to start.                                          *");
		log.info("*                                                                               *");
		log.info("*********************************************************************************");
		
	}
	
}
