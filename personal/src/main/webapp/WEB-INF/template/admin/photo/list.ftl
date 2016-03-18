<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${path.contextPath}/css/jquery-ui.css" rel="stylesheet"/>
<link href="${path.contextPath}/css/jquery.lightbox-0.5.css" rel="stylesheet"/>
<link href="${path.contextPath}/css/public_admin.css" rel="stylesheet"/>
<script type="text/javascript" src="${path.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/jquery.lightbox-0.5.min.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/public.js"></script>
<script type="text/javascript" src="${path.contextPath}/js/admin/photo/list.js"></script>
<script type="text/javascript">
	var contextPath="${path.contextPath}";
</script>
<style type="text/css">
	.album_list,.photo_list{
		height: 500px;
		overflow: auto;
		background:url("${path.contextPath}/images/admin/shadow.jpg") repeat-x;
	}
	.album,.photo{
	 	float:left;
	 	width:98%;
	 	margin-left:20px;
	}
	.album li,.photo li{
		list-style-type: none;
		float:left;
		width:139px;
		margin:10px;
		padding:13px;
		height:139px;
		border:1px solid #eee;
	}
	.album .album_image img:hover,.photo .photo_image a img:hover{
		cursor:pointer;
		-webkit-box-shadow: #666 0px 0px 10px;
		-moz-box-shadow: #666 0px 0px 10px;
		box-shadow: #666 0px 0px 10px;background: #EEFF99;
	}
	.album .album_image img,.photo .photo_image a img{
		width:135px;
		height:135px;
	}
	.album .album_name{
		text-align: center;
		margin-top:3px;
		width:100%;
		color:#aaa;
	}
	.album .album_select{
		display:block;
		width:10px;
		float:left;
		margin-top:10px;
	}
	.remove_album_toolbar,.remove_photo_toolbar{
		height:25px;
		background-image:url("${path.contextPath}/images/div_bg.png");
		margin:-12px -13px -13px -13px;
		padding-top:2px;
		position: relative;
		z-index: 10;
		display:none;
		width:165px;
	}
	.remove_album_toolbar img,.remove_photo_toolbar img{
		display: block;
		float: right;
		width:20px;
		hegiht:20px;
		margin-right:10px;
	}
	.remove_album_toolbar img:hover,.remove_photo_toolbar img:hover{
		cursor:pointer;
	}
	.mainPage_flag{
	    position:relative;
		z-index:20;
		margin-top:-150px;
		margin-bottom:100px;
	}
	.selected_image{
		position:relative;
		z-index: 20;
		width:50px;
		height:50px;
		margin-top:-150px;
		margin-left:95px;
	}
</style>
</head>
<body>
	<div id="album_container">
		<ul>
			<li class="toolbar">
				<div style="float:left;">
					<div style="float:left;" id="addAlbum">
						<img src="${path.contextPath}/images/admin/icons/add.ico" width="15px" height="15px" />
						<span>新建相册</span>
					</div>
					<div style="float:left;" id="deleteAlbum">
						<img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/>
						<span>删除相册</span>
					</div>
				</div>
				<div class="search">
					<input type="text" />
				</div>
			</li>
			<li>
				<div class="album_list">
					<ul class="album">
						<#if error??>
							<li>${error}</li>
						<#elseif pageBean??>
							<#list pageBean.recordList as data>
								<li>
									<div class="album_image" id="${data.id}"><img src="${path.contextPath}${data.imageUrl}"/></div>
								     <div class="album_name">${data.name}</div>
								     <div class="remove_album_toolbar"  id="${data.id}">
										<img src="${path.contextPath}/images/admin/icons/recycle.ico" title="删除该相册"/>
									 </div>
								</li>
							</#list>
						</#if>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div id="photo_container" style="display:none;">
		<ul>
			<li class="toolbar">
				<div style="float:left;">
					<div style="float:left;" id="back">
						<img src="${path.contextPath}/images/admin/icons/back.ico" width="15px" height="15px" />
						<span>返回相册</span>
					</div>
					<div style="float:left;" id="uploadPhoto">
						<img src="${path.contextPath}/images/admin/icons/upload.ico" width="15px" height="15px" />
						<span>上传照片</span>
					</div>
					<div style="float:left;" id="deletePhoto">
						<img src="${path.contextPath}/images/admin/icons/delete.ico" width="15px" height="15px"/>
						<span>删除照片</span>
					</div>
				</div>
			</li>
			<li>
				<div style="margin:10px 5px;">当前相册：<span id="current_album"></span><input type="hidden" id="album_id"/></div>
				<textarea  style="display:none;" id="upload_container"></textarea>
				<div class="photo_list" style="height:490px;">
					<ul class="photo">
						<#if error??>
						     <li>${error}</li>
						<#elseif photos??>
							<#list photos.recordList as data>
								<#if data.showInTheMainPage==1>
									<#assign showStr="<div class='mainPage_flag'><img src='${path.contextPath}/images/showInMainPage.png' width='50px' height='50px'/></div>">
									<#assign iconStr='<img src="${path.contextPath}/images/admin/icons/cancel.ico" class="cancelInMainPage" title="取消在主页展示"/>'>
								<#else>
									<#assign showStr="">
									<#assign iconStr='<img src="${path.contextPath}/images/admin/icons/home.ico" class="showInMainPage" title="在主页展示"/>'>
								</#if>
								<li id="${data.id}">
									<div class="photo_image">
										<a href="javascript:void(0)" class="lightBox_image">
											<img src="${path.contextPath}${data.imageUrl}"/>
										</a>${showStr}
									</div>
									<div class="remove_photo_toolbar" id="${data.id}">
										<img src="${path.contextPath}/images/admin/icons/recycle.ico" class="remove" title="删除该照片"/>
										${iconStr}
										<img src="${path.contextPath}/images/admin/icons/cover.ico" class="setCover" title="设置为相册封面"/>
									</div>
								</li>
							</#list>
						</#if>
					</ul>
				</div>
			</li>
		</ul>
	</div>
	<div style="display:none" id="createAlbum">
		<div style="margin:50px 0 20px 20%;">相册名称：<input type="text"  id="albumName" style="border:1px solid #eee;"/></div>
		<div style="margin-left:40%;"><input type="button" value="确定" id="saveBtn"/></div>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		onload="javascript:this.height=this.contentWindow.document.documentElement.scrollHeight+10"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
</body>
</html>