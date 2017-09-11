package org.tom.pfms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController extends BaseController {
	
	@RequestMapping(value="/rest/request", produces={"application/json;charset=UTF-8"})
	public String rest(HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
		String page = request.getParameter("serviceName");
		String param = request.getParameter("param");
		return "";
	}

}
