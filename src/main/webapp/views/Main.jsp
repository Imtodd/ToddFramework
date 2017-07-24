<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/icon.css">
<title>首页</title>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
.rtitle {
	font-size: 18px;
	font-weight: bold;
	padding: 5px 10px;
	background: #336699;
	color: #fff;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#dg').datagrid({
			onSelect : function(index, row) {
				$('#cc').attr('src', row.link);
			},
			onLoadSuccess : function() {
				var rows = $(this).datagrid('getRows');
				if (rows.length) {
					$(this).datagrid('selectRow', 0);
				}
			}
		});
		$('#t-channels').tree({
			onSelect : function(node) {
				var url = node.attributes.url;
				$('#dg').datagrid('load', {
					url : url
				});
			},
			onLoadSuccess : function(node, data) {
				if (data.length) {
					var id = data[0].children[0].children[0].id;
					var n = $(this).tree('find', id);
					$(this).tree('select', n.target);
				}
			}
		});
	});
</script>
</script>
</head>
<body class="easyui-layout">
	<div border="false" class="rtitle" region="north">开始</div>
	<div region="west" title="导航" spilt="true" border="true"
		style="width: 200px; background: #EAFDFF;">
		<ul></ul>
	</div>
	<div region="center" border="false">
		<div class="easyui-layout" fit="true">
			<div region="north" split="true" border="false" style="height: 600px">
				<table id="dg" border="false" rownumbers="true" fit="true"
					fitColumns="true" singleSelect="true" pagination="true"
					url="${pageContext.request.contextPath }/user/students">
					<thead>
						<tr>
							<th field="name" width="50">姓名</th>
							<th field="age" width="50">年龄</th>
							<th field="student_id" width="200">学号</th>
							<th field="specialty" width="80">特长</th>
						</tr>
					</thead>
				</table>
			</div>
			<div region="center" border="false" style="overflow: hidden">
				<iframe id="cc" scrolling="auto" frameborder="0"
					style="width: 100%; height: 100%"></iframe>
			</div>
		</div>
	</div>
</body>
</html>