package com.java.entity;

public class TrainInfo {
	private String sno;
	private int weeknum;
	private String state;//实训状态
	private int uid;//实训单位
	private String remark;//备注
	private Student student;
	private Unit unit;
	private ShixunInfo shixunInfo;
	//实训指导教师
	private String tno;
	private Teacher teacher;
	//实训题目
	private int subjectid;
	private Subjectname subjectname;
	private int nums;//完成的周总结数目
	
	
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public int getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	public Subjectname getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(Subjectname subjectname) {
		this.subjectname = subjectname;
	}
	public ShixunInfo getShixunInfo() {
		return shixunInfo;
	}
	public void setShixunInfo(ShixunInfo shixunInfo) {
		this.shixunInfo = shixunInfo;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public int getWeeknum() {
		return weeknum;
	}
	public void setWeeknum(int weeknum) {
		this.weeknum = weeknum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
