package com.xiaoxuanfeng.plate.msg.service.impl;

import org.base.BaseDao;
import org.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.msg.dao.MsgDao;
import com.xiaoxuanfeng.plate.msg.domain.Msg;
import com.xiaoxuanfeng.plate.msg.service.MsgService;
@Service
public class MsgServiceImpl extends BaseServiceImpl<Msg> implements MsgService {
	@Autowired
	private MsgDao msgDao;
	
	@Override
	public BaseDao<Msg> getDao() {
		return msgDao;
	}
}
