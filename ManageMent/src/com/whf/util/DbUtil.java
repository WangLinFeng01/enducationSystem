package com.whf.util;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DbUtil {
private static String Url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
private static String userName="root"; //�û���
private static String password="root";//����
private static String jdbcName="com.mysql.jdbc.Driver";//��������
	

 //��ȡ���ݿ�����
 
public static Connection getCon() throws Exception{
	Class.forName(jdbcName);
	Connection con=(Connection) DriverManager.getConnection(Url,userName,password);
	return con;
	
}	

//�ر����ݿ�
public void closeCon(Connection con)throws Exception {
	if(con!=null) {
		con.close();
	}
	
}

public static void main(String[] args) {
	DbUtil  dbutil=new DbUtil();
	try {
		   dbutil.getCon();
		   System.out.println("���ݿ����ӳɹ�!");
	}catch(Exception e) {
		e.printStackTrace();
		   System.out.println("���ݿ�����ʧ��!");
	}
}


	
	
	
	
	
	
	
	
}
