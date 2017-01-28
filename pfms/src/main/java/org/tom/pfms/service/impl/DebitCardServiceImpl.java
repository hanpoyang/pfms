package org.tom.pfms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.DebitSummaryDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.DebitCardDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.DebitCardService;

@Service
public class DebitCardServiceImpl extends BaseService implements
		DebitCardService {
	@Resource
	private DebitCardDao debitCardDao = null;
	
	@Override
	public PaginatedDTO<DebitCardDTO> queryDebitCards(RequestParam rp)
			throws ServiceException {
		try{
			return debitCardDao.queryDebitCards(rp);
		}catch(DaoException ex) {
			log.error("queryDebitCards", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void saveDebit(RequestParam rp) throws ServiceException {
		try{
			debitCardDao.saveDebit(rp);
		}catch(DaoException ex) {
			log.error("saveDebit", ex);
			throw new ServiceException(ex);
		}
		
	}
	
	@Override
	public void updateDebit(RequestParam rp)
		     throws ServiceException {
		try{
			debitCardDao.updateDebit(rp);
		}catch(DaoException ex) {
			log.error("updateDebit", ex);
			throw new ServiceException(ex);
		}
	}
	
	@Override
	public void invalidDebit(RequestParam rp)
		     throws ServiceException {
		try{
			debitCardDao.invalidDebit(rp);
		}catch(DaoException ex) {
			log.error("invalidDebit", ex);
			throw new ServiceException(ex);
		}
	}
	
	@Override
	public List<DebitSummaryDTO> queryDebitSummary() 
			throws ServiceException {
		try{
			return debitCardDao.queryDebitSummary();
		}catch(DaoException ex) {
			log.error("queryDebitSummary", ex);
			throw new ServiceException(ex);
		}
	}

}
