<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="x" uri="http://www.springframework.org/tags/form" %>
 <%@taglib prefix="z" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<div align="center">
<body>
	<z:if test="${!empty requestScope.msg }">
		${ requestScope.msg }
	</z:if>
	<x:form action="${ pageContext.request.contextPath }/user/regist" method="post" modelAttribute="userModel">
		用户名<x:input path="userName"/><br />
		密码<input type="password" name="password"><br />
		不禁用<x:radiobutton path="locked" value="false"/>禁用<x:radiobutton path="locked" value="true"/><br />
		<input type="submit" value="提交">
	</x:form>
</div>
</body>
</html>