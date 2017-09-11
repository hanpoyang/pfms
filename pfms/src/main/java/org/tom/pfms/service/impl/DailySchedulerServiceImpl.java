package org.tom.pfms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.DailySchedulerDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.DailySchedulerDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.DailySchedulerService;

@Service
public class DailySchedulerServiceImpl extends BaseService implements
		DailySchedulerService {

	@Resource
	DailySchedulerDao dailySchedulerDao =  null;
	
	@Override
	public PaginatedDTO<DailySchedulerDTO> queryDailySchedulers(RequestParam rp)
			throws ServiceException {
		try{
			return dailySchedulerDao.queryDailySchedulers(rp);
		}catch(DaoException e) {
			log.error("queryDailySchedulers", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveDailyScheduler(RequestParam rp) throws ServiceException {
		try{
			dailySchedulerDao.saveDailyScheduler(rp);
		}catch(DaoException e) {
			log.error("saveDailyScheduler", e);
			throw new ServiceException(e);
		}		
	}

}
