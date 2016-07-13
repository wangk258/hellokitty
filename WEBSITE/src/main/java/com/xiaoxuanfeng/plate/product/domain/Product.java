package com.xiaoxuanfeng.plate.product.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.xiaoxuanfeng.plate.common.constants.CommonConstants;
import com.xiaoxuanfeng.plate.productType.domain.ProductType;

import common.base.BaseBean;

@Entity
@Table(name = "t_product")
public class Product extends BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = 764770202044160554L;

	private Long id;

	private String name;

	private String description;

	private String imageUrl;

	private Double price;
	
	private ProductType productType;
	
	private String material;
	
	private String year;
	
	private String month;

	private Integer showHomePage = CommonConstants.SHOWHOMEPAGESTATE.NO.getValue();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getShowHomePage() {
		return showHomePage;
	}

	public void setShowHomePage(Integer showHomePage) {
		this.showHomePage = showHomePage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@JoinColumn()
	@ManyToOne(fetch=FetchType.EAGER)
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Column(length=20)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@Column(length=20)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	@Column(length=50)
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	
}