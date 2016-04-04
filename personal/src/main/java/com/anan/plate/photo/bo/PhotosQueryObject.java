package com.anan.plate.photo.bo;

import common.bo.QueryObject;

public class PhotosQueryObject extends QueryObject {
	
	private Long albumId;
	
	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	@Override
	public void addCondition() {
		if(albumId!=null){
			this.addQuery(" and albumId = ?", albumId);
		}
	}
}
