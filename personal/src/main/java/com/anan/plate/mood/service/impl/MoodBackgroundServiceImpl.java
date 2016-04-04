package com.anan.plate.mood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.mood.dao.MoodBackgroundDao;
import com.anan.plate.mood.domain.MoodBackground;
import com.anan.plate.mood.service.MoodBackgroundService;

import common.base.BaseDao;
import common.base.BaseServiceImpl;
@Service
public class MoodBackgroundServiceImpl extends BaseServiceImpl<MoodBackground> implements MoodBackgroundService {
	@Autowired
	private MoodBackgroundDao moodBackgroundDao;
	
	@Override
	public BaseDao<MoodBackground> getDao() {
		return moodBackgroundDao;
	}
}
