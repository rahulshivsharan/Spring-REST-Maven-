package com.pk.dao.student;

import java.util.List;

import com.pk.vo.StudentVO;

public interface StudentDao {
	List<StudentVO> getAll() throws Exception;
	List<StudentVO> createStudent(StudentVO vo) throws Exception;
	List<StudentVO> editStudent(StudentVO vo) throws Exception;
	List<StudentVO> deleteStudent(int studentId) throws Exception;
}
