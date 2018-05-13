package com.java.util;

import java.util.Calendar;

public class TimeUtil {

	public String getNow(){
		 //获取当前时间
	    int y,m,d,h,mi,s;      
	    Calendar cal=Calendar.getInstance();      
	    y=cal.get(Calendar.YEAR);      
	    m=cal.get(Calendar.MONTH)+1;      
	    d=cal.get(Calendar.DATE);      
	    h=cal.get(Calendar.HOUR_OF_DAY);      
	    mi=cal.get(Calendar.MINUTE);      
	    s=cal.get(Calendar.SECOND);      
	    String time=y+"-"+m+"-"+d+" "+h+":"+mi+":"+s;
	    return time;
	}
	
	public String getNow1(){
		 //获取当前时间
	    int y,m,d,h,mi,s;      
	    Calendar cal=Calendar.getInstance();      
	    y=cal.get(Calendar.YEAR);      
	    m=cal.get(Calendar.MONTH)+1;      
	    d=cal.get(Calendar.DATE);      
	    String time=y+"-"+m+"-"+d;
	    return time;
	}
}
