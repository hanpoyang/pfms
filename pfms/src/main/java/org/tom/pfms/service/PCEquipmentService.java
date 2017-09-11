package org.tom.pfms.service;

import java.util.List;

import org.tom.pfms.common.dto.PCEquipmentDTO;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.exception.ServiceException;

public interface PCEquipmentService {
    public List<PCEquipmentDTO> queryPcList(RequestParam rp) throws ServiceException;
    
    public void wakePC(String ip, String mac);
    
    public void shutdown(String username, String ip);
}
