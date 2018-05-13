package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UnitDao;
import com.java.entity.TrainInfo;
import com.java.entity.Unit;
import com.java.service.UnitService;

@Service("unitService")
public class UnitServiceImpl implements UnitService{
	@Resource
	private UnitDao unitDao;

	@Override
	public List<Unit> findUnit(Unit unit) {
		// TODO Auto-generated method stub
		return unitDao.findUnit(unit);
	}

	@Override
	public List<Unit> findAllcity() {
		// TODO Auto-generated method stub
		return unitDao.findAllcity();
	}

	@Override
	public List<Unit> findAllname() {
		// TODO Auto-generated method stub
		return unitDao.findAllname();
	}

	@Override
	public int deleteUnit(Integer uid) {
		// TODO Auto-generated method stub
		return unitDao.deleteUnit(uid);
	}

	@Override
	public int updateUnit(Unit unit) {
		// TODO Auto-generated method stub
		return unitDao.updateUnit(unit);
	}


	@Override
	public int findnum(TrainInfo trainInfo) {
		// TODO Auto-generated method stub
		return unitDao.findnum(trainInfo);
	}

	@Override
	public int insertUnit(Unit unit) {
		// TODO Auto-generated method stub
		return unitDao.insertUnit(unit);
	}

	@Override
	public int insertAll(List<Unit> list) {
		// TODO Auto-generated method stub
		return unitDao.insertAll(list);
	}
	
	@Override
	public List<Unit> findByname(Unit unit) {
		// TODO Auto-generated method stub
		return unitDao.findByname( unit);
	}

	@Override
	public int updateUnit2(Unit unit) {
		// TODO Auto-generated method stub
		return unitDao.updateUnit2(unit);
	}

	@Override
	public String getCompanyNameById(int companyId) {
		return unitDao.getCompanyNameById(companyId);
	}

}
