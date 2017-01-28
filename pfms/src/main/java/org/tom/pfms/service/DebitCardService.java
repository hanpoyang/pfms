package org.tom.pfms.service;

import java.util.List;

import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.DebitSummaryDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface DebitCardService {
	
	public PaginatedDTO<DebitCardDTO> queryDebitCards(RequestParam rp) 
	    throws ServiceException;
	
	public void saveDebit(RequestParam rp) 
		throws ServiceException;
	
	public void updateDebit(RequestParam rp)
	     throws ServiceException;
	
	public void invalidDebit(RequestParam rp)
		 throws ServiceException;
	
	public List<DebitSummaryDTO> queryDebitSummary() 
			throws ServiceException;
}
