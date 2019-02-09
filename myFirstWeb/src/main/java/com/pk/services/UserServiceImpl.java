package com.pk.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pk.dao.user.UserDao;
import com.pk.vo.UserVO;

@Component("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("userDao")
	private UserDao dao;
	
	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public List<UserVO> getUserList() throws Exception {
		List<UserVO> list = dao.getUserList();
		return list;
	}

	public List<UserVO> addUser(UserVO vo) throws Exception {
		List<UserVO> list = dao.addUser(vo);
		return list;
	}

	public List<UserVO> editUser(UserVO vo) throws Exception {
		List<UserVO> list = dao.editUser(vo);
		return list;
	}

	public List<UserVO> deleteUser(int userId) throws Exception {
		List<UserVO> list = dao.deleteUser(userId);
		return list;
	}

	
	public List<UserVO> updatePassword(int userId, String password) throws Exception {
		List<UserVO> list = dao.updatePassword(userId, password);
		return list;
	}
	
	public boolean login(String username, String password) throws Exception {
		boolean flag = false;
		flag = dao.login(username, password);
		return flag;
	}
}
