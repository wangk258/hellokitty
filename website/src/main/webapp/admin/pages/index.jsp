<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/admin/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小旋风后台管理</title>
<%@ include file="/admin/meta.jsp"%>
<script type="text/javascript">
	$(function() {
		initMenu();
		$("#tabs").height(500);
		//默认加载
		$("#initOpenA").click();
		$("#tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});
		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				//closeTab(item.type);
				//console.info(item);
				closeTab(item.id);
			}
		});
		
		//设置div高度
	});
	function initMenu(){
		var data = AjaxUnAsync("navigation/list.ajax?page=-1&rows=1000");
		
		var accordionHtml = "<div  class='easyui-accordion' style='height: 99%' id='accordion'>";
		accordionHtml+= $("#initMenu").html();
		$.each(data.rows,function(){
			if(this.name !="首页")
			accordionHtml+="<div title='"+this.name+"' style='height: 100%'><ul id='"+this.id+"' page='"+this.page+"'></ul></div>";
		});
		$("#menuAccordion").append(accordionHtml);
		accordionHtml+="</div>";
		
		//二级菜单
		data = AjaxUnAsync("editor/list.ajax?page=-1&rows=1000");
		$.each(data.rows,function(){
			var url = this.url;
			if(url.indexOf("?")==-1){
				url+="?pageFlag="+$("#"+this.pkey).attr("page");
			}else{
				url+="&pageFlag="+$("#"+this.pkey).attr("page");
			}
			var editorHtml = "<li><a href='javascript:void(0)' onclick='addTab(this)' url='"+url+"' fun='"+this.id+"' >"+this.ckey+"</a></li>";
			$("#"+this.pkey).append(editorHtml);
		});
		
		//除去菜单管理
		if(!SUPERFLAG){
			$("#menuManage").remove();
		}
		 $.parser.parse("#menuAccordion");
	}
	function addTab(a) {

		if ($('#tabs').tabs('exists', $(a).text())) {

			$("#" + $(a).attr("fun")).attr("src", $(a).attr("url"));
			$('#tabs').tabs('select', $(a).text());

		} else {
			var content = '<iframe scrolling="auto" frameborder="0" id="'
					+ $(a).attr("fun") + '" src="' + $(a).attr("url")
					+ '" style="width:100%;height:517px;"></iframe>';
			$('#tabs').tabs('add', {
				title : $(a).text(),
				content : content,
				closable : true
			});

		}
	}
	function closeTab(action) {

		var alltabs = $('#tabs').tabs('tabs');
		var currentTab = $('#tabs').tabs('getSelected');
		var allTabtitle = [];
		$.each(alltabs, function(i, n) {
			allTabtitle.push($(n).panel('options').title);
		});

		switch (action) {
		case "refresh":
			var $iframe = $(currentTab.panel('options').content);
			$("#" + $iframe.attr("id")).attr("src", $iframe.attr("src"));
			break;
		case "close":
			var currtab_title = currentTab.panel('options').title;
			$('#tabs').tabs('close', currtab_title);
			break;
		case "closeall":
			$.each(allTabtitle, function(i, n) {
				$('#tabs').tabs('close', n);
			});
			break;
		case "closeother":
			var currtab_title = currentTab.panel('options').title;
			$.each(allTabtitle, function(i, n) {
				if (n != currtab_title) {
					$('#tabs').tabs('close', n);
				}
			});
			break;
		case "closeright":
			var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);

			if (tabIndex == alltabs.length - 1) {
				alert('亲，后边没有啦 ^@^!!');
				return false;
			}
			$.each(allTabtitle, function(i, n) {
				if (i > tabIndex) {
					$('#tabs').tabs('close', n);
				}
			});

			break;
		case "closeleft":
			var tabIndex = $('#tabs').tabs('getTabIndex', currentTab);
			if (tabIndex == 0) {
				alert('亲，前边那个上头有人，咱惹不起哦。 ^@^!!');
				return false;
			}
			$.each(allTabtitle, function(i, n) {
				if (i < tabIndex) {
					$('#tabs').tabs('close', n);
				}
			});

			break;
		case "exit":
			$('#closeMenu').menu('hide');
			break;
		}
	}
	function loginOut() {
		window.top.location.href = "/user/loginOut.ajax";
	}
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 82px">
		<div style="float: left; width: 45%; height: 80px;">
			<img src="/images/front/logo.png" width="205" height="80"
				style="display: block; float: left" /> <span
				style="display: block; height: 80px; line-height: 80px; float: left; font-size: 30px;">欢迎使用网站后台管理系统</span>
		</div>
		<div style="width: 30%; float: left; height: 80px">
			<iframe allowtransparency="true" frameborder="0"
				style="width: 385; height: 80px" scrolling="no"
				src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=3&t=0&v=0&d=3&bd=0&k=&f=000000&q=1&e=1&a=1&c=56294&w=385&h=96&align=center"></iframe>
		</div>
		<div
			style="float: right; width: 24%; height: 20px; text-align: right; line-height: 40px;">
			<a href="/user/loginOut.ajax"
				style="margin: 40px 10px 0 30px; display: block; float: right; font-size: 20px;">退出</a>
			<span
				style="margin-top: 40px; display: block; float: right; font-size: 20px;">当前用户：${sessionScope.SESSION_LOGIN_KEY.username}</span>
		</div>
	</div>
	<div data-options="region:'west'" style="width: 150px; height: 100%;">
		<div id="menuAccordion"></div>
	</div>

	<div style="display: none" id="initMenu">
		<div title="导航管理" style="height: 100%">
			<ul>
				<li><a href="javascript:void(0)" onclick="addTab(this)"
					url='${ctx}/admin/pages/navigation/navigationlist.jsp'
					id="initOpenA" fun="navigationlist">导航维护</a>
				</li>
			</ul>
		</div>
		<!-- 	<c:if test="${sessionScope.SESSION_LOGIN_KEY.username eq applicationScope.currentUser} "></c:if> -->

		<div title="菜单管理" style="height: 100%" id="menuManage">
			<ul>
				<li><a href="javascript:void(0)" onclick="addTab(this)"
					url="${ctx}/admin/pages/menu/menulist.jsp" fun="millCycloneHome">菜单维护</a>
				</li>
			</ul>
		</div>

	</div>
	<div data-options="region:'center'" id="rightContentDiv">
		<div id="tabs" class="easyui-tabs" style="width: 100%;"></div>
	</div>
	</div>
	<div id="tabsMenu" class="easyui-menu"
		style="width: 150px; display: none">
		<div id="refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
</body>
</html>