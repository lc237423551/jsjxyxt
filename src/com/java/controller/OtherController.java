package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Dictionary;
import com.java.entity.DictionaryContent;
import com.java.service.DictionaryContentService;
import com.java.service.DictionaryService;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/other")
public class OtherController {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryContentService dictionaryContentService;
	
	/**
	 * 获取所有数据字典类型及类型中的内容，转化为json串
	 * @throws Exception 
	 */
	@RequestMapping("/getDictionarys")
	public void getColleges(HttpServletResponse response) throws Exception{		
		List<Dictionary> dictionarys=dictionaryService.getAllDictionary();
		JSONArray dictionaryResult=new JSONArray();
		for(int i=0;i<dictionarys.size();i++){
			JSONObject j=new JSONObject();
			j.put("id", dictionarys.get(i).getId());
			j.put("pId", -1);
			j.put("name", dictionarys.get(i).getType());
			dictionaryResult.add(j);
			List<DictionaryContent> childrens=dictionaryContentService.getDictionaryContentByDictionaryId(dictionarys.get(i).getId());
			for(int n=0;n<childrens.size();n++){
				JSONObject json=new JSONObject();
				json.put("id", dictionarys.get(i).getId()+"_"+childrens.get(n).getId());
				json.put("pId", dictionarys.get(i).getId());
				json.put("name", childrens.get(n).getValue());
				dictionaryResult.add(json);
			}
		}
		ResponseUtil.write(response, dictionaryResult);
	}
	/**
	 * 增加数据字典内容
	 * @param dictionaryContent
	 * @throws IOException 
	 */
	@RequestMapping("/addDictionaryContent")
	public void addDictionaryContent(DictionaryContent dictionaryContent,HttpServletResponse response) throws IOException{
		dictionaryContentService.addDictionaryContent(dictionaryContent);
		List<Dictionary> dictionarys=dictionaryService.getAllDictionary();
		JSONArray dictionaryResult=new JSONArray();
		for(int i=0;i<dictionarys.size();i++){
			JSONObject j=new JSONObject();
			j.put("id", dictionarys.get(i).getId());
			j.put("pId", -1);
			j.put("name", dictionarys.get(i).getType());
			dictionaryResult.add(j);
			List<DictionaryContent> childrens=dictionaryContentService.getDictionaryContentByDictionaryId(dictionarys.get(i).getId());
			for(int n=0;n<childrens.size();n++){
				JSONObject json=new JSONObject();
				json.put("id", dictionarys.get(i).getId()+"_"+childrens.get(n).getId());
				json.put("pId", dictionarys.get(i).getId());
				json.put("name", childrens.get(n).getValue());
				dictionaryResult.add(json);
			}
		}
		ResponseUtil.write(response,dictionaryResult);
	}
	/**
	 * 修改数据字典内容
	 * @param dictionaryContent
	 * @param response
	 */
	@RequestMapping("/updateDictionaryContent")
	public void updateDictionaryContent(DictionaryContent dictionaryContent,HttpServletResponse response){
		dictionaryContentService.updateDictionaryContent(dictionaryContent);
	}
	/**
	 * 根据dictionaryId获取数据字典内容
	 * @param response
	 * @param dictionaryId
	 * @throws IOException 
	 */
	@RequestMapping("/getDictionary")
	public void getDictionaryContentByDictionaryId(HttpServletResponse response,int dictionaryId) throws IOException{
		List<DictionaryContent> dictionaryContents=dictionaryContentService.getDictionaryContent(dictionaryId);
		JSONArray result=JSONArray.fromObject(dictionaryContents);
		ResponseUtil.write(response, result);
	}
	/**
	 * 删除一条数据字典
	 * @param dictionaryId
	 * @param response
	 */
	@RequestMapping("/deletedictionaryContent")
	public void deletedictionaryContent(String dictionaryId,HttpServletResponse response){
		String[] a=dictionaryId.split("_");
		dictionaryContentService.deleteDictionaryContent(a[1]);
	}
}
