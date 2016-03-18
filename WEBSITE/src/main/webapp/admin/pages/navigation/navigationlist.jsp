<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/meta.jsp"%>
<style type="text/css">
#addDiv table {
	width: 100%;
}

#addDiv table td {
	height: 50px;
}
</style>
<script type="text/javascript"
	src="${ctx}/js/admin/navigation/navigationlist.js"></script>
</head>
<body class="easyui-layout">
	<div id="toolbarDiv">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addMethod(this)" url="navigation/add.ajax">添加</a> <a
			href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" id="editMethod"
			onclick="editMethod(this)" url="navigation/edit.ajax">修改</a> <a
			href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="removeMethod(this)" url="navigation/remove.ajax">删除</a>
	</div>
	<table id="dataGrid"></table>
	<div id="addDiv">
		<form id="addForm" method="post">
			<input type="hidden" name="id" />
			<table>
				<tr>
					<td><label for="name">导航名称:</label>
					</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						data-options="required:true,length:[5,20]" />
					</td>
				</tr>
				<tr>
					<td><label for="email">页面链接:</label>
					</td>
					<td><input class="easyui-validatebox input" type="text"
						name="url" data-options="required:true,length:[5,20]" />
					</td>
				</tr>
				<tr>
					<td><label for="name">页面链接:</label>
					</td>
					<td><input class="easyui-validatebox" type="text" name="page"
						data-options="required:true,length:[5,20]" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>