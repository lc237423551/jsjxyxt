package com.java.service;

import java.util.List;

import com.java.entity.DictionaryContent;

public interface DictionaryContentService {
	/**
	 * 根据数据字典类型id获取该类型下的所有数据字典类型
	 * @param dictionaryId
	 * @return
	 */
	public List<DictionaryContent> getDictionaryContentByDictionaryId(int dictionaryId);
	/**
	 * 增加数据字典内容
	 * @param dictionaryContent
	 */
	public void addDictionaryContent(DictionaryContent dictionaryContent);
	/**
	 * 修改数据字典内容
	 * @param dictionaryContent
	 */
	public void updateDictionaryContent(DictionaryContent dictionaryContent);
	/***
	 * 根据数据字典类型编号获取数据编号类型
	 * @param dictionaryId
	 * @return
	 */
	public List<DictionaryContent> getDictionaryContent(int dictionaryId);
	/**
	 * 删除数据字典
	 * @param dictionaryId
	 */
	public void deleteDictionaryContent(String dictionaryId);
}
