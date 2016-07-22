package com.anan.plate.diary.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anan.plate.constants.MessageConstants;
import com.anan.plate.diary.dao.DiaryDao;
import com.anan.plate.diary.domain.Diary;
import com.anan.plate.diary.service.DiaryService;

import common.base.BaseServiceImpl;
import common.base.mybatis.dao.BaseDao;

@Service
public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService{
	
	@Autowired
	private DiaryDao diaryDao;
	
	@Transactional(rollbackFor=Exception.class)
	public String deleteDiaries(String ids,Class<?> cls) throws Exception {
		if(StringUtils.isNotBlank(ids)){
			this.delete(ids);
			return null;
		}
		else{
			return MessageConstants.SELECT_ITEM_EMPTY;
		}
	}

	@Override
	public BaseDao<Diary> getDao() {
		return diaryDao;
	}

	@Transactional(rollbackFor=Exception.class)
	public void insertBatch() throws Exception {
			
	}
}
