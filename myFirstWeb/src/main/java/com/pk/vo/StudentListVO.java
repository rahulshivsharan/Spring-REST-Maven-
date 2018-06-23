package com.pk.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentListVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1676L;

	public StudentListVO() {
		this.students = new ArrayList<StudentVO>();
	}
	
	private List<StudentVO> students;

	public List<StudentVO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentVO> students) {
		this.students = students;
	}
}
