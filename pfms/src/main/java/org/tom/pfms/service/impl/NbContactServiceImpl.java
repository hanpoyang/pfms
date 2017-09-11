package org.tom.pfms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.NbContactDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.NbContactDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.NbContactService;

@Service
public class NbContactServiceImpl extends BaseService implements
		NbContactService {
    
	@Resource
    NbContactDao nbContactDao;
	
	@Override
	public PaginatedDTO<NbContactDTO> queryNbContact(RequestParam rp)
			throws ServiceException {
		try{
			return nbContactDao.queryNbContact(rp);
		}catch(DaoException ex) {
			log.error("queryNbContact", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void insertNbContact(RequestParam rp) throws ServiceException {
		try{
			nbContactDao.insertNbContact(rp);
		}catch(DaoException ex) {
			log.error("insertNbContact", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void updateNbContact(RequestParam rp) throws ServiceException {
		try{
			nbContactDao.updateNbContact(rp);
		}catch(DaoException ex) {
			log.error("updateNbContact", ex);
			throw new ServiceException(ex);
		}
	}

	@Override
	public void deleteNbContact(RequestParam rp) throws ServiceException {
		try{
			nbContactDao.deleteNbContact(rp);
		}catch(DaoException ex) {
			log.error("deleteNbContact", ex);
			throw new ServiceException(ex);
		}

	}

}
