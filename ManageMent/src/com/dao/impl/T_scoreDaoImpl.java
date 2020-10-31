package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.dao.T_scoreDao;
import com.pojo.T_score;
import com.util.JdbcUtils;
import com.util.QueryRunner;


public class T_scoreDaoImpl implements T_scoreDao {

	@Override
	public List<T_score> T_score_query() {
		String sql="SELECT score,studentId,subjectId FROM t_scoer";
		
		List<T_score> list =new ArrayList<T_score>();
		try {
			Connection  conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				//������ѯ���Ľ����������Course����
				int score=rs.getInt("score");
				int studentId=rs.getInt("studentId");
				int subjectId=rs.getInt("subjectId");
				T_score tscore=new T_score(score,studentId,subjectId);
				//����ѯ�Ķ�����뼯����
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

	@Override
	public void transferTable(Object[] obj) {
		//�ɼ�id,����ʱ��,��߳ɼ�,�Ծ�id,
		String sql="insert into t_scoer value (null,?,?,?,?,?)";
		new QueryRunner().execute(sql, obj);
		JOptionPane.showMessageDialog(null, "�ύ�ɹ�");		
	}	
}
