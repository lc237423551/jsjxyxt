package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.TeacherDao;
import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.Teacher;
import com.java.service.TeacherService;
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{
	@Resource
	private TeacherDao teacherDao;

	@Override
	public List<Teacher> findTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.findTeacher(teacher);
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.updateTeacher(teacher);
	}

	@Override
	public int deleteTeacher(String tno) {
		// TODO Auto-generated method stub
		return teacherDao.deleteTeacher(tno);
	}

	@Override
	public int insertTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.insertTeacher(teacher);
	}

	@Override
	public int insertAll(List<Teacher> list) {
		// TODO Auto-generated method stub
		return teacherDao.insertAll(list);
	}

	@Override
	public Teacher findBySno(String sno) {
		// TODO Auto-generated method stub
		return teacherDao.findBySno(sno);
	}



}
