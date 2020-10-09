package com.dao.impl;

import java.util.List;

import com.dao.T_scheduleDao;
import com.pojo.T_schedule;
import com.util.BeanListResultSetHandler;
import com.util.QueryRunner;


public class T_scheduleDaoImpl implements T_scheduleDao {

	//��д�õ�T_schedule���е����ݵ�
	@Override
	public List<T_schedule> getDatas(String str) {
		
		switch (str) {
		case "��ѧ":
			String sql="select * from t_schedule where courseName = '��ѧ' order by id";
			Object[] params=null;
			List<T_schedule> list=(List<T_schedule>) QueryRunner.query(sql, params, new BeanListResultSetHandler<T_schedule>(T_schedule.class));
			return list;
		case "java����":
			String sql1="select * from t_schedule where courseName = 'java����' order by id";
			Object[] params1=null;
			List<T_schedule> list1=(List<T_schedule>) QueryRunner.query(sql1, params1, new BeanListResultSetHandler<T_schedule>(T_schedule.class));
			return list1;

		default:
			break;
		}
		return null;
	}
}
