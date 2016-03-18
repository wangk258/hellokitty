package com.anan.plate.mood.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.mood.dao.MoodDao;
import com.anan.plate.mood.domain.Mood;
import com.anan.plate.mood.service.MoodService;
@Service
public class MoodServiceImpl extends BaseServiceImpl<Mood> implements MoodService {
	@Autowired
	private MoodDao moodDao;
	
	@Override
	public BaseDao<Mood> getDao() {
		return moodDao;
	}
}
