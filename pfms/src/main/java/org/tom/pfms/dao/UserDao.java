package org.tom.pfms.dao;

import org.tom.pfms.common.dto.UserDTO;

public interface UserDao {
    
	public UserDTO queryUserByUserName(String userName);

}
