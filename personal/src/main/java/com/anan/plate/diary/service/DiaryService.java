package com.anan.plate.diary.service;

import com.anan.plate.diary.domain.Diary;

import common.base.BaseService;

public interface DiaryService extends BaseService<Diary>{
	
	public String deleteDiaries(String ids,Class<?> cls) throws Exception;
	
	public void insertBatch() throws Exception;
	
}
