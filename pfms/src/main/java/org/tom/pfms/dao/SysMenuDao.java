package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.DaoException;

public interface SysMenuDao {
	/**
	 * ��ѯ�˵�
	 * @return
	 * @throws DaoException
	 */
	List<SysMenuBean> queryMenus() throws DaoException;
	
	/**
	 * �����˵�
	 * @param rp
	 * @return
	 * @throws DaoException
	 */
	public void saveMenus(RequestParam rp) throws DaoException;

}
