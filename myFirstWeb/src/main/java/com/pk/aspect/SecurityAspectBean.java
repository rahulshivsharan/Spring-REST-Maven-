package com.pk.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.pk.annotation.IgnoreSecurity;
import com.pk.authorisation.TokenManager;
import com.pk.exception.TokenException;
import com.pk.utils.Constants;
import com.pk.utils.WebContextUtils;


@Aspect
@Component("securityAspectBean")
public class SecurityAspectBean {
	
	@Autowired
	@Qualifier("tokenManager")
	private TokenManager tokenManager;

	public TokenManager getTokenManager() {
		return tokenManager;
	}

	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}
	
	/**
	 * This method is a point-cut,
	 * it is invoked before all the methods which are annotated with @RequestMapping. This is mostly on controller.
	 * If the method is annotated with @IgnoreSecurity then do nothing,
	 * 
	 * If @IgnoreSecurity does not exist then check if an token is present in request header.
	 * If token exists then further check whether its a valid token or not.
	 * 
	 * If its a valid token than and only then proceed further.
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object excecute(ProceedingJoinPoint  pjp) throws Throwable{
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		
		if(method.isAnnotationPresent(IgnoreSecurity.class)) {
			return pjp.proceed();
		}
		
		HttpServletRequest request = WebContextUtils.getRequest(); 
		String token = request.getHeader(Constants.DEFAULT_TOKEN_NAME);
		
		if(!tokenManager.checkToken(token)) {
			String msg = String.format("Token %s is invalid", token);
			throw new TokenException(msg);
		}
		
		return pjp.proceed();
	}
}
