/**
 * Created by ahpeng on 2015/7/15.
 */
require.config({
    baseUrl: contextPath + "/js/lib",
    paths: {
        "jquery": "jquery-1.8.0.min",
        "jQueryUI": "jquery-ui",
        "MD5": "md5.min",
        "calendar": "calendar",
        "angular": "angular.min",
        "bootstrap": "bootstrap.min",
        "publicJS": "public",
        "easyloader": "page/easyloader",
        "pageinate": "jPaginate/jquery.paginate",
        "artDialog": "artDialog/artDialog",
        "iframeTools": "artDialog/plugins/iframeTools",
        "toolbar": "toolbar",
        "Tool": "Tool"
    },
    shim: {
        angular: {
            exports: "angular"
        },
        calendar: {
            exports: "calendar"
        },
        artDialog: {
            deps: ["jquery"],
            exports: "artDialog"
        },
        iframeTools: {
            deps: ["artDialog"]
        }
    }
});
require(["artDialog"], function () {
    require(["iframeTools"], function () {
        $.each($("script[data-main]").attr("data-src").split(","), function (index, model) {
            require([model], function (m) {
                if (m && m.init && typeof m.init === "function") {
                    m.init();
                }
            });
        });
    });
});