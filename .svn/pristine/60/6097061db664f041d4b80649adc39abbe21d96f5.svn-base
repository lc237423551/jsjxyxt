package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.PracticeappliDao;
import com.java.entity.Practiceappli;
import com.java.service.PracticeappliService;
@Service("PracticeappliService")
public class PracticeappliServiceImpl implements PracticeappliService {
	@Resource
	private PracticeappliDao practiceapplidao;
	@Override
	public void insertPracticeappli(Practiceappli practiceappli) {
		
		practiceapplidao.insertPracticeappli(practiceappli);
	}
	@Override
	public void deletePracticeappli(Practiceappli practiceappli) {
		practiceapplidao.deletePracticeappli(practiceappli);
		
	}
	@Override
	public void updatePracticeappli(Practiceappli practiceappli) {
		// TODO Auto-generated method stub
		practiceapplidao.updatePracticeappli(practiceappli);
	}
	@Override
	public Practiceappli selectPracticeappli(String studentId) {
		return practiceapplidao.selectPracticeappli(studentId);
	}
	@Override
	public List<Practiceappli> findPractice(Practiceappli pra) {
		// TODO Auto-generated method stub
		return practiceapplidao.findPractice(pra);
	}

}
