package com.skycity.util;

import java.util.Collections;
import java.util.List;

public class PageInfo<T> {
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	protected int pageNo = 1;
	protected int pageSize = 1;
	protected String orderBy = null;
	protected String order = null;

	protected List<T> rows = Collections.emptyList();
	protected long total = -1;

	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return (pageNo - 1) * pageSize;
	}

	public PageInfo() {
	}
	
	public PageInfo(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public PageInfo<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public PageInfo<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public String toString(){
		return "当前第"+pageNo+"页,每页显示"+pageSize+"条记录,共"+total+"条记录.";
	}
}