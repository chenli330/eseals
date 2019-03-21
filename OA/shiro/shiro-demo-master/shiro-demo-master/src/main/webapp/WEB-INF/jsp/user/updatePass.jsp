<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>

<form onsubmit="return checkPass()" action="${pageContext.request.contextPath }/user/doUpdateUserPass" method="get">
	<input type=hidden value="${needUpdateUserId }" name="id" />
	新密码:<input type="password" id="new" required="required" name="password" /><br/>
	确认密码:<input type="password" id="reNew" required="required" /><br/>
	<input type="submit" value="确认修改" />
</form>

</body>
</html>
<script src="${pageContext.request.contextPath }/static/js/jquery-3.3.1.min.js"></script>
<script>
	function checkPass(){
		var thenew = $("#new").val();
		var renew = $("#reNew").val();
		if(thenew != renew){
			return false;
		}
		return true;
	}
</script>