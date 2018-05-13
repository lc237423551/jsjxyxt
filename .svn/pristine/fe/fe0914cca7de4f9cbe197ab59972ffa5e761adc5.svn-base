package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.DictionaryDao;
import com.java.entity.Dictionary;
import com.java.service.DictionaryService;
@Service("dictionary")
public class DictionaryServiceImpl implements DictionaryService{
	@Resource
	private DictionaryDao dictionaryDao;
	@Override
	public List<Dictionary> getAllDictionary() {
		// TODO Auto-generated method stub
		return dictionaryDao.getAllDictionary();
	}

}
