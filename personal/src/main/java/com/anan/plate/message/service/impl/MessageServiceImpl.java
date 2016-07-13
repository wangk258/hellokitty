package com.anan.plate.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.message.dao.MessageDao;
import com.anan.plate.message.domain.Message;
import com.anan.plate.message.service.MessageService;

import common.base.BaseServiceImpl;
import common.base.hibernate.dao.BaseDao;
@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Override
	public BaseDao<Message> getDao() {
		return messageDao;
	}
}
