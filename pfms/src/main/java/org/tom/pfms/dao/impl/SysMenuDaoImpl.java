package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.SysMenuDao;

@Repository
public class SysMenuDaoImpl extends BaseDao implements SysMenuDao {

	@Override
	public List<SysMenuBean> queryMenus() throws DaoException {
		try{
			return sTemplate.selectList(ConstantSettings.SQL_ID.SysMenuDao.queryMenus);
		} catch(Exception e) {
			log.error("queryMenus", e);
			throw new DaoException(e);
		}
	}

}
