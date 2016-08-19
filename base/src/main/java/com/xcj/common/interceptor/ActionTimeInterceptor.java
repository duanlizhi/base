package com.xcj.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xcj.common.redis.RedisUtil;

public class ActionTimeInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger
			.getLogger(ActionTimeInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		modelAndView.addObject("executeTime", executeTime);
		//String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
		//RedisUtil redisUtil = new RedisUtil();
		//redisUtil.shardedJedis.set(redirectUrl+System.currentTimeMillis(), String.valueOf(executeTime));
	}

}