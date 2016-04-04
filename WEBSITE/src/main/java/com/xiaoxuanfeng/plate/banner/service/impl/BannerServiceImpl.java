package com.xiaoxuanfeng.plate.banner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.banner.dao.BannerDao;
import com.xiaoxuanfeng.plate.banner.domain.Banner;
import com.xiaoxuanfeng.plate.banner.service.BannerService;

import common.base.BaseDao;
import common.base.BaseServiceImpl;
@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService {
	@Autowired
	private BannerDao bannerDao;
	
	@Override
	public BaseDao<Banner> getDao() {
		return bannerDao;
	}
}
