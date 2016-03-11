package com.xiaoxuanfeng.plate.banner.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.base.BaseBean;

import com.xiaoxuanfeng.plate.common.constants.CommonConstants;

@Entity
@Table(name = "t_banner")
public class Banner extends BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = 764770202044160554L;

	private Long id;

	private String imageUrl;

	private Integer showHomePage = CommonConstants.SHOWHOMEPAGESTATE.NO.getValue();

	
	public Banner(){}
	public Banner(String imageUrl) {
		super();
		this.imageUrl = imageUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Integer getShowHomePage() {
		return showHomePage;
	}

	public void setShowHomePage(Integer showHomePage) {
		this.showHomePage = showHomePage;
	}

}