package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.Cla;

public interface ClaService {
	public List<Cla> findCla(Map<String, Object> map);
	public void addCla(Cla cla);
	public void deleteCla(Cla cla);
	public void update(Cla cla);
	public List<Cla> findByTno(String tno);
	//根据指导教师所带的学生得到班级列表
		public List<Cla> findCla1(String tno);
}
