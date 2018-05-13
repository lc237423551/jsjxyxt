package com.java.entity;

public class ShixunInfo {
	private String sno;
	private String ifgraduate;//是否考研
	private String sxpdfState;//实训报告提交状态
	private String sxpdfTime;//实训报告最后提交时间
	private String dealtime;//请假通过时间
	private int nums;//完成的周总结数目
	private String codeState;//源代码提交状态
	
	public String getCodeState() {
		return codeState;
	}
	public void setCodeState(String codeState) {
		this.codeState = codeState;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	private Student student;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getIfgraduate() {
		return ifgraduate;
	}
	public void setIfgraduate(String ifgraduate) {
		this.ifgraduate = ifgraduate;
	}
	public String getSxpdfState() {
		return sxpdfState;
	}
	public void setSxpdfState(String sxpdfState) {
		this.sxpdfState = sxpdfState;
	}
	public String getSxpdfTime() {
		return sxpdfTime;
	}
	public void setSxpdfTime(String sxpdfTime) {
		this.sxpdfTime = sxpdfTime;
	}
	public String getDealtime() {
		return dealtime;
	}
	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}


	
}
