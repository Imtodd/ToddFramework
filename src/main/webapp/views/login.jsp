<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="i18n.login.title"></fmt:message></title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/icon.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
	
</script>
<body style="height: 100%; width: 100%; overflow: hidden; border: none;">
	<button onclick="javascript:window.location.href='${ pageContext.request.contextPath }/user/toRegist';"><fmt:message key="i18n.regist.title"></fmt:message></button>
	<button onclick="javascript:$('#win').window('open')"><fmt:message key="i18n.login.title"></fmt:message></button>

	<div id="win" class="easyui-window" title="<fmt:message key="i18n.login.title"></fmt:message>"
		style="width: 300px; height: 180px;">
		<x:form action="${ pageContext.request.contextPath }/user/Login"
			id="loginFrom" style="padding: 10px 20px 10px 40px;" method="post"
			modelAttribute="userModel">
			<p>
				<fmt:message key="i18n.name"></fmt:message>:
				<x:input path="userName" />
			</p>
			<p>
				<fmt:message key="i18n.password"></fmt:message>: <input type="password" name="password">
			</p>
			<div style="padding: 5px; text-align: center;">
				<a href="#" class="easyui-linkbutton" icon="icon-ok"
					onclick="javascript:$('#loginFrom').submit();"><fmt:message key="i18n.login.title"></fmt:message></a> <a href="#"
					class="easyui-linkbutton" icon="icon-cancel">取消</a>
			</div>
		</x:form>
	</div>
</body>
</html>