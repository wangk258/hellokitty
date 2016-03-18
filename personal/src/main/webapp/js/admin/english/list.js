var typeId;
$(function(){
	english.initEvent();
});
var english=(function(){
	initEvent=function(){
		$("#selectAll").click(function(){
			$("input:checkbox").attr("checked",this.checked);
		});
		if(parent.document.getElementById("dialog_frame")){
			parent.document.getElementById("dialog_frame").height=540;
		}
	};
	edit=function(id){
		openDialog(900,500,contextPath+"/english/getOne.do?id="+id,"dialog_div","dialog_frame");
	};
	del=function(id,isSingle){
		var idArray=[];
		if(isSingle){
			idArray.push(id);
		}
		else{
			var boxs=$(".datacontainer table tbody tr").find("input:checkbox:checked");
			for(var i=0;i<boxs.length;i++){
				idArray.push($(boxs[i]).attr("id"));
			}
		}
		if(window.confirm("你确定要做傻事吗？")){
			$.post(contextPath+"/english/delete.do",{ids:idArray.join(",")},function(result){
				if(result){
					if(result.flag){
						alert("好吧！你成功做了一件傻事了！");
						window.location.reload();
					}
					else{
						alert(result.msg);
					}
				}
				else{
					alert("服务器响应超时！");
				}
			},"json");
		}
	};
	closeDialog=function(id){
		$("#"+id).dialog("close");
	};
	addEnglishArticle=function(){
		openDialog(900,500,contextPath+"/english/getOne.do","dialog_div","dialog_frame");
	};
	return {
		initEvent:initEvent,
		del:del,
		edit:edit,
		closeDialog:closeDialog,
		addEnglishArticle:addEnglishArticle
	};
})();