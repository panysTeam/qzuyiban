package com.qzuyiban.pan.system.bean;

import java.io.Serializable;

import lombok.Data;

//@Data
public class MailBean implements Serializable{

	private static final long serialVersionUID = -5811253757187834172L;
	private String recipient;  //邮件接收人
	private String subject;    //邮件主题
	private String content;	//邮件内容
	
	public MailBean() {
		super();
		
	}
	public MailBean(String recipient, String subject, String content) {
		super();
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
