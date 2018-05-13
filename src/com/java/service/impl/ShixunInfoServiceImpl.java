package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.ShixunInfoDao;
import com.java.entity.ShixunInfo;
import com.java.service.ShixunInfoService;


@Service("shixunInfoService")
public class ShixunInfoServiceImpl implements ShixunInfoService{
	@Resource
	private ShixunInfoDao shixunInfoDao;

	@Override
	public List<ShixunInfo> findShixunInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return shixunInfoDao.findShixunInfo(map);
	}

	@Override
	public int updateShixunInfo(ShixunInfo shixunInfo) {
		// TODO Auto-generated method stub
		return shixunInfoDao.updateShixunInfo(shixunInfo);
	}

	@Override
	public int insertShixunInfo(ShixunInfo shixunInfo) {
		// TODO Auto-generated method stub
		return shixunInfoDao.insertShixunInfo(shixunInfo);
	}

	




}
