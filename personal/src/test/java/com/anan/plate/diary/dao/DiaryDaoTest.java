package com.anan.plate.diary.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;
import com.anan.plate.diary.domain.Diary;

public class DiaryDaoTest extends TestBase {

	@Autowired
	private DiaryDao diaryDao;
	
	@Test
	public void saveTest() throws Exception{
		Diary diary = new Diary();
		diary.setContent("22222222222222");
		diary.setCreatTime(new Timestamp(new Date().getTime()));
		diaryDao.save(diary);
	}
	
	@Test
	public void deleteTest() throws Exception{
//		diaryService.deleteDiaries("24",Diary.class);
//		System.out.println(diaryService.get(24l));
	}
}
