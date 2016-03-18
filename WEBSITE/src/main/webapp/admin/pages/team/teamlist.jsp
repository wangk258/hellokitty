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
<link type="text/css" rel="stylesheet"
	href="${ctx}/css/admin/toolbar.css" />
<script type="text/javascript"
	src="${ctx}/js/public/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/public/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/team/teamlist.js"></script>


</head>
<body class="easyui-layout">
	<div id="toolbarDiv">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addMethod(this)" url="team/add.ajax">添加</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="editMethod(this)" url="team/edit.ajax">修改</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="removeMethod(this)" url="team/remove.ajax">删除</a>

	</div>
	<table id="dataGrid"></table>

	<div id="addDiv" style="display: none">
		<form id="addForm" method="post" enctype="multipart/form-data">
			<input name="description" type="hidden" id="descriptionHidden" /> <input
				name="imageUrl" type="hidden" id="imageUrlHidden" /> <input
				name="id" type="hidden" />
			<table>
				<tr>
					<td class="tdTitle"><label for="name">团队名:</label></td>
					<td><input class="easyui-validatebox" type="text" name="name"
						data-options="required:true,length:[5,20]" /></td>
				</tr>
				<tr>
					<td><label for="name">图片:</label></td>
					<td><a class="easyui-linkbutton upload" id="uploadPhoto"
						href="javascript:void(0)">上传</a>
					</td>
				</tr>

				<tr>
					<td></td>
					<td>
						<div id="imagesDiv" class="showImagesDiv"></div></td>
				</tr>
				<tr>
					<td><label for="name">描述:</label></td>
					<td><span><font color="red">描述内容在富文本中可以编辑样式</font>
					</span>
					</td>
				</tr>
				<tr>
					<td colspan="2"><textarea id="descriptionTextarea"></textarea>
					</td>
				</tr>
			</table>
			<p id="editorImage_container" style="display: none"></p>
		</form>
	</div>
</body>
</html>