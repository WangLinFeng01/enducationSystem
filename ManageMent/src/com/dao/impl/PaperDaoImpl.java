package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.PaperDao;
import com.pojo.Paper;
import com.util.BeanListResultSetHandler;
import com.util.JdbcUtils;
import com.util.QueryRunner;


public  class PaperDaoImpl implements PaperDao {
	@Override
	public List<Paper> gettableDatas() {
		String sql="select * from paper";
		Object[] params=null;
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Paper>(Paper.class));
		
		return list;
	}

	//筛选事件
	@Override
	public List<Paper> getChoiceDatas(Integer obj) {
		String sql="select * from paper where id = ?";
		Object[] params= {obj};
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Paper>(Paper.class));
		
		return list;
	}

	@Override
	public Integer addPaper(Paper paper) {
		String sql="insert into paper values(default,?,?,?)";
		Object[] obj= {paper.getJoinDate(),paper.getPaperName(),paper.getSubjectId()};
		Integer result=new QueryRunner().execute(sql, obj);
		return result;
	}
  // 查询试卷id
	
	@Override
	public List<String> queryPaperId() {
		String sql="select id from paper";
		List<String> list=new ArrayList<String>();
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String id=rs.getInt("id")+"";
				list.add(id);
			}
		    JdbcUtils.close(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
