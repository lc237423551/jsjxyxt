package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.Subjectname;
import com.java.entity.TrainInfo;

public interface SubjectnameService {
	//得到实训题目
	public List<Subjectname> findSubjectname(Subjectname subjectname);
	//根据id得到实训内容
	public Subjectname findById(Integer id);
	//更新实训题目	
	public int updateSubjectname(Subjectname subjectname);
	//更新实训题目	实训题目教师可以为空
		public int updateSubjectname2(Subjectname subjectname);
	//删除
	public int deleteSubjectname(Integer id);
	//保存实训题目
	public int insertSubjectname(Subjectname subjectname);
}
