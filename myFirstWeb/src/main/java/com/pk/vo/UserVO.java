package com.pk.vo;

import java.io.Serializable;

public class UserVO implements Serializable{
	
	private static final long serialVersionUID = 213L;
	
	private Integer userId;
	private String username;
	
	public UserVO() {}
	
	public UserVO(int userId) {
		this.userId = userId;
	}
	
	public UserVO(Integer userId, String userName) {
		this.userId = userId;
		this.username = userName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public int hashCode() {
		int hash = 31;
		int hashFromId = this.userId.hashCode();
		hash = (7 * hash) + hashFromId;
		
		return hash;
	}
	
	
	@Override
	public boolean equals(Object o) {
		boolean flag = false;
		UserVO vo = (UserVO) o;
		
		if(vo != null && this.userId.intValue() == vo.getUserId().intValue()) {
			flag = true;
		}
		return flag;
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("User ID ").append(this.getUserId()).append(", ")
			.append(" User Name ").append(this.getUsername());
		
		return strb.toString();
	}
}
