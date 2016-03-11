<!DOCTYPE html>
<html>
<head>
    <title>小旋风宅急送--团队介绍</title>
    <#include "common/head.ftl">
</head>
<body>
<#include "common/navigation.ftl">
<div class="team main" style="width:1000px;word-warp:break-word;">
	<#list map?keys as key>
		${map[key]}
	</#list>
</div>
<#include "common/footer.ftl">
</body>
</html>