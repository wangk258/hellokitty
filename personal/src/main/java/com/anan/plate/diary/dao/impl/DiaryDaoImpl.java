package com.anan.plate.diary.dao.impl;

import org.springframework.stereotype.Repository;

import com.anan.plate.diary.dao.DiaryDao;
import com.anan.plate.diary.domain.Diary;

import common.base.hibernate.dao.BaseDaoImpl;
@Repository
public class DiaryDaoImpl extends BaseDaoImpl<Diary> implements DiaryDao{

}
