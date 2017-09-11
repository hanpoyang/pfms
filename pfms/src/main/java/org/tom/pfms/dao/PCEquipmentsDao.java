package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.dto.PCEquipmentDTO;

public interface PCEquipmentsDao {
	
	List<PCEquipmentDTO> queryPCEquipments(RequestParam rp) throws DaoException ;

}
