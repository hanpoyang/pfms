package org.tom.pfms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.common.utils.AES256;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.common.utils.MessageUtils;
import org.tom.pfms.service.SysUserService;
import org.tom.pfms.web.vo.EditUserVO;

@Controller
public class SysAccountController extends BaseController {
    
	@Autowired
	SysUserService sysUserSerivce;
	@RequestMapping(value="/sys/edit_password", method=RequestMethod.GET)
	public String editPassWordForm(HttpServletRequest request) {
		UserDTO userDTO = new UserDTO();
		try{
			UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
			String username = user.getUserName();
			userDTO.setUserName(username);
			RequestParam rp = new RequestParam();
			rp.setRequestObject(userDTO);
			userDTO = sysUserSerivce.queryUser(rp);
			request.setAttribute(ConstantSettings.KEY_RESULT, userDTO);
		}catch(ServiceException e) {
			log.error("editPassWordForm", e);
		}
		return "sys/edit_password_form";
	}
	
	@RequestMapping(value="/sys/update_password", method=RequestMethod.POST)
	public String modifyPassword(HttpServletRequest request) {
		EditUserVO userVo = (EditUserVO)encapsulateSubmitDTO(request, EditUserVO.class);
		String message = null;
		try{
			if(userVo != null) {
				UserDTO user = (UserDTO)request.getSession(false).getAttribute(ConstantSettings.LOGIN_USER);
				String oldPassword = user.getPassWord();
				if(oldPassword.equals(AES256.encrypt(userVo.getPassWord()))){
					String newPassWord = userVo.getNewPassWord();
					String repeatNewPassWord = userVo.getRepeatNewPassWord();
					if(newPassWord == null || "".equals(newPassWord)) {
						message = MessageUtils.getMessage(ConstantSettings.LABEL_NEWPASSWORD_NOTEMPTY);
					} else if(repeatNewPassWord == null || "".equals(repeatNewPassWord)){
						message = MessageUtils.getMessage(ConstantSettings.LABEL_REPEATNEWPASSWORD_NOTEMPTY);
					} else if(!newPassWord.equals(repeatNewPassWord)) {
						message = MessageUtils.getMessage(ConstantSettings.LABEL_NEWPASSWORD_NOTEMATCH);
					} else {
						newPassWord = AES256.encrypt(newPassWord);
						RequestParam rp = new RequestParam();
						user.setPassWord(newPassWord);
						rp.setRequestObject(user);
						sysUserSerivce.editUser(rp);
					}
				} else {
					message = MessageUtils.getMessage(ConstantSettings.LABEL_PASSWORD_INCORRECT);
				}
				
			}
		}catch(ServiceException e) {
			log.error("modifyPassWord", e);
			message = e.getMessage();
		}
		
		if(message != null) {
			request.setAttribute(ConstantSettings.KEY_MESSAGE, message);
			return "sys/edit_password_form";
		} else {
			return "sys/edit_password_success";
		}
		
	}
}
