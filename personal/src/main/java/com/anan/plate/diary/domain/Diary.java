package com.anan.plate.diary.domain;

import common.base.BaseBean;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;

@Entity
@Table(name = "t_diary")
@Alias("Diary")
public class Diary extends BaseBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	
	private Long date;
	
	private String week;
	
	private String weather;
	
	private String content;
	
	private String plainText;
	@Column(columnDefinition="varchar(10000)")
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	@Column(columnDefinition="varchar(10000)")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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