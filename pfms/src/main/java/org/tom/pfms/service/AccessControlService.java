package org.tom.pfms.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tom.pfms.common.dto.UserDTO;

public interface AccessControlService {
	
	/**
	 * 验证用户
	 * @param user
	 * @return 
	 *       0: 验证成功
	 *       1: 用户不存在
	 *       2: 密码正确
	 *       3: 用户已过期
	 */
	public Map<String, Object> validateUser(UserDTO user);

}
