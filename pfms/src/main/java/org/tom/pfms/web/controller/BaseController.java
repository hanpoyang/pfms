package org.tom.pfms.web.controller;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.tom.pfms.common.dto.RequestParam;

public class BaseController implements BeanFactoryAware  {
	
	
	protected BeanFactory context = null;
	
	protected Logger log = null;
	
	public BaseController() {
		log = Logger.getLogger(this.getClass());
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		context = beanFactory;
	}
	
	
	protected Object encapsulateSubmitDTO(HttpServletRequest request, Class<?> clz) {
		try{
			Method[] methods = clz.getDeclaredMethods();
			Object dto = clz.newInstance();
			for(int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				String methodName = method.getName();
				//System.out.println("methodName:==="+methodName);
				if(methodName.indexOf("set") > -1) {
					StringBuilder tmpStr = new StringBuilder();
					try {
						String fieldName = tmpStr.append(new String(new char[]{methodName.charAt(3)}).toLowerCase()).append(methodName.substring(4)).toString();
						//System.out.println("-----fieldName:="+fieldName);
						method.invoke(dto, request.getParameter(fieldName));
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("0x040005");
					} 
				}
			}
			return dto;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	protected void saveRequestParam(HttpServletRequest request, RequestParam rp) {
		Object tmpObj = request.getSession(false);
		if(tmpObj != null){
			tmpObj = ((HttpSession)tmpObj).getAttribute("CurrentParam");
			if(tmpObj != null) {
				request.getSession(false).setAttribute("PreviousParam", tmpObj);
			}
		}
		if(rp != null) {
			request.getSession(false).setAttribute("CurrentParam", rp);
		}
	}
	
	protected RequestParam getPreRequestParam(HttpServletRequest request) {
		Object tmpObj = request.getSession(false);
		if(tmpObj != null){
			HttpSession session = (HttpSession)tmpObj;
			tmpObj = session.getAttribute("PreviousParam");
			return (RequestParam)tmpObj;
		}
		return null;
	}
   
	
}
