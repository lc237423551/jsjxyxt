package com.java.entity;

public class Practiceappli {
	private int pa_id;
	private String sno;
	private String pa_internname;
	private String pa_internreason;
	private String pa_internplace;
	private String pa_collteaname;
	private String pa_collteaposition;
	private String pa_collteaphone;
	private String tno;
	private String ifgraduate;
	private int sub_id;
	private int uid;
	private Teacher teacher;
	private Student student;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public int getPa_id() {
		return pa_id;
	}
	public void setPa_id(int pa_id) {
		this.pa_id = pa_id;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getPa_internname() {
		return pa_internname;
	}
	public void setPa_internname(String pa_internname) {
		this.pa_internname = pa_internname;
	}
	public String getPa_internreason() {
		return pa_internreason;
	}
	public void setPa_internreason(String pa_internreason) {
		this.pa_internreason = pa_internreason;
	}

	public String getPa_internplace() {
		return pa_internplace;
	}
	public void setPa_internplace(String pa_internplace) {
		this.pa_internplace = pa_internplace;
	}
	public String getPa_collteaname() {
		return pa_collteaname;
	}
	public void setPa_collteaname(String pa_collteaname) {
		this.pa_collteaname = pa_collteaname;
	}
	public String getPa_collteaposition() {
		return pa_collteaposition;
	}
	public void setPa_collteaposition(String pa_collteaposition) {
		this.pa_collteaposition = pa_collteaposition;
	}
	public String getPa_collteaphone() {
		return pa_collteaphone;
	}
	public void setPa_collteaphone(String pa_collteaphone) {
		this.pa_collteaphone = pa_collteaphone;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public String getIfgraduate() {
		return ifgraduate;
	}
	public void setIfgraduate(String ifgraduate) {
		this.ifgraduate = ifgraduate;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
