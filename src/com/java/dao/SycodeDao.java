package com.java.dao;

import java.util.List;

 
import com.java.entity.Sycode;

public interface SycodeDao {
	public List<Sycode> findSycode(Sycode sycode);
	
	public int updateSycode(Sycode sycode);
	
	public int deleteSycode(Integer codeid);
	
	public int addSycode(Sycode sycode);
	
}
