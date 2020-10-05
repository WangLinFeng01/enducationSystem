package com.whf.dao;

import java.util.List;

import com.whf.pojo.Paper;

public interface PaperDao {

	//得到所有元素的表
	List<Paper> gettableDatas();
	//筛选事件
	List<Paper> getChoiceDatas(Integer obj);
}
