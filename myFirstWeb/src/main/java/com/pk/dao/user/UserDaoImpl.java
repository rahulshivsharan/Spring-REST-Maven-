package com.pk.dao.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.pk.vo.UserVO;

@Component("userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	@Qualifier("datasource")
	private DataSource dataSource;
	
	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * Fetch All the users
	 */
	public List<UserVO> getUserList() throws Exception {
		String sql = "select userId, user_name from Rahul.TBL_USER_AUTH";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
		List<UserVO> userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList;
	}

	/**
	 * Add a new User
	 * Inert new record in user table
	 */
	public List<UserVO> addUser(final UserVO vo) throws Exception {
		Integer count = this.getMaxOfUserId();
		count = count.intValue() + 1;
		vo.setUserId(count.intValue());
		
		String sql = "insert into RAHUL.TBL_USER_AUTH(USERID,USER_NAME) values(?,?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo.getUserId());
				ps.setString(2, vo.getUsername());
			}
		};
		
		int insertedCount = jdbcTemplate.update(sql, pss);
		System.out.println("Total Number of Users inserted "+insertedCount);
		
		List<UserVO> userList = this.getUserList();
		
		return userList;
	}

	/**
	 * Edit a user
	 * run an update query on a particular user 
	 */
	public List<UserVO> editUser(final UserVO vo) throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "update Rahul.TBL_USER_AUTH set USER_NAME = ? where USERID = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, vo.getUsername());
				ps.setInt(2, vo.getUserId());
			}
		};
		
		int updatedCount = jdbcTemplate.update(sql, pss);
		System.out.println("Total Number of Users updated "+updatedCount);
		List<UserVO> userList = this.getUserList();
		return userList;
	}

	/**
	 * Delete a user on the basis of UserID
	 * run a delete query
	 */
	public List<UserVO> deleteUser(final int userId) throws Exception {
		String sql = "delete from Rahul.TBL_USER_AUTH where USERID = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, userId);								
			}
			
		};
		int deletedCount = jdbcTemplate.update(sql, pss);
		System.out.println("Total Number of Users deleted "+deletedCount);
		if(deletedCount == 0) {
			throw new Exception("User with ID "+userId+" not Found");
		}
		List<UserVO> userList = this.getUserList();
		return userList;		
	}
	
	/**
	 * Fetch max count of the USERID present in the USER table
	 * @return
	 * @throws Exception
	 */
	private Integer getMaxOfUserId() throws Exception{
		Integer count = -1;
		String sql = "select max(userid) as CNT  from Rahul.TBL_USER_AUTH";
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
				
		List<Integer> list = template.query(sql, new RowMapper<Integer>() {

			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						Integer cnt = rs.getInt("CNT");
						return cnt;
					}
					
				};
				Integer count = extractor.extractData(rs);
				return count;
			}
			
		});
		
		if(list != null && list.size() == 1) {
			count = list.get(0);
		}
		
		return count;
	}
	
	/**
	 * update Password
	 * run an update query on a particular user 
	 */
	public List<UserVO> updatePassword(final int userId,final String password) throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "update Rahul.TBL_USER_AUTH set PASSWORD = ? where USERID = ?";

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, password);
				ps.setInt(2, userId);
			}
		};
		
		int updatedCount = jdbcTemplate.update(sql, pss);
		System.out.println("Total Number of Users updated "+updatedCount);
		List<UserVO> userList = this.getUserList();
		return userList;
	} 

	public boolean login(final String username,final String password)  throws Exception{
		boolean flag = false;
		int count = 0;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select count(*) as CNT from Rahul.TBL_USER_AUTH where login_name = ? and password = ?";
		
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, username);
				ps.setString(2, password);
			}
		};
		
		RowMapper<Integer> rowMapper = new RowMapper<Integer>() {

			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				ResultSetExtractor<Integer> extractor = new ResultSetExtractor<Integer>() {

					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						Integer cnt = rs.getInt("CNT");
						return cnt;
					}
					
				};
				Integer count = extractor.extractData(rs);
				return count;
			}
			
		};
		
		List<Integer> list  = jdbcTemplate.query(sql, pss, rowMapper);
		
		if(list != null && list.size() == 1) {
			count = list.get(0);
		}
		
		if(count == 1) {
			flag = true;
		}
		
		return flag;
	}
}
