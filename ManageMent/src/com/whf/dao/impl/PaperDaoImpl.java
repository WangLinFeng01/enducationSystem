package com.whf.dao.impl;

import java.util.List;

import com.whf.dao.PaperDao;
import com.whf.pojo.Paper;
import com.whf.util.BeanListResultSetHandler;
import com.whf.util.QueryRunner;


public  class PaperDaoImpl implements PaperDao {
	@Override
	public List<Paper> gettableDatas() {
		String sql="select * from paper";
		Object[] params=null;
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Paper>(Paper.class));
		
		return list;
	}

	//É¸Ñ¡ÊÂ¼þ
	@Override
	public List<Paper> getChoiceDatas(Integer obj) {
		String sql="select * from paper where id = ?";
		Object[] params= {obj};
		List<Paper> list=(List<Paper>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Paper>(Paper.class));
		
		return list;
	}

}
