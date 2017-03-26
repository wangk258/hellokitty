<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="/admin/pages/login/css/base.css" rel="stylesheet"
	type="text/css">
<link href="/admin/pages/login/css/login.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript">

if(self != window.top){
	window.top.location.href="/admin/pages/login.jsp";
}
//登录
function login(){
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	if(validation()){
		var loginForm = document.getElementById("loginForm");
		loginForm.action="/user/login.ajax";
		document.getElementById("loginForm").submit();
	}
}
function validation(){
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	if(username.value && password.value){
		
		return true;
	}else{
		document.getElementById("errMsg").innerHTML="用户名或密码不能为空";
	}
}
</script>
</head>
<body>
	<div class="login">
		<form action="" method="post" id="loginForm">
			<div class="logo_img">
				<img src="/images/front/logo.png" />
			</div>
			<div class="logo"></div>
			<div class="login_form">
				<div id="errMsg"></div>
				<div class="user">
					<input class="text_value" value="" name="username" type="text"
						id="username" placeholder="请输入用户名" /> <input class="text_value"
						value="" name="password" type="password" id="password"
						placeholder="请输入密码" />
				</div>
				<a class="button" id="submit" onclick="login()">登录</a>
			</div>

			<div id="tip"></div>
			<div class="note">本系统由HTML5开发，推荐使用IE9+，FireFox，Chrome浏览器浏览使用！</div>
			<div class="foot">${companyInfo.beian.content }</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	window.onload = function(){
		document.onkeydown = function(e){
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {

		    	login() ;
		     }
		};
		var username = document.getElementById("username");
		username.value="${username}";
		document.getElementById("password").value="";
		var loginMsg = "${loginMsg}";
		if(loginMsg){
			document.getElementById("errMsg").innerHTML=loginMsg;
		}
		//获取焦点
		username.focus();
	}
	
	
</script>
</html>