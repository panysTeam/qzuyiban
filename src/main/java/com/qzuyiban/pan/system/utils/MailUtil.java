package com.qzuyiban.pan.system.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.qzuyiban.pan.system.bean.MailBean;

import freemarker.template.Configuration;
import freemarker.template.Template;
@Component
@PropertySource("application.properties")
public class MailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration;//freemarker
	
//	@Value("${pan.mail.sender}")
	private String MAIL_SENDER="18807776517@163.com";
	
	private Logger logger=LoggerFactory.getLogger(MailUtil.class);
	
	/**
	 * 发送简单的邮件
	 * @param mailBean
	 */
	public void sendSimpleMail(MailBean mailBean){
		try {
			SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
			simpleMailMessage.setFrom(MAIL_SENDER);//设置邮件发送人
			simpleMailMessage.setTo(mailBean.getRecipient());//设置接收人
			simpleMailMessage.setSubject(mailBean.getSubject());
			simpleMailMessage.setText(mailBean.getContent());//邮件内容			
			javaMailSender.send(simpleMailMessage);
		} catch (Exception e) {
			
			logger.error("邮件发送失败：",e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送HTML格式邮件
	 * @param mailBean
	 */
	public void sendHTMLMail(MailBean mailBean){
		javax.mail.internet.MimeMessage mimeMailMessage=null;
		
		try {
			mimeMailMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMailMessage,true);
			mimeMessageHelper.setFrom(MAIL_SENDER);			
			mimeMessageHelper.setTo(mailBean.getRecipient());
			mimeMailMessage.setSubject(mailBean.getSubject());
			
			StringBuilder sb=new StringBuilder();
			sb.append("<h1>SpringBoot测试邮件HTML格式");
			sb.append("\"<p style='color:#F00'>你是可以的</p>")
			.append("<p style='text-align:right'>右对齐</p>");
			mimeMessageHelper.setText(sb.toString(),true);
			javaMailSender.send(mimeMailMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败："+e.getMessage());
		}
	}
	
	/**
     * 发送带附件格式的邮件
     *
     * @param mailBean
     */
    public void sendAttachmentMail(MailBean mailBean) {
        javax.mail.internet.MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());
            mimeMessageHelper.setText(mailBean.getContent());
            //文件路径
            FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/mail.png"));
            mimeMessageHelper.addAttachment("mail.png", file);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }
    }
    
    
    /**
     * 发送基于Freemarker模板的邮件
     *
     * @param mailBean
     */
    public void sendTemplateMail(MailBean mailBean) {
        javax.mail.internet.MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mailBean.getRecipient());
            mimeMessageHelper.setSubject(mailBean.getSubject());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("content", mailBean.getContent());
            model.put("title", "标题Mail中使用了FreeMarker");
            Template template = configuration.getTemplate("mail.ftl");
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeMessageHelper.setText(text, true);

            javaMailSender.send(mimeMailMessage);
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
        }

    }
	
}
