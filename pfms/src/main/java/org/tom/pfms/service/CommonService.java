package org.tom.pfms.service;

import org.apache.log4j.Logger;
import org.tom.pfms.common.exception.ServiceException;

public interface CommonService {
	
    static Logger logger = Logger.getLogger(CommonService.class);
	
	Object entryService(String json) throws ServiceException;
	
}
