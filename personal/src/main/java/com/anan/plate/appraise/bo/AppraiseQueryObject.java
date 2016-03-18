package com.anan.plate.appraise.bo;

import org.apache.commons.lang.StringUtils;
import org.bo.QueryObject;

public class AppraiseQueryObject extends QueryObject {
	
	private String type;
	
	private Long typeId;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Override
	public void addCondition() {
		if(StringUtils.isNotBlank(type)){
			this.addQuery(" and type like ?", "%"+type+"%");
		}
		if(typeId!=null){
			this.addQuery(" and typeId = ?", typeId);
		}
	}

}
