package org.tom.pfms.common.dto;

import java.util.List;

import org.tom.pfms.common.utils.ConstantSettings;

public class PaginatedDTO<T> {
	
	int pageNo = 0;
	
	int pageSize = ConstantSettings.PAGE_SIZE;
	
	int pageCount = 0;
	
	int recordCount = 0;
	
	List<T> dataList = null;

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

	public int getPageCount() {
		if(recordCount > 0) {
		    pageCount = (int)((recordCount - 1) / pageSize) + 1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "PaginatedDTO [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", recordCount=" + recordCount
				+ ", dataList=" + dataList + "]";
	}

}
