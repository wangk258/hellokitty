package com.anan.plate.diary.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.diary.dao.DiaryDao;
import com.anan.plate.diary.domain.Diary;
import com.anan.plate.diary.service.DiaryService;
@Service
public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService {
	@Autowired
	private DiaryDao diaryDao;
	
	@Override
	public BaseDao<Diary> getDao() {
		return diaryDao;
	}
}
