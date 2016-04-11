package com.xiaoxuanfeng.plate.navigation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoxuanfeng.plate.navigation.dao.NavigationDao;
import com.xiaoxuanfeng.plate.navigation.domain.Navigation;
import com.xiaoxuanfeng.plate.navigation.service.NavigationService;
import common.rdbms.base.BaseDao;
import common.rdbms.base.BaseServiceImpl;
@Service
public class NavigationServiceImpl extends BaseServiceImpl<Navigation> implements NavigationService {
	@Autowired
	private NavigationDao navigationDao;
	
	@Override
	public BaseDao<Navigation> getDao() {
		return navigationDao;
	}

	public void deleteAndUpdataSort(String ids) throws Exception {
		
		String[] idsS= ids.split(",");
		//遍历循环删除
		//删除更新排序
		for (int i = 0; i < idsS.length; i++) {
			Navigation deleteBean = navigationDao.get(Long.parseLong(idsS[i]));
			navigationDao.delete(deleteBean);
			String sql = "update Navigation set sort = sort -1 where sort >" + deleteBean.getSort();
			navigationDao.executeBySQL(sql);
		}
		
		
	}
}
