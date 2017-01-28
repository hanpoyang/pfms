package org.tom.pfms.common.dto;

import java.math.BigDecimal;
import java.util.Date;

public class CreditBillDTO {
	String creditBillId;
	String creditCard;
	Date billDate;
	Date imburseDate;
	Integer billYear;
	Integer billMonth;
	String billContent;
	BigDecimal cnyImburseAmount;
	BigDecimal cnyleastAmount;
	BigDecimal usdImburseAmount;
	BigDecimal usdleastAmount;
	String bankName;
	String isClear;
	String servicePhone;

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getCnyImburseAmount() {
		return cnyImburseAmount;
	}

	public void setCnyImburseAmount(BigDecimal cnyImburseAmount) {
		this.cnyImburseAmount = cnyImburseAmount;
	}

	public BigDecimal getCnyleastAmount() {
		return cnyleastAmount;
	}

	public void setCnyleastAmount(BigDecimal cnyleastAmount) {
		this.cnyleastAmount = cnyleastAmount;
	}

	public BigDecimal getUsdImburseAmount() {
		return usdImburseAmount;
	}

	public void setUsdImburseAmount(BigDecimal usdImburseAmount) {
		this.usdImburseAmount = usdImburseAmount;
	}

	public BigDecimal getUsdleastAmount() {
		return usdleastAmount;
	}

	public void setUsdleastAmount(BigDecimal usdleastAmount) {
		this.usdleastAmount = usdleastAmount;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getImburseDate() {
		return imburseDate;
	}

	public void setImburseDate(Date imburseDate) {
		this.imburseDate = imburseDate;
	}

	
	public Integer getBillYear() {
		return billYear;
	}

	public void setBillYear(Integer billYear) {
		this.billYear = billYear;
	}

	public Integer getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(Integer billMonth) {
		this.billMonth = billMonth;
	}

	public String getBillContent() {
		return billContent;
	}

	public void setBillContent(String billContent) {
		this.billContent = billContent;
	}
	
	public String getCreditBillId() {
		return creditBillId;
	}

	public void setCreditBillId(String creditBillId) {
		this.creditBillId = creditBillId;
	}

	@Override
	public String toString() {
		return "CreditBillDTO [creditBillId=" + creditBillId + ", creditCard="
				+ creditCard + ", billDate=" + billDate + ", imburseDate="
				+ imburseDate + ", billYear=" + billYear + ", billMonth="
				+ billMonth + ", billContent=" + billContent
				+ ", cnyImburseAmount=" + cnyImburseAmount
				+ ", cnyleastAmount=" + cnyleastAmount + ", usdImburseAmount="
				+ usdImburseAmount + ", usdleastAmount=" + usdleastAmount
				+ ", bankName=" + bankName + ", isClear=" + isClear
				+ ", servicePhone=" + servicePhone + "]";
	}

}
