package org.tom.pfms.service;

import org.apache.log4j.Logger;

public class BaseService {
    protected Logger log = null;
	
	public BaseService() {
		log = Logger.getLogger(this.getClass());
	}
}
