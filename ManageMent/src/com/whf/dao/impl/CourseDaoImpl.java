package com.whf.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whf.dao.CourseDao;
import com.whf.pojo.Course;
import com.whf.util.JdbcUtils;
import com.whf.util.QueryRunner;


public class CourseDaoImpl implements CourseDao{
	//�γ̱�Ĳ�ѯ
	public List<Course> course_query(int classid) {
		String sql="select * from course where classid=?";
		
		
		
		
		
		
	    List<Course> list=new ArrayList<Course>();
		try {
			Connection  conn=JdbcUtils.getConnection();
			
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, classid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				//������ѯ���Ľ����������Course����
				int id=rs.getInt("id");
				String week=rs.getString("week");
				String morning=rs.getString("morning");
				String afternoon=rs.getString("afternoon");
				String evening=rs.getString("evening");
				Course course=new Course(id,week,morning,afternoon,evening);
				//����ѯ�Ķ�����뼯����
				list.add(course);
			   
				
			 
		
			}
			 rs.close();
			 ps.close();
			 conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���ض��󼯺�
		return list;	
	}

	
	//�γ̱���޸�
	@Override
	public Integer course_update(Course course) {
		String sql="update course set week=?,morning=?,afternoon=?,evening=? where id=? and classid=?";
		
		Object[] obj= {course.getWeek(),course.getMorning(),course.getAfternoon(),course.getEvening(),course.getId(),course.getClassid()};
		Integer result=new QueryRunner().execute(sql,obj);
		return result;

	}

	

}
