package com.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.util.JdbcUtils;

public class Test {
	//测试动态代理的conn;
	public static void main(String[] args) {
		try {
			int i = 0;
			while(i<41) {
				Connection conn = JdbcUtils.getConnection();
				conn.close();
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
