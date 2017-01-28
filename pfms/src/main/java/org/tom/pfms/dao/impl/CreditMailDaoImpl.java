package org.tom.pfms.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.CreditMailDao;

@Repository
public class CreditMailDaoImpl extends BaseDao implements CreditMailDao {

	@Override
	public void save(Map<String, Object> mailMap) throws DaoException {
		try{
			log.info("-------------------------------------------------------------");
			log.info("--mail_info:"+mailMap.get("Subject"));
			log.info("--mail_info:"+mailMap.get("Folder"));
			log.info("--mail_info:"+mailMap.get("ReceivedDate"));
			log.info("-------------------------------------------------------------");
			sTemplate.insert(ConstantSettings.SQL_ID.CreditMailDao.save, mailMap);
		}catch(Exception e) {
			log.error("queryTask", e);
			throw new DaoException(e);
		}
	}

}
