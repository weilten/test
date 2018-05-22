<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 用户退出登录状态
// 清空用户用户登录信息
// session.setAttribute("account", null);
// session.setAttribute("password", null);

// 删除用户登录信息
session.removeAttribute("account");
session.removeAttribute("password");

// 清除cookie信息
response.addCookie(new Cookie("account",null));
response.addCookie(new Cookie("password",null));

// 注销session(session失效)
session.invalidate();

// 重定向到登录页
response.sendRedirect("login.jsp");

%>