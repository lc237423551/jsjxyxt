package com.java.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.TrainInfoDao;
import com.java.entity.TrainInfo;
import com.java.service.TrainInfoService;


@Service("trainInfoService")
public class TrainInfoServiceImpl implements TrainInfoService{
	@Resource
	private TrainInfoDao trainInfoDao;

	@Override
	public List<TrainInfo> findTrainInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return trainInfoDao.findTrainInfo(map);
	}

	@Override
	public int insertTrainInfo(List<TrainInfo> list) {
		// TODO Auto-generated method stub
		return trainInfoDao.insertTrainInfo(list);
	}

	@Override
	public int updateTrainInfo(TrainInfo trainInfo) {
		// TODO Auto-generated method stub
		return trainInfoDao.updateTrainInfo(trainInfo);
	}

	@Override
	public List<TrainInfo> findTrainInfo2(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return trainInfoDao.findTrainInfo2(map);
	}

	@Override
	public List<TrainInfo> findByTno(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return trainInfoDao.findByTno(map);
	}







}
