<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
html,body{
	overflow:hidden;
	margin:10px 10px;
}
ul{
	float:left;
	width:100%;
	margin:0px;
	padding:0px;
}
ul#diary li{
	list-style-type: none;
	float: left;
	width: 33%;
}
.content{
	clear:both;
	border-bottom:1px dotted #aaa;
	border-top:1px dotted #aaa;
	margin-top:20px;
	padding:5px;
}
</style>
</head>
<body>
	<div style="width:700px;margin:0 auto;">
		<ul id="diary">
			<#if error??>
				<li>${error}</li>
			<#else>
				<li id="date">${diary.date}</li>
				<li id="week">${diary.week}</li>
				<li id="weather">${diary.weather}</li>
				<li id="content" class="content" style="width:90%">${diary.content}</li>
			</#if>
		</ul>
	</div>
</body>
</html>