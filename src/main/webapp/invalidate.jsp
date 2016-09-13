<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 清除用户Session
	session.invalidate(); 
	
	// 重定向 login.jsp
	response.sendRedirect(request.getContextPath()+"/login.jsp");
%>