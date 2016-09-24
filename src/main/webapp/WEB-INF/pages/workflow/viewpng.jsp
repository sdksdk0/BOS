<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="${pageContext.request.contextPath }/processdefinition_viewpng.action
			?deploymentId=${deploymentId}&imageResourceName=${imageResourceName}" style="position: absolute;top: 0px; left: 0px;" />
	<s:iterator value="list" var="activityCoordinates">
		<div style="position: absolute;
			width: ${width}px;
			height: ${height}px; 
			top: ${y}px; 
			left: ${x}px; 
			border-color: red; border-style: solid;border-width: 1px;"></div>
	</s:iterator>
</body>
</html>