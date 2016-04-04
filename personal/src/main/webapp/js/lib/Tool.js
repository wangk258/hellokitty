/**
 * Created with JetBrains WebStorm.
 * User: Administrator
 * Date: 12/17/15
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
define(function(){
    var Tool = {
        getParameter: function (name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        }
    }
    return  Tool;
});

