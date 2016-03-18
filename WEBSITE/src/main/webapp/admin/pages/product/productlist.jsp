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
	src="${ctx}/js/admin/product/productlist.js"></script>


</head>
<body class="easyui-layout">
	<div id="toolbarDiv">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true"
			onclick="addMethod(this)" url="product/add.ajax">添加</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" id="editMethod"
			onclick="editMethod(this)" url="product/edit.ajax">修改</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true"
			onclick="removeMethod(this)" url="product/remove.ajax">删除</a>

	</div>
	<table id="dataGrid"></table>

	<div id="addDiv" style="display: none;">
		<form id="addForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="description" id="descriptionHidden" /> <input
				name="imageUrl" type="hidden" id="imageUrlHidden" /> <input
				type="hidden" name="id" />

			<table>
				<tr>
					<td><label for="name">产品图片:</label></td>
					<td><a class="easyui-linkbutton upload" id="uploadPhoto"
						href="javascript:void(0)"
						style="display: block; float: left; margin-top: 60px;">上传</a>
						<div id="imagesDiv" style="float: left;"></div></td>
				</tr>
				<tr>
					<td class="tdTitle"><label for="name">产品名称:</label>
					</td>
					<td><input class="easyui-validatebox" type="text" name="name"
						data-options="required:true,length:[5,20]" />
					</td>
				</tr>
				<tr>
					<td class="tdTitle"><label for="name">产品材料:</label>
					</td>
					<td><input class="easyui-validatebox" type="text"
						name="material" data-options="required:true,length:[5,50]" />
					</td>
				</tr>
				<tr>
					<td><label for="email">产品价格:</label>
					</td>
					<td><input class="easyui-validatebox input" type="text"
						name="price" data-options="required:true"
						onblur="validationPrice(this)" style="width: 60%" /><span><font
							color="red">格式:80.98(小数最多保留2位)</font>
					</span>
					</td>
				</tr>
				<tr>
					<td><label for="email">产品类型:</label>
					</td>
					<td><select id="productType" name="productType.id">

					</select></td>
				</tr>
				<tr>
					<td><label for="email">产品月份:</label>
					</td>
					<td><select id="year" name="year" style="width: 39%">
					</select>年 <select id="month" name="month" style="width: 39%">
					</select>月</td>
				</tr>
				<tr>
					<td><label for="email">产品描述:</label>
					</td>
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