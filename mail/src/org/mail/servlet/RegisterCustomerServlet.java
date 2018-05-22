package org.mail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mail.entity.Customer;
import org.mail.jdbc.JdbcTemplate;

/**
 * 注册客户账号Servlet
 * @author VIC
 *
 */
public class RegisterCustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求编码
		request.setCharacterEncoding("utf8");

		// 获取请求参数
		// account: 是表单中name值
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		
		// 验证请求参数非空
		if(null == account || null == password){
			// 返回登录页
			System.out.println("用户名或密码为空.");
			// 重定向到登录页,重新输入账号和密码
			response.sendRedirect("login.jsp");
		}
		
		Customer customer = new Customer(account,password);
		// 注册
		// 验证账号是否存在,如果存在则返回提示，否则注册
		String searchSql = "select caccount from customer where caccount=?";
		boolean exists = JdbcTemplate.exists(searchSql,customer.getCaccount());
		
		int modify = 0;
		String message = "账号已存在.";
		if(!exists){
			// 执行注册
			String sql = "insert into customer values(auto_id.nextval,?,?)";
			modify = JdbcTemplate.execute(sql, customer.getCaccount(),customer.getCpassword());
			message = modify > 0 ? "注册成功!" :"注册失败";
		}
		//response.getWriter().write("<script>alert('"+message+"');</script>");
		request.getSession().setAttribute("message", message);
		response.sendRedirect("message.jsp");
	}
		
}
