package cn.com.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据对象
 * 
 */
public class PageResult {
	/**
	 * 当前页码
	 */
	private int pageNum;
	/**
	 * 上一页页码
	 */
	private int prevPageNum;
	/**
	 * 下一页页码
	 */
	private int nextPageNum;
	/**
	 * 是否有上一页
	 */
	private boolean isPrevPage;
	/**
	 * 是否有下一页
	 */
	private boolean isNextPage;

	/**
	 * 每页数量
	 */
	private int pageSize;
	/**
	 * 记录总数
	 */
	private long totalSize;
	/**
	 * 页码总数
	 */
	private int totalPages;
	/**
	 * 数据模型
	 */
	private List<?> content;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<?> getContent() {
		return content;
	}
	public void setContent(List<?> content) {
		this.content = content;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public boolean getIsPrevPage() {
		return isPrevPage;
	}

	public void setIsPrevPage(boolean prevPage) {
		isPrevPage = prevPage;
	}

	public boolean getIsNextPage() {
		return isNextPage;
	}

	public void setIsNextPage(boolean nextPage) {
		isNextPage = nextPage;
	}
}
