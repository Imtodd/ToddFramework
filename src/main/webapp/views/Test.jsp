<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	var url;
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '新建用户');
		$('#fm').form('clear');
		url = '';
	}
	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', 'Edit User');
			$('#fm').form('load', row);
			url = '';
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
						title : 'Error',
						msg : result.msg
					});
				}
			}
		});
	}
	function removeUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm',
					'你想要删除这个用户吗?', function(r) {
						if (r) {
							$.post('', {
								id : row.id
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload'); // reload the user data
								} else {
									$.messager.show({ // show error message
										title : 'Error',
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
	<table id="dg" title="我的用户列表" class="easyui-datagrid"
		style="width: 700px; height: 250px" url=""
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="userName" width="50">用户名</th>
				<th field="password" width="50">密码</th>
				<th field="locked" width="50">是否锁定</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="false"
			onclick="newUser()">新建用户</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="false"
			onclick="editUser()">编辑用户</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="false"
			onclick="removeUser()">删除用户</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>姓氏:</label> <input name="firstname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>名字:</label> <input name="lastname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>电话:</label> <input name="phone">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input name="email" class="easyui-validatebox"
					validType="email">
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