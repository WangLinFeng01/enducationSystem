package com.whf.dao;

import java.util.List;

import com.whf.pojo.T_schedule;


public interface T_scheduleDao {
	
	//���ݿ�Ŀ����������ȡ����
	List<T_schedule> getDatas(String str);

}
