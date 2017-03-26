<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(
		function() {
			
		});
</script>
<style type="text/css">


.content {
	width: 650px;
	float: left;
	position:absolute;
	top:250px;
	left:300px;
	height:450px;
}
*{
	color:gray;
}
ul {
	float: left;
	width: 100%;
	margin: 5px 50px;
	padding: 0px;
}

ul li {
	width: 100%;
	list-style-type: none;
	height:20px;
}

ul li span {
	display: block;
	width: 18%;
	float: left;
	text-align: center;
}
#diary_list li{
	border-bottom: 1px dashed #ABCDEF;
	height:30px;
}
#diary_list li:hover{
	font-weight:bolder;
	cursor:pointer;
}
#diary_list li span{
	margin-top:10px;
}
.title{
	border-bottom:3px double #ABCDEF;
	font-weight:bolder;
}
</style>
</head>
<body style="background: url('${path.contextPath}/images/diary/bd.png')">
	<div style="width: 1000px;height:690px; margin: 0 auto;background: url('${path.contextPath}/images/diary/diary_bg.jpg') no-repeat;">
		<div class="content">
			<ul>
				<li class="title"><span>日期</span><span>星期</span><span>天气</span><span>浏览数</span><span>评论数</span></li>
			</ul>
			<ul id="diary_list">
				<#if error??>
					<li>${error}</li>
				<#else>
					<#list pageBean.recordList as data>
						<li onclick="window.open('${path.contextPath}/diary/getone.do?id=${data.id}')">
							<span>${data.date}</span>
							<span>${data.week}</span>
							<span>${data.weather}</span>
							<span>0</span>
							<span>0</span>
						</li>
					</#list>
				</#if>
			</ul>
		</div>
	</div>
</body>
</html>
