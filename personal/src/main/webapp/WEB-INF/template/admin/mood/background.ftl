<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path.contextPath}/css/jquery-ui.css" rel="stylesheet"/>
<link href="${path.contextPath}/css/public_admin.css" rel="stylesheet"/>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/admin/mood/background.js"></script>
<script type="text/javascript">
	var contextPath="${path.contextPath}";
</script>
<style type="text/css">
   .imageContanier{
        width:95%;
        margin:0 25px;
   }
   .imageContanier li{
       width:180px;
       height:180px;   
	   box-shadow:0 0 5px black;
       float:left;
       margin:10px 10px;
       padding:5px;
   }
</style>
</head>
<body>
	<div class="container">
		<ul>
			<li class="toolbar">
				<div>
					<div style="float:left;" class="add">
						<img src="${path.contextPath}/images/admin/icons/add.ico" width="15px" height="15px" />
						<span>上传背景</span>
					</div>
					<div style="float:left;" class="remove">
						<img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/>
						<span>删除背景</span>
					</div>
				</div>
			</li>
			<li>
				<div>
					<ul class="imageContanier">
						<#if error??>
							<li>${error}</li>
						<#else>
						 	<#list images as data>
						 		<li>
						 			<img src="${path.contextPath}${data.imageUrl}" width="180px" height="180px"/>
						 		</li>
						 	</#list>
						</#if>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<textarea id="upload_container" style="display:none;"></textarea>
</body>
</html>