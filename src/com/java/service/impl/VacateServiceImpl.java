package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.VacateDao;
import com.java.entity.Vacate;
import com.java.service.VacateService;
@Service("VacateService")
public class VacateServiceImpl implements VacateService {
	@Resource
	private VacateDao vacatedao;
	@Override
	public void insertVacate(Vacate vacate) {
		vacatedao.insertVacate(vacate);
	}
	@Override
	public List<Vacate> findApply(Map map) {
		// TODO Auto-generated method stub
		return vacatedao.findApply(map);
	}
	@Override
	public void updateStaus(Vacate vacate) {
		vacatedao.updateStaus(vacate);
	}
	@Override
	public void deleteVacate(Vacate vacate) {
		vacatedao.deleteVacate(vacate);
	}
	@Override
	public Vacate getVacateByStudentId(String studentId) {
		return vacatedao.getVacateByStudentId(studentId);
	}
	@Override
	public List<Vacate> searchApply(Map map) {
		// TODO Auto-generated method stub
		return vacatedao.searchApply(map);
	}
	@Override
	public Vacate findById(Integer v_id) {
		// TODO Auto-generated method stub
		return vacatedao.findById(v_id);
	}
   
}
