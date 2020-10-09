package com.dao.impl;

import java.util.List;

import com.dao.FeedbackDao;
import com.pojo.Feedback;
import com.util.BeanListResultSetHandler;
import com.util.QueryRunner;


public class FeedbackDaoImpl implements FeedbackDao {

	
	@Override
	public List<Feedback> getDatas() {
		String sql="select * from feedback order by id";
		Object[] params=null;
		List<Feedback> list=(List<Feedback>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Feedback>(Feedback.class));
		System.out.println(list+"dddd");
		return list;
	}
}
