package com.anan.plate.photo.domain;

import common.base.BaseBean;

import javax.persistence.*;
/**
 * 相册实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_album")
public class Album extends BaseBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	/**
	 * 相册名称
	 */
	private String name;
	/**
	 * 封面图片
	 */
	private String imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
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
	
}