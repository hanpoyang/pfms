package org.tom.pfms.dao;

import org.tom.pfms.common.dto.NbContactDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface NbContactDao {
	
    public PaginatedDTO<NbContactDTO> queryNbContact(RequestParam rp)
        throws DaoException;
    
    public void insertNbContact(RequestParam rp)
    	throws DaoException;
    
    public void updateNbContact(RequestParam rp)
        	throws DaoException;
    
    public void deleteNbContact(RequestParam rp)
        	throws DaoException;
}
