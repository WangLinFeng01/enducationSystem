package com.whf.ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;

import com.whf.util.DbUtil;

//实现线程
public class ImlThread extends Thread{

	private JLabel lblNewLabel_1;
	//判断线程状态
	boolean isStop;

	//实例化集合容器
		Vector vector=new Vector();
		int getn;
		void setbool(boolean a){
			isStop=a;
		}

	
	public ImlThread(JLabel lblNewLabel_1)throws IOException {
		this.lblNewLabel_1=lblNewLabel_1;
		v(vector);
	}

	public void v(Vector a) {
		
	     try {
			//获取链接
			Connection conn=DbUtil.getCon();
					//创建Statement对象
			String sql="select * from Student";
			PreparedStatement cmd = conn.prepareStatement(sql);
			//System.out.println(123);
			//创建结果集
			ResultSet rs= cmd.executeQuery(sql);
			while(rs.next()) {
				//获取数据库表在第一行第二例
				String m=rs.getString(2);
				//获取数据库表在第一行第三例
				String m1=rs.getString(3);
				//拼接姓名和学好
				String m2=m+" "+m1;
				a.add(m2);
			}
			System.out.println(a);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
   @Override
   public void run() {
	while(isStop) {
		try {
			//设置时间间隔为50毫秒
			Thread.sleep(50);
			
            //将数据存入lblNewLabel_1
			lblNewLabel_1.setText(vector.get(getn).toString());
			getn+=1;
			//System.out.println(getn);
			if(getn>=5) {
				getn=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
   }

//public static int getnumber(int n){ 
//	 //根据你输入的总人数返回随机在人数范围内的一个学号
//	   Random r = new Random();
//	   int r1 = r.nextInt(n);
//	   return r1;
//	   }
//
}
