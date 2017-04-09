<!DOCTYPE html>
<html>
<head>
    <title>小旋风宅急送--公司介绍</title>
    <#include "common/head.ftl">
    <link type="text/css" rel="stylesheet" href="/css/front/company.css">
</head>
<body>
<#include "common/navigation.ftl">
<div class="main">
	<#list map?keys as key>
		<div class="introduction">
		    <div class="title">
		    	<div class="speartor leftline"></div>
		    	<span>${key}</span>
		    	<div class="speartor rightline"></div>
		    </div>
		    <div class="content">
		        ${map[key]}
		    </div>
		</div>
	</#list>
</div>
<#include "common/footer.ftl">
</body>
</html>