package com.java.util;

public class StringUtil {
//判断是否为空
	public boolean isEmpty(String s){
		if(s == null || s.length() <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	//判断数组是否存在某字符串 
	public boolean isExist(String[] arr, String targetValue){
		for(String s: arr){
		    if(s.equals(targetValue))
		      return true;
		  }
		  return false;
	}
}
