package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.DebitSummaryDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface DebitCardDao {
	
	public PaginatedDTO<DebitCardDTO> queryDebitCards(RequestParam rp) 
		throws DaoException;
	
	public void saveDebit(RequestParam rp) 
		throws DaoException;
	
	public void updateDebit(RequestParam rp)
	    throws DaoException;
	
	public void invalidDebit(RequestParam rp)
		throws DaoException;
	
	public List<DebitSummaryDTO> queryDebitSummary() 
		throws DaoException;

}
