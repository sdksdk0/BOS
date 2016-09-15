<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
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
<!-- 导入一键上传 js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ocupload/jquery.ocupload-1.1.2.js"></script>
<script type="text/javascript">

	
	function doDelete(){
		var array = $('#grid').datagrid('getSelections'); 
		if(array.length == 0){
			$.messager.alert('警告','请先选择数据！','warning');
		}else{
			$('#delForm').submit();
		}
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-import',
		text : '批量导入',
		iconCls : 'icon-save'
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'province',
		title : '省',
		width : 200,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 200,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 200,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 200,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 200,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [2,5,10],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/region_findByPage",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改区域窗口
		$('#addRegionWindow').window({
	        title: '添加修改区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
	    
	    
	    $('#save').click(function(){
			// 进行form 校验
			if($('#regionForm').form('validate')){
				// 通过校验
				$('#regionForm').submit();
			}else{
				// 校验失败 
				$.messager.alert('警告','格式错误，请重新输入','warning');
			}
		});
	    
		
		// 对批量导入添加一键上传效果 
		$('#button-import').upload({
			name : 'upload', 
			action : '${pageContext.request.contextPath}/region_OCimport', // 表单提交路径
			onComplete : function(response){
				var data = eval("("+response+")");
				$.messager.alert('信息',data.msg,'info');
				$('#grid').datagrid('reload');
			}
		});
		
	});

	function doDblClickRow(rowIndex, rowData){
		// form回显
		$('#id').val(rowData.id);
		$('#id').attr('readonly','readonly');
		$('#province').val(rowData.province);
		$('#city').val(rowData.city);
		$('#district').val(rowData.district);
		$('#postcode').val(rowData.postcode);
		$('#shortcode').val(rowData.shortcode);
		$('#citycode').val(rowData.citycode);
		

		// 弹出窗口
		$('#addRegionWindow').window('open');
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<form id="delForm" action="${pageContext.request.contextPath }/region_delete" method="post">
		<div region="center" border="false">
	    	<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form  id="regionForm" action="${pageContext.request.contextPath }/region_saveOrUpdate" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
					</tr>
					<tr>
						<td><input type="hidden" id="id" name="id" /></td>
					</tr> 
					
					<tr>
						<td>省</td>
						<td><input type="text"  id="province"  name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input type="text"    id="city" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input type="text"   id="district" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input type="text"  id="postcode" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input type="text"  id="shortcode"  name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input type="text"  id="citycode"  name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>