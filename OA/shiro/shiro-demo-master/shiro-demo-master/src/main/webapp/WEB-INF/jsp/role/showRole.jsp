<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tag.jsp"%>
<html>
<head>

<link rel="stylesheet" type="text/css"	href="${pageContext.request.contextPath}/static/css/global.css">

</head>
<body>
<shiro:hasPermission name="role:create">
	<a href="${pageContext.request.contextPath}/role/toAddRole"><button>新增角色</button></a>
</shiro:hasPermission>
<table class="userTable" border="border" cellspacing="1" cellpadding="1">
<thead>
	<tr>
		<td>序号</td>
		<td>角色名称</td>
		<td>角色描述</td>
		<td>角色权限</td>
		<td>操作</td>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="roleWithResourceList" items="${roleWithResourceList }" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${roleWithResourceList.role }</td>
			<td>${roleWithResourceList.description }</td>
			<td>
				<select>
					<c:forEach var="resourceList" items="${roleWithResourceList.resourceList }">
						<option>${resourceList.name }</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<shiro:hasPermission name="role:update">
					<a href="${pageContext.request.contextPath}/role/toEditRole/${roleWithResourceList.rId }">修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="role:delete">
					<a href="${pageContext.request.contextPath}/role/deleteRole/${roleWithResourceList.rId }">删除</a>
				</shiro:hasPermission>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>
