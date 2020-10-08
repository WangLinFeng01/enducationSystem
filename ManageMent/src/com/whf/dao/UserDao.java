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

//用户DAO类
public class UserDao extends BaseDaoImpl <Student> {     
	
	
       //登录验证
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
        
        
        
        
        //用户的注册验证事件----判断用户名是否存在
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
    			//判断useName是否存在
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
         * @param route 下载文件的绝对路径
         * @param s 要下载的题的信息
         * @throws IOException 
         */
        
        //用户下载实现功能
        public void downFile(String route,String s){
        	
        	File file = new File(route+"//试卷.txt");
            File fileParent = file.getParentFile();//返回的是File类型,可以调用exsit()等方法
        	if(!fileParent.exists()) {//判断是否存在
        		fileParent.mkdirs();
        	}
        	if(!file.exists()) {
        		try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	//将文件从内存写入本地中
        	BufferedWriter write=null;
        	try {
        		write= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        		write.write(s);
        		write.newLine();
    			JOptionPane.showMessageDialog(null, "试卷下载成功！");
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
