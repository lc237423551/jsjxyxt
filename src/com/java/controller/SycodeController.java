package com.java.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Sycode;
import com.java.entity.Unit;
import com.java.service.SycodeService;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/sycode")
public class SycodeController {	
	@Resource
	private SycodeService sycodeService;

    @RequestMapping("/getDept")
    public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Sycode> list=new ArrayList<Sycode>();
    	Sycode sycode=new Sycode();
    	sycode.setCodeno("jys");
    	list=sycodeService.findSycode(sycode);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/getPost")
    public String get1(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Sycode> list=new ArrayList<Sycode>();
    	Sycode sycode=new Sycode();
    	sycode.setCodeno("zhch");
    	list=sycodeService.findSycode(sycode);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    @RequestMapping("/getDegree")
    public String get2(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Sycode> list=new ArrayList<Sycode>();
    	Sycode sycode=new Sycode();
    	sycode.setCodeno("xw");
    	list=sycodeService.findSycode(sycode);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
//得到全部
    @RequestMapping("/get")
    public String getAll(HttpServletResponse response,HttpServletRequest request) throws IOException{
    	List<Sycode> list=new ArrayList<Sycode>();
    	list=sycodeService.findSycode(null);
    	JSONObject result=new JSONObject();
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
        return null;
    }
    //添加
    @RequestMapping("/add")
    public String add(HttpServletResponse response,HttpServletRequest request,Sycode sycode) throws IOException{
    	sycodeService.addSycode(sycode);
  	    List<Sycode> list=new ArrayList<Sycode>();
	  	list=sycodeService.findSycode(null);
	  	JSONObject result=new JSONObject();
	  	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		result.put("news", sycode.getCodecontent());
		ResponseUtil.write(response, result);
      return null;
    }
    //删除
    @RequestMapping("/delete")
    public String delete(HttpServletResponse response,HttpServletRequest request) throws IOException{
  		JSONObject result=new JSONObject();
  	  	String codeid=request.getParameter("codeid");
  	  	 
  			try{
  	    		sycodeService.deleteSycode(Integer.valueOf(codeid));
  				result.put("errmsg", "1");
  			}catch (Exception e) {
  				result.put("errmsg", "0");
  			}
 
  	  	
  		List<Sycode> list=new ArrayList<Sycode>();
  	  	list=sycodeService.findSycode(null);
  	  	 
  	  	JSONArray jsonArray=JSONArray.fromObject(list);
  		result.put("result", jsonArray);
  		ResponseUtil.write(response, result);
        return null;
    }
  //修改单位信息
    @RequestMapping("/update")
    public String update(HttpServletResponse response,HttpServletRequest request,Sycode sycode) throws IOException{
  	  JSONObject result=new JSONObject();
  	  try{
  		sycodeService.updateSycode(sycode);
  		  result.put("errmsg", "1");
  	  }catch (Exception e) {
  			result.put("errmsg", "0");
  	 }
  	   
  		result.put("result", null);
  		ResponseUtil.write(response, result);
        return null;
    }
}