package com.anan.plate.album.dao;

import com.anan.plate.core.TestBase;
import com.anan.plate.photo.dao.PhotosDao;
import com.anan.plate.photo.domain.Album;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Date;

public class AlbumDaoTest extends TestBase {

	@Autowired
	private PhotosDao photosDao;
	
	@Test
	public void getTest() throws Exception{
//		System.out.println(photosDao.list());
		Class cls = Album.class;
		Method method = cls.getMethod("setUpdateTime", Long.class);
		method.invoke(cls.newInstance(),new Date().getTime());
	}
}
