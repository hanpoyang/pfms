package org.tom.pfms.dao.impl;

import org.springframework.stereotype.Repository;
import org.tom.pfms.common.dto.TaskJobBean;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.utils.ConstantSettings;
import org.tom.pfms.dao.BaseDao;
import org.tom.pfms.dao.TaskJobDao;

@Repository
public class TaskJobDaoImpl extends BaseDao implements TaskJobDao {
	@Override
	public TaskJobBean queryTask(String jobId) throws DaoException {
		try{
			return (TaskJobBean)sTemplate.selectOne(ConstantSettings.SQL_ID.TaskJobDao.queryTask, jobId);
		}catch(Exception e) {
			log.error("queryTask", e);
			throw new DaoException(e);
		}
		
	}

	@Override
	public void updateTask(TaskJobBean jobBean) throws DaoException {
		try{
			sTemplate.update(ConstantSettings.SQL_ID.TaskJobDao.updateTask, jobBean);
		}catch(Exception e) {
			log.error("updateTask", e);
			throw new DaoException(e);
		}
		
	}
	
	@Override
	public void execParseMailProc() throws DaoException {
		try{
			sTemplate.update(ConstantSettings.SQL_ID.TaskJobDao.execParseMailProc);
		}catch(Exception e) {
			log.error("execParseMailProc", e);
			throw new DaoException(e);
		}
	}

}
