package com.java.service;

import java.util.List;

import com.java.entity.TrainInfo;
import com.java.entity.Unit;

public interface UnitService {
	public List<Unit> findUnit(Unit unit);
	
	    //获得所有城市
		public List<Unit> findAllcity();
		//获得所有公司名称
		public List<Unit> findAllname();
		//删除公司
		public int deleteUnit(Integer uid);
		//修改公司
		public int updateUnit(Unit unit);
		//修改公司  指导老师可以为空
		public int updateUnit2(Unit unit);
		//获取当前周单位实习人数
		public int findnum(TrainInfo trainInfo);
		//添加
		public int insertUnit(Unit unit);
		//批量添加
		public int insertAll(List<Unit> list);
		//通过公司名称得到公司
		public List<Unit> findByname(Unit unit);
		//通过公司编号获取公司名字
		public String getCompanyNameById(int companyId);
}
