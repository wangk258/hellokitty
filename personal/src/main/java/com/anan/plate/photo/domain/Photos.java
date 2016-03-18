package com.anan.plate.photo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 相片实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_photos")
public class Photos implements java.io.Serializable{
	
	private static final long serialVersionUID = 764770202044160554L;
	
	private Long id;
	/**
	 *  相片路径
	 */
	private String imageUrl;
	/**
	 * 相册ID
	 */
	private Long albumId;
	/**
	 * 是否显示在主页(最多5张)
	 */
	private Integer showInTheMainPage;
	/**
	 * 是否作为相册封面
	 */
	private Integer isCover;
	
	public Integer getShowInTheMainPage() {
		return showInTheMainPage;
	}
	public void setShowInTheMainPage(Integer showInTheMainPage) {
		this.showInTheMainPage = showInTheMainPage;
	}
	public Integer getIsCover() {
		return isCover;
	}
	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
}