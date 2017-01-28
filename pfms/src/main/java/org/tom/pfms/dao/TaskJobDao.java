package org.tom.pfms.dao;

import org.tom.pfms.common.dto.TaskJobBean;
import org.tom.pfms.common.exception.DaoException;

public interface TaskJobDao {
    TaskJobBean queryTask(String jobName) throws DaoException;
    void updateTask(TaskJobBean jobBean) throws DaoException;
    void execParseMailProc() throws DaoException;
}
