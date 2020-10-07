package com.whf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.whf.dao.T_scoreDao;
import com.whf.pojo.Course;
import com.whf.pojo.T_score;
import com.whf.util.JdbcUtils;

public class T_scoreDaoImpl implements T_scoreDao {

	@Override
	public List<T_score> T_score_query() {
		String sql="SELECT"
				+ "studentId,"
				+ "sum(CASE when subjectId=1 THEN score END) chinese,"
				+"sum(CASE when subjectId=2 THEN score END) mathematics,"
				+"sum(CASE when subjectId=3 THEN score END) english"
				+"FROM t_scoer" 
				+"GROUP BY studentId";
		List<T_score> list =new ArrayList<T_score>();
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				//遍历查询到的结果集并创建Course对象
				int studentId=rs.getInt("studentId");
				int chinese=rs.getInt("chinese");
				int mathematics=rs.getInt("mathematics");
				int english=rs.getInt("english");
				T_score tscore=new T_score();
				//将查询的对象存入集合中
				list.add(tscore);
			    conn.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<T_score> T_score_update(T_score score) {
		return null;
	}	
	public static void main(String[] args) {
		T_scoreDaoImpl da=new T_scoreDaoImpl();
		List<T_score> list=da.T_score_query();
		for (T_score t_score : list) {
			System.out.println(t_score);
		}
	}
}
