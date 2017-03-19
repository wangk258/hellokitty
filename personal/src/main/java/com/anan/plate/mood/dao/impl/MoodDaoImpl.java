package com.anan.plate.mood.dao.impl;

import org.springframework.stereotype.Repository;

import com.anan.plate.mood.dao.MoodDao;
import com.anan.plate.mood.domain.Mood;

import common.base.mybatis.dao.BaseDaoImpl;
@Repository
public class MoodDaoImpl extends BaseDaoImpl<Mood> implements MoodDao{

}
