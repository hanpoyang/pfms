package org.tom.pfms.service;

import java.util.List;

import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.ServiceException;

public interface SysMenuService {
	/**
	 * ��ѯ�˵�
	 * @return
	 * @throws DaoException
	 */
	List<SysMenuBean> queryMenus() throws ServiceException;
}
