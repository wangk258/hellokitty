function openDialog(width,height,url,divId,frameId){
	$("#"+divId).dialog({
		width:width,
		height:height,
		modal:true,
		open:function(){
			$("#"+frameId).attr("src",url);
		},
		close:function(){
			$("#"+frameId).attr("src","");
		}
	});
}
function pageClear(){
	$("input[type=text]").val("");
	$("input:hidden").val("");
	$("textarea").val("");
	$("input:checkbox,input:radio").attr("checked",false);
}
function getDateByNumber(n) {
	n = parseInt(n, 10);
	// 先解析是不是可以转换成数字类型，如果不能转换返回的是NaN或者直接报错，然后判断是否为0
	if (isNaN(n) || isZero(n)) {
		return "";
	}
	var dt = new Date(n + 3600000);
	var str = dt.getFullYear() + "-";
	var m = dt.getMonth() + 1;
	str += (m > 9 ? m : ("0" + m)) + "-";
	str += (dt.getDate() > 9 ? dt.getDate() : ("0" + dt.getDate()));
	return str;
}
//排除等于0这种情况
function isZero(n) {
	if (isNumber(n)) {
		if (n === 0) {
			return true;
		}
	}
	return false;
}
//判断是不是数字
function isNumber(n) {
	if (typeof (n) !== "number") {
		return false;
	} else {
		return true;
	}
}
function success_callback(result,dialogId,isRemind){
	if(result){
		if(result.flag){
			if(isRemind=="yes"){
				alert("操作成功！");
			}
			if(dialogId&&$("#"+dialogId)){
				$("#"+dialogId).dialog("close");
			}
		}
		else{
			alert("操作失败！"+result.msg);
		}
	}
	else{
		alert("服务器响应超时！");
	}
}
//获取url当中的参数 example:?a=1&b=2&c=3;
function getParameter(paramName){
	var s=window.location.search;
	s=s.slice(s.indexOf('?')+1);
	var index=s.indexOf(paramName);
	if(index<0){
		return null;
	}
	var params=s.split('&');
	for(var i=0,j=params.length;i<j;i++){
		if(params[i].slice(0,params[i].indexOf('=')+1)===paramName+'='){
			return params[i].slice(params[i].indexOf('=')+1);
		}
	}
}