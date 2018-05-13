package com.java.service;

import java.util.List;

import com.java.entity.Dictionary;

public interface DictionaryService {
	/**
	 * 获取所有数据字典类型
	 */
	public List<Dictionary> getAllDictionary();
}
