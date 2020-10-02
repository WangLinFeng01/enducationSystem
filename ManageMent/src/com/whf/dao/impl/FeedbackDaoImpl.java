package com.whf.dao.impl;

import java.util.List;

import com.whf.dao.FeedbackDao;
import com.whf.pojo.Feedback;
import com.whf.util.BeanListResultSetHandler;
import com.whf.util.QueryRunner;


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
