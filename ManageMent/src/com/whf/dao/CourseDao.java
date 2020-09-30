package com.whf.dao;

import java.util.List;

import com.whf.pojo.Course;



public interface CourseDao {
	public List<Course> course_query();
	public List<Course> course_update(Course course);

}
