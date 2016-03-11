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
<script type="text/javascript" src="${ctx}/js/public/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/public/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/company/companylist.js"></script>


</head>
<body class="easyui-layout">  
	<div id="toolbarDiv" >
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addMethod(this)" url="company/add.ajax">添加</a> -->
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="editMethod" onclick="editMethod(this)" url="company/edit.ajax">修改</a>
		<!--  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="removeMethod(this)" url="company/remove.ajax">删除</a>-->
		
	</div >
    	<table id="dataGrid"></table>
    	
    <div id="addDiv" style="display: none">
    	<form id="addForm" method="post" enctype="multipart/form-data">   
    	<!-- 	<input name="content" type="hidden" id="descriptionHidden"/> -->
    		<input name="imageUrl" type="hidden" id="imageUrlHidden"/>
    		<input name="id" type="hidden" />
    		
    		<table>
    			<tr>
	    			<td class="tdTitle"> <label for="name">维护项:</label> </td>
	    			<td> <input class="easyui-validatebox" type="text" name="item" data-options="required:true,length:[5,20]" disabled="disabled"/> </td>
    			</tr>
    		<!--  	<tr>
    				<td> <label for="name">图片:</label> </td>
    				<td><a class="easyui-linkbutton upload" id="uploadPhoto" href="javascript:void(0)">上传</a></td>
    			</tr>
 				
    			<tr>
    				<td></td>
    				<td >
		      			<div id="imagesDiv" style="width: 80%;"></div>
    				</td>
    			</tr>-->
    			
    			<tr>
    			<td class="tdTitle"> <label for="name">内容:</label> </td>
    				<td> <input class="easyui-validatebox" type="text" name="content" data-options="required:true,length:[5,20]" /> </td>
    			</tr>  
    		</table>
		   
   		<!--  <p id="editorImage_container" style="display: none"></p> -->	
		</form>  
    </div>
</body>  
</html>