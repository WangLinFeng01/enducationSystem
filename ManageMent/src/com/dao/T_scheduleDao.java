package com.dao;

import java.util.List;

import com.pojo.T_schedule;


public interface T_scheduleDao {
	
	//根据科目的类型来获取数据
	List<T_schedule> getDatas();
	//根据subject来修改表结构
	int updateTable(Object[] params);

}
