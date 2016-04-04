package com.xiaoxuanfeng.plate.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.product.dao.ProductDao;
import com.xiaoxuanfeng.plate.product.domain.Product;
import com.xiaoxuanfeng.plate.product.service.ProductService;

import common.base.BaseDao;
import common.base.BaseServiceImpl;
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public BaseDao<Product> getDao() {
		return productDao;
	}
}
