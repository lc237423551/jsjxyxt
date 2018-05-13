package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.entity.Cla;
import com.java.entity.Speciality;
import com.java.entity.Teacher;
import com.java.entity.User;
import com.java.service.ClaService;
import com.java.service.SpecialityService;
import com.java.service.TeacherService;
import com.java.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/speciality")
public class SpecialityController {
	@Resource
	private SpecialityService specialityService;
	@Resource
	private ClaService claService;
	@Resource
	private TeacherService teacherService;
	@RequestMapping("/get")
	public String get(HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Speciality> list=new ArrayList<Speciality>();
    	JSONObject result=new JSONObject();
    	Map<String,Object> map=new HashMap<String,Object>();
    	Speciality speciality=new Speciality();
    	list=specialityService.findSpeciality(speciality);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	@RequestMapping("/getwithAll")
	public String getwithAll(HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Speciality> list=new ArrayList<Speciality>();
    	JSONObject result=new JSONObject();
    	Speciality spec=new Speciality();
    	spec.setSpecid("");
    	spec.setSpecname("全部");
    	list=specialityService.findSpeciality(null);
    	list.add(0, spec);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
		return null;
	}
	
	@RequestMapping("/addSpeciality")
	public void addSpeciality(HttpServletResponse response,HttpServletRequest request) throws IOException{
		JSONObject result=new JSONObject();
		String specid=request.getParameter("specid");
		String specname=request.getParameter("specname");
		String tno=request.getParameter("tno");
		Speciality speciality=new Speciality();
		speciality.setSpecid(specid);
		speciality.setSpecname(specname);
		speciality.setTno(tno);
		try{
			specialityService.addSpeciality(speciality);
			result.put("errmsg", "1");
		}catch (Exception e) {
			result.put("errmsg", "0");
		}
		List<Speciality> list=new ArrayList<Speciality>();
		Speciality spe=new Speciality();
    	list=specialityService.findSpeciality(spe);
    	JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result", jsonArray);
		ResponseUtil.write(response, result);
	}
	@RequestMapping("/deleteSpeciality")
	public void deleteSpeciality(HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Speciality> list=new ArrayList<Speciality>();
		String specid=request.getParameter("specid");
		JSONObject result=new JSONObject();
		List<Cla> clalist=new ArrayList();
		Speciality speciality=new Speciality();
		speciality.setSpecid(specid);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("specid", specid);
		clalist=claService.findCla(map);
		Speciality spe=new Speciality();
		if(clalist.isEmpty()){
			specialityService.deleteSpeciality(speciality);
			list=specialityService.findSpeciality(spe);
			JSONArray jsonArray=JSONArray.fromObject(list);
			result.put("result",jsonArray );
		}else{
			result.put("result", "0");
		}
		ResponseUtil.write(response, result);
	}
	@RequestMapping("/updateSpeciality")
	public void updateSpeciality(HttpServletResponse response,HttpServletRequest request,Speciality speciality) throws IOException{
		List<Speciality> list=new ArrayList<Speciality>();
		JSONObject result=new JSONObject();
	     try{
	    	 specialityService.updateSpeciality(speciality);
	     }catch(Exception e){
	    	 result.put("errmsg", "0");
	     }
    	list=specialityService.findSpeciality(null);
		JSONArray jsonArray=JSONArray.fromObject(list);
		result.put("result",jsonArray );
    	ResponseUtil.write(response, result);
	}
	@RequestMapping("/getSpecialityByTno")
	public void getByTno(HttpServletResponse response,HttpServletRequest request) throws IOException{
		User user=(User) request.getSession().getAttribute("user");
		JSONObject result=new JSONObject();
		String tno=user.getUsers();
		Speciality spec=new Speciality();
    	spec.setSpecid("");
    	spec.setSpecname("全部");
    	
		Speciality spe=new Speciality();
		spe.setTno(tno);
		List<Speciality> spelist=specialityService.findSpeciality(spe);
		spelist.add(0, spec);
		JSONArray jsonArray=JSONArray.fromObject(spelist);
		result.put("result",jsonArray );
    	ResponseUtil.write(response, result);
	}
}
