<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>   
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在运行流程实例列表</title>
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
<script type="text/javascript">
	$(function(){
		$("#grid").datagrid({
			rownumbers : true,
			striped : true,
			singleSelect: true
		});
	});
	
	function show(obj){
		//弹出窗口
		window.showModalDialog("${pageContext.request.contextPath}/processinstance_showpng?processInstanceId="+obj);
		
	}
	
	
	
</script>	
</head>
<body class="easyui-layout">
   <div region="center">
   	  <table id="grid" class="easyui-datagrid"  data-options="nowrap:false">
   	  	<thead>
  			<tr>
  				<th data-options="field:'id'" width="120">实例编号</th>
  				<th data-options="field:'name'" width="200">流程定义编号</th>
  				<th data-options="field:'activity'" width="120">运行到哪个任务</th>
  				<th data-options="field:'viewpng'" width="200">查看实例运行信息</th>
  				<th data-options="field:'viewRuntime'" width="200">查看流程图</th>
  			</tr>
  		</thead>
  		<tbody>
  			<s:iterator  value="#processInstances"  var="processInstance">
  				<tr>
  					<td><s:property  value="id" /></td>
  					<td><s:property  value="processDefinitionId" /></td>
  					<td><s:property  value="#processInstance.findActiveActivityNames()" /></td>
  					<td>
  						<s:iterator value="variables" var="entry">
								<s:property value="key"/>=<s:property value="value"/><br/>
							</s:iterator>
  					</td>
  					<td>
  						<a href="#" class="easyui-linkbutton" onclick="show('${id}');">查看流程图</a>
  					</td>
  				</tr>
  			</s:iterator>
  		</tbody>
   	  </table>
   </div>
</body>
</html>