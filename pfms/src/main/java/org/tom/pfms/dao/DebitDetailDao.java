package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.DebitCardDetailCurvesDTO;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface DebitDetailDao {
	
	PaginatedDTO<DebitDetailDTO> queryDebitDetails(RequestParam rp) 
		throws DaoException;
	
	void importDebitDetails(RequestParam rp) 
	    throws DaoException;
	
	List<DebitCardDetailCurvesDTO> queryIncomes(RequestParam rp)
	    throws DaoException;
	
	List<DebitCardDetailCurvesDTO> queryOutcomes(RequestParam rp)
		throws DaoException;

}
