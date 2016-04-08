package common.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import common.bo.PageBean;
import common.bo.QueryObject;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<?> cls;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		cls = (Class<?>) type.getActualTypeArguments()[0];
	}

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public void save(T t) throws Exception {

		hibernateTemplate.save(t);
	}

	public void update(T t) throws Exception {

		hibernateTemplate.update(t);
	}

	public void delete(T t) throws Exception {

		hibernateTemplate.delete(t);
	}

	public void delete(Serializable id) throws Exception {
		T t = this.get(id);
		if (t != null) {
			hibernateTemplate.delete(t);
		} else {
			throw new RuntimeException("数据库中没有相应的数据！");
		}
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws Exception {

		return (T) hibernateTemplate.get(cls, id);
	}

	@SuppressWarnings("unchecked")
	public T get(String hql, Object... objects) throws Exception {
		List<T> list = this.hibernateTemplate.find(hql, objects);

		return list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public PageBean<T> list(final QueryObject qo) throws Exception {
		Integer count = this.getCount(qo);
		PageBean<T> pg = new PageBean<T>(qo.getCurrentPage(), qo.getPageSize(),
				count);
		List<T> list = this.hibernateTemplate
				.executeFind(new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = getQuery(qo, session, new StringBuilder(
								"from " + cls.getSimpleName() + " where 1=1"),true);
						return query.list();
					}
				});
		pg.setRecordList(list);
		return pg;
	}

	@SuppressWarnings("unchecked")
	public PageBean<T> list(final String[] fields, final QueryObject qo)
			throws Exception {
		final String fieldString = this.connectFields(fields);
		final StringBuilder sb = new StringBuilder("select " + fieldString
				+ " from " + cls.getSimpleName() + " where 1=1");
		List<T> recordsList = this.hibernateTemplate
				.executeFind(new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						try {
							Query query = getQuery(qo, session, sb,true);
							List<Object[]> list = query.list();
							return this.array2list(list, fields);
						} catch (IllegalStateException e) {
							System.err
									.println("未找到属性："
											+ e.getMessage()
													.substring(
															e.getMessage()
																	.indexOf(
																			"'"),
															e.getMessage()
																	.lastIndexOf(
																			"'") + 1));
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}

					private List<T> array2list(List<Object[]> list,
							String[] fields) throws Exception {
						List<T> list1 = new ArrayList<T>();
						for (Object[] objects : list) {
							try {
								Object o = cls.newInstance();
								for (int i = 0; i < objects.length; i++) {
									String field = fields[i];
									String methodName = getMethodName(field);
									try {
										Method method = cls.getMethod(
												methodName,
												cls.getDeclaredField(field)
														.getType());
										method.invoke(o, objects[i]);
									} catch (SecurityException e) {
										System.err.println(methodName
												+ "方法不可见！");
										continue;
									} catch (IllegalArgumentException e) {
										System.err.println(methodName
												+ "参数错误或参数类型不匹配！");
										continue;
									} catch (NoSuchMethodException e) {
										System.err.println("没有找到" + methodName
												+ "方法！");
										continue;
									} catch (NoSuchFieldException e) {
										System.err.println("没有找到" + methodName
												+ "属性！");
										continue;
									} catch (IllegalAccessException e) {
										System.err.println(methodName
												+ "方法不可访问！");
										continue;
									} catch (InvocationTargetException e) {
										e.printStackTrace();
										continue;
									}
								}
								list1.add((T) o);
							} catch (InstantiationException e) {
								System.err.println("实例化对象错误！");
							} catch (IllegalAccessException e) {
								System.err.println("对象不可访问！");
							}
						}
						return list1;
					}

					private String getMethodName(String field) {
						return "set" + field.substring(0, 1).toUpperCase()
								+ field.substring(1);
					}
				});
		Integer recordCount = this.getCount(qo);
		PageBean<T> pageBean = new PageBean<T>(qo.getCurrentPage(),
				qo.getPageSize(), recordCount);
		pageBean.setRecordList(recordsList);
		return pageBean;
	}

	public Integer getCount(final QueryObject qo) throws Exception {
		Integer count = this.hibernateTemplate
				.execute(new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = getQuery(qo, session, new StringBuilder(
								"select count(*) from " + cls.getSimpleName()
										+ " where 1=1"),false);
						Object obj = query.uniqueResult();
						if(null != obj){
							return Integer.parseInt(obj.toString());
						}
						return 0;
					}
				});
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<T> list() throws Exception {
		return (List<T>) this.hibernateTemplate.loadAll(cls);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Object... objects) throws Exception {
		return this.hibernateTemplate.find(hql, objects);
	}

	public Integer executeBySQL(final String sql, final Object... objects)
			throws Exception {
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				query = setValue(new StringBuilder(sql),
						Arrays.asList(objects), query);
				return query.executeUpdate();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> findBySQL(final String sql, final Object... objects)
			throws Exception {
		return this.hibernateTemplate
				.executeFind(new HibernateCallback<List<T>>() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						query = setValue(new StringBuilder(sql),
								Arrays.asList(objects), query);
						return query.list();
					}
				});
	}

	/**
	 * 拼接查询的字段
	 * 
	 * @param fields
	 * @return
	 */
	private String connectFields(String[] fields) {
		String s = "";
		for (String field : fields) {
			s += field + ",";
		}
		return s.substring(0, s.lastIndexOf(","));
	}

	/**
	 * 拼接HQL条件语句
	 * 
	 * @param sb
	 * @param conditions
	 * @return
	 */
	private static String connectSql(StringBuilder sb, List<String> conditions) {
		for (int i = 0; i < conditions.size(); i++) {
			sb.append(conditions.get(i));
		}
		return sb.toString();
	}

	/**
	 * 为HQL语句中的参数赋值
	 * 
	 * @param sb
	 * @param params
	 * @param query
	 * @return
	 */
	private static Query setValue(StringBuilder sb, List<Object> params,
			Query query) {
		if (!params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query;
	}

	/**
	 * 通过HQL语句返回一个Query对象
	 * 
	 * @param qo
	 * @param session
	 * @return
	 */
	private Query getQuery(final QueryObject qo, Session session,
			final StringBuilder sb,boolean page) {
		Query query = null;
		if (!qo.getConditions().isEmpty()) {
			String hql = connectSql(sb, qo.getConditions());
			query = session.createQuery(hql);
			query = setValue(new StringBuilder(hql), qo.getParams(), query);
		} else {
			query = session.createQuery(sb.toString());
		}
		if(page){
			if (qo.getCurrentPage() != -1) {// 分页
				query.setFirstResult((qo.getCurrentPage() - 1)* qo.getPageSize());
				query.setMaxResults(qo.getPageSize());
			}
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	public Integer getCount(String hql, Object... params) {

		List<Object> list = this.hibernateTemplate.find(hql, params);
		if (list.get(0) == null) {
			return 0;
		}
		String valueStr = list.get(0).toString();
		return Integer.parseInt(valueStr);
	}

	public void updateBySql(String sql, Object... params) throws Exception {
		this.hibernateTemplate.bulkUpdate(sql, params);
	}

}
