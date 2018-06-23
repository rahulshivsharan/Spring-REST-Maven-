package com.pk.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.pk.vo.StudentVO;

public class StudentExtractor implements ResultSetExtractor<StudentVO>{

	
	public StudentVO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		StudentVO vo = new StudentVO();
		vo.setStudentId(resultSet.getInt("studentId"));
		vo.setStudentName(resultSet.getString("studentName"));
		return vo;
	}

}
