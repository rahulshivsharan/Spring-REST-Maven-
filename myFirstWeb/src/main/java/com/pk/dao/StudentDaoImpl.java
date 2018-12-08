package com.pk.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
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
	
	public List<StudentVO> createStudent(final StudentVO vo) throws Exception {
		String sql = "insert into Rahul.StudentTBL(ID,NAME) values(?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo.getStudentId());
				ps.setString(2, vo.getStudentName());
			}
			
		}; 
		int insertCount = jdbcTemplate.update(sql, pss);
		System.out.println(" TOTAL NUM OF RECORDS INSERTED "+insertCount);
		List<StudentVO> studentList = this.getAll();		
		return studentList;
	}

	public List<StudentVO> editStudent(final StudentVO vo) throws Exception {
		String sql = "update Rahul.StudentTBL set NAME = ? where ID = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, vo.getStudentName());
				ps.setInt(2, vo.getStudentId());				
			}
			
		};
		int insertCount = jdbcTemplate.update(sql, pss);
		List<StudentVO> studentList = this.getAll();
		return studentList;
	}
	
}
