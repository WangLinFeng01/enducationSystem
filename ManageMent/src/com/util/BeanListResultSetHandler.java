package com.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BeanListResultSetHandler<T> implements ResultSetHandler<T>{

	private Class clazz;
	public BeanListResultSetHandler(Class clazz) {
		this.clazz=clazz;
	}
	@Override
	public Object handle(ResultSet rs) {  
		
		try {
			//准备一个list集合
			ArrayList list=new ArrayList();
			//拿到需要取出来的字段
			Field[] fs=clazz.getDeclaredFields();
			//处理结果集，把得到的行的列内容写入我们obj对象中
			while(rs.next()) {
				//需要返回的单个对象
				Object obj=clazz.newInstance();
				for(Field f:fs) {
					//获得访问权利
					f.setAccessible(true);
					String columnLabel=f.getName();
					Object value=rs.getObject(columnLabel);
					f.set(obj,value);
				}
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
