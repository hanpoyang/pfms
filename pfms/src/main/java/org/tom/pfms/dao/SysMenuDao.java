package org.tom.pfms.dao;

import java.util.List;

import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.DaoException;

public interface SysMenuDao {
	/**
	 * ≤È—Ø≤Àµ•
	 * @return
	 * @throws DaoException
	 */
	List<SysMenuBean> queryMenus() throws DaoException;

}
