<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="x" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="z" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="i18n.regist.title"></fmt:message></title>
</head>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/themes/icon.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/UI/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<body style="height: 100%; width: 100%; overflow: hidden; border: none;">
	<button onclick="javascript:$('#win').window('open')">
		<fmt:message key="i18n.regist.title"></fmt:message>
	</button>
	<button
		onclick="javascript:window.location.href='${ pageContext.request.contextPath }/user/toLogin';">
		<fmt:message key="i18n.login.title"></fmt:message>
	</button>
	<div id="win" class="easyui-window"
		title="<fmt:message key="i18n.regist.title"></fmt:message>"
		style="width: 300px; height: 250px;">
		<x:form id="regist"
			action="${ pageContext.request.contextPath }/user/regist"
			method="post" modelAttribute="userModel"
			style="padding: 10px 20px 10px 40px;">
			<p>
				<fmt:message key="i18n.name"></fmt:message>
				:
				<x:input path="userName" />
			</p>
			<p>
				<fmt:message key="i18n.password"></fmt:message>
				: <input type="password" name="password">
			</p>
			<p>
				不禁用
				<x:radiobutton path="locked" value="false" />
				禁用
				<x:radiobutton path="locked" value="true" />
			</p>
			<p>
				<a href="#" class="easyui-linkbutton" icon="icon-ok"
					onclick="javascript:$('#regist').submit();"><fmt:message key="i18n.regist.title"></fmt:message></a> <a href="#"
					class="easyui-linkbutton" icon="icon-cancel">取消</a>
			</p>
		</x:form>
	</div>
</body>
</html>