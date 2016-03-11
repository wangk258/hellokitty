package com.xiaoxuanfeng.plate.navigation.service;

import org.base.BaseService;

import com.xiaoxuanfeng.plate.navigation.domain.Navigation;

public interface NavigationService extends BaseService<Navigation> {

	void  deleteAndUpdataSort(String ids) throws Exception;
}
