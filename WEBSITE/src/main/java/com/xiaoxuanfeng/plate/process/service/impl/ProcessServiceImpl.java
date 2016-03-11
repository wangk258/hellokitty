package com.xiaoxuanfeng.plate.process.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.process.dao.ProcessDao;
import com.xiaoxuanfeng.plate.process.domain.Process;
import com.xiaoxuanfeng.plate.process.service.ProcessService;
@Service
public class ProcessServiceImpl extends BaseServiceImpl<Process> implements ProcessService {
	@Autowired
	private ProcessDao processDao;
	
	@Override
	public BaseDao<Process> getDao() {
		return processDao;
	}
}
