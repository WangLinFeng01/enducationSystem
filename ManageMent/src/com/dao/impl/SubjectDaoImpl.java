package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.SubjectDao;
import com.util.JdbcUtils;

public class SubjectDaoImpl implements SubjectDao {

	//查询所有的科目名称
	public List<String> querysubNames() {
		List<String> list=new ArrayList<String>();
		String sql="select subName from subjcet";
		try {
			Connection conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String subName=rs.getString("subName");
				list.add(subName);
			}
			JdbcUtils.close(conn, ps, rs);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    //根据科目id查询科目名称
	@Override
	public String querysubName(Integer subjectId) {
		String sql="select subName from subjcet where id=?";
		String subName="";
		try {
			Connection conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, subjectId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				subName=rs.getString("subName");
				
			}
			JdbcUtils.close(conn, ps, rs);
			return subName;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
  //根据科目名称查询科目id
	@Override
	public Integer querysubjectId(String subName) {
		String sql="select id from subjcet where subName=?";
		Integer subjectId=0;
		try {
			Connection conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, subName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				subjectId=rs.getInt("id");
			}
			JdbcUtils.close(conn, ps, rs);
			return subjectId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
