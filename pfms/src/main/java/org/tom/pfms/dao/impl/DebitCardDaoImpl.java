package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.DebitCardDTO;
import org.tom.pfms.common.dto.DebitSummaryDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.DebitCardDao;


@Repository
public class DebitCardDaoImpl extends BaseDao implements DebitCardDao{

	@Override
	public PaginatedDTO<DebitCardDTO> queryDebitCards(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<DebitCardDTO> pageDto = new PaginatedDTO<DebitCardDTO>();
			List<DebitCardDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.DebitCardDao.queryDebitCards,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.DebitCardDao.queryDebitCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryDebitCards", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void saveDebit(RequestParam rp) throws DaoException {
		try {
			sTemplate.insert(ConstantSettings.SQL_ID.DebitCardDao.saveDebit, rp);
		} catch (Exception e) {
			log.error("saveDebit", e);
			throw new DaoException(e);
		}
	}
	
	@Override
	public void updateDebit(RequestParam rp)
		    throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.DebitCardDao.updateDebit, rp);
		} catch (Exception e) {
			log.error("updateDebit", e);
			throw new DaoException(e);
		}
	}
	
	@Override
	public void invalidDebit(RequestParam rp)
		    throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.DebitCardDao.invalidDebit, rp);
		} catch (Exception e) {
			log.error("updateDebit", e);
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<DebitSummaryDTO> queryDebitSummary() 
			throws DaoException {
		try {
			return sTemplate.selectList(ConstantSettings.SQL_ID.DebitCardDao.queryDebitSummary);
		} catch (Exception e) {
			log.error("queryDebitSummary", e);
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<KeyValuePair> queryDebits(RequestParam rp) throws DaoException {
		
		try {
			List<KeyValuePair> result = sTemplate.selectList(ConstantSettings.SQL_ID.DebitCardDao.queryDebits, rp);
			return result;
		} catch (Exception e) {
			log.error("queryDebits", e);
			throw new DaoException(e);
		}
	}

}
