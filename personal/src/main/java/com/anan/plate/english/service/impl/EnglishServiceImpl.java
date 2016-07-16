package com.anan.plate.english.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.english.dao.EnglishDao;
import com.anan.plate.english.domain.English;
import com.anan.plate.english.service.EnglishService;

import common.base.BaseServiceImpl;
import common.base.mybatis.dao.BaseDao;
@Service
public class EnglishServiceImpl extends BaseServiceImpl<English> implements EnglishService {
	@Autowired
	private EnglishDao englishDao;
	
	@Override
	public BaseDao<English> getDao() {
		return englishDao;
	}
}
