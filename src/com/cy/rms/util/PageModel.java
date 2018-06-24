package com.cy.rms.util;

import java.util.List;

/**
 * 封装分页信息
 * @author Administrator
 *
 */
public class PageModel<E> {

	//结果集
	private List<E> list;
	
	//查询记录数
	private int totalRecords;
	
	//每页多少条数据
	private int pageSize;
	
	//第几页
	private int pageNo;
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	
	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}