package common.base.mybatis.dao;

import java.io.Serializable;
import java.util.List;

import common.bo.PageBean;
import common.bo.QueryObject;

public interface BaseDao<T> {
	
	/**
	 * 保存一个对象
	 * @param t
	 */
    void save(T t)throws Exception;
	
	/**
	 * 更新一个对象
	 * @param t
	 */

    void update(T t)throws Exception;
	
	/**
	 * 通过Id删除一个对象
	 * @param id
	 */
    void delete(Serializable id)throws Exception;
	
	/**
	 * 通过Id查询一个对象
	 * @param id
	 * @return
	 */
    T get(Serializable id)throws Exception;
	
	/**
	 * 查询所有对象
	 * @return
	 */
    List<T> list()throws Exception;
	
	/**
	 * 分页查询对象集合
	 * @param qo 分页对象
	 * @return
	 */

    PageBean<T> list(QueryObject qo)throws Exception;
	
	/**
	 * 分页查询指定属性集合
	 * @param fields
	 * @param qo
	 * @return
	 */
    PageBean<T> list(String[] fields, QueryObject qo)throws Exception;
	
	/**
	 * 分页查询对象的集合的个数
	 * @param qo
	 * @return
	 */
    Integer getCount(QueryObject qo)throws Exception;
    
	
	Object list(String sql) throws Exception;
	
}
