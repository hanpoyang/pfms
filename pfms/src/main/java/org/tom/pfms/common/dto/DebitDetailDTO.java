package org.tom.pfms.common.dto;

public class DebitDetailDTO {
	private String detailId;
	private String debitCardId;
	private String purchaseDate;
	private String purchaseTime;
	private String income;
	private String outcome;
	private String balance;
	private String purchaseType;
	private String purchaseRemark;
	private String receiveAccountName;
	private String receiveAccountCode;
	private String receiveBankName;
	private String purchaseChannel;
	private String fee;
	private String purchaseStatus;
	private String currencyType;
	private String cardNumber;
	private String bankCode;
	private String bankName;
	private String accountName;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getDebitCardId() {
		return debitCardId;
	}
	public void setDebitCardId(String debitCardId) {
		this.debitCardId = debitCardId;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getPurchaseRemark() {
		return purchaseRemark;
	}
	public void setPurchaseRemark(String purchaseRemark) {
		this.purchaseRemark = purchaseRemark;
	}
	public String getReceiveAccountName() {
		return receiveAccountName;
	}
	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}
	public String getReceiveAccountCode() {
		return receiveAccountCode;
	}
	public void setReceiveAccountCode(String receiveAccountCode) {
		this.receiveAccountCode = receiveAccountCode;
	}
	public String getReceiveBankName() {
		return receiveBankName;
	}
	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}
	public String getPurchaseChannel() {
		return purchaseChannel;
	}
	public void setPurchaseChannel(String purchaseChannel) {
		this.purchaseChannel = purchaseChannel;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Override
	public String toString() {
		return "DebitDetailDTO [detailId=" + detailId + ", debitCardId="
				+ debitCardId + ", purchaseDate=" + purchaseDate
				+ ", purchaseTime=" + purchaseTime + ", income=" + income
				+ ", outcome=" + outcome + ", balance=" + balance
				+ ", purchaseType=" + purchaseType + ", purchaseRemark="
				+ purchaseRemark + ", receiveAccountName=" + receiveAccountName
				+ ", receiveAccountCode=" + receiveAccountCode
				+ ", receiveBankName=" + receiveBankName + ", purchaseChannel="
				+ purchaseChannel + ", fee=" + fee + ", purchaseStatus="
				+ purchaseStatus + ", currencyType=" + currencyType
				+ ", cardNumber=" + cardNumber + ", bankCode=" + bankCode
				+ ", bankName=" + bankName + ", accountName=" + accountName
				+ "]";
	}
	 
}
