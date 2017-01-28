package org.tom.pfms.common.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	private String passWord;
	private String userStatus;
	private Date activeDate;
	private Date expireDate;
	private String lastLoginIp;
	private Date lastLoginDate;
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName
				+ ", passWord=" + passWord  + ", userStatus="
				+ userStatus + ", activeDate=" + activeDate + ", expireDate="
				+ expireDate + ", lastLoginIp=" + lastLoginIp
				+ ", lastLoginDate=" + lastLoginDate + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
  
}
