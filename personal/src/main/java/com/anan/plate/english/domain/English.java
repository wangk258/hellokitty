package com.anan.plate.english.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_english")
public class English implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String mp3Url;
	
	private String orginalName;
	
	public String getOrginalName() {
		return orginalName;
	}
	public void setOrginalName(String orginalName) {
		this.orginalName = orginalName;
	}
	private String text;
	
	public String getMp3Url() {
		return mp3Url;
	}
	public void setMp3Url(String mp3Url) {
		this.mp3Url = mp3Url;
	}
	@Column(columnDefinition="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}