package com.xiaoxuanfeng.plate.msg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.msg.dao.MsgDao;
import com.xiaoxuanfeng.plate.msg.domain.Msg;
import com.xiaoxuanfeng.plate.msg.service.MsgService;

import common.base.BaseServiceImpl;
import common.base.hibernate.dao.BaseDao;
@Service
public class MsgServiceImpl extends BaseServiceImpl<Msg> implements MsgService {
	@Autowired
	private MsgDao msgDao;
	
	@Override
	public BaseDao<Msg> getDao() {
		return msgDao;
	}
}
