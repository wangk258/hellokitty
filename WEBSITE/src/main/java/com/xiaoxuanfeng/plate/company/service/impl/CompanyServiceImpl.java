package com.xiaoxuanfeng.plate.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.company.dao.CompanyDao;
import com.xiaoxuanfeng.plate.company.domain.Company;
import com.xiaoxuanfeng.plate.company.service.CompanyService;

import common.base.BaseServiceImpl;
import common.base.hibernate.dao.BaseDao;
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public BaseDao<Company> getDao() {
		return companyDao;
	}
}
