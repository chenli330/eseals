<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ include file="tag.jsp"%>
<html>
<head>
<title>登录</title>
<style>
.error {
	color: red;
}
</style>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/jquery.easyui.min.js"></script>
</head>
<body style="width:99%;height: 97%;">
	<div id="w" class="easyui-window" title="请先登录"
		data-options="modal:true,closed:false,iconCls:'Lockgo',closable:false,minimizable:false"
		style="width: 400px; padding: 20px 70px 20px 70px;">
<form method="post" action="" >
		<div style="margin-bottom: 10px">
			<input class="easyui-textbox" id="logname" name="username"
				style="width: 100%; height: 30px; padding: 12px"
				data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" id="logpass" type="password" name="password"
				style="width: 100%; height: 30px; padding: 12px"
				data-options="prompt:'登录密码',iconCls:'icon-lock',iconWidth:38">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" type="text" id="logyzm"
				style="width: 49%; height: 30px; padding: 12px" name="securityCode"
				data-options="prompt:'验证码'"/>
			<img style="margin: 0 0 0 3px; vertical-align: middle; height: 26px;"
				src="${pageContext.request.contextPath }/getSecurityCode" title="换一张"
				onclick="this.src='${pageContext.request.contextPath }/getSecurityCode?'+Math.random()"
				/>
		</div>
		<div style="margin-bottom: 20px">
			 记住我：<input type="checkbox" name="rememberMe" value="true">
		</div>
		<div id="msg" style="display: block;" style="margin-bottom: 20px">
			<span style="color:red;" >${error }</span>
		</div> 
		<div>
			<input class="easyui-linkbutton" 
				style="padding: 5px 0px; width: 100%;" type="submit" value="登录" />
		</div>
</form>
	</div>
</body>
</html>
<%
String msg = request.getParameter("msg");//用request得到 
%>
<script>
if('${param.msg}'!=""){
	 alert('该账号异地登录，您被强制下线'); 
}
</script>