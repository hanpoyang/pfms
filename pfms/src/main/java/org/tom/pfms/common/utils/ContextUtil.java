package org.tom.pfms.common.utils;

import org.springframework.context.ApplicationContext;



public class ContextUtil {
    
	private static String contextConfig;
    
    private static ApplicationContext context;
    
	public static String getContextConfig() {
		return contextConfig;
	}

	public static void setContextConfig(String config) {
		contextConfig = config;
	}
	
	public static ApplicationContext getContext() {
		return context;
	}
	
	public static void setContext(ApplicationContext ctxt) {
		context = ctxt;
	}
}
