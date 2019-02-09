package com.pk.authorisation;

public interface TokenManager {
	String createToken(String username);
	
	public boolean checkToken(String token);
	
	public void deleteToken(String token);
	
	public String isTokenExists(String username);
}
