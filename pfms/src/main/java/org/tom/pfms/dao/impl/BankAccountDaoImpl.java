package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.BankLoanDTO;
import org.tom.pfms.common.dto.KeyValuePair;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BankAccountDao;
import org.tom.pfms.dao.BaseDao;

@Repository
public class BankAccountDaoImpl extends BaseDao implements BankAccountDao {
 
	/**
	 * 查询
	 */
	@Override
	public PaginatedDTO<BankLoanDTO> queryCreditList(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<BankLoanDTO> pageDto = new PaginatedDTO<BankLoanDTO>();
			List<BankLoanDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.BankAccountDao.queryCreditList,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.BankAccountDao.queryCreditCount,
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

	@Override
	public void inValidLoan(RequestParam rp) throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.BankAccountDao.update ,rp);
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
		
	}

	@Override
	public void saveLoan(RequestParam rp) throws DaoException {
		try {
			sTemplate.update(ConstantSettings.SQL_ID.BankAccountDao.save ,rp);
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
		
	}
	
	/**
	 * 获取银行列表
	 */
	@Override
	public List<KeyValuePair> queryBanks() throws DaoException {
		
		try {
			List<KeyValuePair> result = sTemplate.selectList(ConstantSettings.SQL_ID.BankAccountDao.queryBanks);
			return result;
		} catch (Exception e) {
			log.error("queryOtherAccountsList", e);
			throw new DaoException(e);
		}
	}
    
}
