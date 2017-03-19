/**
 * Created by ahpeng on 2015/7/15.
 */
    require.config({
        paths:{
            "jquery": "lib/jquery-1.8.0.min",
            "underscore" : "lib/underscore-1.8.3.min",
            "MD5": "lib/md5.min",
            "calendar": "lib/calendar",
            "angular": "lib/angular.min",
            "angularRouter": "lib/angular-route.min",
            "bootstrap": "lib/bootstrap/js/bootstrap.min",
            "datetimepicker": "lib/bootstrap/js/bootstrap-datetimepicker",
            "datetimepicker-local": "lib/bootstrap/js/bootstrap-datetimepicker.zh-CN",
            "page":"lib/bootstrap/js/jquery.bootstrap.pagination.min",
            "artDialog": "lib/artDialog/artDialog",
            "iframeTools": "lib/artDialog/plugins/iframeTools",
            "toolbar": "lib/toolbar/jquery.toolbar",
            "ueditor": "lib/ueditor/ueditor.all",
            "ueditor-config": "lib/ueditor/ueditor.config",
            "accordion":"lib/accordion/jquery.accordion",
            "Utils": "lib/Utils",


            "DiaryController":"admin/controller/DiaryController",

            "DiaryService":"admin/service/DiaryService",

            "DateTimePickerDirective": "admin/directive/DateTimePickerDirective"
        },
        shim: {
            angular: {
                exports: "angular"
            },
            angularRouter: {
                exports: "angularRouter"
            },
            bootstrap: {
                deps: ["jquery"]
            },
            calendar: {
                exports: "calendar"
            },
            artDialog: {
                deps: ["jquery"],
                exports: "artDialog"
            },
            slider: {
                deps: ["jquery"],
                exports: "Slider"
            },
            ueditor: {
                exports: "UE"
            },
            "datetimepicker-local": {
                deps: ["datetimepicker"]
            },
            page:{
                deps:["jquery"],
                exports:"page"
            }
        },
    });
    require(["jquery","angular"], function () {
        require(["angularRouter"],function(){
            app = angular.module("personalApp", ["ngRoute"]);
            require(["admin/app"],function(){});
        });
    });
