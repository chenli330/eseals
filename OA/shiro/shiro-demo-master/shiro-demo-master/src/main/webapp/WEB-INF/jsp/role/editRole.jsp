<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../tag.jsp"%>
<html>
<head>

	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/zTree_v3/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/zTree_v3/css/zTreeStyle/zTreeStyle.css"	type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/global.css">	
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery.ztree.exedit.js"></script>

</head>
<body>

<h2 style="padding-left: 50px;text-align: center;width: 250px;">
<a href="${pageContext.request.contextPath }/role/showRole"><button class="backIcon"></button></a>
<span>修改角色</span>
</h2>

	<div style="width:400px">
		<div style="padding:10px 40px 20px 40px">
	    <form onsubmit="return checkSubmit()" id="ff" method="post" action="${pageContext.request.contextPath }/role/doEditRole" >
	    	<table cellpadding="5" cellspacing="10">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td>
	    				<input type="text" name="role" value="${role.role }" required="required"></input>
	    				<input type="hidden" name="rId" value="${role.rId }"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>角色描述:</td>
	    			<td><input type="text" name="description" value="${role.description }" required="required"></input></td>
	    		</tr>
	    		<tr>
	    			<td>角色权限:</td>
	    			<td>
	    				<input id="resourceId" type="hidden" name="resourceIdStrings"/>
	    				<div id="orgaTree" ><ul id="treeDemo" class="ztree" style="height: 140px;width:180px; overflow: auto;"></ul></div>
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
			idKey : "reId",
			pIdKey : "parentId",
			rootPId : null
		//根节点为空表示最高节点
		}
	},
	async : {
		enable : true,
		url : "${pageContext.request.contextPath }/resource/resourceZtree",
		dataFilter : null
	},
	callback : {
		onAsyncSuccess: expand,
		onAsyncSuccess: zTreeOnAsyncSuccess
	},
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "ps", "N": "s" }
	},
	edit: {
		enable: true,
		drag: {
			isCopy: false,
			isMove: false
		},
		showRenameBtn: false,
		showRemoveBtn: false
	}
};
$(document).ready(function() {
	zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
});
//ztree的异步加载成功回调函数
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
	var resourceList = '${resourceList}';
	var resourceJson = JSON.parse(resourceList);
	var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
	for(var i=0;i<resourceJson.length;i++){
		for(var j=0;j<nodes.length;j++){
			if(nodes[j].name==resourceJson[i].name){
				zTreeObj.checkNode(nodes[j]);
				zTreeObj.expandNode(nodes[j]);
			}
		}
	}
}
//ztree展开第一层
function expand(){
	var node = zTreeObj.getNodeByParam("reId", 1, null);
	zTreeObj.expandNode(node);
}
</script>
<script>
//表单提交校验
function checkSubmit(){
	var nodes = zTreeObj.getCheckedNodes(true);
	var resourceId = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].reId!=1){
			resourceId = resourceId+"/"+ nodes[i].reId
		}
	}
	$("#resourceId").val(resourceId)
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