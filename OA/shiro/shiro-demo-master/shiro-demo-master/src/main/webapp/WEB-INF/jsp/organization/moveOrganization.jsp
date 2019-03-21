<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/zTree_v3/css/demo.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/zTree_v3/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/global.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/zTree_v3/js/jquery.ztree.core.js"></script>
<SCRIPT LANGUAGE="JavaScript">
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
			onClick : zTreeOnClick,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};
	function zTreeOnClick(treeId, treeNode, clickFlag) {
		$("#targetId").val(clickFlag.name);
		$("#parentid").val(clickFlag.oId);
	}
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
		
	});
	
	//移动部门时，不可移入自己子部门下，因此选择目标部门是，移除 被移动部门 以及 其所有子部门
	function zTreeOnAsyncSuccess(){
		var node = zTreeObj.getNodeByParam("oId", '${needMoveId }', null);
		zTreeObj.removeChildNodes(node);
		zTreeObj.removeNode(node);
	} 
</SCRIPT>
<script type="text/javascript">
	function clickShowZtree(){
		$("#ztreeDiv").css("display","block");
	}
</script>
<body>

<form action="${pageContext.request.contextPath}/organization/doMoveOrganization" method="get" >
	<input type="hidden" value="${needMoveId }"  name="oId"/>
	<input type="hidden" value="" id="parentid"  name="parentid"/>
	目标部门：<input id="targetId" type="text" readonly="readonly"/><button id="showZtree" onclick="clickShowZtree()" type="button">选择</button>
	<div id="ztreeDiv" style="display:none;"><ul id="treeDemo" class="ztree moveZtree" style="overflow: auto;"></ul></div>
	<br/>
	<input style="margin-left: 74px;" type="submit" value="确认移动">
</form>

</body>
</html>
