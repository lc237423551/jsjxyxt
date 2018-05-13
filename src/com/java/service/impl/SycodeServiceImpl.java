package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.SycodeDao;
import com.java.entity.Sycode;
import com.java.service.SycodeService;

@Service("sycodeService")
public class SycodeServiceImpl implements SycodeService{
	@Resource
	private SycodeDao sycodeDao;

	@Override
	public List<Sycode> findSycode(Sycode sycode) {
		// TODO Auto-generated method stub
		return sycodeDao.findSycode(sycode);
	}

	@Override
	public int updateSycode(Sycode sycode) {
		// TODO Auto-generated method stub
		return sycodeDao.updateSycode(sycode);
	}

	@Override
	public int deleteSycode(Integer codeid) {
		// TODO Auto-generated method stub
		return sycodeDao.deleteSycode(codeid);
	}

	@Override
	public int addSycode(Sycode sycode) {
		// TODO Auto-generated method stub
		return sycodeDao.addSycode(sycode);
	}


}
