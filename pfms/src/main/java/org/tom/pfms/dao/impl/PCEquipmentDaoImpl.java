package org.tom.pfms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.PCEquipmentDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.PCEquipmentsDao;

@Repository
public class PCEquipmentDaoImpl extends BaseDao implements PCEquipmentsDao {

	@Override
	public List<PCEquipmentDTO> queryPCEquipments(RequestParam rp)
			throws DaoException {
		List<PCEquipmentDTO> pclist = sTemplate.selectList(ConstantSettings.SQL_ID.PCEquipmentDao.queryPcList, rp);
		return pclist;
	}
    
}
