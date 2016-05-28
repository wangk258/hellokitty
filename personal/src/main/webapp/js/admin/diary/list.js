define(["angular","Utils", "easyloader", "toolbar", "jQueryUI"],function(angular,utils){
    var diary = {
        del:function(ids){
            if(!ids || !ids.length){
            	utils.showAlert("请选择要删除的日记！");
            	return;
            }
            if(Object.prototype.toString.call(ids) !== "[object Array]"){
            	ids = [ids];
            }
            if(window.confirm("你确定要做傻事吗？")){
                $.post(contextPath+"/diary/delete.do",{ids:ids.join(",")},function(result){
                    if(result){
                        if(result.flag){
                            utils.showAlert("好吧！这条日记彻底从地球上消失了！");
                            window.location.reload();
                        }
                        else{
                            utils.showAlert(result.msg);
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
            showPopWindow("添加日记",900,550,contextPath+"/diary/single.ashx?id="+id+"&path=edit");
        }
    };
    angular.module('diaryListAdminApp', [])
        .controller("diaryListAdminController",function($scope){
			initToolBar();
			initPageBar();
            $scope.edit = diary.edit;
            $scope.del = diary.del;
            $scope.arr= [];
            $scope.selectItem = function(){
            	if(this.checked){
            		$scope.arr.push(this.id);
            	}
            }
        });
    angular.bootstrap(angular.element("#diaryListAdminApp"), ['diaryListAdminApp']);
	function initToolBar(){
		$(".toolbar div:first").toolBar({
            toolbar: [
                {
                    "element": getBtnStr("plus"),
                    "content":"添加",
                    "callback": function () {
                        diary.add();
                    }
                },
                {
                    "element": getBtnStr("remove"),
                    "content":"删除",
                    "callback": function () {
                        diary.del();
                    }
                }
            ]
        });
		function getBtnStr(type){
			return "<span class='glyphicon glyphicon-"+type+"'></span>";
		}
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
		}
	}
});