var typeId;
$(function(){
	if(parent.document.getElementById("dialog_frame")){
		parent.document.getElementById("dialog_frame").height=540;
	}
	initData();
	initEvent();
});
function initEvent(){
	$("#selectAll").click(function(){
		$("input:checkbox").attr("checked",this.checked);
	});
}
function initData(){
	var formdata={
		key:$.trim($("#key").val()),
		createTime:$.trim($("#createTime").val())
	};
	$.post(top.basePath+"article/list.do",formdata,function(result){
		if(result){
			if(result.flag){
				var objs=result.data.recordList;
				$(".table_body tbody tr").remove();
				for(var i=0;i<objs.length;i++){
					var str='<tr id="tr_'+objs[i].id+'">'+
					'<td><input type="checkbox" id="box_'+objs[i].id+'"/></td>'+
					'<td>'+objs[i].title+'</td>'+
					'<td>'+objs[i].author+'</td>'+
					'<td>'+
					'<a href="javascript:void(0);" onclick="edit('+objs[i].id+')"><img src="../../admin/images/icons/modify.ico" width="15px" height="15px"/></a>&emsp;'+
					'<a href="javascript:void(0);" onclick="del('+objs[i].id+',true)"><img src="../../admin/images/icons/delete.ico" width="15px" height="15px"/></a>'+
					'</td>'+
					'</tr>';
					$(".table_body tbody").append(str);
					$("#box_"+objs[i].id).click(function(){
						$(this).attr("checked",this.checked);
					});
					$("#tr_"+objs[i].id+" td:eq(1),#tr_"+objs[i].id+" td:eq(2)").hover(function(){
						$(this).css("cursor","pointer");
					},function(){
						$(this).css("cursor","default"); 
					});
					$("#tr_"+objs[i].id+" td:eq(1),#tr_"+objs[i].id+" td:eq(2)").click(function(){
						typeId=$(this).parent().attr("id").slice(3);
						openDialog(700,500,"../../admin/article/view.html","dialog_div","dialog_frame");
					});
				}
				window.using("pagination",function(){
					$("#pageBar").pagination({total:result.data.recordCount,pageSize:10,showPageList:false});
				});
				$(".table_body tbody tr").hover(function(){
					$(this).css("background-color","#eee");
				},function(){
					$(this).css("background-color",""); 
				});
			}
			else{
				alert(result.msg);
			}
		}
		else{
			alert("服务器响应超时！");
		}
	});
}
function edit(id){
	typeId=id;
	openDialog(900,500,"../../admin/article/add.html","dialog_div","dialog_frame");
}
function del(id,isSingle){
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
		$.post(top.basePath+"article/delete.do",{ids:idArray.join(",")},function(result){
			if(result){
				if(result.flag){
					alert("好吧！你成功做了一件傻事了！");
					initData();
				}
				else{
					alert(result.msg);
				}
			}
			else{
				alert("服务器响应超时！");
			}
		});
	}
}
function closeDialog(id){
	$("#"+id).dialog("close");
}
function addDiary(){
	typeId=null;
	openDialog(900,500,"../../admin/article/add.html","dialog_div","dialog_frame");
}