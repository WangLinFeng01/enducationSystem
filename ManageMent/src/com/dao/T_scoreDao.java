package com.dao;

import java.util.List;

import com.pojo.T_score;

public interface T_scoreDao {
	public List<T_score> T_score_query();
	public List<T_score> T_score_update(T_score score);
	//考试结果插入到数据库
	public void transferTable(Object[] obj);
}
