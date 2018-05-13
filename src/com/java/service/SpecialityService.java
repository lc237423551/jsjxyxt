package com.java.service;

import java.util.List;

import com.java.entity.Speciality;

public interface SpecialityService {
	public List<Speciality> findSpeciality(Speciality speciality);
	
	public void addSpeciality(Speciality speciality);
	
	public void deleteSpeciality(Speciality speciality);
	
	public void updateSpeciality(Speciality speciality);
}
