package org.tom.pfms.dao;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.DaoException;

public interface SysUserDao {
	
    /**
     * ≤È”√ªß
     * @param user
     * @throws DaoException
     */
	public UserDTO queryUser(RequestParam rp) throws DaoException;
	
	public void editUser(RequestParam rp) throws DaoException;
}
