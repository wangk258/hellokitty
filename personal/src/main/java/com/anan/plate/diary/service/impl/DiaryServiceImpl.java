package com.anan.plate.diary.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.diary.dao.DiaryDao;
import com.anan.plate.diary.domain.Diary;
import com.anan.plate.diary.service.DiaryService;

import common.base.BaseDao;
import common.base.BaseServiceImpl;
@Service
public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService {
	@Autowired
	private DiaryDao diaryDao;
	
	@Override
	public BaseDao<Diary> getDao() {
		return diaryDao;
	}
	
	public String deleteDiaries(String ids,Class<Diary> cls) throws Exception {
		if(StringUtils.isNotBlank(ids)){
			this.delete(ids, cls);
			return null;
		}
		else{
			return MessageConstants.SELECT_ITEM_EMPTY;
		}
	}
}
