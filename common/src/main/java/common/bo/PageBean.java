package common.bo;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {

	// 传递的参数或是配置的参数
	private Integer currentPage; // 当前页
	private Integer pageSize; // 每页显示多少条记录

	// 查询数据库

	private List<T> recordList = new ArrayList<T>(); // 本页的数据列表
	private Integer recordCount; // 总记录数

	// 计算
	private Integer pageCount; // 总页数
	private Integer beginPageIndex; // 页码列表的开始索引（包含）
	private Integer endPageIndex; // 页码列表的结束索引（包含）

	public PageBean() {
	};

	/**
	 * 只接受4个必要的属性，会自动的计算出其他3个属性的值
	 * 
	 * @param currentPage
	 * @param pageSize
	 * @param recordList
	 * @param recordCount
	 */
	public PageBean(Integer currentPage, Integer pageSize, Integer recordCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;

		if (currentPage == null || currentPage <= 1) {
			this.currentPage = 1;
		}

		if (pageSize == null || pageSize <= 0) {
			this.pageSize = 15;
		}
		// 计算 pageCount
		pageCount = (recordCount + pageSize - 1) / pageSize;

		if (currentPage != null && currentPage >= pageCount) {
			this.currentPage = pageCount;
		}

		// 计算 beginPageIndex 与 endPageIndex
		// >> 总页码小于等于10页时，全部显示
		if (pageCount <= 6) {
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}
		// >> 总页码大于10页时，就只显示当前页附近的共10个页码
		else {
			// 默认显示 前3页 + 当前页 + 后4页
			beginPageIndex = currentPage - 2; // 7 - 2 = 5;
			endPageIndex = currentPage + 3; // 7 + 3 = 10; --> 5 ~ 10

			// 如果前面不足4个页码时，则显示前10页
			if (beginPageIndex < 1) {
				beginPageIndex = 1;
				endPageIndex = 6;
			}
			// 如果后面不足5个页码时，则显示后10页
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 5;
			}
		}
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getBeginPageIndex() {
		return beginPageIndex;
	}

	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", recordList=" + recordList
				+ ", recordCount=" + recordCount + ", pageCount=" + pageCount + ", beginPageIndex=" + beginPageIndex
				+ ", endPageIndex=" + endPageIndex + "]";
	}

}
