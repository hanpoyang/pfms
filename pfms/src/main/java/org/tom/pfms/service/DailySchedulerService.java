package org.tom.pfms.service;

import org.tom.pfms.common.dto.DailySchedulerDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface DailySchedulerService {
	
	PaginatedDTO<DailySchedulerDTO> queryDailySchedulers(RequestParam rp)
	    throws ServiceException;
	
	void saveDailyScheduler(RequestParam rp)
	    throws ServiceException;
}
