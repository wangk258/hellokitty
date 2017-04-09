package com.anan.plate.album.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;
import com.anan.plate.photo.dao.PhotosDao;

public class AlbumDaoTest extends TestBase {

	@Autowired
	private PhotosDao photosDao;
	
	@Test
	public void getTest() throws Exception{
		System.out.println(photosDao.list());
	}
}
