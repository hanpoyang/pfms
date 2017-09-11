package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.DailySchedulerDTO;
import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.DailySchedulerDao;

@Repository
public class DailySchedulerDaoImpl extends BaseDao implements DailySchedulerDao {

	@Override
	public PaginatedDTO<DailySchedulerDTO> queryDailySchedulers(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<DailySchedulerDTO> pageDto = new PaginatedDTO<DailySchedulerDTO>();
			List<DailySchedulerDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.DailySchedulerDao.queryDailySchedulers,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.DailySchedulerDao.queryDailySchedulerCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryDailySchedulers", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void saveDailyScheduler(RequestParam rp) throws DaoException {
		try {
			sTemplate.insert(ConstantSettings.SQL_ID.DailySchedulerDao.saveDailyScheduler, rp);
		} catch (Exception e) {
			log.error("saveDailyScheduler", e);
			throw new DaoException(e);
		}
		
	}

}
