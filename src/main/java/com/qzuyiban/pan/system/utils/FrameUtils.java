package com.qzuyiban.pan.system.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.ClassUtils;

import cn.yiban.open.FrameUtil;

public class FrameUtils {

	public void login(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String appid = "0248abdbefd44e6a";		// ** AppID
		String secrt = "baf3e3b411856c6d37b03336a7ab106a";		// ** AppSecret
		String cburl = "http://f.yiban.cn/xiaopanyihao";	// ** 注意，这里的回调地址是【易班生成的站内应用地址】

		try {
			PrintWriter out=response.getWriter();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		try
		{
			FrameUtil util = new FrameUtil(request, response, appid, secrt, cburl);
			String token = (String) session.getAttribute("token");
			if (token == null)
			{
				if (util.perform() == false)
				{
					return;
				}
				String userid   = util.getUserId();
				String username = util.getUserName();
				String acctoken = util.getAccessToken();
				
				session.setAttribute("userid",   userid);
				session.setAttribute("username", username);
				session.setAttribute("token",    acctoken);
				
				
				response.sendRedirect("http://127.0.0.1:8080/qzuyiban/hello");
				return;
			}
			else
			{
				response.sendRedirect("http://f.yiban.cn/xiaopanyihao");
				return;
			}
//			String source = util.adaptive();
//			System.out.println(source);
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			try {
				response.sendRedirect("http://f.yiban.cn/xiaopanyihao");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
