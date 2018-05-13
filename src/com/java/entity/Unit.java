package com.java.entity;

public class Unit {
	private int uid;
	private String uname;
	private String uadress;
	private String uphone;
	private String uremark;
	private String ucity;
	private String upeople;//联系人
	private int unums;//实训人数
	private String tno;//固定指导老师
	private String ustatus;
	private Teacher teacher;
	
	
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getUcity() {
		return ucity;
	}
	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUadress() {
		return uadress;
	}
	public void setUadress(String uadress) {
		this.uadress = uadress;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUremark() {
		return uremark;
	}
	public void setUremark(String uremark) {
		this.uremark = uremark;
	}
	public String getUpeople() {
		return upeople;
	}
	public void setUpeople(String upeople) {
		this.upeople = upeople;
	}
	public int getUnums() {
		return unums;
	}
	public void setUnums(int unums) {
		this.unums = unums;
	}
    	
}
