package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.TrainInfo;

public interface TrainInfoService {
	public List<TrainInfo> findTrainInfo(Map<String, Object> map);
	//含有任务书内容 
		public List<TrainInfo> findTrainInfo2(Map<String, Object> map);
		//根据指导老师得到指导老师所指导的学生列表
		public List<TrainInfo> findByTno(Map<String, Object> map);
	//添加
		
		public int insertTrainInfo(List<TrainInfo> list);
		public int updateTrainInfo(TrainInfo trainInfo);
}
