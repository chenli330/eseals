<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../tag.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>
	<p>
		当前节点：<span style="color:blue" id="nodeName">${current_organization.name }</span>
	</p>
	<P>
		<a href="${pageContext.request.contextPath}/organization/toOrganizationEdit/${current_organization.oId }"><button type="button">修改</button></a>
		<c:if test="${current_organization.oId!=1 }">
			<a href="${pageContext.request.contextPath}/organization/doDelOrganization/${current_organization.oId }"><button type="button">删除</button></a>
			<a href="${pageContext.request.contextPath}/organization/toMoveOrganization/${current_organization.oId }"><button type="button">移动</button></a>
		</c:if>
		<a href="${pageContext.request.contextPath}/organization/toAddOrganization/${current_organization.oId }"><button type="button">增加子部门</button></a>
	</P>

</body>
</html>
