<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSP Request对象使用-处理登录业务逻辑  --%>
<%-- Request: 表示请求对象,包括客户端请求头(请求属性设置)，请求体(参数),请求路径,请求方式 --%>

<%-- JSP Response对象使用-响应登录请求  --%>
<%-- Response: 表示响应对象,包括返回给客户端的响应头(响应头属性设置)，响应结果(返回的内容),设置cookie--%>

<%
// 设置请求编码
request.setCharacterEncoding("utf8");

// 获取请求参数
// account: 是表单中name值
String account = request.getParameter("account");
String password = request.getParameter("password");
Object remember = request.getParameter("remember");

//自定义request对象的属性
//request.setAttribute("message", "登录成功.");

//获取request自定义的属性
//String message = request.getAttribute("message").toString();
//System.out.println("message: " + message);

// 获取请求内容长度
//int contentLength = request.getContentLength();

// 获取cookie
//Cookie[] cookies = request.getCookies();

// 获取会话session
//request.getSession();

// 获取get方式请求的参数值
// http://localhost:8080/mail/home/do_login.jsp?abcddd
// http://localhost:8080/mail/home/do_login.jsp?{"a":"v"}
//String queryString = request.getQueryString();

// 获取客户端请求路径
// http://localhost:8080/mail/home/do_login.jsp?abcddd
// 获取到：home/do_login.jsp
//String requestURL = request.getRequestURI();
//System.out.println("requestURL: " + requestURL);

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
	
	// session对象： 表示会话,负责跟踪管理维护客户端会话信息(会话ID,会话状态,自定义会话数据)
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
}

%>
