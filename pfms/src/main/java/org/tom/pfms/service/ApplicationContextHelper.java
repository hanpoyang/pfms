package org.tom.pfms.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.utils.CommonUtils;
@Service
public class ApplicationContextHelper implements ApplicationContextAware, InitializingBean {
    private static ApplicationContext applicationContext;
    
    @Override
    public void afterPropertiesSet(){
    	CommonUtils.startReadPCStatus();
    }
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	ApplicationContextHelper.applicationContext = applicationContext;
    }
 
 
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
     
    public static <T>T getBean(String beanName , Class<T>clazz) {
        return applicationContext.getBean(beanName , clazz);
    }

}