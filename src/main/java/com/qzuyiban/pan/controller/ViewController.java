package com.qzuyiban.pan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.qzuyiban.pan.system.utils.FrameUtils;



@Controller
public class ViewController {

	@RequestMapping("/login.html")
	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session) { 
		FrameUtils fu=new FrameUtils();
		fu.login(request, response, session);
	}
	
	@RequestMapping("/fail")
	public String fail(){
		return "fail";
	}
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
		
}
