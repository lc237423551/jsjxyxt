package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.SpecialityDao;
import com.java.entity.Speciality;
import com.java.service.SpecialityService;



@Service("specialityService")
public class SpecialityServiceImpl implements SpecialityService{
	@Resource
	private SpecialityDao specialityDao;

	@Override
	public List<Speciality> findSpeciality(Speciality speciality) {
		// TODO Auto-generated method stub
		return specialityDao.findSpeciality(speciality);
	}

	

	@Override
	public void addSpeciality(Speciality speciality) {
		specialityDao.addSpeciality(speciality);
		
	}



	@Override
	public void deleteSpeciality(Speciality speciality) {
		specialityDao.deleteSpeciality(speciality);
		
	}



	@Override
	public void updateSpeciality(Speciality speciality) {
		specialityDao.updateSpeciality(speciality);
		
	}


	










}
