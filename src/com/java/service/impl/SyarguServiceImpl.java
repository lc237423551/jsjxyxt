package com.java.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.SyarguDao;
import com.java.entity.Syargu;
import com.java.service.SyarguService;



@Service("syarguService")
public class SyarguServiceImpl implements SyarguService{
	@Resource
	private SyarguDao syarguDao;

	@Override
	public List<Syargu> findSyargu(Syargu syargu) {
		// TODO Auto-generated method stub
		return syarguDao.findSyargu(syargu);
	}

	@Override
	public int updateSyargu(Syargu syargu) {
		// TODO Auto-generated method stub
		return syarguDao.updateSyargu(syargu);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return syarguDao.deleteAll();
	}
}









