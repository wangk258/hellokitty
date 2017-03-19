package com.anan.plate.music.dao.impl;

import org.springframework.stereotype.Repository;

import com.anan.plate.music.dao.MusicDao;
import com.anan.plate.music.domain.Music;

import common.base.mybatis.dao.BaseDaoImpl;
@Repository
public class MusicDaoImpl extends BaseDaoImpl<Music> implements MusicDao{

}
