package com.whf.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.whf.base.BaseDaoImpl;
import com.whf.model.Student;
import com.whf.util.DbUtil;

//�û�DAO��
public class UserDao extends BaseDaoImpl <Student> {     
	
	
       //��¼��֤
        public  Student login(Connection con,Student user) throws Exception{
              Student resultUser=null;
              String sql="select * from Student where userName=? and password=?";
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
        //�û���ע����֤�¼�----�ж��û����Ƿ����
        public Boolean isLogonFrame(String userName) {
        	Connection con=null;
        	boolean bool = false;
        	try {
    			con=new DbUtil().getCon();//��ȡ����
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
        
        
        
        
        
        
        
        
        
}
