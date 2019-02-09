package com.pk.authorisation;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.pk.utils.CodecUtil;

@Component("tokenManager")
public class DefaultTokenManager implements TokenManager{

	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();
	
	public String createToken(String username) {
		String token = CodecUtil.createUUID();
		String existingToken = this.isTokenExists(username);
		if(existingToken != null) {
			tokenMap.remove(existingToken);
		}
		tokenMap.put(token, username);
		return token;
	}

	
	public boolean checkToken(String token) {
		boolean isValid = false;
		isValid = (!StringUtils.isEmpty(token) && tokenMap.containsKey(token)) ? true : false; 
		return isValid;
	}

	public void deleteToken(String token) {
		tokenMap.remove(token);
	}
	
	public String isTokenExists(String username) {
		Set<Map.Entry<String, String>> entrySet = tokenMap.entrySet();
		String existingToken = null;
		
		for(Iterator<Map.Entry<String, String>> itr = entrySet.iterator();itr.hasNext();) {
			Map.Entry<String, String> entry = itr.next();
			
			if(entry.getValue().equals(username)) {
				existingToken = entry.getKey();
			}
		}
		
		return existingToken;
	}
}
