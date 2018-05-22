<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.concurrent.atomic.AtomicInteger"%>
    
<%-- application对象：该对象是全局对象,应用程序启动时创建,应用程序停止时销毁,是所有页面共享,一般用于存储系统全局配置，或用户资源 --%>
<%-- 首页访问计数器  --%>
<%
// 获取计数器对象
Object countObj = application.getAttribute("counts");
AtomicInteger count = null;

// 如果没有计数器则创建一个新的计数器对象
if(null == countObj){
	count = new AtomicInteger(0);
}else{
	// 如果已经有计数器对象则转换
	count = (AtomicInteger)countObj;
}

// 计数: +1
count.incrementAndGet();

// 保存计数器对象
application.setAttribute("counts", count);

%>
