package com.qzuyiban.pan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QzuyibanApplicationTests {

	@Autowired
    private JavaMailSender mailSender;
	
	@Test
	public void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("893620159@qq.com");
        message.setTo("2898627607@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
	
	}
	
	 @Test
	    public void sendSimpleMail() throws Exception {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("18807776517@163.com");
	        message.setTo("2898627607@qq.com");
	        message.setSubject("主题：简单邮件");
	        message.setText("测试邮件内容");

	        mailSender.send(message);
	    }

}
