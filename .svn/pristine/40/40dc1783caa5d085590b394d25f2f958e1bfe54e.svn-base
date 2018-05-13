package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.AdviceDao;
import com.java.entity.Advice;
import com.java.service.AdviceService;

@Service("adviceService")
public class AdviceServiceImpl implements AdviceService{
	@Resource
	private AdviceDao adviceDao;

	@Override
	public List<Advice> findAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return adviceDao.findAdvice(advice);
	}

	@Override
	public int updateAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return adviceDao.updateAdvice(advice);
	}

	@Override
	public int deleteAdvice(String advice_id) {
		// TODO Auto-generated method stub
		return adviceDao.deleteAdvice(advice_id);
	}

	@Override
	public int insertAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return adviceDao.insertAdvice(advice);
	}

}
