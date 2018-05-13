package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.StudentDao;
import com.java.entity.Student;
import com.java.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Resource
	private StudentDao studentDao;

	@Override
	public List<Student> findStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.findStudent(student);
	}

	@Override
	public int updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.updateStudent(student);
	}

	@Override
	public int deleteStudent(String sno) {
		// TODO Auto-generated method stub
		return studentDao.deleteStudent(sno);
	}

	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDao.insertStudent(student);
	}

	@Override
	public int insertAll(List<Student> list) {
		// TODO Auto-generated method stub
		return studentDao.insertAll(list);
	}

	@Override
	public Student getStudentbyId(String studentId) {
		return studentDao.getStudentbyId(studentId);
	}

	


}
