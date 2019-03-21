<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../tag.jsp"%>
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
			onClick : zTreeOnClick
		}
	};
	function zTreeOnClick(treeId, treeNode, clickFlag) {
		$("#nodeIframe").attr("src", "${pageContext.request.contextPath }/organization/toOrganizationDetail/"+clickFlag.oId);
		$(".showDetail").css("display","block");
	}
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#treeDemo"), setting);
	});
	function refreshZtree(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		treeObj.reAsyncChildNodes(null, "refresh");
		$(".showDetail").css("display","none");
	}
</SCRIPT>
</head>
<body>
	<div>
		<ul id="treeDemo" class="ztree" style="height: 80%; overflow: auto;"></ul>
		<div>
			<button type="button" onclick="refreshZtree()" >点击刷新树</button>
		</div>
		<div class="showDetail">
			<iframe id="nodeIframe" height="100%" width="100%" src="" scrolling="auto " frameborder="0 " />
		</div>
		
	</div>
</body>
</html>