package common.rdbms.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

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
	public void delete(T t) throws Exception {
		getDao().delete(t);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String ids, Class<?> cls) throws Exception {
		if (StringUtils.isNotBlank(ids)) {
			getDao().executeBySQL(
					"delete from t_" + cls.getSimpleName() + " where id in ("
							+ ids + ")");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Serializable id) throws Exception {
		getDao().delete(id);
	}

	public T get(Serializable id) throws Exception {
		return (T) getDao().get(id);
	}

	public T get(String hql, Object... objects) throws Exception {
		return getDao().get(hql, objects);
	}

	public PageBean<T> list(QueryObject qo) throws Exception {
		return getDao().list(qo);
	}

	public List<T> list() throws Exception {
		return getDao().list();
	}

	public List<T> list(String hql, Object... objects) throws Exception {
		return getDao().list(hql, objects);
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
