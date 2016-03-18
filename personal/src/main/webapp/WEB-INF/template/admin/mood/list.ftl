<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path.contextPath}/css/jquery-ui.css" rel="stylesheet"/>
<link href="${path.contextPath}/css/public_admin.css" rel="stylesheet"/>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/admin/mood/list.js"></script>
<script type="text/javascript">
	var contextPath="${path.contextPath}";
</script>
<style type="text/css">
	.content{
		position:absolute;
		 z-index:4;
		 width:200px;
		 height:200px;
		 margin-top:-123px;
	}
</style>
</head>
<body>
    <div>
		<ul>
			<li class="toolbar">
				<div>
					<div style="float:left;" class="add">
						<img src="${path.contextPath}/images/admin/icons/add.ico" width="15px" height="15px" />
						<span>写心情</span>
					</div>
					<div style="float:left;" class="remove">
						<img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/>
						<span>删除</span>
					</div>
					<div style="float:left;" class="background">
						<img src="${path.contextPath}/images/admin/icons/background_manager.ico" width="15px" height="15px"/>
						<span>背景管理</span>
					</div>
				</div>
			</li>
			<li>
				<div style="background:url('${path.contextPath}/images/admin/shadow.jpg') repeat-x;height:510px;overflow:auto;">
					<ul>
						<#if error??>
							<li>${error}</li>
						<#else>
						 	<#list moods as data>
						 		<li>
						 			<img src="${data.imageUrl}" width="200px" height="200px"/>
						 			<div class="content">${data.content}</div>
						 		</li>
						 	</#list>
						</#if>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		onload="javascript:this.height=this.contentWindow.document.documentElement.scrollHeight+10"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
</body>
</html>