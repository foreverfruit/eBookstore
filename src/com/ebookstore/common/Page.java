package com.ebookstore.common;

import java.util.List;

public class Page {

	private List records;
	
	private int pageIndex;//当前页码
	private int totalPageCount;//总页码
	private int prePageIndex;//上一页
	private int nextPageIndex;//下一页
	
	private int pageSize=3;//每页显示的记录条数
	private int totalRecordsCount;//总记录条数
	
	private int startRecordIndex;//每页开始记录的索引
	
	private String url;//查询分页的请求的地址
	
	public Page(int pageIndex, int totalRecordsCount) {
		this.pageIndex = pageIndex;
		this.totalRecordsCount = totalRecordsCount;
		//计算总页码
		totalPageCount = (totalRecordsCount+pageSize-1)/pageSize;
		//计算开始记录的索引
		startRecordIndex = (pageIndex-1)*pageSize;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPrePageIndex() {
		prePageIndex = pageIndex - 1;
		if(prePageIndex<1){
			prePageIndex = 1;
		}
		return prePageIndex;
	}

	public void setPrePageIndex(int prePageIndex) {
		this.prePageIndex = prePageIndex;
	}

	public int getNextPageIndex() {
		nextPageIndex = pageIndex + 1;
		if(nextPageIndex>totalPageCount){
			nextPageIndex = totalPageCount;
		}
		return nextPageIndex;
	}

	public void setNextPageIndex(int nextPageIndex) {
		this.nextPageIndex = nextPageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public int getStartRecordIndex() {
		return startRecordIndex;
	}

	public void setStartRecordIndex(int startRecordIndex) {
		this.startRecordIndex = startRecordIndex;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page [pageIndex=" + pageIndex + ", totalPageCount="
				+ totalPageCount + ", prePageIndex=" + prePageIndex
				+ ", nextPageIndex=" + nextPageIndex + ", pageSize=" + pageSize
				+ ", totalRecordsCount=" + totalRecordsCount
				+ ", startRecordIndex=" + startRecordIndex + ", url=" + url
				+ "]";
	}
	
}
