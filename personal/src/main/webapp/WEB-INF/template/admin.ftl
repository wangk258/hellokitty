<#include "common/main.ftl">
<@head title="安安小屋后台管理" isAdmin=false>
		<style type="text/css">
			html,body{
				overflow:hidden;
				background:url("/images/admin/bg.png") repeat scroll 0 0 ;
			}
			ul{
				float:left;
			}
			ul li{
				float:left;
				list-style-type: none;
			}
			ul li:hover{
				background-image:url('${path.contextPath}/images/div_bg.png');
				border-radius:10px;
				cursor:pointer;
			}
			.div_title{
				background-image:url('${path.contextPath}/images/div_bg.png');
				width:80px;
				text-align:center;
				vertical-align:middle;
				margin:10px 31px;
				border-radius:10px;
				color:white;
				font-weight:bolder;
			}
			.div_img{
				margin:20px 40px;
			}
			img{
				width:64px;
				height:64px;
				border:0;
			}
		</style>
</@head>
<@body>
	<div style="width:1000px;margin:0 auto;">
		<ul>
			<li onclick="openDialog(1000,580,'${path.contextPath}/diary/list.do?path=admin','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/diary.ico"/>
				</div>
				<div class="div_title"><span>心灵日记</span></div>
			</li>
			<li onclick="openDialog(1000,580,'admin/article/list.html','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/article.ico"/>
				</div>
				<div class="div_title"><span>美文欣赏</span></div>
			</li>
			<li onclick="openDialog(1000,580,'${path.contextPath}/album/list.do?path=admin','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/article.ico"/>
				</div>
				<div class="div_title"><span>美丽记忆</span></div>
			</li>
			<li onclick="openDialog(1000,580,'${path.contextPath}/webSiteCollection/list.do?path=admin','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/article.ico"/>
				</div>
				<div class="div_title"><span>网站收藏</span></div>
			</li>
			<li onclick="openDialog(1000,580,'admin/english/list.html','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/article.ico"/>
				</div>
				<div class="div_title"><span>英语美文</span></div>
			</li>
			<li onclick="openDialog(1000,580,'${path.contextPath}/mood/list.do?path=admin','dialog_div','dialog_frame')">
				<div class="div_img">
					<img src="${path.contextPath}/images/admin/icons/article.ico"/>
				</div>
				<div class="div_title"><span>英语美文</span></div>
			</li>
		</ul>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
</@body>
