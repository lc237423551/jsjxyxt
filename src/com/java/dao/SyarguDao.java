package com.java.dao;

import java.util.List;

import com.java.entity.Syargu;

public interface SyarguDao {
	//
	public List<Syargu> findSyargu(Syargu syargu);
	
	public int updateSyargu(Syargu syargu);
	
	public int deleteAll();
}
