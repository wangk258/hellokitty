package com.anan.plate.album.dao;

import com.anan.plate.core.TestBase;
import com.anan.plate.photo.dao.PhotosDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumDaoTest extends TestBase {

    @Autowired
    private PhotosDao photosDao;

    @Test
    public void getTest() throws Exception {
        this.photosDao.list();
    }
}
