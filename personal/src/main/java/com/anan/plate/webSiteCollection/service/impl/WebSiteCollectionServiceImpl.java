package com.anan.plate.webSiteCollection.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anan.plate.webSiteCollection.dao.WebSiteCollectionDao;
import com.anan.plate.webSiteCollection.domain.WebSiteCollection;
import com.anan.plate.webSiteCollection.service.WebSiteCollectionService;

import common.base.BaseServiceImpl;
import common.base.mybatis.dao.BaseDao;
@Service
public class WebSiteCollectionServiceImpl extends BaseServiceImpl<WebSiteCollection> implements WebSiteCollectionService {
	@Autowired
	private WebSiteCollectionDao webSiteCollectionDao;
	
	@Override
	public BaseDao<WebSiteCollection> getDao() {
		return webSiteCollectionDao;
	}
}
