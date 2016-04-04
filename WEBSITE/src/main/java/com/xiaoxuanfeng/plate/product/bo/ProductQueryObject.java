package com.xiaoxuanfeng.plate.product.bo;

import org.apache.commons.lang.StringUtils;

import common.bo.QueryObject;

public class ProductQueryObject extends QueryObject {

	private String name;
	
	private Long productType;
	
	private String pkey;
	
	private String season;
	
	private String month;
	
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Override
	public void addCondition() {
		if(StringUtils.isNotEmpty(this.name)){
			this.addQuery("  and name like ? ", "%"+name+"%");
		}
		
		if(null !=this.productType && -1 != this.productType){
			this.addQuery("  and productType.id = ? ", productType);
		}
		
		if(StringUtils.isNotBlank(year)){
			this.addQuery(" and year =?",year);
		}
		
		if(StringUtils.isNotBlank(month) && !"-1".equals(month)){
			season=null;
			this.addQuery(" and month = ? ",month);
		}
		
		if(null != season ){
			String str="(";
			for(int i=Integer.parseInt(season)*3;i>=Integer.parseInt(season)*3-2;i--){
				 str+=i+",";
			}
			str=str.substring(0,str.length()-1)+")";
			this.addQuery(" and month in "+str);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getProductType() {
		return productType;
	}
	public void setProductType(Long productType) {
		this.productType = productType;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
}
