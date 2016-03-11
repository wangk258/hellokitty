<!DOCTYPE html>
<html>
<head>
    <title>小旋风宅急送--流程介绍</title>
    <#include "common/head.ftl">
</head>
<body>
<#include "common/navigation.ftl">
<div class="process main" style="width:1000px;word-wrap:break-word;">
<#list map?keys as key>
		${map[key]}
	</#list>
</div>
<#include "common/footer.ftl">
</body>
</html>