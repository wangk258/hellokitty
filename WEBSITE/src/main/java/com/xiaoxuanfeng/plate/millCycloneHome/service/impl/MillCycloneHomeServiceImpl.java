package com.xiaoxuanfeng.plate.millCycloneHome.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.millCycloneHome.dao.MillCycloneHomeDao;
import com.xiaoxuanfeng.plate.millCycloneHome.domain.MillCycloneHome;
import com.xiaoxuanfeng.plate.millCycloneHome.service.MillCycloneHomeService;
@Service
public class MillCycloneHomeServiceImpl extends BaseServiceImpl<MillCycloneHome> implements MillCycloneHomeService {
	@Autowired
	private MillCycloneHomeDao millCycloneHomeDao;
	
	@Override
	public BaseDao<MillCycloneHome> getDao() {
		return millCycloneHomeDao;
	}
}