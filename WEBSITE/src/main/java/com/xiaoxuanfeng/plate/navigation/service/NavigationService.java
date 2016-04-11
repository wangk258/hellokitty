package com.xiaoxuanfeng.plate.navigation.service;

import com.xiaoxuanfeng.plate.navigation.domain.Navigation;
import common.rdbms.base.BaseService;

public interface NavigationService extends BaseService<Navigation> {

	void  deleteAndUpdataSort(String ids) throws Exception;
}
