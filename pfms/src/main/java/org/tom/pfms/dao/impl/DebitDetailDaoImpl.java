package org.tom.pfms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.DebitCardDetailCurvesDTO;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.DebitDetailImportDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.DebitDetailDao;

@Repository
public class DebitDetailDaoImpl extends BaseDao implements DebitDetailDao {

	@Override
	public PaginatedDTO<DebitDetailDTO> queryDebitDetails(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<DebitDetailDTO> pageDto = new PaginatedDTO<DebitDetailDTO>();
			List<DebitDetailDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.DebitDetailDao.queryDebitDetails,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.DebitDetailDao.queryDebitDetailCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryDebitDetails", e);
			throw new DaoException(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void importDebitDetails(RequestParam rp)
			throws DaoException {
		SqlSession session = null;
		try {
			SqlSessionFactory factory = sTemplate.getSqlSessionFactory();
			session = factory.openSession(ExecutorType.BATCH, false);
			List<DebitDetailImportDTO> list = (List<DebitDetailImportDTO>)rp.getRequestObject();
			String username = rp.getLoginUserName();
			for(DebitDetailImportDTO importDTO : list) {
				RequestParam tmpRp = new RequestParam(); 
				tmpRp.setRequestObject(importDTO);
				tmpRp.setLoginUserName(username);
				session.insert(ConstantSettings.SQL_ID.DebitDetailDao.importDebitDetails, tmpRp);
			}
			session.commit();
			
		} catch (Exception e) {
			log.error("importDebitDetails", e);
			throw new DaoException(e);
		}
		
	}

	@Override
	public List<DebitCardDetailCurvesDTO> queryIncomes(RequestParam rp)
			throws DaoException {
		try {
			List<DebitCardDetailCurvesDTO> resultList = sTemplate.selectList(ConstantSettings.SQL_ID.DebitDetailDao.queryIncomes,
					rp);
			return resultList;
		} catch (Exception e) {
			log.error("queryIncomes", e);
			throw new DaoException(e);
		}
	}

	@Override
	public List<DebitCardDetailCurvesDTO> queryOutcomes(RequestParam rp)
			throws DaoException {
		try {
			List<DebitCardDetailCurvesDTO> resultList = sTemplate.selectList(ConstantSettings.SQL_ID.DebitDetailDao.queryOutcomes,
					rp);
			return resultList;
		} catch (Exception e) {
			log.error("queryOutcomes", e);
			throw new DaoException(e);
		}
	}
	
	

}
