package com.pk.dao;

import java.util.List;

import com.pk.vo.StudentVO;

public interface StudentDao {
	List<StudentVO> getAll() throws Exception;
	List<StudentVO> createStudent(StudentVO vo) throws Exception;
}
