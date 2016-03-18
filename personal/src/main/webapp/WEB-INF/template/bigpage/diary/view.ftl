<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<style type="text/css">
*{
	color:gray;
	font-size:15px;
}
html,body{
	margin:10px 10px;
	background-color: #87c91f;
}
ul{
	float:left;
	width:68%;
	margin-left:50px;
	padding:0px;
}
ul#diary li{
	list-style-type: none;
	float: left;
	width: 28%;
}


#pageBar{
	position:absolute;
	left:350px;
	width:500px;
	margin-top: 10px;
}
#pageBar ul li{
	width:30px;
	margin:0px;
}
.content{
	clear:both;
	padding:5px;
}
.div_diary{
	width:824px;
	margin:0 auto;
}
</style>
</head>
<body>
	<div class="div_diary" style="height:255px;background-image: url('${path.contextPath}/images/diary/diary-bg-top.gif');border-top-left-radius:10px;border-top-right-radius:10px; ">
		<ul id="diary" style="margin-top: 130px;">
			<li id="date" style="margin-left:60px;">${diary.date}</li>
			<li id="week">${diary.week}</li>
			<li id="weather">${diary.weather}</li>
		</ul>
	</div>
	<div class="div_diary" style="background-image: url('${path.contextPath}/images/diary/diary-bg-content.gif');">
		<ul>
			<li id="content" class="content" style="list-style-type: none;color:black;">${diary.content}</li>
		</ul>
	</div>
	<div class="div_diary" style="height:63px;background-image: url('${path.contextPath}/images/diary/diary-bg-bottom.gif');border-bottom-left-radius:10px;border-bottom-right-radius:10px;"></div>
</body>
<script type="text/javascript">
	$("#content").parent().parent().height($("#content").height()+10);
</script>
</html>