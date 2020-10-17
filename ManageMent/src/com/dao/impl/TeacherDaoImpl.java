package com.dao.impl;

import java.util.List;

import com.dao.TeacherDao;
import com.pojo.Teacher;
import com.util.BeanListResultSetHandler;
import com.util.QueryRunner;
import com.util.ResultSetHandler;

//����ʵ�ֽӿ�TeacherDao��ʵ����TeacherDaoImpl
public class TeacherDaoImpl implements TeacherDao {

	//��дTeacher����
	@Override
	public List<Teacher> getDatas() {
		String sql="select teaName,password from teacher";
		Object[] params=null;
		List<Teacher> list=(List<Teacher>) QueryRunner.query(sql, params, new BeanListResultSetHandler<Teacher>(Teacher.class));
		System.out.println(list+"dddd");
		return list;
	}
}
