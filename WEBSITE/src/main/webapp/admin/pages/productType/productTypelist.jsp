<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/meta.jsp" %>
<script type="text/javascript" src="${ctx}/js/public/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${ctx}/js/public/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/productType/productTypelist.js"></script>


</head>
<body class="easyui-layout">  
	<div id="toolbarDiv" >
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addMethod(this)" url="productType/add.ajax">添加</a>
		<!--  -->
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="editMethod" onclick="editMethod(this)" url="productType/edit.ajax">修改</a>
		
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeMethod(this)" url="productType/remove.ajax">删除</a>
	</div >
    	<table id="dataGrid"></table>
    	
    <div id="addDiv" style="display: none">
    	<form id="addForm" method="post" enctype="multipart/form-data" >
    	<input type="hidden" name="id" id="id"/>  
    		<table>
    	   		<tr>
    	   		<td colspan="2"><span><font color="red">类型名称不允许重复添加</font></span></td>
    	   		</tr>
    			<tr>
    	   			<td width="15%" height="50px"><label for="name">类型名称:</label></td>
    	   			<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" validType="length[1,10]"/></td>
    	   		</tr>
    		</table>
		</form>  
    </div>
</body>  
</html>