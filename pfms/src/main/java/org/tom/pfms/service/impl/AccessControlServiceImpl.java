package org.tom.pfms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.utils.AES256;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.UserDao;
import org.tom.pfms.service.AccessControlService;
import org.tom.pfms.service.BaseService;
@Service
@Transactional
public class AccessControlServiceImpl extends BaseService implements AccessControlService {
	
	@Autowired
	private UserDao userDao = null;
	
	@Override
	public Map<String, Object> validateUser(UserDTO user) {
		Map<String, Object> result = new HashMap<String, Object>(); 
		String passWord = user.getPassWord();
		passWord = AES256.encrypt(passWord);
		try{
		    user = userDao.queryUserByUserName(user.getUserName());
		    if(user != null) {
		    	if(passWord.equals(passWord)){
		    		result.put(ConstantSettings.LOGIN_RESULT, ConstantSettings.LOGIN_SUCCESS);
		    		result.put(ConstantSettings.LOGIN_USER, user);
		    	} else {
		    		result.put(ConstantSettings.LOGIN_RESULT, ConstantSettings.LOGIN_ERROR_PASSWORD);
		    	}
		    } else {
		    	result.put(ConstantSettings.LOGIN_RESULT, ConstantSettings.LOGIN_INVALID_USER);
		    }
		   
		}catch(Exception e) {
			log.error("validateUser" ,e);
			result.put(ConstantSettings.LOGIN_RESULT, ConstantSettings.LOGIN_INVALID_USER);
		}
		return result;
	}

}
