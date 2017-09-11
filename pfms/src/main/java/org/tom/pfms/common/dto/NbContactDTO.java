package org.tom.pfms.common.dto;

public class NbContactDTO {
	private String contactId;
	private String contactName;
	private String contactCellphone;
	private String contactFixedphone;
	private String contactEmail;
	private String contactQq;
	private String contactNick;
	private String contactWechat;
	private String contactOther;
	private String contactAddress;
	private String contactCompany;
	private String contactDescrip;
	private String contactStatus;
	private String contactEnName;
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactCellphone() {
		return contactCellphone;
	}
	public void setContactCellphone(String contactCellphone) {
		this.contactCellphone = contactCellphone;
	}
	public String getContactFixedphone() {
		return contactFixedphone;
	}
	public void setContactFixedphone(String contactFixedphone) {
		this.contactFixedphone = contactFixedphone;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getContactQq() {
		return contactQq;
	}
	public void setContactQq(String contactQq) {
		this.contactQq = contactQq;
	}
	public String getContactNick() {
		return contactNick;
	}
	public void setContactNick(String contactNick) {
		this.contactNick = contactNick;
	}
	public String getContactWechat() {
		return contactWechat;
	}
	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}
	public String getContactOther() {
		return contactOther;
	}
	public void setContactOther(String contactOther) {
		this.contactOther = contactOther;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getContactCompany() {
		return contactCompany;
	}
	public void setContactCompany(String contactCompany) {
		this.contactCompany = contactCompany;
	}
	public String getContactDescrip() {
		return contactDescrip;
	}
	public void setContactDescrip(String contactDescrip) {
		this.contactDescrip = contactDescrip;
	}
	public String getContactStatus() {
		return contactStatus;
	}
	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}
	public String getContactEnName() {
		return contactEnName;
	}
	public void setContactEnName(String contactEnName) {
		this.contactEnName = contactEnName;
	}
	@Override
	public String toString() {
		return "NbContactDTO [contactId=" + contactId + ", contactName="
				+ contactName + ", contactCellphone=" + contactCellphone
				+ ", contactFixedphone=" + contactFixedphone
				+ ", contactEmail=" + contactEmail + ", contactQq=" + contactQq
				+ ", contactNick=" + contactNick + ", contactWechat="
				+ contactWechat + ", contactOther=" + contactOther
				+ ", contactAddress=" + contactAddress + ", contactCompany="
				+ contactCompany + ", contactDescrip=" + contactDescrip
				+ ", contactStatus=" + contactStatus + ", contactEnName="
				+ contactEnName + "]";
	}
	
}
