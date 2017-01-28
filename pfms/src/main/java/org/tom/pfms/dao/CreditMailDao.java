package org.tom.pfms.dao;

import java.util.Map;

import org.tom.pfms.common.exception.DaoException;

public interface CreditMailDao {
	public void save(Map<String, Object> mailMap) throws DaoException;
}
