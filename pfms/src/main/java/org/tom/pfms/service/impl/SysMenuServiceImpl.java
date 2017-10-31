package org.tom.pfms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.tom.pfms.common.dto.RequestParam;
import org.tom.pfms.common.dto.SysMenuBean;
import org.tom.pfms.common.exception.DaoException;
import org.tom.pfms.common.exception.ServiceException;
import org.tom.pfms.dao.SysMenuDao;
import org.tom.pfms.service.BaseService;
import org.tom.pfms.service.SysMenuService;

@Service
public class SysMenuServiceImpl extends BaseService implements SysMenuService {
    
	@Resource
	SysMenuDao sysMenuDao = null;
	
	@Override
	public List<SysMenuBean> queryMenus() throws ServiceException {
		try{
			List<SysMenuBean> resultMenuList = new ArrayList<SysMenuBean>();
			List<SysMenuBean> menuList = sysMenuDao.queryMenus();
			for(SysMenuBean menu : menuList) {
				String parentMenuId = menu.getParentMenuId();
				if(StringUtils.isEmpty(parentMenuId)){
					menu.setChildMenus(genMenu(menuList, menu.getMenuId()));
					resultMenuList.add(menu);
				}
			}
			return resultMenuList;
		} catch(DaoException e) {
			log.error("queryMenus", e);
			throw new ServiceException(e);
		}
	}
	
	/*
	 * …Ë÷√≤Àµ•
	 */
	private List<SysMenuBean> genMenu(List<SysMenuBean> menus, String parentMenuId){
		List<SysMenuBean> list = new ArrayList<SysMenuBean>();
		for(SysMenuBean menu: menus) {
			String parentId = menu.getParentMenuId();
			if(parentMenuId.equals(parentId)){
				menu.setChildMenus(genMenu(menus, menu.getMenuId()));
				list.add(menu);
			}
		}
		return list;
	}

	@Override
	public void saveMenus(RequestParam rp) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			sysMenuDao.saveMenus(rp);
		}catch(DaoException e) {
			log.error("saveMenus", e);
			throw new ServiceException(e);
		}
	}

	
}
