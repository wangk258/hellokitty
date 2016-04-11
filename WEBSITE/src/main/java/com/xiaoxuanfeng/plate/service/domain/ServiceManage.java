package com.xiaoxuanfeng.plate.service.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import common.rdbms.base.BaseBean;

@Entity
@Table(name = "t_serviceManage")
public class ServiceManage extends BaseBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	
	private String content;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}