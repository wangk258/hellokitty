var contentEditor;
var MP3Uploader;
$(function(){
	$.ajaxSetup({
		async:false
	});
	initEvent();
});
function initEvent(){
	MP3Uploader=new UE.ui.Editor({fileUrl:contextPath+"/english/mp3Upload.do"});
	MP3Uploader.render("MP3Upload");
	MP3Uploader.ready(function(){
		MP3Uploader.hide();//editor.hide();
		MP3Uploader.addListener('afterUpfile', function (t, arg){
			var fileName=arg[0].original;
			$("#fileUrl").text(fileName);
			$("#orgName").val(fileName);
			$("#title").val(fileName.slice(0,fileName.lastIndexOf(".")));
			$("#uploadMP3").hide();
			$("#mp3Url").val(arg[0].url.slice(arg[0].url.indexOf("english_player")));
		});
	});
	contentEditor=new UE.ui.Editor({initialFrameWidth:740,initialFrameHeight:400});
	contentEditor.render("text");
	$("#saveBtn").click(function(){
		if(validateForm()){
			$.post(contextPath+"/english/saveOrUpdate.do",$("#form1").serialize(),function(result){
				alert(result.flag?"恭喜发财！":"出错啦！"+result.msg);
				window.parent.english.closeDialog("dialog_div");
				window.parent.location.reload();
			},"json");
		}
	});
	$("#uploadMP3").click(function(){
		MP3Uploader.getDialog("attachment").open();
	});
}
function validateForm(){
	if(($.trim($("#title").val()))==""){
		alert("哥，文章标题呢？");
		return false;
	}
	if(contentEditor.getContent()==""){
		alert("还是写一下原文吧，不然水平差的听到的都是天书！");
		return false;
	}
	return true;
}