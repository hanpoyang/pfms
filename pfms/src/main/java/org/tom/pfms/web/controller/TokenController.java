package org.tom.pfms.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tom.pfms.common.utils.CommonUtils;
import org.tom.pfms.common.utils.ConstantSettings;

@Controller
public class TokenController extends BaseController {
	
	@RequestMapping("/token")
    public String getToken(HttpServletRequest request, HttpServletResponse response) {
    	String token =  CommonUtils.getUUID();
    	response.addHeader("expires", "0");
    	response.addHeader("cache-control", "no-cache");
    	response.addHeader("pragma", "no-cache");
    	
    	try{
    		if(request.getSession(false) == null) {
        		token = "{\"flag\":\"false\"}";
        	    response.getWriter().print(token);
        	    return null;
        	}
        	request.getSession(false).setAttribute(ConstantSettings.KEY_TOKEN, token);
    		token = "{\"flag\":\"success\",\"token\":\""+token+"\"}";
    	    response.getWriter().print(token);
    	}catch(IOException e) {
    		log.error("getToken", e);
    	}
    	return null;
    }
}
