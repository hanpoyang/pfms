package org.tom.pfms.common.dto;

public class OtherAccountDTO {
    private String accountId;
    private String username;
    private String password;
    private String accountDescription;
    private String accountStatus;
    private String visitAddr;
    private String accountOwner;
    
    
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	
	public String getVisitAddr() {
		return visitAddr;
	}
	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}
	@Override
	public String toString() {
		return "OtherAccountDTO [accountId=" + accountId + ", username="
				+ username + ", password=" + password + ", accountDescription="
				+ accountDescription + ", accountStatus=" + accountStatus
				+ ", visitAddr=" + visitAddr + ", accountOwner=" + accountOwner
				+ "]";
	}
}
