package com.pk.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pk.vo.StudentVO;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao{

	@Autowired	
	@Qualifier("datasource")
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public List<StudentVO> getAll() throws Exception{
		String sql = "Select id as studentId, name as studentName from Rahul.StudentTBL";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<StudentVO> studentList = jdbcTemplate.query(sql, new StudentRowMapper());
		return studentList;
	}
	
}
