package com.xiaoxuanfeng.plate.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.service.dao.ServiceManageDao;
import com.xiaoxuanfeng.plate.service.domain.ServiceManage;
import com.xiaoxuanfeng.plate.service.service.ServiceManageService;
import common.rdbms.base.BaseDao;
import common.rdbms.base.BaseServiceImpl;
@Service
public class ServiceManageServiceImpl extends BaseServiceImpl<ServiceManage> implements ServiceManageService {
	@Autowired
	private ServiceManageDao serviceManageDao;
	
	@Override
	public BaseDao<ServiceManage> getDao() {
		return serviceManageDao;
	}
}
