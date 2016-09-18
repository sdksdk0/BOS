<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	function doAdd(){
		// form清空
		$('#id').val('');
		$('#id').removeAttr('readonly');
		$('#name').val('');
		$('#telephone').val('');
		$('#station').val('');
		// pda 勾选
		$('#haspda').removeAttr("checked");
		// 标准 select 回显
		$('#standardId').combobox('setValue', '');  
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	// 作废操作 
	function doDelete(){
		// 先判断 用户是否选择
		$("#mark").val("1");
		var array = $('#grid').datagrid('getSelections'); 
		if(array.length == 0){
			$.messager.alert('警告','请先选择数据！','warning');
		}else{
			$('#delForm').submit();
		}
	}
	//还原
	function doRestore(){
		// 先判断 用户是否选择
		$("#mark").val("0");
		var array = $('#grid').datagrid('getSelections'); 
		if(array.length == 0){
			$.messager.alert('警告','请先选择数据！','warning');
		}else{
			$('#delForm').submit();
		}
		
	}
	//工具栏
	var toolbar = [ {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 200,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 200,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 200,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 200,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 200,
		align : 'center',
		formatter : function(data,row, index){
			return data.name;
		}
	}, {
		field : 'station',
		title : '所属单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [2,5,10],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staff_findByPage.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		// 页面加载后，发起Ajax请求 
		/*
		$.post("${pageContext.request.contextPath}/standard_ajaxlist.action",function(data){
			// 根据返回结果，生成下拉列表 
			// 传统js
			for(var i=0; i< data.length ; i++){
				// data[i].id 
			}
			// jquery
			$(data).each(function(){
				var option = $("<option value='"+this.id+"'>"+this.name+"</option>");
				$("#standardList").append(option);
			});
			
			// 应用combobox效果 
			$("#standardList").combobox({});
		});
		*/
		
		// 为保存按钮添加 点击事件
		$('#save').click(function(){
			// 进行form 校验
			if($('#staffForm').form('validate')){
				// 通过校验
				$('#staffForm').submit();
			}else{
				// 校验失败 
				$.messager.alert('警告','格式错误，请重新输入','warning');
			}
		});
	});

	function doDblClickRow(rowIndex, rowData){
		// form回显
		$('#id').val(rowData.id);
		$('#id').attr('readonly','readonly');
		$('#name').val(rowData.name);
		$('#telephone').val(rowData.telephone);
		$('#station').val(rowData.station);
		// pda 勾选
		if(rowData.haspda == "1"){
			$('#haspda').attr("checked","checked");
		}else{
			$('#haspda').removeAttr("checked");
		}
		// 标准 select 回显
		$('#standardId').combobox('setValue', rowData.standard.id);  
		
		// 弹出窗口
		$('#addStaffWindow').window('open');
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<form id="delForm" action="${pageContext.request.contextPath }/staff_deleteOrRestom" method="post">
		<input  type="hidden"  id="mark" name="mark" > 
		<div region="center" border="false">
	    	<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save"  class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffForm" action="${pageContext.request.contextPath }/staff_saveOrUpdate" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					
				 	<tr>
						<td><input type="hidden" id="id" name="id" /></td>
					</tr> 
					<tr>
						<td>姓名</td>
						<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" id="telephone" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" id="station" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" id="haspda" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
<%--							<select id="standardList">--%>
<%--							</select>--%>
							

							<input class="easyui-combobox"  id="standardId" name="standard.id"  
							data-options="url:'${pageContext.request.contextPath}/standard_ajaxlist',valueField:'id',textField:'name',required:true" />
						</td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	