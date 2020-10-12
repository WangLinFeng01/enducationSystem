package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.QuestionSettingDao;
import com.pojo.Question;
import com.util.BeanListResultSetHandler;
import com.util.JdbcUtils;
import com.util.QueryRunner;

public class QuestionSettingDaoImpl implements QuestionSettingDao{

	@Override
	public Integer questionSetting(Question question) {
		
		String sql="insert into question(answer,joinTime,optionA,optionB,optionC,optionD,optionE,subText,type,subjectId,paperId) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		Object[] obj= {question.getAnswer(),question.getJoinTime(),question.getOptionA(),question.getOptionB(),question.getOptionC()
				,question.getOptionD(),question.getOptionE(),question.getSubText(),question.getType(),question.getSubjectId(),question.getPaperId()};
		Integer result=new QueryRunner().execute(sql, obj);
		return result;
		
		
	}

	@Override
	public List<Question> queryQuestion(Integer paperId) {
		String sql="select * from question where paperId=?";
		Object[] params= {paperId};
		List <Question>list=(List<Question>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Question>(Question.class));
		return list;
	}

	@Override
	public List queryPaperId() {
		String sql="select DISTINCT paperId from question";
		List list=new ArrayList();
		try {
			Connection  conn=JdbcUtils.getConnection();
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 String paperid="";
			 while(rs.next()) {
				 
				 paperid= Integer.toString(rs.getInt("paperId"));
				 list.add(paperid);
			 }
			 JdbcUtils.close(conn, ps, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
	}

	@Override
	public Integer questionUpdate(Question question) {
		String sql="update question set answer=?,optionA=?,optionB=?,optionC=?,optionD=?,optionE=?,subText=?,type=?,subjectId=?,paperId=? where id=?";
		Object[] obj= {question.getAnswer(),question.getOptionA(),question.getOptionB(),question.getOptionC(),question.getOptionD(),question.getOptionE(),
				question.getSubText(),question.getType(),question.getSubjectId(),question.getPaperId(),question.getId()};
		Integer result=new QueryRunner().execute(sql, obj);
		return result;
	}

}
