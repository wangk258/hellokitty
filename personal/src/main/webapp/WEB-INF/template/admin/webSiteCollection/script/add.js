var contentEditor;
$(function(){
	$.ajaxSetup({
		async:false
	});
	initEvent();
	if(parent.typeId){
		initData(parent.typeId);
	}
});
function initData(id){
	$.post(top.basePath+"webSiteCollection/queryOne.do",{id:id},function(result){
		if(result){
			if(result.flag){
				$("#content").val(result.data.content);
				$("#webSiteUrl").val(result.data.webSiteUrl);
				$("#siteId").val(id);
			}else{
				alert(result.msg);
			}
		}else{
			alert("服务器响应超时！");
		}
	});
}
function initEvent(){
	$("#saveBtn").click(function(){
		if(validateForm()){
			$.post(top.basePath+"webSiteCollection/saveOrUpdate.do",$("#form1").serialize(),function(result){
				alert(result.flag?"恭喜发财了啦！":"出错啦！"+result.msg);
				window.parent.closeDialog("dialog_div");
				window.parent.initData();
			});
		}
	});
}
function validateForm(){
	if(($.trim($("#content").val()))==""){
		alert("请填写一下网站的标题！");
		return false;
	}
	var webSiteUrl=$("#webSiteUrl").val();
	if(($.trim(webSiteUrl))==""){
		alert("请输入一下网站的网址！");
		return false;
	}
	var strRegex = /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/;
	var re=new RegExp(strRegex);
	if(!re.test($.trim(webSiteUrl))){
		alert("url格式不正确！");
		return false;
	}
	return true;
	
}