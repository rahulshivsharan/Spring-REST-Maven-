package com.pk.services;

import java.util.List;

import com.pk.vo.StudentVO;

public interface StudentService {
	List<StudentVO> getAll() throws Exception;
	List<StudentVO> createStudent(StudentVO vo) throws Exception;
}
