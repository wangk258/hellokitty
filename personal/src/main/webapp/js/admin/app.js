/**
 * Created by ahpeng on 2016/6/29.
 */
define(["bootstrap","DiaryController","DiaryService"],function(){
    app.config(function($routeProvider){
        $routeProvider.
            when('/diarylist/:pageIndex',{
                controller:"DiaryListController",
                templateUrl:baseUrl+"/js/admin/view/diarylist.html"
            }).
            when("/diarydetail/:id",{
                controller:"DiaryDetailController",
                templateUrl:baseUrl+"/js/admin/view/diarydetail.html"
            });
    });
    app.controller("personalController", ["$scope","DiaryService",function ($scope,service) {
        $scope.funcs = ["个人随笔", "爱好学习", "用户管理"];
        $scope.count=[];
        $scope.count.length = 1;
    }]);
    angular.bootstrap(angular.element("#personalApp"), ['personalApp']);
});