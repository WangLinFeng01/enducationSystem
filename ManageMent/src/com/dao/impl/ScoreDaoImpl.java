package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.ScoreDao;
import com.pojo.TScore;
import com.util.JdbcUtils;


public class ScoreDaoImpl implements ScoreDao {

	@Override
	public List<TScore> Score_query() {
		
		String sql="SELECT" +" "+
				"studentId," +" "+
				"userName," +" "+
				"sum(CASE when subjectId=1 THEN score END) chinese," +" "+ 
				"sum(CASE when subjectId=2 THEN score END) mathematics," +" "+
				"sum(CASE when subjectId=3 THEN score END) english," +" "+
				"SUM(score) total" +" "+
				"FROM t_scoer,student" +" " + 
				"where t_scoer.studentId=student.id" +" " + 
				"GROUP BY studentId"+" "+ 
				"ORDER BY total DESC";
		List<TScore> list=new ArrayList<TScore>();
		
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				int studentId= rs.getInt("studentId");
				String userName=rs.getString("userName");
				int chinese=rs.getInt("chinese");
				int mathematics=rs.getInt("mathematics");
				int english=rs.getInt("english");
				int total=rs.getInt("total");
				TScore tscore=new TScore(studentId, userName, chinese, mathematics, english, total);
				list.add(tscore);
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TScore> query1(String str){

		String sql="SELECT\r\n" + 
					"t2.studentId,	\r\n" + 
					"t1.userName,\r\n" + 
					"sum(CASE when t2.subjectId=1 THEN t2.score END) chinese,\r\n" + 
					"\r\n" + 
					"sum(CASE when t2.subjectId=2 THEN t2.score END) mathematics,\r\n" + 
					"sum(CASE when t2.subjectId=3 THEN t2.score END) english,\r\n" + 
					"SUM(t2.score) total\r\n" + 
					"FROM t_scoer as t2\r\n" + 
					"INNER JOIN (SELECT id ,userName FROM student WHERE userName="+"?"+") as t1\r\n" + 
					"where t2.studentId=t1.id\r\n" + 
					"GROUP BY t2.studentId";
				
		List<TScore> list=new ArrayList<TScore>();
		
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, str);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				int studentId= rs.getInt("studentId");
				String userName=rs.getString("userName");
				int chinese=rs.getInt("chinese");
				int mathematics=rs.getInt("mathematics");
				int english=rs.getInt("english");
				int total=rs.getInt("total");
				TScore tscore=new TScore(studentId, userName, chinese, mathematics, english, total);
				list.add(tscore);
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}
	
	public List<TScore> query2(String str){

		String sql="SELECT t1.studentId,t2.userName,t1.score FROM\r\n" + 
				"(SELECT studentId,score from t_scoer\r\n" + 
				"WHERE subjectId=(SELECT id FROM subjcet WHERE subName="+"?"+")\r\n" + 
				") as t1\r\n" + 
				"inner JOIN student as t2			\r\n" + 
				"WHERE t2.id=t1.studentId\r\n" + 
				"ORDER BY t1.score DESC";
				
		List<TScore> list=new ArrayList<TScore>();
		
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, str);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {
				int studentId= rs.getInt("studentId");
				String userName=rs.getString("userName");
				int score=rs.getInt("score");
				TScore tscore=new TScore(studentId, userName, score);
				list.add(tscore);
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public List<TScore> T_score_update(TScore score) {
		// TODO Auto-generated method stub
		return null;
	}

}
