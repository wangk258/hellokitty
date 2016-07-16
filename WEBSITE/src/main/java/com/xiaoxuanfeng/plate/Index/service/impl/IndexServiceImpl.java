package com.xiaoxuanfeng.plate.Index.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.Index.dao.IndexDao;
import com.xiaoxuanfeng.plate.Index.domain.Index;
import com.xiaoxuanfeng.plate.Index.service.IndexService;

import common.base.BaseServiceImpl;
import common.base.mybatis.dao.BaseDao;
@Service
public class IndexServiceImpl extends BaseServiceImpl<Index> implements IndexService {
	@Autowired
	private IndexDao indexDao;
	
	@Override
	public BaseDao<Index> getDao() {
		return indexDao;
	}
}
