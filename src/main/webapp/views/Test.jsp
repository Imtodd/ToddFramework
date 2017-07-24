<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="jquery,ui,easy,easyui,web">
<meta name="description"
	content="easyui help you build your web page easily!">
<title>jQuery EasyUI 增删改查 实例</title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/demo/demo.css">
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '新建学生');
		$('#fm').form('clear');
		url = '${ pageContext.request.contextPath }/user/saveStu';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑学生');
			$('#fm').form('load', row);
			url = '${ pageContext.request.contextPath }/user/updateStu?id='+row.id;
		}
	}
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : '错误',
						msg : result.msg
					});
				}
			}
		});
	}
	function removeUser() {
		debugger;
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '你想要删除这个学生吗?', function(r) {
				if (r) {
					$.post('${ pageContext.request.contextPath }/user/deleteStu', {
						id : row.id
					}, function(result) {
						if (result.success) {
							$('#dg').datagrid('reload'); // reload the user data
						} else {
							$.messager.show({ // show error message
								title : '错误',
								msg : result.msg
							});
						}
					}, 'json');
				}
			});
		}
	}
</script>
</head>
<body>
	<div align="center">
		<table id="dg" title="学生管理列表" class="easyui-datagrid"
			style="width: 400px; height:400px"
			url="${pageContext.request.contextPath }/user/students"
			pagination="true"
			toolbar="#toolbar" rownumbers="true" fitColumns="true"
			singleSelect="true">
			<thead>
				<tr>
					<th field="name" width="50">姓名</th>
					<th field="age" width="50">年龄</th>
					<th field="student_id" width="50">学号</th>
					<th field="specialty" width="50">特长</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="false"
			onclick="newUser()">新建学生</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="false" onclick="editUser()">编辑学生</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove"
			plain="false" onclick="removeUser()">删除学生</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">学生信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>姓名:</label> <input name="name" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>年龄:</label> <input name="age" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>学号:</label> <input name="student_id">
			</div>
			<div class="fitem">
				<label>特长:</label> <input name="specialty"
					class="easyui-validatebox" validType="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>