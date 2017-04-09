package com.xiaoxuanfeng.plate.productType.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.productType.dao.ProductTypeDao;
import com.xiaoxuanfeng.plate.productType.domain.ProductType;
import com.xiaoxuanfeng.plate.productType.service.ProductTypeService;

import common.base.BaseServiceImpl;
import common.base.mybatis.dao.BaseDao;
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements ProductTypeService {
	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Override
	public BaseDao<ProductType> getDao() {
		return productTypeDao;
	}
}
