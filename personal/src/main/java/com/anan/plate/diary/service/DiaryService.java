package com.anan.plate.diary.service;

import com.anan.plate.diary.domain.Diary;
import common.rdbms.base.BaseService;

public interface DiaryService extends BaseService<Diary> {
	
	public String deleteDiaries(String ids,Class<Diary> cls) throws Exception;
}
