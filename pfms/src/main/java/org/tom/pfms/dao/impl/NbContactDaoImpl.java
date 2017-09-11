package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.DebitDetailDTO;
import org.tom.pfms.common.dto.NbContactDTO;
import org.tom.pfms.common.dto.PaginatedDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.NbContactDao;

@Repository
public class NbContactDaoImpl extends BaseDao implements NbContactDao {

	@Override
	public PaginatedDTO<NbContactDTO> queryNbContact(RequestParam rp)
			throws DaoException {
		try {
			PaginatedDTO<NbContactDTO> pageDto = new PaginatedDTO<NbContactDTO>();
			List<NbContactDTO> dataList = sTemplate.selectList(ConstantSettings.SQL_ID.NbContactDao.queryNbContact,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(dataList.toString());
			}
			pageDto.setDataList(dataList);
			Integer count = sTemplate.selectOne(ConstantSettings.SQL_ID.NbContactDao.queryNbContactCount,
					rp);
			if(log.isDebugEnabled()) {
			    log.debug(count);
			}
			pageDto.setRecordCount(count);
			return pageDto;
		} catch (Exception e) {
			log.error("queryNbContact", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void insertNbContact(RequestParam rp) throws DaoException {
		try {
			sTemplate.insert(ConstantSettings.SQL_ID.NbContactDao.insertNbContact, rp);
		} catch (Exception e) {
			log.error("insertNbContact", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void updateNbContact(RequestParam rp) throws DaoException {
		try {
			sTemplate.insert(ConstantSettings.SQL_ID.NbContactDao.updateNbContact, rp);
		} catch (Exception e) {
			log.error("updateNbContact", e);
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteNbContact(RequestParam rp) throws DaoException {
		try {
			sTemplate.insert(ConstantSettings.SQL_ID.NbContactDao.deleteNbContact, rp);
		} catch (Exception e) {
			log.error("deleteNbContact", e);
			throw new DaoException(e);
		}
	}

}
