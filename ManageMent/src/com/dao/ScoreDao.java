package com.dao;

import java.util.List;

import com.pojo.TScore;

public interface ScoreDao {
	public List<TScore> Score_query();//全表查询
	public List<TScore> query1(String str);//姓名查询
	public List<TScore> query2(String str);//科目查询
	public List<TScore> T_score_update(TScore score);
}
