<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<style type="text/css">
.left {
	width: 30%;
	float: left;
	box-shadow: 0px 0px 100px #ABCDEF;

ul {
	width: 100%;
	list-style-type: none;
}
li{
	width:80%;
	height:30px;
	line-height:30px;
}
li:hover{
	cursor:pointer;
	color:red;
}
.right {
	width: 65%;
	Float: right;
	padding:5px;
.player{
	margin-left:10%;
	margin-top:10px;
}
</style>
</head>
<body style="background: url('${path.contextPath}/images/english/english_bg.png')">
	<div style="width: 1000px; margin: 0 auto;">
		<div class="left">
			<div class="player">
				<object type="application/x-shockwave-flash"
					data="${path.contextPath}/english_player/player.swf?mp3="
					width="240" height="40" id="dewplayer-rect">
					<param name="wmode" value="transparent" />
					<param name="flashvars" value="autoplay=true&showtime=true&autoreplay=true" />
				</object>
			</div>
			<ul>
	</div>
</body>
</html>