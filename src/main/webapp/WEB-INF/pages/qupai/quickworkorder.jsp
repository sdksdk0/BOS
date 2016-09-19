<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作单快速录入</title>
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
	var editIndex ;
	
	function doAdd(){
		if(editIndex != undefined){
			$("#grid").datagrid('endEdit',editIndex);
		}
		if(editIndex==undefined){
			
			$("#grid").datagrid('insertRow',{
				index : 0,
				row : {}
			});
			$("#grid").datagrid('beginEdit',0);
			editIndex = 0;
		}
	}
	
	function doSave(){
		$("#grid").datagrid('endEdit',editIndex );
	}
	
	function doCancel(){
		if(editIndex!=undefined){
			$("#grid").datagrid('cancelEdit',editIndex);
			$("#grid").datagrid('deleteRow',editIndex);
			editIndex = undefined;
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-add',	
		text : '新增一行',
		iconCls : 'icon-edit',
		handler : doAdd
	}, {
		id : 'button-cancel',
		text : '取消编辑',
		iconCls : 'icon-cancel',
		handler : doCancel
	}, {
		id : 'button-save',
		text : '保存',
		iconCls : 'icon-save',
		handler : doSave
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		title : '工作单号',
		width : 200,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'arrivecity',
		title : '到达地',
		width : 200,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	},{
		field : 'product',
		title : '产品',
		width : 200,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'num',
		title : '件数',
		width : 200,
		align : 'center',
		editor :{
			type : 'numberbox',
			options : {
				required: true
			}
		}
	}, {
		field : 'weight',
		title : '重量',
		width : 200,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}, {
		field : 'floadreqr',
		title : '配载要求',
		width : 220,
		align : 'center',
		editor :{
			type : 'validatebox',
			options : {
				required: true
			}
		}
	}] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : true,
			rownumbers : true,
			striped : true,
			pageList: [2,5,10],
			pagination : true,
			toolbar : toolbar,
			url :  "${pageContext.request.contextPath}/workordermanage_findByPage",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow,
			onAfterEdit : function(rowIndex, rowData, changes){
				editIndex = undefined;
				//提交一个ajax请求，将编辑的行数据发送到服务器执行保存操作
				$.post("${pageContext.request.contextPath}/workordermanage_saveOrUpdate",rowData,function(data){
					 //如果是success就是成功
					 if(data.result == "success"){
						$('#grid').datagrid('reload');
					}else{
						$.messager.alert('保存失败',data.msg, 'error');
					}
				});
			}
		});
	});

	function doDblClickRow(rowIndex, rowData){
		$('#grid').datagrid('beginEdit',rowIndex);
		editIndex = rowIndex;
	}
	
	function doSearch(value,name){
		//将查询条件缓存到datagrid
		$('#grid').datagrid('load',{
			conditionName:name,
			conditionValue:value
		});
	}
	
	
	
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div data-options="region:'north'">
		<!-- 编写搜索框 -->
		<!--
			 prompt 默认提示内容
			 menu 搜索条件下拉选项 
			 searcher 点击搜索按钮执行js函数名称
		 -->
		<input id="ss" class="easyui-searchbox" style="width:300px" 
			data-options="prompt:'请输入您的查询内容',menu:'#nm',searcher:doSearch"/>
			
		<div id="nm">
			<div data-options="name:'arrivecity'">按照到达地搜索</div>
			<div data-options="name:'product'">按照货物名称搜索</div>
		</div>
	</div>
	
	
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
</body>
</html>