package org.tom.pfms.dao.impl;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.UserDTO;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.SysUserDao;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDao implements SysUserDao {

	@Override
	public UserDTO queryUser(RequestParam rp) throws DaoException {
		// TODO Auto-generated method stub
		try {
			Object obj = sTemplate.selectOne(ConstantSettings.SQL_ID.UserDao.queryUser, rp);
			if(obj != null) {
				return (UserDTO)obj;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("queryUser", e);
			throw new DaoException(e);
		}
	}


	@Override
	public void editUser(RequestParam rp) throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.UserDao.editUser, rp);
		} catch (Exception e) {
			log.error("editUser", e);
			throw new DaoException(e);
		}
	}

}
