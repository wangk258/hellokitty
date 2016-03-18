package com.anan.plate.appraise.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.appraise.dao.AppraiseDao;
import com.anan.plate.appraise.domain.Appraise;
import com.anan.plate.appraise.service.AppraiseService;
@Service
public class AppraiseServiceImpl extends BaseServiceImpl<Appraise> implements AppraiseService {
	@Autowired
	private AppraiseDao appraiseDao;
	
	@Override
	public BaseDao<Appraise> getDao() {
		return appraiseDao;
	}
}
