package org.tom.pfms.service;

import org.tom.pfms.common.dto.NbContactDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface NbContactService {
	public PaginatedDTO<NbContactDTO> queryNbContact(RequestParam rp)
        throws ServiceException;
	    
    public void insertNbContact(RequestParam rp)
    	throws ServiceException;
    
    public void updateNbContact(RequestParam rp)
        throws ServiceException;
    
    public void deleteNbContact(RequestParam rp)
        throws ServiceException;
    
    public void invalidNbContact(RequestParam rp)
            throws ServiceException;
}
