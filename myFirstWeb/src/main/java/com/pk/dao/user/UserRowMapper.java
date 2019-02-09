package com.pk.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pk.vo.UserVO;

public class UserRowMapper implements RowMapper<UserVO>{

	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserExtractor extractor = new UserExtractor();
		UserVO vo = extractor.extractData(rs);
		return vo;
	}
	
}
