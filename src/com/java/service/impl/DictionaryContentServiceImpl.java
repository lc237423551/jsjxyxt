package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.DictionaryContentDao;
import com.java.entity.DictionaryContent;
import com.java.service.DictionaryContentService;
@Service("dictionaryContentService")
public class DictionaryContentServiceImpl implements DictionaryContentService {
	@Resource
	private DictionaryContentDao dictionaryContentDao;

	@Override
	public List<DictionaryContent> getDictionaryContentByDictionaryId(int dictionaryId) {
		// TODO Auto-generated method stub
		return dictionaryContentDao.getDictionaryContentByDictionaryId(dictionaryId);
	}

	@Override
	public void addDictionaryContent(DictionaryContent dictionaryContent) {
		dictionaryContentDao.addDictionaryContent(dictionaryContent);
	}

	@Override
	public void updateDictionaryContent(DictionaryContent dictionaryContent) {
		// TODO Auto-generated method stub
		dictionaryContentDao.updateDictionaryContent(dictionaryContent);
	}

	@Override
	public List<DictionaryContent> getDictionaryContent(int dictionaryId) {
		// TODO Auto-generated method stub
		return dictionaryContentDao.getDictionaryContent(dictionaryId);
	}

	@Override
	public void deleteDictionaryContent(String dictionaryId) {
		dictionaryContentDao.deleteDictionaryContent(dictionaryId);
	}
	
	

}
