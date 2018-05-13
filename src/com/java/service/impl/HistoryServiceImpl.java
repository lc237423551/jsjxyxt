package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.HistoryDao;
import com.java.entity.History;
import com.java.service.HistoryService;




@Service("historyService")
public class HistoryServiceImpl implements HistoryService{
	@Resource
	private HistoryDao historyDao;

	@Override
	public List<History> findHistory(History history) {
		// TODO Auto-generated method stub
		return historyDao.findHistory(history);
	}

	@Override
	public int updateHistory() {
		// TODO Auto-generated method stub
		return historyDao.updateHistory();
	}

	@Override
	public int insertAll(List<History> list) {
		// TODO Auto-generated method stub
		return historyDao.insertAll(list);
	}


}
