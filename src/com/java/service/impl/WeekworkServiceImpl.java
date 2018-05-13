package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.WeekworkDao;
import com.java.entity.Weekwork;
import com.java.service.WeekworkService;


@Service("weekworkService")
public class WeekworkServiceImpl implements WeekworkService{
	@Resource
	private WeekworkDao weekworkDao;

	@Override
	public List<Weekwork> findWeekwork(Weekwork weekwork) {
		// TODO Auto-generated method stub
		return weekworkDao.findWeekwork(weekwork);
	}

	@Override
	public int findCount(String sno) {
		// TODO Auto-generated method stub
		return weekworkDao.findCount(sno);
	}

	@Override
	public int updateWeekwork(Weekwork weekwork) {
		// TODO Auto-generated method stub
		return weekworkDao.updateWeekwork(weekwork);
	}

	@Override
	public int insertWeekwork(List<Weekwork> list) {
		// TODO Auto-generated method stub
		return weekworkDao.insertWeekwork(list);
	}


}
