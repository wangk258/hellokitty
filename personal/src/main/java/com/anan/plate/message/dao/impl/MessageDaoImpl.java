package com.anan.plate.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.anan.plate.message.dao.MessageDao;
import com.anan.plate.message.domain.Message;

import common.base.mybatis.dao.BaseDaoImpl;
@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao{

}
