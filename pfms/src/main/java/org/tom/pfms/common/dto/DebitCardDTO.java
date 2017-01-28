package org.tom.pfms.common.dto;


public class DebitCardDTO {
	String debitCardId;
	String bankName;
	String bankCode;
	String accountName;
	String cardNumber;
	String issueBankName;
	String cardBalance;
	String cardDescription;
	String cardStatus;
	String servicePhone;
	String cardOwner;
	String currencyType;
	String belongToUser;
	
	public String getBelongToUser() {
		return belongToUser;
	}
	public void setBelongToUser(String belongToUser) {
		this.belongToUser = belongToUser;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getDebitCardId() {
		return debitCardId;
	}
	public void setDebitCardId(String debitCardId) {
		this.debitCardId = debitCardId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getIssueBankName() {
		return issueBankName;
	}
	public void setIssueBankName(String issueBankName) {
		this.issueBankName = issueBankName;
	}
	public String getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}
	public String getCardDescription() {
		return cardDescription;
	}
	public void setCardDescription(String cardDescription) {
		this.cardDescription = cardDescription;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getServicePhone() {
		return servicePhone;
	}
	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	@Override
	public String toString() {
		return "DebitCardDTO [debitCardId=" + debitCardId + ", bankName="
				+ bankName + ", bankCode=" + bankCode + ", accountName="
				+ accountName + ", cardNumber=" + cardNumber
				+ ", issueBankName=" + issueBankName + ", cardBalance="
				+ cardBalance + ", cardDescription=" + cardDescription
				+ ", cardStatus=" + cardStatus + ", servicePhone="
				+ servicePhone + ", cardOwner=" + cardOwner + ", currencyType="
				+ currencyType + ", belongToUser=" + belongToUser + "]";
	}
	

}
