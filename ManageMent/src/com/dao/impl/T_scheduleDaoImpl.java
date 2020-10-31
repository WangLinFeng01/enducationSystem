package com.dao.impl;

import java.util.List;

import com.dao.T_scheduleDao;
import com.pojo.T_schedule;
import com.util.BeanListResultSetHandler;
import com.util.QueryRunner;


public class T_scheduleDaoImpl implements T_scheduleDao {

	//重写拿到T_schedule表中的数据的
	@Override
	public List<T_schedule> getDatas() {
			String sql="select * from t_schedule";
			Object[] params=null;
			List<T_schedule> list=(List<T_schedule>) QueryRunner.query(sql, params, new BeanListResultSetHandler<T_schedule>(T_schedule.class));
			return list;
	}

	@Override
	public int updateTable(Object[] params) {
		String sql = "update t_schedule set speed = ? where subject = ?";
		int i =new QueryRunner().execute(sql, params);
		return i;
	}
}
