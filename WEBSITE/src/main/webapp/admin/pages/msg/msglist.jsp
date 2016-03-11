<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/meta.jsp" %>
<script type="text/javascript">
	var contextPath = BASEPATH;
</script> 
<script type="text/javascript" src="${ctx}/js/admin/msg/msglist.js"></script>


</head>
<body class="easyui-layout">  
	<div id="toolbarDiv" >
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addMethod(this)" url="company/add.ajax">添加</a> -->
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="editMethod" onclick="editMethod(this)" url="company/edit.ajax">修改</a>--> 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="removeMethod(this)" url="msg/remove.ajax">删除</a>
		
	</div >
    	<table id="dataGrid"></table>
    	
</body>  
</html>