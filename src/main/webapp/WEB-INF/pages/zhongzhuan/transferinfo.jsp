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
<body class="easyui-layout">
	<div region="center" style="overflow:auto;padding:5px;" border="false">
      	<form id="transferForm" method="post" action="${pageContext.request.contextPath }/task_saveTransferinfo">
           <table class="table-edit"  width="95%" align="center">
           		<tr class="title"><td colspan="2">中转信息办理</td></tr>
	           	<tr>
	           		<td>中转信息:</td>
	           		<td>
	           		<!-- 获得请求中taskId -->
	           		<s:hidden name="taskId" value="%{#parameters.taskId}"></s:hidden>
	           		<textarea name="info" rows="5" cols="60"></textarea></td>
	           	</tr>
	           	<tr>
	           		<td>是否到达:</td>
	           		<td><select name="arrive" class="easyui-combobox">
	           			<option value="0">未到达</option>
	           			<option value="1">到达</option>
	           		</select></td>
	           	</tr>
	           	<tr>
	           		<td colspan="2">
	           			<a href="javascript:$('#transferForm').submit();" class="easyui-linkbutton" iconCls="icon-save">办理</a>
	           		</td>
	           	</tr>
           </table>
       </form>
	</div>
</body>
</html>