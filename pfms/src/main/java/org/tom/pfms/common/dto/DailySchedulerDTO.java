package org.tom.pfms.common.dto;

public class DailySchedulerDTO {
	private String schedulerId;
	private String schedulerSubject;
	private String schedulerDescrip;
	private String schedulerYear;
	private String schedulerMonth;
	private String schedulerDay;
	private String schedulerHour;
	private String schedulerMinute;
	private String schedulerDuration;
	private String schedulerOwner;
	private String schedulerStatus;
	public String getSchedulerId() {
		return schedulerId;
	}
	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}
	public String getSchedulerSubject() {
		return schedulerSubject;
	}
	public void setSchedulerSubject(String schedulerSubject) {
		this.schedulerSubject = schedulerSubject;
	}
	public String getSchedulerDescrip() {
		return schedulerDescrip;
	}
	public void setSchedulerDescrip(String schedulerDescrip) {
		this.schedulerDescrip = schedulerDescrip;
	}
	public String getSchedulerYear() {
		return schedulerYear;
	}
	public void setSchedulerYear(String schedulerYear) {
		this.schedulerYear = schedulerYear;
	}
	public String getSchedulerMonth() {
		return schedulerMonth;
	}
	public void setSchedulerMonth(String schedulerMonth) {
		this.schedulerMonth = schedulerMonth;
	}
	public String getSchedulerDay() {
		return schedulerDay;
	}
	public void setSchedulerDay(String schedulerDay) {
		this.schedulerDay = schedulerDay;
	}
	public String getSchedulerHour() {
		return schedulerHour;
	}
	public void setSchedulerHour(String schedulerHour) {
		this.schedulerHour = schedulerHour;
	}
	public String getSchedulerMinute() {
		return schedulerMinute;
	}
	public void setSchedulerMinute(String schedulerMinute) {
		this.schedulerMinute = schedulerMinute;
	}
	public String getSchedulerOwner() {
		return schedulerOwner;
	}
	public void setSchedulerOwner(String schedulerOwner) {
		this.schedulerOwner = schedulerOwner;
	}
	public String getSchedulerStatus() {
		return schedulerStatus;
	}
	public void setSchedulerStatus(String schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}
	
	public String getSchedulerDuration() {
		return schedulerDuration;
	}
	public void setSchedulerDuration(String schedulerDuration) {
		this.schedulerDuration = schedulerDuration;
	}
	@Override
	public String toString() {
		return "DailySchedulerDTO [schedulerId=" + schedulerId
				+ ", schedulerSubject=" + schedulerSubject
				+ ", schedulerDescrip=" + schedulerDescrip + ", schedulerYear="
				+ schedulerYear + ", schedulerMonth=" + schedulerMonth
				+ ", schedulerDay=" + schedulerDay + ", schedulerHour="
				+ schedulerHour + ", schedulerMinute=" + schedulerMinute
				+ ", schedulerDuration=" + schedulerDuration
				+ ", schedulerOwner=" + schedulerOwner + ", schedulerStatus="
				+ schedulerStatus + "]";
	}
	
}
