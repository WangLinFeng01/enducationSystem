package com.whf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.whf.dao.QuestionSettingDao;
import com.whf.pojo.Question;
import com.whf.util.JdbcUtils;

public class QuestionSettingDaoImpl implements QuestionSettingDao{

	@Override
	public Integer questionSetting(Question question) {
		// TODO Auto-generated method stub
		String sql="insert into question(answer,joinTime,optionA,optionB,optionC,optionD,optionE,subText,type,subjectId,paperId) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection conn=JdbcUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
	
			ps.setString(1, question.getAnswer());
			ps.setObject(2, question.getJoinTime());
			ps.setString(3, question.getOptionA());
			ps.setString(4, question.getOptionB());
			ps.setString(5, question.getOptionC());
			ps.setString(6, question.getOptionD());
			ps.setString(7, question.getOptionE());
			ps.setString(8, question.getSubText());
			ps.setString(9, question.getType());
			ps.setInt(10, question.getSubjectId());
			ps.setInt(11, question.getPaperId());
			
			Integer result =ps.executeUpdate();
			conn.close();
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
