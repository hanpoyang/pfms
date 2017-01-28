package org.tom.pfms.dao;

import org.tom.pfms.common.dto.CreditBillDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;

public interface CreditBillDao {
	
	public PaginatedDTO<CreditBillDTO> queryCreditBill(RequestParam rp) throws DaoException;
	
	public void updateIsClear(RequestParam rp) throws DaoException;
	
	public CreditBillDTO queryCreditBillDetail(RequestParam rp) throws DaoException; 

}
