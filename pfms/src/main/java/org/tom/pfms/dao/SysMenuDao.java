package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.DaoException;

public interface SysMenuDao {
	/**
	 * 查询菜单
	 * @return
	 * @throws DaoException
	 */
	List<SysMenuBean> queryMenus() throws DaoException;
	
	/**
	 * 新增菜单
	 * @param rp
	 * @return
	 * @throws DaoException
	 */
	public void saveMenus(RequestParam rp) throws DaoException;

}
