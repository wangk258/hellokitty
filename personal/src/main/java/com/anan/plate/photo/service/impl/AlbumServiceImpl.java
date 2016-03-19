package com.anan.plate.photo.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.photo.dao.AlbumDao;
import com.anan.plate.photo.domain.Album;
import com.anan.plate.photo.service.AlbumService;
@Service
public class AlbumServiceImpl extends BaseServiceImpl<Album> implements AlbumService {
	@Autowired
	private AlbumDao albumDao;
	
	@Override
	public BaseDao<Album> getDao() {
		return albumDao;
	}
}