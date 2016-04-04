package com.anan.plate.webSiteCollection.bo;

import common.bo.QueryObject;

public class WebSiteCollectionQueryObject extends QueryObject {
	
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void addCondition() {
	}

}
