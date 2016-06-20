package com.srx.model.common;

import java.util.Collection;

public class Page<T> {
	public static final int DEFAULT_PAGE_INDEX = 1;
	public static final int DEFAULT_PAGE_SIZE = 20;
	/**
	 * 首页页码,默认为1
	 */
	private long pageIndex;
	/**
	 * 当前页索引
	 */
	private long currentPage;
	/**
	 * 每页显示数量,默认为20
	 */
	private long pageSize;
	/**
	 * 数据总量
	 */
	private long total;
	/**
	 * 数据页数量(总页数)
	 */
	private long totalPage = -1;
	/**
	 * 是否有上一页
	 */
	private boolean hasPrePage;
	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;

	private Collection<T> rows;

	public Page() {
		this.pageIndex = DEFAULT_PAGE_INDEX;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	public Page(long currentPage, long pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public Page(long currentPage, long pageSize, Collection<T> rows) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.rows = rows;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage < 0 ? (this.getTotal() % this.getPageSize() > 0 ? this.getTotal() / this.getPageSize() + 1
				: this.getTotal() / this.getPageSize()) : totalPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}
}
