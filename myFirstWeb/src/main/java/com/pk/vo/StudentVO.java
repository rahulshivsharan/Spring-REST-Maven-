package com.pk.vo;

import java.io.Serializable;

public class StudentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 145L;
	
	private Integer studentId;
	private String studentName;
	
	public Integer getStudentId() {
		return studentId;
	}
	
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@Override
	public int hashCode(){
		int hash = 31;
		int hashFromId = this.studentId.hashCode();
		hash = (7 * hash) + hashFromId;
		return hash;	
	}
	
	@Override
	public boolean equals(Object object) {
		boolean flag = false;
		StudentVO vo = (StudentVO) object;
		
		if(vo != null && this.studentId.intValue() == vo.studentId.intValue()) {
			flag = true;
		}
		
		return flag;
	}
	
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("\nStudent-ID ").append(this.studentId).append(",\tStudent-Name ").append(this.studentName);
		return strb.toString();
	}
}
