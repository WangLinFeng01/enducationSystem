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
			Connection conn=DbUtil.getCon();
					//����Statement����
			String sql="select * from Student";
			PreparedStatement cmd = conn.prepareStatement(sql);
			//System.out.println(123);
			//���������
			ResultSet rs= cmd.executeQuery(sql);
			while(rs.next()) {
				//��ȡ���ݿ���ڵ�һ�еڶ���
				String m=rs.getString(2);
				//��ȡ���ݿ���ڵ�һ�е�����
				String m1=rs.getString(3);
				//ƴ��������ѧ��
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
			//����ʱ����Ϊ50����
			Thread.sleep(50);
			
            //�����ݴ���lblNewLabel_1
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
//	 //��������������������������������Χ�ڵ�һ��ѧ��
//	   Random r = new Random();
//	   int r1 = r.nextInt(n);
//	   return r1;
//	   }
//
}
