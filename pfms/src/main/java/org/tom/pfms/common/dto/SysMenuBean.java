package org.tom.pfms.common.dto;

import java.util.List;

public class SysMenuBean {
    String menuId;
    String menuName;
    List<SysMenuBean> childMenus = null;
    String menuUrl;
    String parentMenuId;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		if(this.menuUrl == null || this.menuUrl.length() == 0)this.menuUrl = "#";
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	
	public List<SysMenuBean> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<SysMenuBean> childMenus) {
		this.childMenus = childMenus;
	}
	
	public String getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	
	@Override
	public String toString() {
		return "SysMenuBean [menuId=" + menuId + ", menuName=" + menuName
				+ ", childMenus=" + childMenus + ", menuUrl=" + menuUrl
				+ ", parentMenuId=" + parentMenuId + "]";
	}
	
}
