package com.xiaoxuanfeng.plate.product.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoxuanfeng.plate.product.dao.ProductDao;
import com.xiaoxuanfeng.plate.product.domain.Product;
import common.rdbms.base.BaseDaoImpl;
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{

}
