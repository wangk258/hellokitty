<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/meta.jsp"%>
<script type="text/javascript">
	var contextPath = BASEPATH;
</script>
<script type="text/javascript"
	src="${ctx}/js/public/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/public/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="${ctx}/js/admin/banner/bannerlist.js"></script>


</head>
<body class="easyui-layout">
	<div id="toolbarDiv">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addMethod(this)" url="banner/add.ajax">添加</a>
		<!-- 
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editMethod(this)" url="banner/edit.ajax">修改</a>
		 -->
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="removeMethod(this)" url="banner/remove.ajax">删除</a>
	</div>
	<table id="dataGrid"></table>

	<div id="addDiv" style="display: none">
		<form id="addForm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td><label for="email">图片:</label>
					</td>
					<td><a class="easyui-linkbutton upload" id="uploadPhoto"
						href="javascript:void(0)">上传</a><span><font color="red">可批量上传</font>
					</span></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<div id="imagesDiv" style="width: 80%;"></div></td>
				</tr>
			</table>
			<p id="editorImage_container" style="display: none"></p>
		</form>
	</div>
</body>
</html>