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



//ʵ���߳�
public class ImlThread extends Thread{

	private JLabel lblNewLabel_1;
	//�ж��߳�״̬
	boolean isStop;

	//ʵ������������
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
			//��ȡ����
	    	Connection conn=JdbcUtils.getConnection();
					//����Statement����
			String sql="select * from student";
			PreparedStatement cmd = conn.prepareStatement(sql);
			//System.out.println(123);
			//���������
			ResultSet rs= cmd.executeQuery(sql);
			while(rs.next()) {
				//��ȡ���ݿ���ڵ�һ�еڶ���������
				String m=rs.getString(2);
				a.add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	int start = length();//�õ����ֵĸ���
   @Override
   public void run() {
	   //���ݿ�û���ֶ�
	   
	while(isStop) {
		try {
			//����ʱ����Ϊ50����
			if(start>0&&start<5) {//�ж�ֹͣʱ��
				Thread.sleep(70);
			}else {
				Thread.sleep(100);
			}
			//�ж����ݱ��Ƿ�Ϊ��
			if(start ==0) {
			JOptionPane.showMessageDialog(null, "��Ǹ,û��ѧ�����Ե���;");
			isStop =false;
			}
						
            //�����ݴ���lblNewLabel_1
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
   
   //�õ����ݿ������ֵĳ���(wang)
   public int length() {
	   int x = 0;
	   try {
		Connection con =JdbcUtils.getConnection();
		String sql = "select userName from student";
		//�õ�Ԥ����
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();//����username�Ľ����
		while(rs.next()) {
			x++;
		}		
	} catch (Exception e) {
		e.printStackTrace();
	}
	   return x;
   }
//public static int getnumber(int n){ 
//	 //��������������������������������Χ�ڵ�һ��ѧ��
//	   Random r = new Random();
//	   int r1 = r.nextInt(n);
//	   return r1;
//	   }
//
}
