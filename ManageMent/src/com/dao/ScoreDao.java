package com.dao;

import java.util.List;

import com.pojo.TScore;

public interface ScoreDao {
	public List<TScore> Score_query();//ȫ���ѯ
	public List<TScore> query1(String str);//������ѯ
	public List<TScore> query2(String str);//��Ŀ��ѯ
	public List<TScore> T_score_update(TScore score);
}
