package com.qzuyiban.pan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qzuyiban.pan.service.UserService;
import com.qzuyiban.pan.system.bean.MailBean;
import com.qzuyiban.pan.system.utils.MailUtil;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUser")
	public Map<String,Object> findUser(){
		Map<String,Object> resultUser=userService.find();
		return resultUser;
	}
	
	@RequestMapping("/testMail")
	public void testMail(){
		MailUtil mailUtil=new MailUtil();
		MailBean mailBean=new MailBean("2898627607@qq.com","testMail","testMail");
//		mailUtil.sendTemplateMail(mailBean);
//		mailUtil.sendHTMLMail(mailBean);
		mailUtil.sendSimpleMail(mailBean);
	}
	
	public static void main(String[] args) {
		UserController uc=new UserController();
		uc.testMail();
	}
}
