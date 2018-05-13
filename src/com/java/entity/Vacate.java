package com.java.entity;

import java.util.Date;

public class Vacate {
	private int v_id;
	private String sno;
	private Date v_strattime;
	private Date v_endtime;
	private Date v_leavetime;
	private String telphone;
	private String v_reason;
	private String v_stayplace;
	private int pa_id;
	private int v_status;
	private Date v_confirmtime;
	private Date v_throughtime;
	private Practiceappli practiceappli;
	private Student student;
	private Teacher teacher;
	private Unit unit;
	private Cla cla;
	private Speciality speciality;
	private String v_backreason;
	
	
	public String getV_backreason() {
		return v_backreason;
	}
	public void setV_backreason(String v_backreason) {
		this.v_backreason = v_backreason;
	}
	public Speciality getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	public Cla getCla() {
		return cla;
	}
	public void setCla(Cla cla) {
		this.cla = cla;
	}
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public Date getV_strattime() {
		return v_strattime;
	}
	public void setV_strattime(Date v_strattime) {
		this.v_strattime = v_strattime;
	}
	public Date getV_endtime() {
		return v_endtime;
	}
	public void setV_endtime(Date v_endtime) {
		this.v_endtime = v_endtime;
	}
	public Date getV_leavetime() {
		return v_leavetime;
	}
	public void setV_leavetime(Date v_leavetime) {
		this.v_leavetime = v_leavetime;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getV_reason() {
		return v_reason;
	}
	public void setV_reason(String v_reason) {
		this.v_reason = v_reason;
	}
	public String getV_stayplace() {
		return v_stayplace;
	}
	public void setV_stayplace(String v_stayplace) {
		this.v_stayplace = v_stayplace;
	}
	public int getPa_id() {
		return pa_id;
	}
	public void setPa_id(int pa_id) {
		this.pa_id = pa_id;
	}
	public int getV_status() {
		return v_status;
	}
	public void setV_status(int v_status) {
		this.v_status = v_status;
	}
	public Date getV_confirmtime() {
		return v_confirmtime;
	}
	public void setV_confirmtime(Date v_confirmtime) {
		this.v_confirmtime = v_confirmtime;
	}
	public Date getV_throughtime() {
		return v_throughtime;
	}
	public void setV_throughtime(Date v_throughtime) {
		this.v_throughtime = v_throughtime;
	}
	public Practiceappli getPracticeappli() {
		return practiceappli;
	}
	public void setPracticeappli(Practiceappli practiceappli) {
		this.practiceappli = practiceappli;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
