package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.entity.Vacate;

public interface VacateDao {
	/***
	 * 添加申请信息
	 * @param vacate
	 */
	public void insertVacate(Vacate vacate);
	/***
	 * 得到申请信息
	 * @param map
	 * @return
	 */
	public List<Vacate> findApply(Map map);
	/***
	 * 更新申请信息
	 * @param vacate
	 */
	public void updateStaus(Vacate vacate);
	public void deleteVacate(Vacate vacate);
	/**
	 * 根据学生id获取记录
	 * @param studentId
	 */
	public Vacate getVacateByStudentId(String studentId);
	/***
	 * 搜索
	 * @param map
	 * @return
	 */
	public List<Vacate> searchApply(Map map);
	
	
	public Vacate findById(Integer v_id);
}
