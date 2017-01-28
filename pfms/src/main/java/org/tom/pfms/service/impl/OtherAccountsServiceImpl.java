package org.tom.pfms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.OtherAccountsDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.OtherAccountsService;

@Service("otherAccountService")
@Transactional
public class OtherAccountsServiceImpl extends BaseService implements OtherAccountsService  {
	
	@Autowired
	private OtherAccountsDao otherAccountsDao =  null;
	
	public PaginatedDTO<OtherAccountDTO> queryOtherAccountsList(RequestParam rp) 
	    throws ServiceException {
		try{
			return otherAccountsDao.queryOtherAccountsList(rp);
		}catch(DaoException ex) {
			log.error("queryOtherAccountsList", ex);
			throw new ServiceException(ex);
		}
    }
	
	public void delete(RequestParam rp) 
		throws ServiceException {
		try{
			otherAccountsDao.delete(rp);
		}catch(DaoException ex) {
			log.error("queryOtherAccountsList", ex);
			throw new ServiceException(ex);
		}
	}
	
	
	public void saveAdd(RequestParam rp)  
			throws ServiceException {
		try{
			otherAccountsDao.saveAdd(rp);
		}catch(DaoException ex) {
			log.error("queryOtherAccountsList", ex);
			throw new ServiceException(ex);
		}
	}
	
	public void saveUpdate(RequestParam rp)  
			throws ServiceException {
		try{
			otherAccountsDao.saveUpdate(rp);
		}catch(DaoException ex) {
			log.error("queryOtherAccountsList", ex);
			throw new ServiceException(ex);
		}
	}
	
}
