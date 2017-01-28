package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.OtherAccountDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.OtherAccountsDao;

@Repository("OtherAccountsDao")
public class OtherAccountsDaoImpl extends BaseDao  implements OtherAccountsDao{
	
	public PaginatedDTO<OtherAccountDTO> queryOtherAccountsList(RequestParam rp) 
	    throws DaoException{
		try {
			PaginatedDTO<OtherAccountDTO> pageDto = new PaginatedDTO<OtherAccountDTO>();
			List<OtherAccountDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.OtherAccountsDao.queryOtherAccountsList,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.OtherAccountsDao.queryOtherAccountsCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
	}
	
	public void delete(RequestParam rp) 
		    throws DaoException{
			try {
				sTemplate.delete(ConstantSettings.SQL_ID.OtherAccountsDao.delete ,rp);
			} catch (Exception e) {
				log.error("queryOtherAccountsList", e);
				throw new DaoException(e);
			}
	}
	
	public void saveAdd(RequestParam rp)
	       throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.OtherAccountsDao.save ,rp);
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
	}
	
	public void saveUpdate(RequestParam rp)
	       throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.OtherAccountsDao.update ,rp);
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
	}
}
