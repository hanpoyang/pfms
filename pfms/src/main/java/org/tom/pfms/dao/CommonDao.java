package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface  CommonDao<T> {
	
    int saveOrUpdate(String sql, RequestParam rp) throws DaoException;
    
    T queryForOne(String sql, RequestParam rp) throws DaoException;
    
    List<T> queryForList(String sql, RequestParam param) throws DaoException;
    
    void executeProcedure(String sql, RequestParam param) throws DaoException;
}
