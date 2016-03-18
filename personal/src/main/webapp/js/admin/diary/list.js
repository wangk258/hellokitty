var diary=(function(){
	var initPage=function(){
		var trs=$(".table_body tbody tr");
		trs.each(function(){
			$(this).hover(function(){
				$(this).css("cursor","pointer");
				$(this).css("background-color","#eee");
				$(this).siblings().css("background-color","");
			});
			$(this).find("td:eq(1)").click(function(){
				var id=$(this).parent().attr("id");
				view(id);
			});
			$(this).find("td:eq(2)").click(function(){
				var id=$(this).parent().attr("id");
				view(id);
			});
		});
		initEvent();
	};
	var initEvent=function(){
		if(parent.document.getElementById("dialog_frame")){
			parent.document.getElementById("dialog_frame").height=540;
		}
		$("#selectAll").click(function(){
			$("input:checkbox").attr("checked",this.checked);
		});
	};
	var view=function(id){
		openDialog(700,500,contextPath+"/diary/getone.do?id="+id+"&path=admin","dialog_div","dialog_frame");
	};
	var del=function(id,isSingle){
		var idArray=[];
		if(isSingle){
			idArray.push(id);
		}
		else{
			var boxs=$("table tr:not(:first)").find("input:checkbox:checked");
			for(var i=0;i<boxs.length;i++){
				idArray.push($(boxs[i]).attr("id").slice(4));
			}
		}
		if(window.confirm("你确定要做傻事吗？")){
			$.post(contextPath+"/diary/delete.do",{ids:idArray.join(",")},function(result){
				if(result){
					if(result.flag){
						alert("好吧！这条日记彻底从地球上消失了！");
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
	var closeDialog=function(id){
		$("#"+id).dialog("close");
		window.location.reload();
	};
	var addDiary=function(){
		openDialog(900,500,contextPath+"/diary/getone.do?id=&path=add","dialog_div","dialog_frame");
	};
	var edit=function(id){
		openDialog(900,500,contextPath+"/diary/getone.do?id="+id+"&path=edit","dialog_div","dialog_frame");
	};
	return {
		initPage:initPage,
		addDiary:addDiary,
		edit:edit,
		del:del,
		closeDialog:closeDialog
	};
})();
$(function(){
	diary.initPage();
});