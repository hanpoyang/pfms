package org.tom.pfms.common.dto;

public class DebitCardDetailCurvesDTO {
    String ym;
    String income;
    String outcome;
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
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
	@Override
	public String toString() {
		return "DebitCardDetailCurvesDTO [ym=" + ym + ", income=" + income
				+ ", outcome=" + outcome + "]";
	}
    
}
