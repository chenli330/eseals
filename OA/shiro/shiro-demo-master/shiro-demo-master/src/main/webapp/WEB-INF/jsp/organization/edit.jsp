<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tag.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/organization/update" method="get" >
	<input type="hidden" value="${current_organization.oId }" name="oId"/>
	名称：<input type="text" name="name" value="${current_organization.name }"/><br/><br/>
	<input type="submit" value="确认修改">
</form>

</body>
</html>
