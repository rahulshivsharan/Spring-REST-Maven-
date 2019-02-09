package com.pk.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pk.annotation.IgnoreSecurity;
import com.pk.authorisation.TokenManager;
import com.pk.services.UserService;
import com.pk.utils.Constants;
import com.pk.vo.response.Response;

/**
 * This controller is responsible for authenticating user
 * 
 * @author Rahul Shivsharan
 *
 */
@RestController
@RequestMapping(value="/token")
public class TokenController {
	
	public TokenController() {
		System.out.println("TockenController initialised...");
	}
	
	@Autowired
	@Qualifier("userService")
	private UserService service;
	
	@Autowired
	@Qualifier("tokenManager")
	private TokenManager tokenManager;

	public TokenManager getTokenManager() {
		return tokenManager;
	}

	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
	
	/**
	 * The method below authenticates user with the password send to him.
	 * Once user is authenticated, a new UUID token is generated which is saved locally and the same is
	 * send in response cookie.
	 * 
	 * If user is not authorized and if any existing token for that username exists, then its deleted. 
	 * 
	 * @param username
	 * @param password
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded")
	@IgnoreSecurity
	public Response login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse httpResponse) {
		Response response = null;
		boolean flag = false;
		String token = null;
		Cookie cookie = null;
		try {
			
			/**
			 * Check if the user is authorised
			 */
			flag = this.service.login(username, password);
			
			
			if(flag == true) {
				/**
				 * if user is authorized than generate a new token
				 * set it in cookie and send it in response.
				 * At the same time save the newly generated token locally for
				 * further verification
				 */
				token = tokenManager.createToken(username);
				cookie = new Cookie(Constants.DEFAULT_TOKEN_NAME, token);
				httpResponse.addCookie(cookie);
				
				response = new Response();
				response.success("Login Success");
			}else {
				
				/**
				 * is user is not authorized then
				 * check if any previous token exists for the username,
				 * if yes, then remove that old token.
				 * 
				 * And send a dummy token in the response cookie.
				 */
				token = tokenManager.isTokenExists(username);
				if(token != null) {
					tokenManager.deleteToken(token);
				}
				cookie = new Cookie(Constants.DEFAULT_TOKEN_NAME, "202323113232");
				httpResponse.addCookie(cookie);
				
				response = new Response();
				response.failure("Login Failure");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return response;
	}
}
