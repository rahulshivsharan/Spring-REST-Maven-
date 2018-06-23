package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pk.dao.StudentDao;
import com.pk.vo.StudentVO;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	@Qualifier("studentDao")
	private StudentDao dao;
	
	public StudentDao getDao() {
		return dao;
	}

	public void setDao(StudentDao dao) {
		this.dao = dao;
	}

	public List<StudentVO> getAll() throws Exception {
		List<StudentVO> studentList = null;		
		studentList = this.dao.getAll();
		return studentList;
	}
	
}
