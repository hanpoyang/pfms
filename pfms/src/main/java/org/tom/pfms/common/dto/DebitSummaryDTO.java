package org.tom.pfms.common.dto;

public class DebitSummaryDTO {
    private String bankName;
    private String bankCode;
    private String currencyType;
    private String totalBalance;
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}
	@Override
	public String toString() {
		return "DebitSummaryDTO [bankName=" + bankName + ", bankCode="
				+ bankCode + ", currencyType=" + currencyType
				+ ", totalBalance=" + totalBalance + "]";
	}
	
}
