package org.tom.pfms.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.service.SysMenuService;
@Controller
public class RootAccessController extends BaseController {
	
	@Resource
	private SysMenuService sysMenuService = null;
	 
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
	public String showPortal(HttpServletRequest request) throws Exception {
		try{
			List<SysMenuBean> menus = sysMenuService.queryMenus();
			request.setAttribute("menus", menus);
		}catch(ServiceException e) {
			log.error("showPortal", e);
		}
		return "portal";
	}
	
	
	@RequestMapping("/watchsession")
	@ResponseBody
	public String watchSession(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String message = session == null ? "false" : "true";
		return message;
	}
}
