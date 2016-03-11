package org.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class QueryObject {
	
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	private Integer currentPage=1;
	
	private Integer pageSize=15;
	
	private List<String> conditions=new ArrayList<String>();
	
	private List<Object> params=new ArrayList<Object>();
	
	public Boolean flag=false;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getConditions() {
		if(!flag){
			this.addCondition();
			flag=true;
		}
		return conditions;
	}

	public List<Object> getParams() {
		return params;
	}
	
	public void addQuery(String hql,Object...objects){
		this.conditions.add(hql);
		this.params.addAll(Arrays.asList(objects));
	}
	
	public abstract void addCondition();
}
