package com.xiaoxuanfeng.plate.editor.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.base.BaseBean;

@Entity
@Table(name = "t_editor")
public class Editor extends BaseBean implements java.io.Serializable {

	private static final long serialVersionUID = 764770202044160554L;

	private Long id;

	private Long pkey;

	private String ckey;

	private String content;

	private Integer navigationType;

	private String url;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(length = 20)
	public Long getPkey() {
		return pkey;
	}

	public void setPkey(Long pkey) {
		this.pkey = pkey;
	}

	@Column(length = 30)
	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNavigationType() {
		return navigationType;
	}

	public void setNavigationType(Integer navigationType) {
		this.navigationType = navigationType;
	}
	@Column(length=50)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}