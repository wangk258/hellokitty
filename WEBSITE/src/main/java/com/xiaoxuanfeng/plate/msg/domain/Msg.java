package com.xiaoxuanfeng.plate.msg.domain;

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
@Table(name = "t_msg")
public class Msg extends BaseBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	
	private String name;
	
	private String phone;
	
	private String content;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Lob
	@Basic(fetch=FetchType.LAZY)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}