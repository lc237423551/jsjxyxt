package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.ShixunInfo;

public interface ShixunInfoService {
	public List<ShixunInfo> findShixunInfo(Map<String, Object> map);
	
	public int updateShixunInfo(ShixunInfo shixunInfo);
	
	//添加
		public int insertShixunInfo(ShixunInfo shixunInfo);
}
