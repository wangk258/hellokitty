<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path.contextPath}/css/jquery-ui.css" rel="stylesheet"/>
<link href="${path.contextPath}/css/public_admin.css" rel="stylesheet"/>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/admin/mood/add.js"></script>
<script type="text/javascript">
	var contextPath="${path.contextPath}";
</script>
<style type="text/css">
	.selector{
		background: url('${path.contextPath}/images/admin/selector.png') no-repeat 0 65px scroll;
		height:150px;
		width:20px;
		float:left;
		box-shadow: 0px 0px 10px 1px green inset;
		border-top-right-radius:5px;
		border-bottom-right-radius:5px;
	}
	.selector:hover{
		cursor:pointer;
	}
	.small{
		height:150px;
		width:150px;
		box-shadow:0px 0px 1px 1px green inset;
		float:left;
		border-top-left-radius:5px;
		border-bottom-left-radius:5px;
	}
	.imagelist{
		position:absolute;
		z-index:4;
		margin-left:170px;
		margin-top:50px;
		box-shadow:0 0 5px black;
		border-radius:10px;
		width:390px;
		height:330px;	
		overflow-y:auto;
		overflow-x:hidden;
	}
	.imagelist ul{
		margin:0 7px;
	}
	.imagelist li{
		float:left;
		width:100px;
		padding:5px;
		margin:3px 5px;
		border:1px solid gray;
	}
	.imagelist li:hover{
		cursor:pointer;
		border:1px solid blue;
	}
	.viewContent{
		position:absolute;
		z-index:10;
		width:160px;
		height:160px;
		margin-top:-180px;
		word-wrap:break-word;
		font-size:20px;
		text-align:center;
		color:white;
		font-weight:bolder;
		padding:10px;
	}
	.direction{
		width:80px;
		height:150px;
		float:left;
		opacity:0.5;
	}
	.direction:hover{
	    cursor:pointer;
		opacity:1;
	}
	.left{
		background:url("${path.contextPath}/images/admin/left.png") no-repeat 0 30px scroll;
	}
	.right{
		background:url("${path.contextPath}/images/admin/right.png") no-repeat 0 30px scroll;
		float:right;
	}
	.middle{
		width:430px;
		height:150px;
		float:left;
		overflow:hidden;
	}
	.middle ul{
		position:relative;
	}
	.middle li{
		float:left;
		width:100px;
		height:100px;
		margin:15px 5px;
	}
</style>
</head>
<body>
    <div class="container">
		<table border="0" width="95%" align="center">
			<tr>
				<td align="right">选择背景：</td>
				<td>
					<div class="imageContainer">
						<div class="small">
							<img src="" height="150px" width="150px"/>
						</div>
						<div class="selector">
						</div>
					</div>
					<div style="display:none;background-color:white;" class="imagelist">
						<ul>
							<#list images as data>
								<li>
									<img src="${path.contextPath}/${data.imageUrl}" width="100px" height="100px"/>
								</li>
							</#list>							
						</ul>
					</div>
				</td>
				<td>
				<div class="direction left"></div>
				<div class="middle">
					<ul>
						<#list images as data>
								<li>
									<img src="${path.contextPath}/${data.imageUrl}" width="100px" height="100px"/>
								</li>
							</#list>
					</ul>
				</div>
				<div class="direction right"></div>
				</td>
			</tr>
			<tr>
				<td align="right">
					心情：
				</td>
				<td colspan="2" height="250px">
					<textarea style="width:98%;height:200px;"></textarea>
					<div class="view" style="display:none;">
						<img src="" height="180px" width="180px"/>
						<div class="viewContent">
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>