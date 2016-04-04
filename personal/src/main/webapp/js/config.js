/**
 * Created by ahpeng on 2015/7/15.
 */
require.config({
    baseUrl: contextPath+"/js/lib",
    paths: {
        "jQueryUI": "jquery-ui",
        "MD5": "md5.min",
        "calendar": "calendar",
//        "angular": "angular.min",
        "bootstrap": "bootstrap.min",
        "publicJS": "public",
        "easyloader": "page/easyloader",
        "pageinate": "jPaginate/jquery.paginate",
        "artDialog":"artDialog/artDialog",
        "toolbar": "toolbar",
        "Tool": "Tool"
    },
    shim: {
//        angular:{
//            exports:"angular"
//        },
        calendar: {
            exports: "calendar"
        },
        jQueryUI: {
            exports: "jQueryUI"
        },
        artDialog:{
        	exports:"artDialog"
        }
    }
});
require(["artDialog"],function(){
	$.each($("script[data-main]").attr("data-src").split(","), function (index, model) {
	    require([model], function (m) {
	        if (m && m.init && typeof m.init === "function") {
	            m.init();
	        }
	    });
	});
});

