package com.pk.dao.student;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pk.vo.StudentVO;

public class StudentRowMapper implements RowMapper<StudentVO>{

	public StudentVO mapRow(ResultSet resultSet, int line) throws SQLException {
		StudentExtractor studentExtractor = new StudentExtractor();
		StudentVO vo = studentExtractor.extractData(resultSet);
		return vo;
	}
	
}
