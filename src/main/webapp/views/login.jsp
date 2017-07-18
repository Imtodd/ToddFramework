<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<script type="text/javascript">
$('#cc').combobox({
	url: ...,
	required: true,
	valueField: 'id',
	textField: 'text'
});</script>
<body>
	<div class="easyui-dialog" style="width: 400px; height: 200px"
		data-options="title:'My Dialog',collapsible:true,iconCls:'icon-ok',onOpen:function(){}">
		dialog content.</div>
	<input id="cc" style="width: 200px" />
	<a href="${ pageContext.request.contextPath }/user/toRegist">注册</a>
	<div align="center">
		<x:form action="${ pageContext.request.contextPath }/user/Login"
			method="post" modelAttribute="userModel">
			用户名<x:input path="userName" />
			<br />
			密码<input type="password" name="password">
			<br />
			<input type="submit" value="登录">
		</x:form>
	</div>
</body>
</html>