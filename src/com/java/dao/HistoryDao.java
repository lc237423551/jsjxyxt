package com.java.dao;

import java.util.List;

import com.java.entity.History;

public interface HistoryDao {
	public List<History> findHistory(History history);
	//清空历史
	public int updateHistory();
	//批量添加 
	public  int  insertAll(List<History> list);
}
