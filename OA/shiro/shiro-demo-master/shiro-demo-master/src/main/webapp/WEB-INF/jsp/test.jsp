<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="tag.jsp"%>
<html>
<head>

</head>
<body>

<h1>这是测试页</h1>

<table border="1" cellspacing="1" cellpadding="1">
	<thead>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>age</td>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="testList" items="${testList }">
			<tr>
				<td>${testList.id }</td>
				<td>${testList.name }</td>
				<td>${testList.age }</td>
				<td>
					<a href="${pageContext.request.contextPath }/test/delForTest/${testList.id }">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>
