package com.pk.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.pk.vo.UserVO;

public class UserExtractor implements ResultSetExtractor<UserVO>{

	public UserVO extractData(ResultSet rs) throws SQLException, DataAccessException {
		UserVO vo = new UserVO();
		vo.setUserId(rs.getInt("USERID"));
		vo.setUsername(rs.getString("USER_NAME"));
		return vo;
	}
	
}
