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
			//׼��һ��list����
			ArrayList list=new ArrayList();
			//�õ���Ҫȡ�������ֶ�
			Field[] fs=clazz.getDeclaredFields();
			//�����������ѵõ����е�������д������obj������
			while(rs.next()) {
				//��Ҫ���صĵ�������
				Object obj=clazz.newInstance();
				for(Field f:fs) {
					//��÷���Ȩ��
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
