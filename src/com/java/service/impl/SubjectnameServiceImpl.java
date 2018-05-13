package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.SubjectnameDao;
import com.java.entity.Subjectname;
import com.java.service.SubjectnameService;

@Service("subjectnameService")
public class SubjectnameServiceImpl implements SubjectnameService{
	@Resource
	private SubjectnameDao subjectnameDao;

	@Override
	public List<Subjectname> findSubjectname(Subjectname subjectname) {
		// TODO Auto-generated method stub
		return subjectnameDao.findSubjectname(subjectname);
	}

	@Override
	public Subjectname findById(Integer id) {
		// TODO Auto-generated method stub
		return subjectnameDao.findById(id);
	}

	@Override
	public int updateSubjectname(Subjectname subjectname) {
		// TODO Auto-generated method stub
		return subjectnameDao.updateSubjectname(subjectname);
	}

	@Override
	public int deleteSubjectname(Integer id) {
		// TODO Auto-generated method stub
		return subjectnameDao.deleteSubjectname(id);
	}

	@Override
	public int insertSubjectname(Subjectname subjectname) {
		// TODO Auto-generated method stub
		return subjectnameDao.insertSubjectname(subjectname);
	}

	@Override
	public int updateSubjectname2(Subjectname subjectname) {
		// TODO Auto-generated method stub
		return subjectnameDao.updateSubjectname2(subjectname);
	}

	


}
