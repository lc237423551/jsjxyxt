package com.java.dao;

import java.util.List;

import com.java.entity.Weekwork;

public interface WeekworkDao {
	public List<Weekwork> findWeekwork(Weekwork weekwork);
	
	//获得已完成的周总结个数
	public int findCount(String sno);
	
	//保存周总结
	public int updateWeekwork(Weekwork weekwork);
	
	//添加
	public int insertWeekwork(List<Weekwork> list);
}
