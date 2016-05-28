define(["angular","publicJS", "easyloader", "toolbar", "jQueryUI"],function(angular){
    var diary = {
        del:function(id,isSingle){
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
            if(!idArray.length){
                alert("请勾选要删除的日记！");
                return;
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
        },
        add:function(){
            this.edit("");
        },
        edit:function(id){
            showPopWindow("添加日记",900,650,contextPath+"/diary/single.ashx?id="+id+"&path=edit");
        }
    };
    angular.module('diaryListAdminApp', [])
        .controller("diaryListAdminController",function($scope){
            $scope.edit = diary.edit;
            $scope.del = diary.del;
        });
    angular.bootstrap(angular.element("#diaryListAdminApp"), ['diaryListAdminApp']);
	function bindEvent(){
		//$(".edit").click(function(){
		//	var id = $(this).attr("data-id");
		//	diary.edit(id);
		//});
		//$(".del").click(function(){
		//	var id = $(this).attr("data-id");
		//	diary.del(id,true);
		//});
		//$("#selectAll").click(function(){
		//	$("input:checkbox").attr("checked",this.checked);
		//});
		setDialogSize();
	}
	function initToolBar(){
		$(".toolbar div:first").toolBar({
            toolbar: [
                {
                    "element": "<img src='/images/admin/add.ico'/>",
                    "content": "添加",
                    "callback": function () {
                        diary.add();
                    }
                },
                {
                    "element": "<img src='/images/admin/delete.ico'/>",
                    "content": "删除",
                    "callback": function () {
                        diary.del(null, false);
                    }
                }
            ]
        });
	}
	function initPageBar(){
		window.using("pagination",function(){
			var option={
				total: pageObj.recordCount || 1,
				pageSize:pageObj.pageSize || 10,
                pageNumber:pageObj.pageNumber,
				showPageList:false,
                onSelectPage:function(pageNumber,pageSize){
                    window.location.href=contextPath+"/diary/list.ashx?path=admin&currentPage="+pageNumber+"&pageSize="+pageSize;
                }
			}
			$("#pageBar").pagination(option);
		});
	}
	return {
		init:function(){
			bindEvent();
			initToolBar();
			initPageBar();
		}
	}
});