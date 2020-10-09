package com.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.util.JdbcUtils;


public class BaseDaoImpl<T>{
	private Class clazz;

	public BaseDaoImpl(){
		//�õ�son�ĸ��� ����
		Type type=this.getClass().getGenericSuperclass() ;
		//��ȡ������ķ�������
		ParameterizedType pt=(ParameterizedType)type;
		//ȡ�������еĲ���
		Type tType=pt.getActualTypeArguments()[0];
		this.clazz=(Class)tType;
	}
	
	public void add(T t) {
		Object[] params=null;
		//�ַ���ƴ��
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
		//�����getter�����ĵ���
		for(int i=0;i<fs.length;i++) {
			fs[i].setAccessible(true);
			try {
//�õ��������ֵ
				params[i]=fs[i].get(t);
				System.out.println(params[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ע���¼�����
		Connection con=null;
		try {
			//�õ����ݿ�����
			con=JdbcUtils.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);
			//����
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// ִ��sql
			int rows =ps.executeUpdate();
			if(rows>0) {
				con.commit();
			}else {
				JOptionPane.showMessageDialog(null, "�û����Ѵ��ڣ�");
				con.rollback();
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}//��ȡ����
	}

	public void udpate(T t) {
		
	}

	public void delete(int id) {
		
	}

	public T query(int id) {
		return null;
	}
}
