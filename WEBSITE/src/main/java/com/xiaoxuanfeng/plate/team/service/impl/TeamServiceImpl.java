package com.xiaoxuanfeng.plate.team.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.team.dao.TeamDao;
import com.xiaoxuanfeng.plate.team.domain.Team;
import com.xiaoxuanfeng.plate.team.service.TeamService;

import common.base.BaseServiceImpl;
import common.base.hibernate.dao.BaseDao;
@Service
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService {
	@Autowired
	private TeamDao teamDao;
	
	@Override
	public BaseDao<Team> getDao() {
		return teamDao;
	}
}
