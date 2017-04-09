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
<script type="text/javascript"
	src="${ctx}/js/admin/millCycloneHome/millCycloneHomelist.js"></script>


</head>
<body>

	<div id="addDiv">
		<form id="addForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" /> <input type="hidden"
				name="content" id="descriptionHidden" />

			<div class="datagrid-toolbar mytoolbar">
				<span><a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true"
					onclick="addMethod(this)" url="millCycloneHome/add.ajax" id="saveA">保存</a>
				</span> <span><a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-detail',plain:true"
					onclick="viemMethod(this)" url="millCycloneHome/wind.php">预览</a>
				</span>
				<div id="msg" style="display: inline;">
					<font color="red">描述内容在富文本中可以编辑样式</font>
				</div>
			</div>
			<div>
				<textarea id="descriptionTextarea"></textarea>
			</div>
		</form>
	</div>
</body>
</html>