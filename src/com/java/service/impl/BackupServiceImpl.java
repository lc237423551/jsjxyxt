package com.java.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/**
 * 数据库备份服务实现类
 * @author admin
 *
 */
@Service
public class BackupServiceImpl {
	static Logger logger=Logger.getLogger(BackupServiceImpl.class);
	
	
	//每隔执行一次
	@Scheduled(cron="0 0 7 * * ?")
	public void BackupDatabaseJob(){
		try {
			String result=backup("jsjxy","jsjxy","jsjxy");
			logger.info("数据库备份成功:时间"+new Date().toString()+";备份名称:"+result);
		} catch (Exception e) {
			logger.error("数据库备份失败:"+e.getMessage());
		}
		System.out.println("OK");
    }
	
	
	/**
	 * 备份数据库
	 * @param user 数据库用户名
	 * @param password 数据库密码
	 * @param database 要备份的数据库的名
	 * @return
	 * @throws IOException
	 */
	public static String backup(String user,String password,String database) throws IOException{
		  Date date = new Date();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		  String filepath = "d:\\mysqlBack"+sdf.format(date)+".sql";
		  File file = new File("d:\\",sdf.format(date)+".sql");
		  if(!file.exists()){
		   file.createNewFile();  
		  }
		  String stmt1 = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.1\\bin\\mysqldump " + database +" -h 127.0.0.1 "+ " -u " + user + " -p" +
		  password + " --default-character-set=utf8 --result-file=" + filepath;
		  try {
		    Runtime.getRuntime().exec(stmt1);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  return filepath;
	}
	
	
}
