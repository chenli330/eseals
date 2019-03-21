<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tag.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/global.css">
</head>
<body>
<shiro:hasPermission name="user:create">
	<a href="${pageContext.request.contextPath}/user/toAddUser"><button>新增用户</button></a>
</shiro:hasPermission>
<table class="userTable" border="1">
	<thead>
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>组织</td>
			<td>角色</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="userList" varStatus="status" items="${userList }">
			<tr>
				<td>${status.index+1 }</td>
				<td>${userList.username }</td>
				<td>${userList.organization.name }</td>
				<td>${userList.role.description }</td>
				<td>
					<shiro:hasPermission name="user:update">
						<a href="${pageContext.request.contextPath }/user/toEdit/${userList.id }">修改</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="user:delete">
						<a href="${pageContext.request.contextPath }/user/deleteUser/${userList.id }">删除</a>
					</shiro:hasPermission><shiro:hasPermission name="user:update">
						<a href="${pageContext.request.contextPath }/user/toUpdateUserPass/${userList.id }">改密</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>
