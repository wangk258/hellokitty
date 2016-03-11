package com.xiaoxuanfeng.plate.navigation.bo;

import org.apache.commons.lang.StringUtils;
import org.bo.QueryObject;

public class NavigationQueryObject extends QueryObject {
	
	private String name;
	
	private String url;
	
	public String getName() {
		return name;
	}

	


	public void setName(String name) {
		this.name = name;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	@Override
	public void addCondition() {
		if(StringUtils.isNotBlank(name)){
			this.addQuery(" and name like ? ","%"+name+"%");
		}
		if(StringUtils.isNotBlank(url)){
			this.addQuery(" and url like ? ","%"+url+"%");
		}
		
		this.addQuery(" order by sort ");
	}

	
}
