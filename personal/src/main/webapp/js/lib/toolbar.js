/**
 * Created by ahpeng on 2015/8/28.
 */
!function(T){
    if(define && define.amd && typeof define === "function"){
        define(T);
    }else{
        T(jQuery);
    }
}(!function ($) {
    $.fn.toolBar = function (options) {
        if (this[0].tagName.toLowerCase() !== "div") {
            this[0].innerHTML = "element error!";
            return;
        }
        if ($.isEmptyObject(options) || $.isEmptyObject(options.toolbar)) {
            this[0].innerHTML = "configure cannot be empty !";
            return;
        }
        if (typeof options !== "object") {
            this[0].innerHTML = "configure must be a object or array !";
            return;
        }
        if (options.hoverStyle && !options.normalStyle) {
            this[0].innerHTML = "if you configure the hoverStyle,please configure the normalStyle";
            return;
        }
        var c = null, ul = null;
        if (options.hoverStyle || options.normalStyle) {
            c = options;
        }
        options = options.toolbar;
        if ($.type(options) === "object") {
            var _str = "<ul class='toolbar_ul'><li>" + options.element + "<span>" + options.content + "</span></li>";
            _str += "</ul>";
            $(this).html(_str);
            $(this).delegate("li", "click", options.callback);
        }
        else {
            ul = $("<ul class='toolbar_ul'></ul>").appendTo(this);
            for (var i = 0, j = options.length; i < j; i++) {
                var _str = "<li>" + options[i].element + "<span>" + options[i].content + "</span></li>";
                ul.append(_str);
                ul.find("li:last").unbind().bind("click", options[i].callback);
            }
        }
        if (c) {
            ul.find("li").css(c.normalStyle);
            if (c.hoverStyle) {
                ul.find("li").hover(function () {
                    $(this).css(c.hoverStyle);
                }, function () {
                    $(this).css(c.normalStyle);
                });
            }
        }
    }
    return $.fn.toolBar;
}(jQuery));
