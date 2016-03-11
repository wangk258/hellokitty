package com.xiaoxuanfeng.plate.productType.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.base.BaseBean;

@Entity
@Table(name = "t_productType")
public class ProductType extends BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = 764770202044160554L;

	private Long id;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}