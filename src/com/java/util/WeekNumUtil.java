package com.java.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
public class WeekNumUtil {
	
/**
 * 得到可修改的周总结数
 */
	public String getWeeknums(String weekdate){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = null;
    	Date nowDate=null;
    	String now=df.format(new Date());
    	try {
			startDate=df.parse(weekdate);
			nowDate=df.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String weeknum="";
    	int week=(int) ((nowDate.getTime()-startDate.getTime())/(7*24*60*60*1000))+1;
    	if(week<=18){
    		  		if(nowDate.getTime()<startDate.getTime()){//实训未开始
    		  			weeknum=weeknum+0+"&";
	    		  	}else if(week==1){
	    				weeknum=weeknum+week+"&";
	    			}else {
    					weeknum=(week-1)+"&"+weeknum+week+"&";
	    			}
    			}else{
    				weeknum=weeknum+18+"&";
    			}
    	      return weeknum;
    		}
    
  /**
   *   
   * @param weekdate
   * @return
   * 得到当前周数
   */
    public int getWeeknum(String weekdate){
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    	Date startDate = null;
    	Date nowDate=null;
    	int num=0;
    	String now=df.format(new Date());
    	try {
			startDate=df.parse(weekdate);
			nowDate=df.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  if(nowDate.getTime()<startDate.getTime()){//实训未开始
    		  num=0; 
    	  }else{
    		  num=(int) ((nowDate.getTime()-startDate.getTime())/(7*24*60*60*1000))+1;
    	  }
    	  return num;
    }
    /**
     * 得到该日期在实训期间的周数
     * @param weekdate
     * @param date
     * @return
     */
    public int get(String weekdate,String  date){
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    	Date weekDate = null;
    	Date date1=null;
    	int num=0;
    	try {
    		weekDate=df.parse(weekdate);
    		date1=df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  num=(int) ((date1.getTime()-weekDate.getTime())/(7*24*60*60*1000))+1;
	   	  if(num>18) num=18;
	   	  return num;
   }
}
