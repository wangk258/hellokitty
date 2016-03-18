<#include "common/main.ftl">
<@head title="安安小屋--欢迎光临" isAdmin=false>
	<link rel="stylesheet" href="${path.contextPath}/css/index.css" type="text/css"/>
	<script type="text/javascript" src="${path.contextPath}/js/index.js"></script>
</@head>
<@body>
	<header class="sentence font shadow">
		<marquee onmouseover=this.stop() onmouseout=this.start() scrollAmount=3 scrollDelay=10 direction=left>
			<span id="content"></span>
		</marquee>
	</header>
	<div class="div_container">
		<div class="row">
			<div class="col-sm-4 col-xs-12">
				<img src="${path.contextPath}/images/logo.ico" style="margin-left:30px;"/><br/>
				<img src="${path.contextPath}/images/logo_text.png" style="margin-left:-15px;margin-bottom:20px;"/>
				<ul class="nav">
						<li><a href="${path.contextPath}/diary/list.do">心灵日记/DIARY</a></li>
						<li><a href="#">美文鉴赏/ARTICLE</a></li>
						<li><a href="${path.contextPath}/webSiteCollection/list.do">网站收藏/WEBSIT</a></li>
						<li><a href="${path.contextPath}/english/list.do">英语美文/ENGHLISH</a></li>
						<li><a href="#">个人简历/RESUME</a></li>
				</ul>
			</div>
			<div class="col-sm-8 col-xs-12" style="border:1px solid red;margin-top:15px;margin-left:-15px;">
				<iframe src="${path.contextPath}/photos/frontview.do"></iframe>
			</div>
		</div>
	</div>
	<div class="div_container">
		<div class="quote">
			Time goes by so fast, people go in and out of your life. You must never miss the opportunity to tell these people how much they mean to you.<br/>
			时间在流逝，生命中人来人往。不要错失机会，告诉他们在你生命中的意义。
		</div>
	</div>
	<div class="div_container">
		<div class="row t" style="width:100%;margin:2% auto;">
			<div class="col-md-4 col-sm-6 col-xs-12">
				<div class="huahua">
					<img src="${path.contextPath}/images/diary/diary-index.ico" class="chenlong" />
					<span class="xingguangdadao">心灵日记<br/>DIARY</span>
					<ul class="liboqing">
						<#if diarys??>
							<#list diarys.recordList as diary>
								<li><img src="${path.contextPath}/images/ul_icon.png"/>&emsp;&emsp;<a href="${path.contextPath}/diary/getone.do?id=${diary.id}">${diary.date}</a></li>
							</#list>
						</#if>
					</ul>
				</div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-12">
				<div class="huahua">
					<img src="${path.contextPath}/images/webcollection/web-index.ico"  class="chenlong"/>
					<span class="xingguangdadao">网站收藏<br/>WEBSITE</span>
					<ul class="liboqing">
						<#if websites??>
							<#list websites.recordList as website>
								<#if website.content?length gt 20>
									<#assign content=website.content?substring(0,20)/>
								<#else>
									<#assign content=website.content/>
								</#if>
								<li><img src="${path.contextPath}/images/ul_icon.png"/><a href="${website.webSiteUrl}" target="_blank">${content}</a></li>
							</#list>
						</#if>
					</ul>
				</div>
			</div>
			<div class="col-md-4 col-sm-12 col-xs-12">
				<div class="huahua">
					<img src="${path.contextPath}/images/english/english-index.ico" class="chenlong" />
					<span class="xingguangdadao">英语美文<br/>ENGLISH</span>
					<ul class="liboqing">
						<#if englishs??>
							<#list englishs.recordList as english>
								<#if english.title?length gt 25>
									<#assign content=english.title?substring(0,25)/>
								<#else>
									<#assign content=english.title/>
								</#if>
								<li><img src="${path.contextPath}/images/ul_icon.png"/><a href="${path.contextPath}/english/list.do?id=${english.id}" target="_blank">${content}</a></li>
							</#list>
						</#if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</@body>