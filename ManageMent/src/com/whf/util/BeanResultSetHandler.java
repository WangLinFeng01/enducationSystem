package com.whf.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;

public class BeanResultSetHandler<T> implements ResultSetHandler<T>{

	private Class clazz;
	public BeanResultSetHandler(Class clazz) {
		this.clazz=clazz;
	}
	@Override
	public T handle(ResultSet rs) {
		try {
			//需要返回的单个对象
			Object obj=clazz.newInstance();
			//拿到需要取出来的字段
			Field[] fs=clazz.getDeclaredFields();
			//处理结果集，把得到的行的列内容写入我们obj对象中
			while(rs.next()) {
				for(Field f:fs) {
					//获得访问权利
					f.setAccessible(true);
					String columnLabel=f.getName();
					Object value=rs.getObject(columnLabel);
					f.set(obj,value);
				}
			}
			return (T) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
