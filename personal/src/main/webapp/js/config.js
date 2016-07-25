/**
 * Created by ahpeng on 2015/7/15.
 */
(function () {
    var libUrl = "lib/",
        controllerUrl = "admin/controller/",
        serviceUrl = "admin/service/",
        directiveUrl = "admin/directive/";
    require.config({
        paths: {
            /*load libs*/
            "jquery": libUrl + "jquery-1.8.0.min",
            "underscore" : libUrl + "underscore-1.8.3.min",
            "jQueryUI": libUrl + "jquery-ui",
            "MD5": libUrl + "md5.min",
            "calendar": libUrl + "calendar",
            "angular": libUrl + "angular.min",
            "angularRouter": libUrl + "angular-route.min",
            "bootstrap": libUrl + "bootstrap/js/bootstrap.min",
            "datetimepicker": libUrl + "bootstrap/js/bootstrap-datetimepicker",
            "datetimepicker-local": libUrl + "bootstrap/js/bootstrap-datetimepicker.zh-CN",
            "easyloader": libUrl + "page/easyloader",
            "pageinate": libUrl + "jPaginate/jquery.paginate",
            "artDialog": libUrl + "artDialog/artDialog",
            "iframeTools": libUrl + "artDialog/plugins/iframeTools",
            "slider": libUrl + "slider",
            "toolbar": libUrl + "toolbar",
            "ueditor": libUrl + "ueditor/ueditor.all",
            "ueditor-config": libUrl + "ueditor/ueditor.config",
            "Utils": libUrl + "Utils",


            /*load controller*/
            "DiaryController": controllerUrl + "DiaryController",

            /*load service*/
            "DiaryService": serviceUrl + "DiaryService",

            /*load directive*/
            "DateTimePickerDirective": directiveUrl + "DateTimePickerDirective"
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
            }
        }
    });
    
    require(["jquery","underscore"], function () {
        require(["angular","artDialog"], function (angular) {
            require(["angularRouter"], function () {
                app = angular.module("personalApp", ["ngRoute"]);
                require(["admin/app"], function () {});
            });
        });
    });

    //require(["jquery"], function () {
    //    $.each($("script[data-main]").attr("data-src").split(","), function (index, model) {
    //        require([model], function (m) {
    //            if (m && m.init && typeof m.init === "function") {
    //                m.init();
    //            }
    //        });
    //    });
    //});
})();
