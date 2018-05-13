package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.ClaDao;
import com.java.entity.Cla;
import com.java.service.ClaService;


@Service("claService")
public class ClaServiceImpl implements ClaService{
	@Resource
	private ClaDao claDao;

	@Override
	public List<Cla> findCla(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return claDao.findCla(map);
	}

	@Override
	public void addCla(Cla cla) {
		
		claDao.addCla(cla);
		
	}

	@Override
	public void deleteCla(Cla cla) {
		claDao.deleteCla(cla);
		
	}

	@Override
	public void update(Cla cla) {
		claDao.updateCla(cla);
		
	}

	@Override
	public List<Cla> findByTno(String tno) {
		// TODO Auto-generated method stub
		return claDao.findByTno(tno);
	}

	@Override
	public List<Cla> findCla1(String tno) {
		// TODO Auto-generated method stub
		return claDao.findCla1(tno);
	}








}
