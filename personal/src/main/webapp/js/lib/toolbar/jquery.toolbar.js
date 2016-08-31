/**
 * Created by ahpeng on 2015/8/28.
 */
!function(T){
    if(define && define.amd && typeof define === "function"){
        define(["jquery"],T);
    }else{
        T(jQuery);
    }
}(function ($) {
    function ToolBar(options){
        var default_options = {
            toolbar:[],
            hoverStyle:""
        };
        this.options = $.extend({},default_options,options);
        this.render();
    }
    function processTemplate(tmpl,data){
        return tmpl.replace(/\${(\w+)}/g,function(match,prop) {
            return data[prop];
        });
    }
    $.extend(ToolBar.prototype,{
       render:function(){
           var container = $("<ul class='w-toolbar_wrap'></ul>").appendTo(this.options.dom),
               itemTmpl = "<li class='w-toolbar_item'>${element}<span>&nbsp;&nbsp;${content}</span></li>";
           for(var i=0;i<this.options.toolbar.length;i++){
               var item = this.options.toolbar[i];
               $(processTemplate(itemTmpl,item)).appendTo(container).unbind().bind("click",item.callback);
           }
       }
    });
    $.fn.toolBar = function (options) {
        this.each(function(){
            options.dom = $(this);
            new ToolBar(options);
        });
    }
    return $.fn.toolBar;
});
