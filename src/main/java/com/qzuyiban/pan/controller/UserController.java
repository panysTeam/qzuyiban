package com.qzuyiban.pan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzuyiban.pan.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUser")
	public Map<String,Object> findUser(){
		Map<String,Object> resultUser=userService.find();
		return resultUser;
	}
	
}
