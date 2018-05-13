package com.java.service;

import java.util.List;

import com.java.entity.Advice;

public interface AdviceService {
	public List<Advice> findAdvice(Advice advice);
	
	//修改
	  public int updateAdvice(Advice advice);
	//删除
	  public int deleteAdvice(String advice_id);
	//添加
	  public int insertAdvice(Advice advice);
}
