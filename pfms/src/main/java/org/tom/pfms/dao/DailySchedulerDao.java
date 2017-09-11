package org.tom.pfms.dao;

import org.tom.pfms.common.dto.DailySchedulerDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;

public interface DailySchedulerDao {
	
    PaginatedDTO<DailySchedulerDTO> queryDailySchedulers(RequestParam rp)
        throws DaoException;
    
    void saveDailyScheduler(RequestParam rp)
        throws DaoException;
}
