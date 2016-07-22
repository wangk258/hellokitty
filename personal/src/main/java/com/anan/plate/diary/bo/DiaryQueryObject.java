package com.anan.plate.diary.bo;

import common.bo.QueryObject;

public class DiaryQueryObject extends QueryObject {
	
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public void addCondition() {
	}

}
