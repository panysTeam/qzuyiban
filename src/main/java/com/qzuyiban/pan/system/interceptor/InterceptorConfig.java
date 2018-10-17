package com.qzuyiban.pan.system.interceptor;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



@Configuration
public class InterceptorConfig implements HandlerInterceptor {

	private static final Logger log=LoggerFactory.getLogger(InterceptorConfig.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("---------------------开始进入请求地址拦截--------------------");
		
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("token"));
		if(StringUtils.isNotEmpty((String) session.getAttribute("token"))){			
			return true;
		}else{
			PrintWriter printWriter=response.getWriter();
			printWriter.write("session is invalid,please login!");
			response.sendRedirect("http://f.yiban.cn/xiaopanyihao");
//			request.getRequestDispatcher("http://f.yiban.cn/xiaopanyihao").forward(request,response);
			return false;
		}	
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("--------------------处理请求完成后视图渲染之前的处理操作----------------");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("-----------------视图渲染之后的操作----------------------");
	}
}
