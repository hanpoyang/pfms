package org.tom.pfms.dao;

import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;


public interface OtherAccountsDao {
	
	public PaginatedDTO<OtherAccountDTO> queryOtherAccountsList(RequestParam rp) 
	    throws DaoException;
	
	public void delete(RequestParam rp) 
		    throws DaoException;
	
	public void saveAdd(RequestParam rp) 
		    throws DaoException;
	
	public void saveUpdate(RequestParam rp) 
		    throws DaoException;
}
