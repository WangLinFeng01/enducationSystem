package com.whf.util;


import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class DbUtil {
private static String Url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
private static String userName="root"; //用户名
private static String password="root";//密码
private static String jdbcName="com.mysql.jdbc.Driver";//加载驱动
	

 //获取数据库连接
 
public static Connection getCon() throws Exception{
	Class.forName(jdbcName);
	Connection con=(Connection) DriverManager.getConnection(Url,userName,password);
	return con;
	
}	

//关闭数据库
public void closeCon(Connection con)throws Exception {
	if(con!=null) {
		con.close();
	}
	
}

public static void main(String[] args) {
	DbUtil  dbutil=new DbUtil();
	try {
		   dbutil.getCon();
		   System.out.println("数据库连接成功!");
	}catch(Exception e) {
		e.printStackTrace();
		   System.out.println("数据库连接失败!");
	}
}


	
	
	
	
	
	
	
	
}
