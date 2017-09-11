package org.tom.pfms.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {
	
	@RequestMapping("/entry")
	public String entry(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		
		return "";
		
	}

}
