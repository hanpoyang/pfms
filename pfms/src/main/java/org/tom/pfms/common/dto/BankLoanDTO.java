package org.tom.pfms.common.dto;

public class BankLoanDTO {
    String creditId;
    String bankName;
    String accountName;
    String cardNumber;
    String status;
    String validDate;
    String bankCode;
	String cardOwner;
    String securityCode;
    String servicePhone;
	public String getCreditId() {
		return creditId;
	}
	
	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	@Override
	public String toString() {
		return "BankLoanDTO [creditId=" + creditId + ", bankName=" + bankName
				+ ", accountName=" + accountName + ", cardNumber=" + cardNumber
				+ ", status=" + status + ", validDate=" + validDate
				+ ", bankCode=" + bankCode + ", cardOwner=" + cardOwner
				+ ", securityCode=" + securityCode + ", servicePhone="
				+ servicePhone + "]";
	}
}
