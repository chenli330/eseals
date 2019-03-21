<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tag.jsp"%>
<html>
<head>

	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/zTree_v3/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/zTree_v3/css/zTreeStyle/zTreeStyle.css"	type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/global.css">	
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery.ztree.core.js"></script>
	
	
</head>
<body>
<h2 style="padding-left: 50px;text-align: center;width: 250px;">
<a href="${pageContext.request.contextPath }/user/findAllUser"><button class="backIcon"></button></a>
<span>新增用户</span>
</h2>

	<div style="width:400px">
		<div style="padding:10px 40px 20px 40px">
	    <form onsubmit="return checkSubmit()" id="ff" method="post" action="${pageContext.request.contextPath }/user/doAddUser" >
	    	<table cellpadding="5" cellspacing="10">
	    		<tr>
	    			<td>用户名:</td>
	    			<td><input onblur="queryNameUnique()" id="queryName" type="text" name="username" required="required"></input><span id="queryNameResult"></span></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input type="password" name="password" required="required"></input></td>
	    		</tr>
	    		<tr>
	    			<td>部门:</td>
	    			<td>
	    				<input id="orgaInput" onclick="showOrgaTree()" type="text" readonly="readonly"/><span></span>
	    				<input id="orgaId" name="organizationId" type="hidden"/>
	    				<div id="orgaTree" style="display:none;" ><ul id="treeDemo" class="ztree" style="height: 80%;width:180px; overflow: auto;"></ul></div>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>角色:</td>
	    			<td>
	    				<select style="width:100%;" name="roleIds">
	    					<option value="">请选择</option>
	    					<c:forEach var="roleList" items="${roleList }">
	    						<option value="${roleList.rId }">${roleList.description }</option>
	    					</c:forEach>
	    				</select>
	    			</td>
	    		</tr>
	    	</table>
	    	<div style="text-align:center;padding:5px">
	    		<button class="easyui-linkbutton" type="submit">submit</button>
	    		<button class="easyui-linkbutton" type="reset">reset</button>
	    	</div>
	    </form>
	    </div>
	</div>
</body>
</html>
<script type="text/javascript">
//部门ztree
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
	data : {
		simpleData : {
			enable : true,
			idKey : "oId",
			pIdKey : "parentid",
			rootPId : null
		//根节点为空表示最高节点
		}
	},
	async : {
		enable : true,
		url : "${pageContext.request.contextPath }/organization/ztreeGetOrganization",
		dataFilter : null
	},
	callback : {
		onClick : zTreeOnClick
	}
};
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
});
//ztree的点击函数
function zTreeOnClick(treeId, treeNode, clickFlag){
	$("#orgaInput").val(clickFlag.name);
	$("#orgaId").val(clickFlag.oId);
	$("#orgaTree").slideUp('slow');
}
</script>
<script>
//其他函数
function showOrgaTree(){
	$("#orgaTree").slideDown('slow')
}
//表单提交校验
function checkSubmit(){
	var orgaVal = $("#orgaInput").val();
	var queryNameResult = $("#queryNameResult").text();
	if(orgaVal=="" || queryNameResult != ""){
		$("#orgaInput").next().html("&times;").css("color","red");
		return false;
	}else{
		$("#orgaInput").next().html("");
		return true;
	}
	return false;
}
// 验证用户名唯一
function queryNameUnique(){
	var username = $("#queryName").val();
	if(username == ""){
		return;
	}
	$.post("${pageContext.request.contextPath }/user/queryNameUnique?username="+username,function(data){
		var resultMap = JSON.parse(data);
		if(resultMap.result != "true"){
			$("#queryName").next().text("被占用！").css({"color":"red","font-size":"10px"});
		}else{
			$("#queryName").next().text("");
		}
	},"text")
}
</script>