<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 登录检查业务处理: 验证用户是否登录 --%>
<%-- 定义公共方法 --%>
<%!

// 获取cookie
public String getCookie(HttpServletRequest request,String name){
	String value = null;
	Cookie[] cookies = request.getCookies();
	if(null != cookies && cookies.length > 0){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(name)){
				value = cookie.getValue();
			}
		}
	}
return value;
}

%>

<%-- 执行登录检查 --%>
<%

// 如果用户已经成功登录一次并且记住密码了,再次访问则自动登录
// 从session中获取用户登录信息
Object acccountObj = session.getAttribute("account");
Object passwordObj = session.getAttribute("password");
String acccount = null;
String password = null;
System.out.println("从session对象中获取用户的登录信息: " + acccount + " " + password);

if(null != acccountObj && null != passwordObj){
	acccount = acccountObj.toString();
	password = passwordObj.toString();
}else{
	// 从cookie读取登录信息
	acccount = getCookie(request, "account");
	password = getCookie(request, "password");
	System.out.println("从cookie对象中获取用户的登录信息： " + acccount + " " + password);
}

// 进行登录
if((null != acccount && ""!=acccount) && (null != password && ""!=password)){
	//response.sendRedirect("home.jsp");
	System.out.println("已经登录成功一次");
}else{
	System.out.println("未登录");
	response.sendRedirect("../home/login.jsp");
}

%>