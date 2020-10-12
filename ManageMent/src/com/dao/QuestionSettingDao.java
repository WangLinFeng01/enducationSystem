package com.dao;


import java.util.List;

import com.pojo.Question;

public interface QuestionSettingDao {
	 public Integer questionSetting(Question question);
     public List<Question> queryQuestion(Integer paperId);
     public List queryPaperId();
     public Integer questionUpdate(Question question);
}
