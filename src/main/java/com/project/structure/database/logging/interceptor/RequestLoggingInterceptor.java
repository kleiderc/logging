package com.project.structure.database.logging.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		request.setAttribute("startTime", System.currentTimeMillis());
		
		logger.info("INTERCEPTOR - Request: {} {}", request.getMethod(), request.getRequestURI());
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		long startTime = (Long) request.getAttribute("startTime");
		
		logger.info("INTERCEPTOR - Completed: {} {} - Status: {} - Duration: {} ms", request.getMethod(),
				request.getRequestURI(), response.getStatus(), System.currentTimeMillis() - startTime);
	}

}
