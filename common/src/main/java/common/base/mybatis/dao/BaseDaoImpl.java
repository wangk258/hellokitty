package common.base.mybatis.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
		this.sqlSession.update(entity.getName()+".update",t);
		
	}

	public void delete(Serializable id) throws Exception {
		if(null != id){
			if(StringUtils.isNotBlank(id.toString())){
				String[] ids = id.toString().split(",");
				if(null != ids && ids.length > 0){
					if(ids.length == 1){
						this.sqlSession.delete(entity.getName()+".deleteById",Long.parseLong(ids[0]));
					}else{
						this.sqlSession.delete(entity.getName()+".delete",ids);
					}
				}
			}
		}
	}

	public T get(Serializable id) throws Exception {
		return this.sqlSession.selectOne(entity.getName()+".getById",id);
	}

	public List<T> list() throws Exception {
		return this.sqlSession.selectList(entity.getName()+".getAll");
	}

	@SuppressWarnings("unchecked")
	public PageBean<T> list(QueryObject qo) throws Exception {
		PageBean<T> pageBean = new PageBean<T>(qo.getCurrentPage(),qo.getPageSize(),this.getCount(qo));
		pageBean.setRecordList((List<T>) this.sqlSession.selectList(entity.getName()+".getSome", qo));
		return pageBean;
	}

	public PageBean<T> list(String[] fields, QueryObject qo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getCount(QueryObject qo) throws Exception {
		return this.sqlSession.selectOne(entity.getName()+".getCount",qo);
	}

}
