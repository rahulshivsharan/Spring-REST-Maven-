package com.pk.dao.user;

import java.util.List;

import com.pk.vo.UserVO;

public interface UserDao {
List<UserVO> getUserList() throws Exception;
	
	List<UserVO> addUser(UserVO vo) throws Exception;
	
	List<UserVO> editUser(UserVO vo) throws Exception;
	
	List<UserVO> deleteUser(int userId) throws Exception;
	
	List<UserVO> updatePassword(int userId, String password) throws Exception;
	
	boolean login(String username, String password) throws Exception;
}
