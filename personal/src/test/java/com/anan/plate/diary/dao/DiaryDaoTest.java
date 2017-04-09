package com.anan.plate.diary.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;
import com.anan.plate.diary.bo.DiaryQueryObject;
import com.anan.plate.diary.domain.Diary;

import common.bo.QueryObject;

public class DiaryDaoTest extends TestBase {

	@Autowired
	private DiaryDao diaryDao;
	
	@Test
	public void saveTest() throws Exception{
		Diary diary = new Diary();
		diary.setContent("22222222222222");
		diaryDao.save(diary);
	}
	
	@Test
	public void deleteTest() throws Exception{
		diaryDao.delete("100");
	}
	
	@Test
	public void getOneTest() throws Exception{
		Diary diary = diaryDao.get(5);
		System.out.println(diary);
	}
	
	@Test
	public void getAllTest() throws Exception{
		List<Diary> list = diaryDao.list();
		for(Diary diary : list){
			System.out.println(diary);
		}
	}
	
	@Test
	public void getSomeTest() throws Exception{
		QueryObject qo = new DiaryQueryObject();
		qo.setCurrentPage(2);
		qo.setPageSize(3);
		System.out.println(diaryDao.list(qo).getRecordList());
	}
}
