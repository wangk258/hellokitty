<#macro head title isAdmin>
	<!DOCTYPE HTML>
	<html>
		<head>
			<title>${title}</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<meta http-equiv="pragma" content="no-cache"/>
			<meta http-equiv="cache-control" content="no-cache"/>
			<meta http-equiv="expires" content="0"/>    
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
			<meta http-equiv="description" content="This is my page"/>
			<link href="${path.contextPath}/css/jquery-ui.css" rel="stylesheet"/>
			<link href="${path.contextPath}/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet"/>
			<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
			<script type="text/javascript" src="${path.contextPath}/js/jquery-ui.js"></script>
			<script type="text/javascript" src="${path.contextPath}/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
			<#if isAdmin>
				<script type="text/javascript">
					var contextPath="${path.contextPath}";
				</script>
				<link href="${path.contextPath}/js/page/themes/default/pagination.css" rel="stylesheet"/>
				<link href="${path.contextPath}/css/public_admin.css" rel="stylesheet"/>
				<script type="text/javascript" src="${path.contextPath}/js/date/WdatePicker.js"></script>
				<script type="text/javascript" src="${path.contextPath}/js/page/easyloader.js"></script>
				<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.all.min.js"></script>
				<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.config.js"></script>
			</#if>
			<script type="text/javascript" src="${path.contextPath}/js/public.js"></script>
			<#nested>
		</head>
</#macro> 

<#macro body>
	<body>
		<#nested>
	</body>
	</html>
</#macro>