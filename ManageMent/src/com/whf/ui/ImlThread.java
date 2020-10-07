package com.whf.ui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.whf.util.JdbcUtils;



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
	    	Connection conn=JdbcUtils.getConnection();
					//创建Statement对象
			String sql="select * from student";
			PreparedStatement cmd = conn.prepareStatement(sql);
			//System.out.println(123);
			//创建结果集
			ResultSet rs= cmd.executeQuery(sql);
			while(rs.next()) {
				//获取数据库表在第一行第二例的姓名
				String m=rs.getString(2);
				a.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	int start = length();//得到名字的个数
   @Override
   public void run() {
	   //数据库没有字段
	   
	while(isStop) {
		try {
			//设置时间间隔为50毫秒
			if(start>0&&start<5) {//判断停止时间
				Thread.sleep(70);
			}else {
				Thread.sleep(100);
			}
			//判断数据表是否为空
			if(start ==0) {
			JOptionPane.showMessageDialog(null, "抱歉,没有学生可以点名;");
			isStop =false;
			}
						
            //将数据存入lblNewLabel_1
			lblNewLabel_1.setText(vector.get(getn).toString());
			getn+=1;
			//System.out.println(getn);
			if(getn>=start) {
				getn=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
   }
   
   //得到数据库中名字的长度(wang)
   public int length() {
	   int x = 0;
	   try {
		Connection con =JdbcUtils.getConnection();
		String sql = "select userName from student";
		//拿到预编译
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();//关于username的结果集
		while(rs.next()) {
			x++;
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return x;
   }
//public static int getnumber(int n){ 
//	 //根据你输入的总人数返回随机在人数范围内的一个学号
//	   Random r = new Random();
//	   int r1 = r.nextInt(n);
//	   return r1;
//	   }
//
}
