package common.base.mybatis.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import common.bo.PageBean;
import common.bo.QueryObject;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private SqlSessionTemplate sqlSession;

	private Class<?> entity;
	
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		entity = (Class<?>) type.getActualTypeArguments()[0];
	}
	
	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession){
		this.sqlSession = sqlSession;
		Configuration configuration = this.sqlSession.getConfiguration();
		String[] baseIds = new String[]{".paramSql",".deleteById",".getById",".getAll"};
//		String[] baseIds = new String[]{".paramSql",".deleteById",".getAll"};
		for(String baseId : baseIds){
			MappedStatement baseMappedStatement = configuration.getMappedStatement(BaseDao.class.getName() + baseId);
			String id = entity.getName() + baseId;
			if(!configuration.hasStatement(id)){
				MappedStatement.Builder builder = new MappedStatement.Builder(configuration, id, baseMappedStatement.getSqlSource(), baseMappedStatement.getSqlCommandType());
				builder.parameterMap(baseMappedStatement.getParameterMap());
				builder.resultMaps(baseMappedStatement.getResultMaps());
				MappedStatement ms = builder.build();
				configuration.addMappedStatement(ms);
			}
		}
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
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("id", Long.parseLong(ids[0]));
						map.put("table", "t_"+entity.getSimpleName());
						this.sqlSession.delete(entity.getName()+".deleteById",map);
					}else{
						this.sqlSession.delete(entity.getName()+".delete",ids);
					}
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("table", "t_"+entity.getSimpleName());
		Map<String,Object> result = this.sqlSession.selectOne(entity.getName()+".getById",map);
		Object obj = entity.newInstance();
		BeanUtils.populate(obj, result);
		return (T) obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> list() throws Exception {
		List<Map<String,Object>> list = this.sqlSession.selectList(entity.getName()+".getAll","t_"+entity.getSimpleName());
		List<T>  result = new ArrayList<T>();
		for(Map<String,Object> map:list){
			Object obj = entity.newInstance();
			BeanUtils.populate(obj, map);
			result.add((T)obj);
		}
		return result;
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

	public Object list(String sql) throws Exception {
		return this.sqlSession.selectList(entity.getName() + ".paramSql",sql);
	}
}
