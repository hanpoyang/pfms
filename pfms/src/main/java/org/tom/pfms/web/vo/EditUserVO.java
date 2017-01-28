package org.tom.pfms.web.vo;

import java.io.Serializable;

public class EditUserVO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	private String passWord;

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWorld) {
		this.passWord = passWorld;
	}

	private String newPassWord;
	
	private String repeatNewPassWord;
	
	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	

	public String getRepeatNewPassWord() {
		return repeatNewPassWord;
	}

	public void setRepeatNewPassWord(String repeatNewPassWord) {
		this.repeatNewPassWord = repeatNewPassWord;
	}

	@Override
	public String toString() {
		return "EditUserVO [passWord=" + passWord + ", newPassWord="
				+ newPassWord + ", repeatNewPassWord=" + repeatNewPassWord
				+ "]";
	}

}
