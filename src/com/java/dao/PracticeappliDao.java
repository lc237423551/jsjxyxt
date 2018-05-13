package com.java.dao;

import java.util.List;

import com.java.entity.Practiceappli;

public interface PracticeappliDao {
	//添加实训申请
	public void insertPracticeappli(Practiceappli practiceappli);
	//删除实训申请
	public void deletePracticeappli(Practiceappli practiceappli);
	//更新请假表
	public void updatePracticeappli(Practiceappli practiceappli);
	/**
	 * 根据id获取记录
	 * @param practiceappliId
	 */
	public Practiceappli selectPracticeappli(String studentId);
	//根据id得到记录
	public List<Practiceappli> findPractice(Practiceappli practiceappli);
}
