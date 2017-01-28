package org.tom.pfms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.CreditBillDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.CreditBillDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.CreditBillService;

@Service
public class CreditBillServiceImpl extends BaseService implements CreditBillService {
	
	@Resource
	private CreditBillDao creditBillDao;
	@Override
	public PaginatedDTO<CreditBillDTO> queryCreditBill(RequestParam rp)
			throws ServiceException {
		try{
			return creditBillDao.queryCreditBill(rp);
		}catch(DaoException e) {
			log.error("queryCreditBill", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateIsClear(RequestParam rp) throws ServiceException {
		try{
			creditBillDao.updateIsClear(rp);
		}catch(DaoException e) {
			log.error("updateIsClear", e);
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public CreditBillDTO queryCreditBillDetail(RequestParam rp) throws ServiceException {
		try{
			return creditBillDao.queryCreditBillDetail(rp);
		}catch(DaoException e) {
			log.error("queryCreditBillDetail", e);
			throw new ServiceException(e);
		}
	}
}
