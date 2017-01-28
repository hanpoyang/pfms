package org.tom.pfms.service;

import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface OtherAccountsService {
	public PaginatedDTO<OtherAccountDTO> queryOtherAccountsList(RequestParam rp)  
			throws ServiceException ;
	
	public void delete(RequestParam rp)  
			throws ServiceException ;
	
	public void saveAdd(RequestParam rp)  
			throws ServiceException ;
	
	public void saveUpdate(RequestParam rp)  
			throws ServiceException ;
	
}
