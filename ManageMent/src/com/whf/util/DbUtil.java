package com.whf.util;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DbUtil {
private  String Url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
private  String userName="root"; //�û���
private  String password="root";//����
private  String jdbcName="com.mysql.jdbc.Driver";//��������
	

 //��ȡ���ݿ�����
 
public  Connection getCon() throws Exception{
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
