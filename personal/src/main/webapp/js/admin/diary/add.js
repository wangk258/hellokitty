var contentEditor;
$(function(){
	$.ajaxSetup({
		async:false
	});
	initEvent();
});
function initEvent(){
	contentEditor=new UE.ui.Editor({initialFrameWidth:"100%",initialFrameHeight:400,imageUrl:contextPath+"/upload.do?type=diary"});
	contentEditor.render("content");
	$("#week").attr("readonly",true);
	$("#date").blur(function(){
		var day=new Date(this.value).getDay();
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
		$("#week").val(arr_week[day]);
	});
	$("#saveBtn").click(function(){
		if(validateForm()){
			$.post(contextPath+"/diary/saveOrUpdate.do",$("#form1").serialize(),function(result){
				alert(result.flag?"恭喜发财！":"出错啦！"+result.msg);
				window.parent.diary.closeDialog("dialog_div");
			},"json");
		}
	});
}

function validateForm(){
	if(($.trim($("#date").val()))==""){
		alert("你想写哪天的日记啊，好歹也选一下撒！");
		return false;
	}
	if(($.trim($("#week").val()))==""){
		alert("还记的那天是星期几不，不记得赶快去查一下，马上给我填上！");
		return false;
	}
	if(($.trim($("#weather").val()))==""){
		alert("是不是那天的天气影响你的心情啦，你都不想写它？");
		return false;
	}
	if(contentEditor.getContent()==""){
		alert("虽然我不知道你是有多不愿意写，但是你还是多少写一点撒！");
		return false;
	}
	else{
		$("#plainText").val(contentEditor.getContentTxt());
	}
	return true;
	
}