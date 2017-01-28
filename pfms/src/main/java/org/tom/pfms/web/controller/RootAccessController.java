package org.tom.pfms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.web.controller.BaseController;
@Controller
public class RootAccessController extends BaseController {
	 
		@RequestMapping("/")
		public String defaultAccess(HttpServletRequest request){
			HttpSession session = request.getSession(false);
			if(session != null) {
				Object sessionObj = session.getAttribute(ConstantSettings.LOGIN_USER);
				if(null != sessionObj) {
					return "redirect:portal";
				}
				
			}
			return "redirect:signon";
		}
		
		@RequestMapping("/portal")
		public String showPortal(HttpServletRequest request){
			return "portal";
		}
}
