package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.Vacate;

public interface VacateService {
	public void insertVacate(Vacate vacate);
	/**
	 * 根据条件返回申请记录
	 * @param map
	 * @return
	 */
	public List<Vacate> findApply(Map map);
	public void updateStaus(Vacate vacate);
	public void deleteVacate(Vacate vacate);
	/**
	 * 根据学生id获取记录
	 * @param studentId
	 */
	public Vacate getVacateByStudentId(String studentId);
	
	public List<Vacate> searchApply(Map map);
	
	public Vacate findById(Integer v_id);
}
