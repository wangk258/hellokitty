/**
 * Created by ahpeng on 2016/6/29.
 */
define(["require","Utils","toolbar", "easyloader", "DateTimePickerDirective"], function (require,utils) {
    var loadedpage = false,
        initToolBar = function($scope){
            $(".toolbar div:first").toolBar({
                toolbar: [
                    {
                        "element": getBtnStr("plus"),
                        "content": "添加",
                        "callback": function () {
                            window.location.href = "#/diarydetail/0";
                        }
                    },
                    {
                        "element": getBtnStr("remove"),
                        "content": "删除",
                        "callback": function () {
                            console.log($scope.checkedArr);
                            //$scope.delete($scope.checkedArr);
                        }
                    }
                ]
            });
            function getBtnStr(type) {
                return "<span class='glyphicon glyphicon-" + type + "'></span>";
            }
        };

    app.controller("DiaryListController", ["$scope", "DiaryService", "$routeParams", "$location", function ($scope, diaryService, $routeParams, $location) {
        diaryService.getlist($routeParams.pageIndex).then(function (data) {
            if (data && data.flag) {
                data = data.data;
                $scope.diarylist = data.recordList;
                if (loadedpage) return;
                loadedpage = true;
                window.using("pagination", function () {
                    var option = {
                        total: data.recordCount || 1,
                        pageSize: data.pageSize || 15,
                        pageNumber: data.currentPage || 1,
                        showPageList: false,
                        onSelectPage: function (pageNumber, pageSize) {
                            window.location.href = "#/diarylist/" + pageNumber;
                        }
                    }
                    angular.element("#pageBar").pagination(option);
                });
            } else {
                $scope.diarylist = [];
            }
        });
        $scope.selectItem = function(id,checked){
            console.log("selectItem");
            if(checked){
                if($scope.checkedArr){
                    $scope.checkedArr.push(id);
                }else{
                    $scope.checkedArr = [id];
                }
            }else{
                $scope.checkedArr.splice(_.indexOf($scope.checkedArr,id),1);
            }
        }
        $scope.selectAll = function(e){
            $scope.checked = e.target.checked;
        }
        $scope.delete = function(ids){
            if(ids && ids.length){
                diaryService.delete(ids).then(function(data){
                    if(data){
                        if(data.flag){
                            window.location.href="#/diarylist/1#";
                        }else{
                            utils.showAlert(data.msg);
                        }
                    }else{
                        utils.showAlert("网络错误！");
                    }
                });
            }
        }
        initToolBar($scope);
    }]);

    app.controller("DiaryDetailController", ["$scope", "DiaryService", "$routeParams","$timeout", function ($scope, DiaryService, $routeParams,$timeout) {
        require(["ueditor","Utils","ueditor-config"], function (UE,utils) {
            var contentEditor = new UE.ui.Editor({
                initialFrameWidth: "100%",
                initialFrameHeight: window.screen.availHeight * 0.60,
                imageUrl: baseUrl + "/upload.do?type=diary"
            });
            DiaryService.getDetailById($routeParams.id).then(function (data) {
                if (data && data.flag) {
                    $scope.diary = data.data;
                } else {
                    $scope.diary = {};
                }
                setTimeout(function () {
                    contentEditor.render("content");
                    contentEditor.ready(function(){
                        contentEditor.addListener("contentChange",function(){
                            $scope.diary.content = contentEditor.getContent();
                        });
                    });
                }, 0);
            });
            $scope.submit = function(invalid){
                if(invalid){
                    $scope.submitted = true;
                    return;
                }
                $scope.diary.plainText =contentEditor.getPlainTxt();
                DiaryService.save($scope.diary).then(function(data){
                    if(data){
                        if(data.flag){
                            window.location.href="#/diarylist/1";
                        }else{
                            utils.showAlert(data.msg);
                        }
                    }else{
                        utils.showAlert("服务器无响应!");
                    }
                });
            }
        });
    }]);
});