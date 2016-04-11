package com.anan.plate.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.music.dao.MusicDao;
import com.anan.plate.music.domain.Music;
import com.anan.plate.music.service.MusicService;
import common.rdbms.base.BaseDao;
import common.rdbms.base.BaseServiceImpl;
@Service
public class MusicServiceImpl extends BaseServiceImpl<Music> implements MusicService {
	@Autowired
	private MusicDao musicDao;
	
	@Override
	public BaseDao<Music> getDao() {
		return musicDao;
	}
}
