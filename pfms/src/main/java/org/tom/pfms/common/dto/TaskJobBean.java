package org.tom.pfms.common.dto;

import java.util.Date;

public class TaskJobBean {
	String synTaskId;
    String synFlag;
    Date lastSynDate;
    long lastSynDuration;
	public String getSynFlag() {
		return synFlag;
	}
	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}
	public Date getLastSynDate() {
		return lastSynDate;
	}
	public void setLastSynDate(Date lastSynDate) {
		this.lastSynDate = lastSynDate;
	}
	public long getLastSynDuration() {
		return lastSynDuration;
	}
	public void setLastSynDuration(long lastSynDuration) {
		this.lastSynDuration = lastSynDuration;
	}
	public String getSynTaskId() {
		return synTaskId;
	}
	public void setSynTaskId(String synTaskId) {
		this.synTaskId = synTaskId;
	}
	@Override
	public String toString() {
		return "TaskJobBean [synTaskId=" + synTaskId + ", synFlag=" + synFlag
				+ ", lastSynDate=" + lastSynDate + ", lastSynDuration="
				+ lastSynDuration + "]";
	}
    
    
}
