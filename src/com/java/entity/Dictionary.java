package com.java.entity;

import java.util.List;
/**
 * 数据字典类型
 * @author Administrator
 *
 */
public class Dictionary {
	private int id;
	private String type;
	List<DictionaryContent> dictionarys;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<DictionaryContent> getDictionarys() {
		return dictionarys;
	}
	public void setDictionarys(List<DictionaryContent> dictionarys) {
		this.dictionarys = dictionarys;
	}
}
