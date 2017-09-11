package org.tom.pfms.service;

import java.util.List;

import org.tom.pfms.common.dto.DebitCardDetailCurvesDTO;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;

public interface DebitDetailService {
	PaginatedDTO<DebitDetailDTO> queryDebitDetails(RequestParam rp) 
			throws ServiceException;
		
	void importDebitDetails(String username, String fileName) 
		    throws ServiceException;
		
	List<DebitCardDetailCurvesDTO> queryIncomes(RequestParam rp)
			    throws ServiceException;
			
	List<DebitCardDetailCurvesDTO> queryOutcomes(RequestParam rp)
				throws ServiceException;		
	
	Object[] getInAndOut(RequestParam rp)
			throws ServiceException;
}
