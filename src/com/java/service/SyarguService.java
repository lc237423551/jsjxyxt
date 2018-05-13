package com.java.service;

import java.util.List;

import com.java.entity.Syargu;

public interface SyarguService {
public List<Syargu> findSyargu(Syargu syargu);
	
	public int updateSyargu(Syargu syargu);
	
	public int deleteAll();
}
