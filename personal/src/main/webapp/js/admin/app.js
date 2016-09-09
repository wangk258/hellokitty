/**
 * Created by ahpeng on 2016/6/29.
 */
define([
    "require",
    "underscore",
    "bootstrap",
    "DiaryController",
    "DiaryService",
    "accordion"],function(require){
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
        $(".p-accordion").accordion({
            items:[
                {
                    text:"个人随笔",
                    sub:[
                        {
                            text:"心灵日记",
                            href:"#/diarylist/1",
                            icon:"/images/admin/diary.png"
                        }
                    ]
                }
            ],
            subItemTmpl:'<li class="w-accordion_sub_item">\
                            <a href="${href}">\
                                <div class="div_img">\
                                    <img src="${icon}">\
                                </div>\
                                <div class="div_title"><span>${text}</span></div>\
                            </a>\
                        </li>'
        });
    }]);
    angular.bootstrap(angular.element("#personalApp"), ['personalApp']);
});