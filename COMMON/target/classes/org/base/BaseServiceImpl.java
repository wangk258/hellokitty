package org.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bo.PageBean;
import org.bo.QueryObject;


public abstract class BaseServiceImpl<T> implements BaseService<T> {

	public abstract BaseDao<T> getDao();

	public void save(T t) throws Exception {
			getDao().save(t);
	}

	public void update(T t) throws Exception {
		getDao().update(t);
	}

	public void delete(T t) throws Exception {
		getDao().delete(t);
	}

	public void delete(String ids) throws Exception {
		if(StringUtils.isNotBlank(ids)){
			String[] array=ids.split(",");
			for (String id : array) {
				this.delete(Long.valueOf(id));
			}
		}
	}
	
	public void delete(Serializable id) throws Exception {
		getDao().delete(id);
	}
	
	public T get(Serializable id) throws Exception{
		return (T) getDao().get(id);
	}

	public T get(String hql, Object... objects)throws Exception {
		return getDao().get(hql, objects);
	}
	
	public PageBean<T> list(QueryObject qo) throws Exception{
		return getDao().list(qo);
	}

	public List<T> list() throws Exception {
		return getDao().list();
	}
	
	public List<T> list(String hql,Object...objects) throws Exception {
		return getDao().list(hql,objects);
	}

	public PageBean<T> list(String[] fields, QueryObject qo) throws Exception {
		return getDao().list(fields, qo);
	}

	public Integer getCount(QueryObject qo) throws Exception {
		return getDao().getCount(qo);
	}

	public Integer executeBySQL(String sql, Object... params) throws Exception {
		return getDao().executeBySQL(sql, params);
	}

	public List<T> findBySQL(String sql, Object... objects) throws Exception {
		return getDao().findBySQL(sql, objects);
	}
}
