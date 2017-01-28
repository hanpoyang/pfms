package org.tom.pfms.service;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.ServiceException;

public interface SysUserService {
	public UserDTO queryUser(RequestParam rp) throws ServiceException;
	
	public void editUser(RequestParam rp) throws ServiceException;
}
