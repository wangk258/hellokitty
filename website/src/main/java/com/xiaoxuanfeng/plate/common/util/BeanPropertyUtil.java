package com.xiaoxuanfeng.plate.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanPropertyUtil {

	/**
	 * 拷贝指定的属性
	 * 
	 * @param sessionBean
	 *            需持久化的实体
	 * @param bean
	 *            用来接收参数的实体
	 * @param fieldNames
	 *            字段名称数组
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchFieldException
	 */
	public static void Copy(Object sessionBean, Object bean, String[] fieldNames) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchFieldException {
		Class<?> sessionClass = sessionBean.getClass();
		Class<?> beanClass = bean.getClass();
		Method getMethod = null;
		Method setMethod = null;
		Object object = null;
		for (String fieldName : fieldNames) {
			getMethod = beanClass.getMethod(buildGetMethod(fieldName));
			object = getMethod.invoke(bean);
			/*if (object != null && object instanceof BaseBean) {
				//System.out.println("关联实体不能被注入！" + getMethod.getName());
				continue;
			}*/
			if (object != null) {
				setMethod = sessionClass.getMethod(buildSetMethod(fieldName), object.getClass());
				setMethod.invoke(sessionBean, object);
			} else {
				Method[] methods = sessionClass.getMethods();
				String setMethodName = buildSetMethod(fieldName);
				for (Method method : methods) {
					if(method.getName().equals(setMethodName))
					{
						setMethod = method;
						setMethod.invoke(sessionBean, object);
						break;
					}
				}
			}
		}
	}

	/**
	 * 构建get方法名
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String buildGetMethod(String fieldName) {
		return "get" + (new String(changeFirstLetter(fieldName)));
	}

	/**
	 * 构建set方法名
	 * 
	 * @param fieldName
	 * @return
	 */
	private static String buildSetMethod(String fieldName) {
		return "set" + (new String(changeFirstLetter(fieldName)));
	}

	/**
	 * 改变字段首字母为大写
	 * 
	 * @param fieldName
	 * @return
	 */
	private static char[] changeFirstLetter(String fieldName) {
		if (fieldName != null && !"".equals(fieldName)) {
			char[] arrays = fieldName.toCharArray();

			arrays[0] = Character.toUpperCase(arrays[0]);

			return arrays;
		} else {
			return new char[] {};
		}
	}


}
