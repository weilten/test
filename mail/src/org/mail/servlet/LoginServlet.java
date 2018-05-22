package org.mail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 设置请求编码
		request.setCharacterEncoding("utf8");

		// 获取请求参数
		// account: 是表单中name值
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		Object remember = request.getParameter("remember");

		// 验证请求参数非空
		if(null == account || null == password){
			// 返回登录页
			System.out.println("用户名或密码为空.");
			// 重定向到登录页,重新输入账号和密码
			response.sendRedirect("login.jsp");
		}

		// 进行登录
		if("admin".equals(account) && "123456".equals(password)){
			// 登录成功
			 System.out.println("登录成功");
			
		/*	// session对象： 表示会话,负责跟踪管理维护客户端会话信息(会话ID,会话状态,自定义会话数据)
			session.setAttribute("account", account);
			session.setAttribute("password", password);
			System.out.println("保存用户登录信息到session");
			
			// 如果是记住密码
			if(Boolean.valueOf(remember.toString())){
				// 创建账号cookie对象
				Cookie accountCookie = new Cookie("account",account);
				// 创建密码cookie对象
				String ecyPassword = response.encodeUrl(password);
				System.out.println("ecyPassword: " + ecyPassword);
				Cookie passwordCookie = new Cookie("password", ecyPassword);
				// 添加cookie(向客户端写cookie数据)
				response.addCookie(accountCookie);
				response.addCookie(passwordCookie);
				System.out.println("保存用户登录信息到cookie");
			}
			
			// 重定向到首页
			response.sendRedirect("home.jsp");
		}else{
			// 登录失败
			System.out.println("登录失败");
			// 重定向到登录页,重新输入账号和密码
			response.sendRedirect("login.jsp");
		}*/
	}
	
	}

}
