package com.anan.plate.diary.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.anan.plate.core.TestBase;
import com.anan.plate.diary.domain.Diary;

public class DiaryServiceTest extends TestBase {

	@Autowired
	private DiaryService diaryService;
	
	@Test
	public void saveTest() throws Exception{
		Diary diary = new Diary();
		diary.setContent("saveTest");
		this.diaryService.save(diary);
	}
	
	@Test
	public void saveBatchTest() throws Exception{
		this.diaryService.insertBatch();
	}
}
