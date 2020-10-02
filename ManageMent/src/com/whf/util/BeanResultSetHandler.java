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
			//��Ҫ���صĵ�������
			Object obj=clazz.newInstance();
			//�õ���Ҫȡ�������ֶ�
			Field[] fs=clazz.getDeclaredFields();
			//�����������ѵõ����е�������д������obj������
			while(rs.next()) {
				for(Field f:fs) {
					//��÷���Ȩ��
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
