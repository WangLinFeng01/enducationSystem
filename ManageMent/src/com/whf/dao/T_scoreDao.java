package com.whf.dao;

import java.util.List;

import com.whf.pojo.T_score;

public interface T_scoreDao {
	public List<T_score> T_score_query();
	public List<T_score> T_score_update(T_score score);
}
