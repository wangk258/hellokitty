<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/admin/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/admin/menu/menulist.js"></script>
</head>
<body class="easyui-layout">
	<div id="toolbarDiv">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addMethod(this)" url="editor/addMenu.ajax">添加</a> <a
			href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" id="editMethod"
			onclick="editMethod(this)" url="editor/editMenu.ajax">修改</a> <a
			href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true"
			onclick="removeMethod(this)" url="editor/remove.ajax">删除</a>
	</div>
	<table id="dataGrid"></table>

	<div id="addDiv" style="display: none">
		<form id="addForm" method="post">
			<input type="hidden" name="id" id="id" />
			<table>
				<tr>
					<td width="15%" height="50px"><label for="name">菜单名:</label>
					</td>
					<td><input class="easyui-validatebox" type="text" name="ckey"
						id="ckey" data-options="required:true,length:[5,20]" />
					</td>
				</tr>
				<tr>
					<td><label for="email">&emsp;分类:</label>
					</td>
					<td><select name="navigationType" id="navigationType"
						onchange="setUrl(this)">
							<option value="0">编辑</option>
							<option value="1">维护</option>
					</select></td>
				</tr>
				<tr>
					<td><label for="email">&emsp;导航:</label>
					</td>
					<td><select name="pkey" id="pkey"
						onchange="setUrlByNavi(this)">

					</select></td>
				</tr>
				<tr>
					<td width="15%" height="50px"><label for="name">&emsp;&emsp;url:</label>
					</td>
					<td><input class="easyui-validatebox" type="text" name="url"
						id="url" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>