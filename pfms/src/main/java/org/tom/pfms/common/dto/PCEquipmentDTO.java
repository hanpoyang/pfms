package org.tom.pfms.common.dto;

public class PCEquipmentDTO {
	private String equId;
	private String equCode;
	private String equCategory;
	private String equOwner;
	private String equMac;
	private String equName;
	private String equDesc;
	private String equStatus;
	private String equIp;
	private String isWol;
	private String superUser;
	private String superPassKey;

	public String getSuperUser() {
		return superUser;
	}

	public void setSuperUser(String superUser) {
		this.superUser = superUser;
	}

	public String getSuperPassKey() {
		return superPassKey;
	}

	public void setUserPassKey(String superPassKey) {
		this.superPassKey = superPassKey;
	}

	public String getEquId() {
		return equId;
	}

	public void setEquId(String equId) {
		this.equId = equId;
	}

	public String getEquCode() {
		return equCode;
	}

	public void setEquCode(String equCode) {
		this.equCode = equCode;
	}

	public String getEquCategory() {
		return equCategory;
	}

	public void setEquCategory(String equCategory) {
		this.equCategory = equCategory;
	}

	public String getEquOwner() {
		return equOwner;
	}

	public void setEquOwner(String equOwner) {
		this.equOwner = equOwner;
	}

	public String getEquMac() {
		return equMac;
	}

	public void setEquMac(String equMac) {
		this.equMac = equMac;
	}

	public String getEquName() {
		return equName;
	}

	public void setEquName(String equName) {
		this.equName = equName;
	}

	public String getEquDesc() {
		return equDesc;
	}

	public void setEquDesc(String equDesc) {
		this.equDesc = equDesc;
	}

	public String getEquStatus() {
		return equStatus;
	}

	public void setEquStatus(String equStatus) {
		this.equStatus = equStatus;
	}

	public String getEquIp() {
		return equIp;
	}

	public void setEquIp(String equIp) {
		this.equIp = equIp;
	}

	public String getIsWol() {
		return isWol;
	}

	public void setIsWol(String isWol) {
		this.isWol = isWol;
	}

	@Override
	public String toString() {
		return "PCEquipmentsDTO [equId=" + equId + ", equCode=" + equCode
				+ ", equCategory=" + equCategory + ", equOwner=" + equOwner
				+ ", equMac=" + equMac + ", equName=" + equName + ", equDesc="
				+ equDesc + ", equStatus=" + equStatus + ", equIp=" + equIp
				+ ", isWol=" + isWol + "]";
	}

}
