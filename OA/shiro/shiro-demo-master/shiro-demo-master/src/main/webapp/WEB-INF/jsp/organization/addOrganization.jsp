<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>

<form action="${pageContext.request.contextPath}/organization/addOrganization" method="get" >
	<input type="hidden" value="${parentid }" name="parentId" />
	名称：<input type="text" name="name" /><br/><br/>
	<input type="submit" value="确认增加">
</form>


</body>
</html>
