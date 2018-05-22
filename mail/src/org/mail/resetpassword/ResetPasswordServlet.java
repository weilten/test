package org.mail.resetpassword;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mail.entity.Customer;

public class ResetPasswordServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf8");
		// 获得用户的 session 对象
		HttpSession session = req.getSession();
		//获取用户名
		String Uesr = (String) session.getAttribute("");
		
		String oldpassword = req.getParameter("oldpassword");
		String newpassword = req.getParameter("newpassword");
		String confirmpassword = req.getParameter("confirmpassword");
		PreparedStatement preparedStatement = null;
		String selectpassword="select UPASSWORD from users where UACCOUNT = User";
		Connection connection = null;
		ResultSet resultSet = null;
		String upSql = "update users set UPASSWORD = newpassword";
		
		if(null == oldpassword || null == newpassword || null == confirmpassword){
			// 新密码旧密码确认密码不能为空
			resp.getWriter().write("<script>alert('三项栏目都不能为空');</script>");
		}
		
		if(confirmpassword != newpassword){
			resp.getWriter().write("<script>alert('新密码和确认密码不一致');</script>");
		}
		
		// 创建预编译对象、设置值和执行sql语句
		try {
			preparedStatement = connection.prepareStatement(selectpassword);
			preparedStatement.setString(1, oldpassword);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				preparedStatement = connection.prepareStatement(upSql);
				preparedStatement.setString(1, newpassword);
				int modiry = preparedStatement.executeUpdate();
				String message = modiry > 0 ? "更改密码成功" : "更改密码失败";
				resp.getWriter().write("<script>alert(\""+message+"\");");
				resp.sendRedirect("person/index.jsp");
			   }
			else{
				resp.getWriter().write("<script>alert('旧密码错误');</script>");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		try {
			
			if(null != preparedStatement){
				preparedStatement.close();
			}
			if(null != connection){
				connection.close();
			if(null != resultSet){
				resultSet.close();
			}
		}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
}
	}
}
