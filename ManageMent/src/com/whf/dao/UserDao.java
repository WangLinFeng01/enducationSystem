package com.whf.dao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.whf.base.BaseDaoImpl;
import com.whf.pojo.Student;
import com.whf.pojo.Teacher;
import com.whf.util.JdbcUtils;

//�û�DAO��
public class UserDao extends BaseDaoImpl <Student> {     
	
	
       //��¼��֤
        public  Student login(Connection con,Student user) throws Exception{
              Student resultUser=null;
              String sql="select * from student where userName=? and password=?";
              PreparedStatement ps=con.prepareStatement(sql);       
              ps.setString(1,user.getUserName());
              ps.setString(2,user.getPassWord());
              ResultSet rs=ps.executeQuery();
              if(rs.next()) {
            	  resultUser=new Student();
                  resultUser.setPassWord(rs.getString("password"));     
                  resultUser.setUserName(rs.getString("userName"));             
              }
                     
              return resultUser; 
}
        
        public  Teacher login1(Connection con1,Teacher user1) throws Exception{
            Teacher resultUser=null;
            String sql="select * from teacher where teaName=? and password=?";
            PreparedStatement ps=con1.prepareStatement(sql);       
            ps.setString(1,user1.getUserName());
            ps.setString(2,user1.getPassWord());
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
          	  resultUser=new Teacher();
                resultUser.setPassWord(rs.getString("password"));     
                resultUser.setUserName(rs.getString("teaName"));             
            }
                   
            return resultUser; 
}
        
        
        
        
        //�û���ע����֤�¼�----�ж��û����Ƿ����
        public Boolean isLogonFrame(String userName) {
        	Connection con=null;
        	boolean bool = false;
        	try {
    			con=JdbcUtils.getConnection();
    			String sql = "select userName from student";
    			PreparedStatement ps = con.prepareStatement(sql);
    			ResultSet rs3 = ps.executeQuery();
    			List<String> list = new ArrayList<>();
    			while(rs3.next()) {
    				list.add(rs3.getString(1));
    			}
    			//�ж�useName�Ƿ����
    			if(list.contains(userName)) {
    		    	bool = true;
    			}
    			con.close();
        	}catch(Exception e4) {
        		e4.printStackTrace();
        	}
        	return bool;
        }
        
        /**
         * 
         * @param route �����ļ��ľ���·��
         * @param s Ҫ���ص������Ϣ
         * @throws IOException 
         */
        
        //�û�����ʵ�ֹ���
        public void downFile(String route,String s){
        	
        	File file = new File(route+"//�Ծ�.txt");
            File fileParent = file.getParentFile();//���ص���File����,���Ե���exsit()�ȷ���
        	if(!fileParent.exists()) {//�ж��Ƿ����
        		fileParent.mkdirs();
        	}
        	if(!file.exists()) {
        		try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	//���ļ����ڴ�д�뱾����
        	BufferedWriter write=null;
        	try {
        		write= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        		write.write(s);
        		write.newLine();
    			JOptionPane.showMessageDialog(null, "�Ծ����سɹ���");
        		write.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
        	try {
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }
        
        
        
        
        
        
        
        
}
