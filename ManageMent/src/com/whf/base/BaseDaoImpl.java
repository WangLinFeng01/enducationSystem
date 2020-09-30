package com.whf.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.whf.util.DbUtil;


public class BaseDaoImpl<T>{
	private Class clazz;
	DbUtil dbUtil = new DbUtil();
	public BaseDaoImpl(){
		//拿到son的父类 类型
		Type type=this.getClass().getGenericSuperclass() ;
		//获取到父类的泛型类型
		ParameterizedType pt=(ParameterizedType)type;
		//取出泛型中的参数
		Type tType=pt.getActualTypeArguments()[0];
		this.clazz=(Class)tType;
	}
	
	public void add(T t) {
		Object[] params=null;
		//字符串拼接
		StringBuilder sb=new StringBuilder("");
		String preStr="insert into ";
		sb.append(preStr);
		String tableName=this.clazz.getSimpleName().toLowerCase();
		sb.append("`"+tableName+"` ");
		Field[] fs=this.clazz.getDeclaredFields();
		params=new Object[fs.length];
		String sqlTemp="( ";
		for(Field f:fs) {
			f.setAccessible(true);
			String column=f.getName();
			sqlTemp+=column+",";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1)+") ";
		sb.append(sqlTemp);
		sb.append(" values (");
		sqlTemp="";
		for(int i=0;i<fs.length;i++) {
			sqlTemp+="?,";
		}
		sqlTemp=sqlTemp.substring(0,sqlTemp.length()-1);
		sb.append(sqlTemp);
		sb.append(")");
		String sql=sb.toString();
		//insert into person id,name,age,pid values (?,?,?,)
		//对象的getter方法的调用
		for(int i=0;i<fs.length;i++) {
			fs[i].setAccessible(true);
			try {
//拿到对象里的值
				params[i]=fs[i].get(t);
				System.out.println(params[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Connection con=null;
		try {
			//拿到数据库连接
			con=dbUtil.getCon();
			PreparedStatement ps = con.prepareStatement(sql);
			//传参
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行sql
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}//获取连接
	}

	public void udpate(T t) {
		
	}

	public void delete(int id) {
		
	}

	public T query(int id) {
		return null;
	}
}
