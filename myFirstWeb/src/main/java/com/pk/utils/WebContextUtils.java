package com.pk.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebContextUtils {
	private WebContextUtils() {}
	
	public static HttpServletRequest getRequest() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletReqAttributes = (ServletRequestAttributes) attributes;
		HttpServletRequest request = servletReqAttributes.getRequest();
		return request;
	}
}
