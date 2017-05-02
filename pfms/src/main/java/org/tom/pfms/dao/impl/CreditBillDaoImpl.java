package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.CreditBillDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.CreditBillDao;

@Repository
public class CreditBillDaoImpl extends BaseDao implements CreditBillDao {

	@Override
	public PaginatedDTO<CreditBillDTO> queryCreditBill(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<CreditBillDTO> pageDto = new PaginatedDTO<CreditBillDTO>();
			List<CreditBillDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.CreditBillDao.queryCreditBill,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.CreditBillDao.queryCreditBillCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryCreditBill", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void updateIsClear(RequestParam rp) throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.CreditBillDao.updateIsClear ,rp);
		} catch (Exception e) {
			log.error("updateIsClear", e);
			throw new DaoException(e);
		}

	}
	
	@Override
	public CreditBillDTO queryCreditBillDetail(RequestParam rp) throws DaoException {
		try {
			return (CreditBillDTO)sTemplate.selectOne(ConstantSettings.SQL_ID.CreditBillDao.queryCreditBillDetail ,rp);
		} catch (Exception e) {
			log.error("queryCreditBillDetail", e);
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<CreditBillDTO> queryCreditBillSummary(RequestParam rp) throws DaoException {
		try {
			List<CreditBillDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.CreditBillDao.queryCreditBillSummary, rp);
			return dataList;
		} catch (Exception e) {
			log.error("queryCreditBillSummary", e);
			throw new DaoException(e);
		}
	}

}
