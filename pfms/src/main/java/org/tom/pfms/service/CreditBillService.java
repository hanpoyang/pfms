package org.tom.pfms.service;

import org.tom.pfms.common.dto.CreditBillDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface CreditBillService {
    
	public PaginatedDTO<CreditBillDTO> queryCreditBill(RequestParam rp) throws ServiceException;
	
	public void updateIsClear(RequestParam rp) throws ServiceException;
	
	public CreditBillDTO queryCreditBillDetail(RequestParam rp) throws ServiceException;
	
}
