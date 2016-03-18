var contentEditor;
$(function(){
	$.ajaxSetup({
		async:false
	});
	initEvent();
	pageClear();
	if(parent.typeId){
		initData(parent.typeId);
	}
});
function initData(id){
	$.post(top.basePath+"article/queryOne.do",{id:id},function(result){
		if(result){
			if(result.flag){
				$("#title").val(result.data.title);
				$("#author").val(result.data.author);
				$("#fromAddress").val(result.data.fromAddress);
				contentEditor.ready(function(){
					contentEditor.setContent(result.data.content);
				});
				$("#diaryId").val(id);
			}else{
				alert(result.msg);
			}
		}else{
			alert("服务器响应超时！");
		}
	});
}
function initEvent(){
	contentEditor=new UE.ui.Editor({initialFrameWidth:770,initialFrameHeight:400});
	contentEditor.render("content");
	$("#saveBtn").click(function(){
		if(validateForm()){
			$.post(top.basePath+"article/saveOrUpdate.do",$("#form1").serialize(),function(result){
				alert(result.flag?"算你运气好，文章已经保存了！":"出错啦！"+result.msg);
				window.parent.closeDialog("dialog_div");
				window.parent.initData();
			});
		}
	});
}

function validateForm(){
	if(($.trim($("#title").val()))==""){
		alert("哥，你写文章都没有标题的吗？");
		return false;
	}
	if(($.trim($("#author").val()))==""){
		alert("我敢肯定这篇文章不是你写的，还是写一下原作者吧！");
		return false;
	}
	if(($.trim($("#fromAddress").val()))==""){
		alert("还是写一下出处，不然到时候构成侵权了，谁都救不了你！");
		return false;
	}
	if(contentEditor.getContent()==""){
		alert("还是要写一下内容撒，不然别人怎么欣赏啊！");
		return false;
	}
	return true;
	
}