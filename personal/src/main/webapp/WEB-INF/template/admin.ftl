<#include "common/main.ftl">
<@head title="安安小屋后台管理" isAdmin=false>
<style type="text/css">
    html, body {
        overflow: hidden;
        background: url("${path.contextPath}/images/admin/bg.png") repeat scroll 0 0;
    }

    ul {
        float: left;
    }

    ul li {
        float: left;
        list-style-type: none;
        height:150px;
    }

    ul li:hover {
        background-image: url('${path.contextPath}/images/opacity.png');
        border-radius: 10px;
        cursor: pointer;
    }

    .div_title {
        background-image: url('${path.contextPath}/images/opacity.png');
        width: 80px;
        text-align: center;
        vertical-align: middle;
        margin: 10px 31px;
        border-radius: 10px;
        color: white;
        font-weight: bolder;
        line-height:25px;
    }

    .div_img {
        margin: 20px 40px;
    }

    img {
        width: 64px;
        height: 64px;
        border: 0;
    }
    .controlmenu{
        position:absolute;
        bottom:0;
        left:0;
        background: url("${path.contextPath}/images/admin/controlmenu.gif") repeat-x;
        height:30px;
        width:100%;
        cursor:pointer;
    }
    .controlmenu .b-left{
        background: url('${path.contextPath}/images/admin/startbutton.gif')  0 0 no-repeat;
        width:10px;
        height:30px;
        margin-left:10px;
        float:left;
    }
    .controlmenu .b-center{
        background: url('${path.contextPath}/images/admin/startbutton.gif')  0 -60px repeat-x;
        width:70px;
        height:30px;
        float:left;
    }
    .controlmenu .b-center .b-icon{
        background: url('${path.contextPath}/images/admin/startbutton-icon.gif')  0 0 no-repeat;
        width:23px;
        height:23px;
        margin-top:5px;
        float:left;
    }
    .controlmenu .b-center .b-text{
        color:#333333;
        font-weight: bolder;
        float:left;
        font-size: 20px;
        height:30px;
        line-height: 30px;
    }
    .controlmenu .b-right{
        background: url('${path.contextPath}/images/admin/startbutton.gif')  0px -30px no-repeat;
        width:10px;
        height:30px;
        float:left;
    }
</style>
</@head>
<@body>
	<div style="width:1000px;margin:0 auto;">
	    <ul>
	        <li onclick="utils.showPopWindow('日记列表',1000,500,'${path.contextPath}/diary/list.do?path=admin')">
	            <div class="div_img">
	                <img src="${path.contextPath}/images/admin/diary.png"/>
	            </div>
	            <div class="div_title"><span>心灵日记</span></div>
	        </li>
	    </ul>
	</div>
	<div class="controlmenu">
	    <div class="b-left"></div>
	    <div class="b-center">
	        <div class="b-icon"></div>
	        <div class="b-text">退出</div>
	    </div>
	    <div class="b-right"></div>
	</div>
	<div id="dialog_div" style="display:none;overflow: auto;overflow-x:hidden;">
		<iframe id="dialog_frame" src="" width="100%" frameborder="0" scrolling="no"
		 style="left:0px;right: 0px;bottom: 0px;"></iframe>
	</div>
	<script type="text/javascript" src="${path.contextPath}/js/lib/require.min.js" data-main="${path.contextPath}/js/config.js" data-src="${path.contextPath}/js/admin/index/index.js"></script>
</@body>
