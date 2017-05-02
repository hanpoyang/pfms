package org.tom.pfms.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.AccessControlService;

import com.google.gson.Gson;

@Controller
public class LoginController extends BaseController {
    private AccessControlService accessControlService = null;
	
	@Autowired
	public void setAccessControlService(AccessControlService accessControlService) {
		this.accessControlService = accessControlService;
	}
    
	@RequestMapping(value="/signon", method=RequestMethod.GET)
    public String showSignOnForm(HttpServletRequest request) {
		try{
			HttpSession session = request.getSession(false);
			if(session != null) {
				Object sessionObj = session.getAttribute(ConstantSettings.LOGIN_USER);
				if(null != sessionObj) {
					return "redirect:portal";
				}
			}
			return "signon";
		}catch(Exception e){
			return "signon";
		}
		
	}

	@RequestMapping(value="/signon", method=RequestMethod.POST)
    public String doSignOnForm(HttpServletRequest request) {
		UserDTO user = new UserDTO();
		String message = "";
		int iResult = 0;
		try {
			String userName = request.getParameter("userName").trim();
			String passWord = request.getParameter("passWord").trim();
			String remeberMe = request.getParameter("remeberMe");
			user.setUserName(userName);
			user.setPassWord(passWord);
			Map<String, Object> loginResultMap = accessControlService.validateUser(user);
			String loginResult = (String)loginResultMap.get(ConstantSettings.LOGIN_RESULT);
			if(ConstantSettings.LOGIN_SUCCESS.equals(loginResult)) {
				UserDTO loginUser = (UserDTO)loginResultMap.get(ConstantSettings.LOGIN_USER);
				request.getSession(true).setAttribute(ConstantSettings.LOGIN_USER, loginUser);
			}
			iResult = Integer.parseInt(loginResult);
			switch(iResult) {
			case -1:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_INVALID_USER);
			break;
			case 1:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_SUCCESS);
			break;
			case 0:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_ERROR_PASSWORD);
			}
			
			if("1".equals(remeberMe)) {
				request.setAttribute(ConstantSettings.KEY_RESULT, user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("showSignOnForm: login action error.", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_INVALID_USER);;
		}
		String result = iResult == 1 ? "redirect:portal" : "signon";
		request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
    	return result;
    }
	
	@RequestMapping("/signout")
	public String signOut(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(null != session) {
			Object obj = session.getAttribute(ConstantSettings.LOGIN_USER);
			if(obj != null) {
				session.setAttribute(ConstantSettings.LOGIN_USER, null);
				session.invalidate();
			}
		}
		return "redirect:/signon";
	}
	
	@RequestMapping(value="/rest/signon", method=RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
    public String doSignOn(HttpServletRequest request) {
		UserDTO user = new UserDTO();
		Map<String, Object> result = new HashMap<String, Object>();
		String message = "";
		int iResult = 0;
		try {
			String userName = request.getParameter("userName").trim();
			String passWord = request.getParameter("passWord").trim();
			String remeberMe = request.getParameter("remeberMe");
			user.setUserName(userName);
			user.setPassWord(passWord);
			Map<String, Object> loginResultMap = accessControlService.validateUser(user);
			String loginResult = (String)loginResultMap.get(ConstantSettings.LOGIN_RESULT);
			if(ConstantSettings.LOGIN_SUCCESS.equals(loginResult)) {
				UserDTO loginUser = (UserDTO)loginResultMap.get(ConstantSettings.LOGIN_USER);
				request.getSession(true).setAttribute(ConstantSettings.LOGIN_USER, loginUser);
			}
			iResult = Integer.parseInt(loginResult);
			switch(iResult) {
			case -1:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_INVALID_USER);
			break;
			case 1:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_SUCCESS);
			break;
			case 0:
				message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_ERROR_PASSWORD);
			}
			
			if("1".equals(remeberMe)) {
				result.put(ConstantSettings.LOGIN_USER, user.getUserName());
			}
			HttpSession session = request.getSession(false);
			String sessionId = session.getId();
			result.put("FLAG", iResult);
			result.put("SESSID", sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("showSignOnForm: login action error.", e);
			message = MessageUtils.getMessage(ConstantSettings.LABEL_LOGIN_INVALID_USER);;
		}
		result.put(ConstantSettings.KEY_MESSAGE, message);
		Gson gson = new Gson();
		return gson.toJson(result);
    }
	
}
