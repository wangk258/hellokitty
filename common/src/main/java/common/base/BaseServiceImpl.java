package common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import common.base.mybatis.dao.BaseDao;
import common.bo.PageBean;
import common.bo.QueryObject;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	public abstract BaseDao<T> getDao();

	@Transactional(rollbackFor = Exception.class)
	public void save(T t) throws Exception {
		getDao().save(t);
	}

	@Transactional(rollbackFor = Exception.class)
	public void update(T t) throws Exception {
		getDao().update(t);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Serializable id) throws Exception {
		getDao().delete(id);
	}

	public T get(Serializable id) throws Exception {
		return getDao().get(id);
	}

	public PageBean<T> list(QueryObject qo) throws Exception {
		return getDao().list(qo);
	}

	public List<T> list() throws Exception {
		return getDao().list();
	}


	public PageBean<T> list(String[] fields, QueryObject qo) throws Exception {
		return getDao().list(fields, qo);
	}
	
	public Object list(String sql) throws Exception{
		return getDao().list(sql);
	}

	public Integer getCount(QueryObject qo) throws Exception {
		return getDao().getCount(qo);
	}
}
