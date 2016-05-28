
/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 12/17/15
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
define(function(){
    var Utils = {
        getParameter: function (name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        },
        getQueryString:function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]);
            return null;
        },
        showAlert:function (content,ok){
        	art.dialog({
        		title:"提示",
        		icon:"warning",
        		content:"<span style='color:gray;font-size:15px'>"+content+"</span>",
        		ok:function(){
        			if(ok && typeof ok === "function"){
                        ok();
                    }
        		}
        	}).lock();
        },
        showPopWindow:function (title,width, height, url){
            art.dialog.open(url,{
                title:title,
                width:width,
                height:height
            }).lock();
        }

    }
    return  Utils;
});

