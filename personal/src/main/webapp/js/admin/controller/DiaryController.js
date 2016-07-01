/**
 * Created by ahpeng on 2016/6/29.
 */
define(["require","toolbar","easyloader"],function(require){
    var loadedpage = false;
    app.controller("DiaryListController",["$scope","DiaryService","$routeParams","$location",function($scope,diaryService,$routeParams,$location){
        diaryService.getlist($routeParams.pageIndex).then(function(data){
            if(data && data.flag){
                $scope.diarylist = data.data.recordList;
                if(loadedpage) return;
                loadedpage = true;
                window.using("pagination",function(){
                    var option={
                        total: data.recordCount || 1,
                        pageSize:data.pageSize || 15,
                        pageNumber:data.currentPage || 1,
                        showPageList:false,
                        onSelectPage:function(pageNumber,pageSize){
                            window.location.href = "#/diarylist/"+pageNumber;
                        }
                    }
                    angular.element("#pageBar").pagination(option);
                });
            }else{
                $scope.diarylist = [];
            }
        });
        initToolBar($scope);
    }]);
    app.controller("DiaryDetailController",["$scope","DiaryService","$routeParams",function($scope,DiaryService,$routeParams){
        require(["ueditor","ueditor-config","datetimepicker","datetimepicker-local","DateTimePickerDirective"],function(UE){
            var contentEditor = new UE.ui.Editor({
                initialFrameWidth : "100%",
                initialFrameHeight : angular.element(".content").height() * 0.75,
                imageUrl : baseUrl + "/upload.do?type=diary"
            });
            DiaryService.getDetailById($routeParams.id).then(function(data){
                if(data && data.flag){
                    $scope.diary = data.data;
                }else{
                    $scope.diary = {};
                }
                setTimeout(function(){
                    contentEditor.render("content");
                },0);
            });
        });
    }]);
    function initToolBar($scope){
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
                        $scope.del($scope.arr);
                    }
                }
            ]
        });
        function getBtnStr(type){
            return "<span class='glyphicon glyphicon-"+type+"'></span>";
        }
    }
});