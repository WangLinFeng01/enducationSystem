package com.dao;

import java.util.List;

import com.pojo.Course;



public interface CourseDao {
	public List<Course> course_query(int classid);
	public Integer course_update(Course course);

}
