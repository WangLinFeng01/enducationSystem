package com.dao;

import java.util.List;

public interface SubjectDao {
	public List<String> querysubNames();
	public String querysubName(Integer subjectId);
	public Integer querysubjectId(String querysubName);
}
