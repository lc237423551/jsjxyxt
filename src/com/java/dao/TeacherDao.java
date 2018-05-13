package com.java.dao;

import java.util.List;

import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.Teacher;

public interface TeacherDao {
	public List<Teacher> findTeacher(Teacher teacher);
	
	public int updateTeacher(Teacher teacher);
	
	public int deleteTeacher(String tno);
	
	public int insertTeacher(Teacher teacher);
	
	public int insertAll(List<Teacher> list);
	//根据学号得到班主任信息
	public Teacher findBySno(String sno);
}
