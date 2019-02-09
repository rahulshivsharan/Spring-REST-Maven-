package com.pk.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk.services.UserService;
import com.pk.vo.UserVO;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	public UserController() {
		System.out.println("UserController initialised....");
	}
	
	@Autowired
	@Qualifier("userService")
	private UserService service;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(value="",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<List<UserVO>> getUserList() {
		List<UserVO> list = null;
		ResponseEntity<List<UserVO>> entity = null;
		try {
			list = this.service.getUserList();
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,produces="application/json")
	public ResponseEntity<List<UserVO>> createUser(@RequestBody UserVO vo){
		ResponseEntity<List<UserVO>> entity = null;
		List<UserVO> list = null;		
		try {			
			list = this.service.addUser(vo);
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<List<UserVO>> editUser(@PathVariable("id") int userId,@RequestBody UserVO vo){
		ResponseEntity<List<UserVO>> entity = null;
		List<UserVO> list = null;		
		try {			
			vo.setUserId(userId);
			list = this.service.editUser(vo);
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.OK);
		}catch(Exception e) {	
			list = new ArrayList<UserVO>();
			list.add(new UserVO(-1,e.getMessage()));
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE,produces="application/json")
	public ResponseEntity<List<UserVO>> deleteUser(@PathVariable("id") int userId){
		ResponseEntity<List<UserVO>> entity = null;
		List<UserVO> list = null;
		try {			
			list = this.service.deleteUser(userId);
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.OK);
		}catch(Exception e) {	
			list = new ArrayList<UserVO>();
			list.add(new UserVO(-1,e.getMessage()));
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.PUT,produces="application/json")
	public ResponseEntity<List<UserVO>> updatePassword(@RequestParam("userId") String id,@RequestParam("password") String password){
		ResponseEntity<List<UserVO>> entity = null;
		List<UserVO> list = null;		
		int userId = -1;
		try {			
			userId = Integer.parseInt(id);
			list = this.service.updatePassword(userId, password);
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.OK);
		}catch(Exception e) {	
			list = new ArrayList<UserVO>();
			list.add(new UserVO(-1,e.getMessage()));
			entity = new ResponseEntity<List<UserVO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
}
