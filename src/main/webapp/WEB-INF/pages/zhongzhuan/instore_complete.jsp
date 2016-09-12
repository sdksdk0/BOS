<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
</head>
<body>
<s:form action="task_instorecomplete" theme="simple" method="post" id="inStoreForm">
	<table class="table-edit" width="100%" >
		<tr class="title"><td colspan="2">办理入库任务</td></tr>
		<tr>
			<td width="200">入库信息</td>
			<td>
				<s:hidden name="taskId" value="%{#parameters.taskId}"></s:hidden>
				<s:textarea name="info" rows="5" cols="80"></s:textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a id="btn" href="javascript:$('#inStoreForm').submit();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">办理入库任务</a>  
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>