<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.5.5.3/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/global.css">
	</head>
<body class="easyui-layout">
	 <div data-options="region:'north',title:'主题',split:true" style="height:125px;">
		<p>这是shiro-demo</p>
		<p>当前登陆用户：${current_user.username }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/logout">退出</a>
			<span style="float: right;">${weather }</span>
		</p>
	</div>
	<div data-options="region:'south',title:'时间',split:true" style="height:100px;">
		<p id="date"></p>
	</div>
	<div class="west" data-options="region:'west',title:'具有的权限',split:true">
		<c:forEach items="${resourcesList }" var="lis">
			<div style="margin-bottom:10px">
				<c:if test="${lis.parentId==1 }">
					<a href="#" class="easyui-linkbutton" onclick="addTab('${lis.name }','${pageContext.request.contextPath }${lis.url }')">${lis.name }</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
	<div id="tt" class="easyui-tabs" data-options=" region: 'center',title: 'center title' " style="padding:5px;background:#eee; ">
		<div title="Home">
			<iframe class="allIframe" id="main" scrolling="auto " frameborder="0 " src="${pageContext.request.contextPath }/welcome"></iframe>
		</div>
	</div>
</body>
</html>
<script>
	function addTab(title, url) {
		if($('#tt').tabs('exists', title)) {
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe class="allIframe" scrolling="auto " frameborder="0 "  src=" ' + url + ' " style="width:100%;height:98%; "></iframe>'; 
			$('#tt').tabs('add', {
				title: title,
				content: content,
				closable: true
			});
		}
	}
	
	function showDate(){
		var date = new Date();
		var year = date.getFullYear(); 
		var yue = (date.getMonth()+1)>9?date.getMonth():"0"+date.getMonth();
		var ri = date.getDate()>9?date.getDate():"0"+date.getDate();
		var shi = date.getHours()>9?date.getHours():"0"+date.getHours();
		var fen = date.getMinutes()>9?date.getMinutes():"0"+date.getMinutes();
		var miao = date.getSeconds()>9?date.getSeconds():"0"+date.getSeconds();
		var time  = year+"-"+yue+"-"+ri+" "+shi+":"+fen+":"+miao; 
		$("#date").text(time)
	}
	
	setInterval(showDate,1000);
</script>