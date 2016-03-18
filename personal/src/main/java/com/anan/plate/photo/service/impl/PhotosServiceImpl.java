package com.anan.plate.photo.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.photo.dao.PhotosDao;
import com.anan.plate.photo.domain.Photos;
import com.anan.plate.photo.service.PhotosService;
@Service
public class PhotosServiceImpl extends BaseServiceImpl<Photos> implements PhotosService {
	@Autowired
	private PhotosDao photosDao;
	
	@Override
	public BaseDao<Photos> getDao() {
		return photosDao;
	}
}
