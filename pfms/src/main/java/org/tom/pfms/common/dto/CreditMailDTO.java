package org.tom.pfms.common.dto;

import java.util.Date;

public class CreditMailDTO {
    String mailId;
    String mailFolder;
    String mailSubject;
    String mailBody;
    Date receiveDate;
    String synFlag;
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailFolder() {
		return mailFolder;
	}
	public void setMailFolder(String mailFolder) {
		this.mailFolder = mailFolder;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getSynFlag() {
		return synFlag;
	}
	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}
	@Override
	public String toString() {
		return "CreditMailDTO [mailId=" + mailId + ", mailFolder=" + mailFolder
				+ ", mailSubject=" + mailSubject + ", mailBody=" + mailBody
				+ ", receiveDate=" + receiveDate + ", synFlag=" + synFlag + "]";
	}
	
}
