package org.tom.pfms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.SysUserDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.SysUserService;
@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {
    @Autowired
	SysUserDao sysUserDao;
	
    @Override
	public UserDTO queryUser(RequestParam rp) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			return sysUserDao.queryUser(rp);
		}catch(DaoException e) {
			log.error("queryUser", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void editUser(RequestParam rp) throws ServiceException {
		try{
			sysUserDao.editUser(rp);
		}catch(DaoException e) {
			log.error("queryUser", e);
			throw new ServiceException(e);
		}
		
	}

}
