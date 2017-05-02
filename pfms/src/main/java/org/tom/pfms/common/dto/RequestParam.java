package org.tom.pfms.common.dto;

import java.util.Date;

import org.tom.pfms.common.utils.ConstantSettings;


public class RequestParam {

	private static final long serialVersionUID = 1L;
	
	Object requestObject = null;
	int pageNo = 1;
	int pageSize = ConstantSettings.PAGE_SIZE;
	
	String limit = "0";
	String offset = "0";
	
	Date startDate = null;
	
	Date endDate = null;
	
	String loginUserName = "";
	
	public String getLoginUserName() {
		return loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	public String getLimit() {
		return String.valueOf(pageSize * pageNo);
	}
	
	public void setOffset(String offset) {
		this.offset = offset;
	}
	
	public String getOffset() {
		return String.valueOf(pageSize * (pageNo - 1));
	}
	public Object getRequestObject() {
		return requestObject;
	}
	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "RequestParam [requestObject=" + requestObject + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", limit=" + limit
				+ ", offset=" + offset + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", loginUserName=" + loginUserName
				+ "]";
	}
	
}
