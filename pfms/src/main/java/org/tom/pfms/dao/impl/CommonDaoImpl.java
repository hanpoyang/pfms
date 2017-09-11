package org.tom.pfms.dao.impl;

import java.util.List;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.dao.CommonDao;

public class CommonDaoImpl<T> implements CommonDao<T> {

	@Override
	public int saveOrUpdate(String sql, RequestParam rp) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T queryForOne(String sql, RequestParam rp) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> queryForList(String sql, RequestParam param)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeProcedure(String sql, RequestParam param)
			throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
