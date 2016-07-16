package common.base.mybatis.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import common.bo.PageBean;
import common.bo.QueryObject;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SqlSessionTemplate sqlSession;

	private Class<?> entity;
	
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		entity = (Class<?>) type.getActualTypeArguments()[0];
	}
	
	public void save(T t) throws Exception {
		this.sqlSession.insert(entity.getName()+".insert", t);
	}

	public void update(T t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(T t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void delete(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public T get(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T get(String hql, Object... objects) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> list(String hql, Object... objects) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public PageBean<T> list(QueryObject qo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public PageBean<T> list(String[] fields, QueryObject qo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCount(QueryObject qo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer executeBySQL(String sql, Object... params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findBySQL(String sql, Object... objects) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
