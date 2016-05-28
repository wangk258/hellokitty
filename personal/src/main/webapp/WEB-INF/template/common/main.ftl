<#macro head title isAdmin>
	<!DOCTYPE HTML>
	<html>
		<head>
			<title>${title}</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
			<meta http-equiv="pragma" content="no-cache"/>
			<meta http-equiv="cache-control" content="no-cache"/>
			<meta http-equiv="expires" content="0"/>    
			<meta http-equiv="keywords" content=""/>
			<meta http-equiv="description" content=""/>
			<link href="${path.contextPath}/css/lib/jquery-ui.css" rel="stylesheet"/>
			<link href="${path.contextPath}/js/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
			<link href='${path.contextPath}/js/lib/jPaginate/css/style.css' rel='stylesheet'/>
			<link href="${path.contextPath}/js/lib/artDialog/skins/blue.css" rel="stylesheet"/>
			<#if isAdmin>
				<link href="${path.contextPath}/css/lib/toolbar.css" rel="stylesheet"/>
				<link href="${path.contextPath}/css/lib/public_admin.css" rel="stylesheet"/>
			</#if>
			<#nested>
			<script type="text/javascript">
				var contextPath = "${path.contextPath}";
			</script>
		</head>
</#macro> 

<#macro body>
	<body>
		<#nested>
	</body>
	</html>
</#macro>